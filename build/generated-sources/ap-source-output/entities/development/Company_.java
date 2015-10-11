package entities.development;

import entities.Company;
import entities.InfoGeneral;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-11T14:24:46")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, String> description;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, String> cvr;
    public static volatile SingularAttribute<Company, InfoGeneral> infoGeneral;
    public static volatile SingularAttribute<Company, Integer> marketValue;
    public static volatile SingularAttribute<Company, Integer> infoId;
    public static volatile SingularAttribute<Company, Integer> numEmployees;

}