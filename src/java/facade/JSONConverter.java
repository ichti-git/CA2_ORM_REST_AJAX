/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Address;
import entities.City;
import entities.Hobby;
import entities.InfoGeneral;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author ichti
 */
public class JSONConverter {  
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String getJSONfromPersons(Collection<Person> ps, String info) {
        JsonArray persons = new JsonArray();
        Iterator ite = ps.iterator();
        while (ite.hasNext()) {
            persons.add(gson.fromJson(getJSONfromPerson((Person)ite.next(), info), JsonObject.class));
        }
        
        return gson.toJson(persons);
    }
    public static String getJSONfromPerson(Person p, String info) {
        JsonObject json = new JsonObject();
        String[] infoitems = info.split(",");
        for (String infoitem : infoitems) {
            if (infoitem.equals("firstname")) json.addProperty("firstname", p.getFirstName());
            if (infoitem.equals("lastname")) json.addProperty("lastname", p.getLastName());
            if (infoitem.equals("hobbies")) {
                JsonArray hobbyarray = new JsonArray();
                Collection<Hobby> hobbies = p.getHobbyCollection();
                Iterator ite = hobbies.iterator();
                while(ite.hasNext()) {
                    JsonObject hobby = new JsonObject();
                    Hobby nexthobby = (Hobby)ite.next();
                    hobby.addProperty("hobby", nexthobby.getName());
                    hobby.addProperty("description", nexthobby.getDescription());
                    hobbyarray.add(hobby);
                }
                if (hobbyarray.size() > 0) json.add("hobbies", hobbyarray);
            }
            if (infoitem.equals("email")) json.addProperty("email", p.getInfoGeneral().getEmail());
            if (infoitem.equals("phones") && p.getInfoGeneral().getPhoneCollection() != null) {
                JsonArray phonearray = new JsonArray();
                Collection<Phone> phones = p.getInfoGeneral().getPhoneCollection();
                Iterator ite = phones.iterator();
                while(ite.hasNext()) {
                    JsonObject phone = new JsonObject();
                    Phone nextphone = (Phone)ite.next();
                    phone.addProperty("phone", nextphone.getPhoneNumber());
                    phone.addProperty("description", nextphone.getDescription());
                    phonearray.add(phone);
                }
                if (phonearray.size() > 0) json.add("phones", phonearray);
            }
            if (infoitem.equals("street")) json.addProperty("street", p.getInfoGeneral().getAddressId().getStreet());
            if (infoitem.equals("streetnumber")) json.addProperty("streetnumber", p.getInfoGeneral().getAddressId().getStreetNumber());
            if (infoitem.equals("zipcode") && p.getInfoGeneral().getAddressId().getZipCode() != null) 
                json.addProperty("zipcode", p.getInfoGeneral().getAddressId().getZipCode().getZipCode());
            if (infoitem.equals("city") && p.getInfoGeneral().getAddressId().getZipCode() != null) 
                json.addProperty("city", p.getInfoGeneral().getAddressId().getZipCode().getCity());
        }
        return gson.toJson(json);  
    }
    
    public static Person getPersonfromJSON(String json) {
        JsonObject jsonobj = new JsonParser().parse(json).getAsJsonObject();
        Person p = new Person();
        InfoGeneral ig = new InfoGeneral();
        Address a = new Address();
        Collection<Hobby> hs = new ArrayList<>();
        City c = new City();
        a.setZipCode(c);
        ig.setAddressId(a);
        p.setInfoGeneral(ig);
        p.setHobbyCollection(hs);
        //From Person class
        if (jsonobj.get("firstname") != null) p.setFirstName(jsonobj.get("firstname").toString());
        if (jsonobj.get("lastname") != null) p.setLastName(jsonobj.get("lastname").toString());
        if (jsonobj.get("hobbies") != null) {
            JsonArray hobbiesArr = jsonobj.getAsJsonArray("hobbies");
            for(JsonElement hitem : hobbiesArr){
                Hobby hobby = new Hobby();
                hobby.setName(hitem.getAsJsonObject().get("name").getAsString());
                hobby.setDescription(hitem.getAsJsonObject().get("description").getAsString());
                p.getHobbyCollection().add(hobby);
            }
        }
        
        //From InfoGeneral class
        if (jsonobj.get("email") != null) p.getInfoGeneral().setEmail(jsonobj.get("email").toString());
        if (jsonobj.get("phones") != null) {
            JsonArray phonesArr = jsonobj.getAsJsonArray("phones");
            for(JsonElement pitem : phonesArr){
                Phone phone = new Phone();
                phone.setPhoneNumber(pitem.getAsJsonObject().get("number").getAsString());
                phone.setDescription(pitem.getAsJsonObject().get("description").getAsString());
                p.getInfoGeneral().getPhoneCollection().add(phone);
            }
        }
        //Address
        if (jsonobj.get("street") == null && jsonobj.get("streetnumber") == null) {
            p.getInfoGeneral().setAddressId(null);
        }
        else {
            if (jsonobj.get("street") != null) p.getInfoGeneral().getAddressId().setStreet(jsonobj.get("street").toString());
            if (jsonobj.get("streetnumber") != null) p.getInfoGeneral().getAddressId().setStreetNumber(jsonobj.get("streetnumber").toString());
            //City
            if (jsonobj.get("zipcode") != null) {
                p.getInfoGeneral().getAddressId().getZipCode().setZipCode(jsonobj.get("zipcode").toString());
                
                if (jsonobj.get("city") != null) {
                    p.getInfoGeneral().getAddressId().getZipCode().setCity(jsonobj.get("city").toString());
                }
            }   
            else {
                p.getInfoGeneral().getAddressId().setZipCode(null);
            }
            
        }
        
        return p;
    }
    
    public static Address getAddressfromJSONObject(JsonObject jsonaddr) {
        Address a = new Address();
        if (jsonaddr.get("street") != null) a.setStreet(jsonaddr.get("street").toString());
        if (jsonaddr.get("streetnumber") != null) a.setStreetNumber(jsonaddr.get("streetnumber").toString());
        //if (jsonaddr.get("zip") != null) a.setZipCode(getCityfromJSONObject(jsonaddr.get("zipcode")));
        
        a.setStreet(null);
        
        return a;
    }
    
    public static City getCityfromJSONObject(JsonObject jsoncity) {
        City c = new City();
        if (jsoncity.get("zipcode") != null) c.setZipCode(jsoncity.get("zipcode").toString());
        if (jsoncity.get("cityname") != null) c.setCity(jsoncity.get("cityname").toString());
        
        return c;
    }
}
