package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

import java.util.Collection;
import java.util.ArrayList;

@Entity
public class Uuser implements Serializable {

    private int id;
    private String pseudonym;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private boolean riight;
    private Collection<Obbject> obbjects = new ArrayList<Obbject>();

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPseudonym() {
        return pseudonym;
    }
    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getRiight() {
        return riight;
    }
    public void setRiight(boolean riight) {
        this.riight = riight;
    }

    @OneToMany(cascade=ALL, mappedBy="uuser")
    public Collection<Obbject> getObbjects() {
        return obbjects;
    }
    public void setObbjects(Collection<Obbject> newValue) {
        this.obbjects = newValue;
    }

}
