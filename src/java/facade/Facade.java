/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Address;
import entities.City;
import entities.Company;
import entities.Hobby;
import entities.InfoGeneral;
import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author ichti
 */
public class Facade implements FacadeInterface {
    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory e) {
      emf = e;
    }

    public EntityManager getEntityManager() {
      return emf.createEntityManager();
    }
    /**
     *
     * @return
     */
    @Override
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Person.findAll").getResultList();
        } 
        finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        try {
            return (Person)em.createNamedQuery("Person.findByInfoId").setParameter("infoId", id).getSingleResult();
        } 
        finally {
            em.close();
        }
    }

    @Override
    public void addPerson(Person p) {
        EntityManager em = getEntityManager();
        InfoGeneral ig = new InfoGeneral();
        Collection<Hobby> hs = p.getHobbyCollection();
        if (p.getInfoGeneral() != null) ig = p.getInfoGeneral();
        try {
            em.getTransaction().begin();
            p.setInfoId(addInfoGeneral(ig));
            p.setInfoGeneral(ig);
            em.persist(p);
            Iterator ite = hs.iterator();
            while (ite.hasNext()) {
                em.persist(ite.next());
            }
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    
    @Override
    public void addCompany(Company c) {
        EntityManager em = getEntityManager();
        InfoGeneral ig = new InfoGeneral();
        try {
            em.getTransaction().begin();
            em.persist(ig);
            em.flush();
            c.setInfoId(ig.getInfoId());
            em.persist(c);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    
    //Delete for both Person and Company. 
    @Override
    public InfoGeneral deleteGeneral(int id) {
        EntityManager em = getEntityManager();
        try {
            InfoGeneral ig = em.find(InfoGeneral.class, id);
            if(ig == null){
              //throw new PersonNotFoundException("No Person found with provided id");
            }
            em.getTransaction().begin();
            em.remove(ig);
            em.getTransaction().commit();
            return ig;
        }
        finally {
            em.close();
        }
    }
    public Person editPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            Person ep = em.find(Person.class, p.getInfoId());
            Query q = em.createNamedQuery("Person.findByInfoId").setParameter("infoId", p.getInfoId());
            if (ep == null) {
                //throw new Exception();
            }
            if (p.getFirstName() != null) ep.setFirstName(p.getFirstName());
            if (p.getLastName() != null) ep.setLastName(p.getLastName());
            if (p.getHobbyCollection() != null) ep.setHobbyCollection(p.getHobbyCollection());
            if (p.getInfoGeneral() != null) ep.setInfoGeneral(editInfoGeneral(p.getInfoGeneral()));
            em.getTransaction().begin();
            em.merge(ep);
            em.getTransaction().commit();
            return ep;
        }
        finally {
            em.close();
        }
        
    }

    private InfoGeneral editInfoGeneral(InfoGeneral ig) {
        EntityManager em = getEntityManager();
        try {
            if (ig.getInfoId() == null) {
                int id = addInfoGeneral(ig);
                return em.find(InfoGeneral.class, id);
            }
            else {
                InfoGeneral ige = em.find(InfoGeneral.class, ig.getInfoId());
            //if (ige != null) {
                if (ig.getEmail() != null) ige.setEmail(ig.getEmail());
                if (ig.getPhoneCollection() != null) ige.setPhoneCollection(ig.getPhoneCollection());
                if (ig.getAddressId() != null) ige.setAddressId(editAddress(ig.getAddressId()));
                em.getTransaction().begin();
                em.merge(ige);
                em.getTransaction().commit();
                return ige;
            }
        }
        finally {
            em.close();
        }
    }
    
    private Address editAddress(Address a) {
        EntityManager em = getEntityManager();
        Address ae = em.find(Address.class, a.getAddressId());
        if (ae != null) {
            try {

                if (a.getStreet() != null) ae.setStreet(a.getStreet());
                if (a.getStreetNumber() != null) ae.setStreetNumber(a.getStreetNumber());
                if (a.getZipCode() != null) ae.setZipCode(editCity(a.getZipCode()));
                em.getTransaction().begin();
                em.merge(ae);
                em.getTransaction().commit();
                return ae;
            }
            finally {
                em.close();
            }
        }
        else {
            return addAddress(a);
        }
    }
    
    private City editCity(City c) {
        EntityManager em = getEntityManager();
        City ce = em.find(City.class, c.getZipCode());
        if (ce != null) {
            try {
                if (c.getCity() != null) ce.setCity(c.getCity());
                em.getTransaction().begin();
                em.merge(ce);
                em.getTransaction().commit();
                return ce;
            }
            finally {
                em.close();
            }
        }
        else {
            return addCity(c);
        }
    }
    
    @Override
    public Company getCompany(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> getCompanies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company editCompany(Company c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private int addInfoGeneral(InfoGeneral ig) {
        EntityManager em = getEntityManager();
        Address a = new Address();
        if (ig.getAddressId() != null) a = ig.getAddressId();
        ig.setAddressId(addAddress(a));
        try {
            em.getTransaction().begin();
            em.persist(ig);
            em.flush();
            em.getTransaction().commit();
            return ig.getInfoId();
        }
        finally {
            em.close();
        }
    }
    
    private Address addAddress(Address a) {
        EntityManager em = getEntityManager();
        Query q = null;
        if (a.getStreet() != null && a.getStreetNumber() != null) {
            q = em.createQuery("SELECT a FROM Address WHERE street='"+a.getStreet()+"' AND streetnumber='"+a.getStreetNumber()+"'");
        }
        if ((q != null && q.getResultList().isEmpty()) || q == null) {
            City c = new City();
            if (a.getZipCode() != null) c = a.getZipCode();
            a.setZipCode(addCity(c));
            try {
                em.getTransaction().begin();
                em.persist(a);
                em.flush();
                em.getTransaction().commit();
                return a;
            }
            finally {
                em.close();
            }
        }
        else {
            return (Address)q.getSingleResult();
        }
    }    
    
    private City addCity(City c) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("City.findByZipCode").setParameter("zipCode", c.getZipCode());
        if (q.getResultList().isEmpty()) {
            try {
                em.getTransaction().begin();
                em.persist(c);
                em.flush();
                em.getTransaction().commit();
                return c;
            }
            finally {
                em.close();
            }
        }
        else {
            return (City)q.getSingleResult();
        }
    }
    
    private void addHobbies(Collection<Hobby> hobbies) {
        EntityManager em = getEntityManager();
        Iterator ite = hobbies.iterator();
        while (ite.hasNext()) {
            try {
                em.getTransaction().begin();
                em.persist(ite.next());
                em.flush();
                em.getTransaction().commit();
            }
            finally {
                em.close();
            }
        }
    }
}
