package entities.development;

import entities.Hobby;
import entities.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-11T15:58:30")
@StaticMetamodel(Hobby.class)
public class Hobby_ { 

    public static volatile SingularAttribute<Hobby, Integer> hobbyId;
    public static volatile SingularAttribute<Hobby, String> description;
    public static volatile SingularAttribute<Hobby, String> name;
    public static volatile CollectionAttribute<Hobby, Person> personCollection;

}