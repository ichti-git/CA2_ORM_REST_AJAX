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
            if (infoitem.equals("id")) json.addProperty("id", p.getInfoId());
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
        //Address
        Address a = new Address();
        City c = new City();
        if (jsonobj.get("street") == null && jsonobj.get("streetnumber") == null && jsonobj.get("zipcode") == null) {
            a = null;
        }
        else {
            if (jsonobj.get("street") != null) a.setStreet(jsonobj.get("street").getAsString());
            if (jsonobj.get("streetnumber") != null) a.setStreetNumber(jsonobj.get("streetnumber").getAsString());
            //City
            if (jsonobj.get("zipcode") != null) {
                c.setZipCode(jsonobj.get("zipcode").getAsString());
                
                if (jsonobj.get("city") != null) {
                    c.setCity(jsonobj.get("city").getAsString());
                }
            }   
            else {
                c = null;
            }
            a.setZipCode(c);
        }
        //From InfoGeneral class
        InfoGeneral ig = new InfoGeneral();
        Collection<Phone> ps = null; 
        if (jsonobj.get("email") == null && jsonobj.get("email") == null) {
            ig = null;
        }
        else {
            if (jsonobj.get("email") != null) ig.setEmail(jsonobj.get("email").getAsString());
            if (jsonobj.get("phones") != null) {
                JsonArray phonesArr = jsonobj.getAsJsonArray("phones");
                for(JsonElement pitem : phonesArr){
                    if (pitem.getAsJsonObject().get("number") != null) {
                        ps = new ArrayList<>();
                        Phone phone = new Phone();
                        phone.setPhoneNumber(pitem.getAsJsonObject().get("number").getAsString());
                        if (pitem.getAsJsonObject().get("description") != null)
                            phone.setDescription(pitem.getAsJsonObject().get("description").getAsString());
                        ps.add(phone);
                    }
                }
            }
            ig.setPhoneCollection(ps);
            ig.setAddressId(a);
        }
        
        Person p = new Person();
        Collection<Hobby> hs = null; 
        //From Person class
        if (jsonobj.get("firstname") != null) p.setFirstName(jsonobj.get("firstname").getAsString());
        if (jsonobj.get("lastname") != null) p.setLastName(jsonobj.get("lastname").getAsString());
        if (jsonobj.get("hobbies") != null) {
            hs = new ArrayList<>();
            JsonArray hobbiesArr = jsonobj.getAsJsonArray("hobbies");
            for(JsonElement hitem : hobbiesArr){
                if (hitem.getAsJsonObject().get("name") != null) {
                    Hobby hobby = new Hobby();
                    hobby.setName(hitem.getAsJsonObject().get("name").getAsString());
                    if (hitem.getAsJsonObject().get("description") != null)
                        hobby.setDescription(hitem.getAsJsonObject().get("description").getAsString());
                    hs.add(hobby);
                }
            }
        }
        p.setInfoGeneral(ig);
        p.setHobbyCollection(hs);
        return p;
    }
    
    public static Address getAddressfromJSONObject(JsonObject jsonaddr) {
        Address a = new Address();
        if (jsonaddr.get("street") != null) a.setStreet(jsonaddr.get("street").getAsString());
        if (jsonaddr.get("streetnumber") != null) a.setStreetNumber(jsonaddr.get("streetnumber").getAsString());
        //if (jsonaddr.get("zip") != null) a.setZipCode(getCityfromJSONObject(jsonaddr.get("zipcode")));
        
        a.setStreet(null);
        
        return a;
    }
    
    public static City getCityfromJSONObject(JsonObject jsoncity) {
        City c = new City();
        if (jsoncity.get("zipcode") != null) c.setZipCode(jsoncity.get("zipcode").getAsString());
        if (jsoncity.get("cityname") != null) c.setCity(jsoncity.get("cityname").getAsString());
        
        return c;
    }
}
