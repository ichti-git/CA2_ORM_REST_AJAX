package entities.test;

import entities.Address;
import entities.City;
import entities.InfoGeneral;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-07T11:30:34")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, City> zipCode;
    public static volatile SingularAttribute<Address, String> streetNumber;
    public static volatile CollectionAttribute<Address, InfoGeneral> infoGeneralCollection;
    public static volatile SingularAttribute<Address, Integer> addressId;

}