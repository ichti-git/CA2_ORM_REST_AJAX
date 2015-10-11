package entities.deploy;

import entities.Address;
import entities.City;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-11T14:24:46")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> zipCode;
    public static volatile CollectionAttribute<City, Address> addressCollection;
    public static volatile SingularAttribute<City, String> city;

}