/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.InfoGeneral;
import entities.Person;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
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
    public void testAll() {
        testGetPersons();
        //testGetPerson();
        testAddPerson();
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
    }
    
    @Test
    public void testGetPerson() {
        Person p = new Person(2, "Alice", "Smith");
        assertEquals(p, facade.getPerson(2));
    }
    
    //@Test 
    public void testAddPerson() {
        Person p = new Person("Lars", "Larsen");
        facade.addPerson(p);
        assertEquals(p, facade.getPerson(4));
    }
    
    //@Test
    public void testDeleteGeneral() {
        InfoGeneral ig1 = new InfoGeneral(3);
        InfoGeneral ig2 = facade.deleteGeneral(3);
        Person p1 = new Person(3, "Bob", "Gordard");
        Person p2 = ig2.getPerson();
        assertEquals(p2, p1);
        assertEquals(ig2 ,ig1);
    }
    
    @Test
    public void testEditPerson() {
        Person p = new Person(1);
        InfoGeneral ig = new InfoGeneral();
        ig.setEmail("hangoo@my.ll");
        p.setLastName("Goose");
        p.setInfoGeneral(ig);
        p = facade.editPerson(p);
        
        assertEquals(p, facade.getPerson(1));
        
    }
}
