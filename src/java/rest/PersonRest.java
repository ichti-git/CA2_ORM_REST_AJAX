/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.InfoGeneral;
import entities.Person;
import exception.GeneralNotFoundException;
import exception.PersonNotFoundException;
import facade.Facade;
import facade.JSONConverter;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author ichti
 */
@Path("person")
public class PersonRest {

    @Context
    private UriInfo context;
    Facade facade = new Facade(Persistence.createEntityManagerFactory("CA2PU_TEST"));
    String completeinfo = "id,firstname,lastname,email,hobbies,phones,street,streetnumber,zipcode,city";

    /**
     * Creates a new instance of RESTAPI
     */
    public PersonRest() {
    }

    /**
     * Retrieves representation of an instance of rest.RESTAPI
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("/complete")
    public String getPersonsComplete() {
        return JSONConverter.getJSONfromPersons(facade.getPersons(), completeinfo);
    }
    
    @GET
    @Produces("application/json")
    @Path("complete/{id}")
    public String getPersonComplete(@PathParam("id") int id) throws PersonNotFoundException {
        return JSONConverter.getJSONfromPerson(facade.getPerson(id), completeinfo);
    }
    
    
    @GET
    @Produces("application/json")
    @Path("custom/{id}/{info}")
    public String getPersonCustom(@PathParam("id") int id, @PathParam("info") String info) throws PersonNotFoundException {
        return JSONConverter.getJSONfromPerson(facade.getPerson(id), info);
    }
    
    @GET
    @Produces("application/json")
    @Path("/custom/{info}")
    public String getPersonsCustom(@PathParam("info") String info) {
        return JSONConverter.getJSONfromPersons(facade.getPersons(), info);
    }

    /**
     * PUT method for updating or creating an instance of RESTAPI
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String createPerson(String content) {
        Person p = JSONConverter.getPersonfromJSON(content);
        p = facade.addPerson(p);
        return JSONConverter.getJSONfromPerson(p, completeinfo);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}")
    public String editPerson(@PathParam("id") int id, String content) throws PersonNotFoundException {
        Person p = JSONConverter.getPersonfromJSON(content);
        p.setInfoId(id);
        p = facade.editPerson(p);
        return JSONConverter.getJSONfromPerson(p, completeinfo);
    }
    
    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public String deletePerson(@PathParam("id") int id) throws GeneralNotFoundException {
        InfoGeneral ig = facade.deleteGeneral(id);
        return JSONConverter.getJSONfromPerson(ig.getPerson(), completeinfo);
    }
}
