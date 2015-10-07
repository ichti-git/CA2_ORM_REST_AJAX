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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ichti
 */
@Entity
@Table(name = "Hobby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h"),
    @NamedQuery(name = "Hobby.findByHobbyId", query = "SELECT h FROM Hobby h WHERE h.hobbyId = :hobbyId"),
    @NamedQuery(name = "Hobby.findByDescription", query = "SELECT h FROM Hobby h WHERE h.description = :description"),
    @NamedQuery(name = "Hobby.findByName", query = "SELECT h FROM Hobby h WHERE h.name = :name")})
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hobbyId")
    private Integer hobbyId;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "PersonHobby", joinColumns = {
        @JoinColumn(name = "hobbyId", referencedColumnName = "hobbyId")}, inverseJoinColumns = {
        @JoinColumn(name = "personId", referencedColumnName = "infoId")})
    @ManyToMany
    private Collection<Person> personCollection;

    public Hobby() {
    }

    public Hobby(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public Hobby(Integer hobbyId, String name) {
        this.hobbyId = hobbyId;
        this.name = name;
    }

    public Integer getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hobbyId != null ? hobbyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby)) {
            return false;
        }
        Hobby other = (Hobby) object;
        if ((this.hobbyId == null && other.hobbyId != null) || (this.hobbyId != null && !this.hobbyId.equals(other.hobbyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hobby[ hobbyId=" + hobbyId + " ]";
    }
    
}
