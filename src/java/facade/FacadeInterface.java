/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Company;
import entities.Person;
import entities.InfoGeneral;
import java.util.List;

/**
 *
 * @author ichti
 */
public interface FacadeInterface {
    public Person getPerson(int id);
    public Company getCompany(int id);
    public List<Person> getPersons();
    public List<Company> getCompanies();
    public void addPerson(Person p);
    public void addCompany(Company c);
    public InfoGeneral deleteGeneral(int id);
    public Person editPerson(Person p);
    public Company editCompany(Company c);
    
}
