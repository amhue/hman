package com.amhue.hman.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
    @Id @GeneratedValue private Integer id;
    private String name;
    private String email;
    private String phone;

    private boolean isMgmt;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Booking> bookings;

    private String docName;
    private String docType;

    public Users() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isMgmt() { return isMgmt; }
    public void setMgmt(boolean isMgmt) { this.isMgmt = isMgmt; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }
    public String getDocType() { return docType; }
    public void setDocType(String docType) { this.docType = docType; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + (isMgmt ? 1231 : 1237);
        result =
            prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        result = prime * result + ((docName == null) ? 0 : docName.hashCode());
        result = prime * result + ((docType == null) ? 0 : docType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Users other = (Users)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (isMgmt != other.isMgmt)
            return false;
        if (bookings == null) {
            if (other.bookings != null)
                return false;
        } else if (!bookings.equals(other.bookings))
            return false;
        if (docName == null) {
            if (other.docName != null)
                return false;
        } else if (!docName.equals(other.docName))
            return false;
        if (docType == null) {
            if (other.docType != null)
                return false;
        } else if (!docType.equals(other.docType))
            return false;
        return true;
    }

    public Users(Integer id, String name, String email, String phone,
                 boolean isMgmt, List<Booking> bookings, String docName,
                 String docType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isMgmt = isMgmt;
        this.bookings = bookings;
        this.docName = docName;
        this.docType = docType;
    }
}
