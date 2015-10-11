/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facade.Facade;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.parsing.Parser;

import javax.persistence.EntityManagerFactory;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
/**
 *
 * @author ichti
 */
public class RestTest {    
    private Facade facade = new Facade(Persistence.createEntityManagerFactory("CA2PU_TEST"));

    public RestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        baseURI = "https://localhost:8443/CA2/";
        defaultParser = Parser.JSON;
        basePath = "api/person/";
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

    @Test
    public void testGetPersonsComplete() {
        given()
                .relaxedHTTPSValidation()
        .when()
                .get("complete")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", hasItems("John", "Alice", "Bob"))
                .body("id", hasItems(1,2,3))
                .body("email", hasItems("j@smith.org", "a@smith.org", "bob@last.em"))
                .body("street", hasItems("Hansensvej", "TinyRoad"))
                .body("zipcode", hasItems("3600", "4000"))
                .body("get(0).phones.phone", hasItems("+4567883249", "+4586224591"))
                .body("get(1).hobbies.hobby", hasItems("Swimming", "Dancing"));
    }
    @Test
    public void testGetPersonComplete() {
        given()
                .relaxedHTTPSValidation()
        .when()
                .get("complete/3")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(3))
                .body("firstname", equalTo("Bob"))
                .body("lastname", equalTo("Gordard"))
                .body("email", equalTo("bob@last.em"))
                .body("street", equalTo("TinyRoad"))
                .body("streetnumber", equalTo("2"))
                .body("zipcode", equalTo("4000"))
                .body("city", equalTo("Roskilde"));
    }
    @Test
    public void testGetPersonNotFound() {
        given()
                .relaxedHTTPSValidation()
        .when()
                .get("complete/500")
        .then()
                .statusCode(404)
                .contentType("application/json")
                .body("message", equalTo("Person not found"));
    }
    @Test
    public void testGetPersonNotNumberId() {
        given()
                .relaxedHTTPSValidation()
        .when().
                get("complete/err").
        then().
                statusCode(500).
                contentType("application/json").
                body("message", equalTo("Internal error occured. Sorry for the inconvenience"));
                
    }
    @Test
    public void testAddPerson() {
        given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body("{\"firstname\":\"Lars\",\"lastname\":\"Larsen\",\"email\":\"ll@new.dk\","
                        + "\"street\":\"Larsvej\",\"streetnumber\":\"804\",\"zipcode\":\"3400\"}")
                .auth().basic("admin", "admin")
        .when()
                .put("")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(4))
                .body("firstname", equalTo("Lars"))
                .body("lastname", equalTo("Larsen"))
                .body("email", equalTo("ll@new.dk"))
                .body("street", equalTo("Larsvej"))
                .body("streetnumber", equalTo("804"))
                .body("zipcode", equalTo("3400"));
        
    }
    @Test
    public void testEditPerson() {
        given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body("{\"lastname\":\"Gordard\"," +
                      " \"street\":\"TinyRoad\"," +
                      " \"streetnumber\":\"2\"}")
                .auth().basic("admin", "admin")
        .when()
                .post("/2")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(2))
                .body("firstname", equalTo("Alice"))
                .body("lastname", equalTo("Gordard"))
                .body("email", equalTo("a@smith.org"))
                .body("hobbies.hobby", hasItems("Swimming", "Dancing"))
                //street and streetnumber will fail, because for some reason it only
                //returns the updated Person, but not the updated InfoGeneral (and 
                //hense Address also). If you use get afterward, it will give you 
                //the correct info, so it does update.
                .body("street", equalTo("TinyRoad")) 
                .body("streetnumber", equalTo("2"))
                .body("zipcode", equalTo("3600"));
                
    }
    @Test
    public void testMalformedJson() {
        given().
                relaxedHTTPSValidation().
                contentType("application/json").
                body("{\"firstname\": \"error\"]").
                auth().basic("admin", "admin").
        when().
                post("/1").
        then().
                contentType("application/json").
                statusCode(500).
                body("message", equalTo("Malformed json given"));
                
    }
            
}
