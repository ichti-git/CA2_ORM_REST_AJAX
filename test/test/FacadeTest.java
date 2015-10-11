/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Address;
import entities.Hobby;
import entities.InfoGeneral;
import entities.Person;
import exception.GeneralNotFoundException;
import exception.PersonNotFoundException;
import facade.Facade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ichti
 */
public class FacadeTest {
    
    private Facade facade = new Facade(Persistence.createEntityManagerFactory("CA2PU_TEST"));
    
    public FacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    //Test in correct order, where order matters
    @Test 
    public void testOrder() throws PersonNotFoundException, GeneralNotFoundException {
        testGetPersons();
        //testGetPerson();
        //testAddPerson();
        testDeleteGeneral();
        //testEditPerson();
    }
    //@Test
    public void testGetPersons() {
        List<Person> ps = new ArrayList();
        ps.add(new Person(1, "John", "Smith"));
        ps.add(new Person(2, "Alice", "Smith"));
        ps.add(new Person(3, "Bob", "Gordard"));
        assertEquals(ps, facade.getPersons()); 
        //it's only checking on Id's, so we know that persons with id 1,2,3 exists
    }
    
    @Test
    public void testGetPersonsFail() {
        List<Person> ps = new ArrayList();
        ps.add(new Person(5, "John", "Smith"));
        ps.add(new Person(8, "Alice", "Smith"));
        ps.add(new Person(10, "Bob", "Gordard"));
        assertThat(ps, not(facade.getPersons())); 
        //it's only checking on Id's, so we know that persons with id 5,8,10 does not exist.
    }
    
    @Test
    public void testGetPerson() throws PersonNotFoundException {
        assertThat(facade.getPerson(1).getInfoId(), is(1));
        assertThat(facade.getPerson(1).getFirstName(), is("John"));
        assertThat(facade.getPerson(1).getLastName(), is("Smith"));
    }
    
    @Test
    public void testGetPersonHobby() throws PersonNotFoundException {
        Collection<Hobby> hs = facade.getPerson(2).getHobbyCollection();
        Hobby h1 = new Hobby(1);
        Hobby h2 = new Hobby(2);
        assertThat(true, is(hs.contains(h1)));
        assertThat(true, is(hs.contains(h2)));
    }
    
    @Test
    public void testGetPersonEmail() throws PersonNotFoundException {
        assertThat(facade.getPerson(1).getInfoGeneral().getEmail(), is("j@smith.org"));
    }
    
    @Test
    public void testGetPersonAddress() throws PersonNotFoundException {
        assertThat(facade.getPerson(1).getInfoGeneral().getAddressId().getStreet(), is("Hansensvej"));
        assertThat(facade.getPerson(1).getInfoGeneral().getAddressId().getStreetNumber(), is("44"));
    }
    
    @Test
    public void testGetPersonCity() throws PersonNotFoundException {
        assertThat(facade.getPerson(1).getInfoGeneral().getAddressId().getZipCode().getZipCode(), is("3600"));
        assertThat(facade.getPerson(1).getInfoGeneral().getAddressId().getZipCode().getCity(), is("Frederikssund"));
    }
    
    //incomplete test
    @Test 
    public void testAddPerson() throws PersonNotFoundException{
        Person p = new Person("Lars", "Larsen");
        facade.addPerson(p);
        assertThat(facade.getPerson(4), is(p));
    }
    
    //@Test
    public void testDeleteGeneral() throws GeneralNotFoundException, PersonNotFoundException {
        
        InfoGeneral ig = facade.deleteGeneral(3);
        try {
            Person p = facade.getPerson(3);
        }
        catch (PersonNotFoundException e) {
            assertThat(e.getMessage(), is("Person not found."));
        }
        assertThat(ig.getInfoId(), is(3));
        assertThat(ig.getPerson().getFirstName(), is("Bob"));
        assertThat(ig.getPerson().getLastName(), is("Gordard"));
        
    }
    
    @Test
    public void testEditPerson() throws PersonNotFoundException {
        Person p = new Person(2);
        InfoGeneral ig = new InfoGeneral();
        ig.setEmail("hangoo@my.ll");
        p.setLastName("Goose");
        Address a = new Address();
        a.setStreet("Moonroad");
        ig.setAddressId(a);
        p.setInfoGeneral(ig);
        facade.editPerson(p);
        assertThat(facade.getPerson(2).getInfoGeneral().getEmail(), is("hangoo@my.ll"));
        assertThat(facade.getPerson(2).getLastName(), is("Goose"));
        assertThat(facade.getPerson(2).getInfoGeneral().getAddressId().getStreet(), is("Moonroad"));
        
    }
}
