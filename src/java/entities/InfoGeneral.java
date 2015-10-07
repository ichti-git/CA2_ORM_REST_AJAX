/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ichti
 */
@Entity
@Table(name = "InfoGeneral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoGeneral.findAll", query = "SELECT i FROM InfoGeneral i"),
    @NamedQuery(name = "InfoGeneral.findByInfoId", query = "SELECT i FROM InfoGeneral i WHERE i.infoId = :infoId"),
    @NamedQuery(name = "InfoGeneral.findByEmail", query = "SELECT i FROM InfoGeneral i WHERE i.email = :email")})
public class InfoGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "infoId")
    private Integer infoId;
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infoId")
    private Collection<Phone> phoneCollection;
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    @ManyToOne
    private Address addressId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "infoGeneral")
    private Company company;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "infoGeneral")
    private Person person;

    public InfoGeneral() {
    }

    public InfoGeneral(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof InfoGeneral)) {
            return false;
        }
        InfoGeneral other = (InfoGeneral) object;
        if ((this.infoId == null && other.infoId != null) || (this.infoId != null && !this.infoId.equals(other.infoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InfoGeneral[ infoId=" + infoId + " ]";
    }
    
}
