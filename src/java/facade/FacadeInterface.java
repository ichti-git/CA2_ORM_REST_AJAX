/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Company;
import entities.Person;
import entities.InfoGeneral;
import exception.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author ichti
 */
public interface FacadeInterface {
    public Person getPerson(int id) throws PersonNotFoundException;
    public Company getCompany(int id); //throws CompanyNotFoundException
    public List<Person> getPersons(); 
    public List<Company> getCompanies();
    public Person addPerson(Person p);
    public Company addCompany(Company c);
    public InfoGeneral deleteGeneral(int id);
    public Person editPerson(Person p) throws PersonNotFoundException;
    public Company editCompany(Company c); //throws CompanyNotFoundException
    
}
