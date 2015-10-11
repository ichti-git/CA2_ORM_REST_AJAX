package entities.development;

import entities.Hobby;
import entities.InfoGeneral;
import entities.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-11T14:24:46")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> lastName;
    public static volatile CollectionAttribute<Person, Hobby> hobbyCollection;
    public static volatile SingularAttribute<Person, InfoGeneral> infoGeneral;
    public static volatile SingularAttribute<Person, Integer> infoId;
    public static volatile SingularAttribute<Person, String> firstName;

}