/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ichti
 */
@Entity
@Table(name = "Phone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p"),
    @NamedQuery(name = "Phone.findByPhoneId", query = "SELECT p FROM Phone p WHERE p.phoneId = :phoneId"),
    @NamedQuery(name = "Phone.findByPhoneNumber", query = "SELECT p FROM Phone p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Phone.findByDescription", query = "SELECT p FROM Phone p WHERE p.description = :description")})
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "phoneId")
    private Integer phoneId;
    @Basic(optional = false)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "infoId", referencedColumnName = "infoId")
    @ManyToOne(optional = false)
    private InfoGeneral infoId;

    public Phone() {
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int id) {
        this.phoneId = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoGeneral getInfoId() {
        return infoId;
    }

    public void setInfoId(InfoGeneral infoId) {
        this.infoId = infoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.phoneNumber == null && other.phoneNumber != null) || (this.phoneNumber != null && !this.phoneNumber.equals(other.phoneNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Phone[ phoneNumber=" + phoneNumber + " ]";
    }
    
}
