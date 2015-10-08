package entities.test;

import entities.InfoGeneral;
import entities.Phone;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-08T14:11:57")
@StaticMetamodel(Phone.class)
public class Phone_ { 

    public static volatile SingularAttribute<Phone, String> phoneNumber;
    public static volatile SingularAttribute<Phone, String> description;
    public static volatile SingularAttribute<Phone, Integer> phoneId;
    public static volatile SingularAttribute<Phone, InfoGeneral> infoId;

}