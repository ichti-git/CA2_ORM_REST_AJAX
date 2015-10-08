/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ichti
 */
@Entity
@Table(name = "Person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByInfoId", query = "SELECT p FROM Person p WHERE p.infoId = :infoId"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "infoId")
    private Integer infoId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @ManyToMany(mappedBy = "personCollection")
    private Collection<Hobby> hobbyCollection;
    @JoinColumn(name = "infoId", referencedColumnName = "infoId", insertable = false, updatable = false)
    @OneToOne(optional = false, orphanRemoval = true)
    private InfoGeneral infoGeneral;

    public Person() {
    }

    public Person(Integer infoId) {
        this.infoId = infoId;
    }
    
    public Person(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
    }
    
    public Person(Integer id, String fname, String lname) {
        this.infoId = id;
        this.firstName = fname;
        this.lastName = lname;
    } 

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlTransient
    public Collection<Hobby> getHobbyCollection() {
        return hobbyCollection;
    }

    public void setHobbyCollection(Collection<Hobby> hobbyCollection) {
        this.hobbyCollection = hobbyCollection;
    }

    public InfoGeneral getInfoGeneral() {
        return infoGeneral;
    }

    public void setInfoGeneral(InfoGeneral infoGeneral) {
        this.infoGeneral = infoGeneral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infoId != null ? infoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.infoId == null && other.infoId != null) || (this.infoId != null && !this.infoId.equals(other.infoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Person[ infoId=" + infoId + " ]";
    }
    
}
