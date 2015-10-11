package entities.development;

import entities.Address;
import entities.Company;
import entities.InfoGeneral;
import entities.Person;
import entities.Phone;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-11T15:58:30")
@StaticMetamodel(InfoGeneral.class)
public class InfoGeneral_ { 

    public static volatile CollectionAttribute<InfoGeneral, Phone> phoneCollection;
    public static volatile SingularAttribute<InfoGeneral, Person> person;
    public static volatile SingularAttribute<InfoGeneral, String> email;
    public static volatile SingularAttribute<InfoGeneral, Company> company;
    public static volatile SingularAttribute<InfoGeneral, Integer> infoId;
    public static volatile SingularAttribute<InfoGeneral, Address> addressId;

}