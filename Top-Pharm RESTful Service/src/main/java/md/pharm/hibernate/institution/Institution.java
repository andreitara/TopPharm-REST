package md.pharm.hibernate.institution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.institution.attributes.InstitutionType;
import md.pharm.hibernate.user.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="Institution", uniqueConstraints = {
        @UniqueConstraint(columnNames = "longName")})
public class Institution {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "longName")
    @NotNull
    @Size(max = 100)
    private String longName;

    @Column(name = "shortName")
    @NotNull
    @Size(max = 20)
    private String shortName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionTypeID")
    private InstitutionType type;

    @Column(name = "phone1")
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$")
    @Size(max = 40)
    private String phone1;

    @Column(name = "phone2")
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$")
    @Size(max = 40)
    private String phone2;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="InstitutionDoctor", joinColumns=@JoinColumn(name="institutionID"), inverseJoinColumns=@JoinColumn(name="doctorID"))
    @JsonIgnore
    private Set<Doctor> doctors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="InstitutionUser", joinColumns=@JoinColumn(name="institutionID"), inverseJoinColumns=@JoinColumn(name="userID"))
    @JsonIgnore
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<InstitutionComment> institutionComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<InstitutionHistory> institutionHistories;

    public Institution(){}

    public Institution(Integer id){ this.id = id; }

    public Institution(String longName, String shortName, InstitutionType type, String phone1, String phone2, String city, String street) {
        this.longName = longName;
        this.shortName = shortName;
        this.type = type;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.city = city;
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<InstitutionComment> getInstitutionComments() {
        return institutionComments;
    }

    public void setInstitutionComments(Set<InstitutionComment> institutionComments) {
        this.institutionComments = institutionComments;
    }

    public Set<InstitutionHistory> getInstitutionHistories() {
        return institutionHistories;
    }

    public void setInstitutionHistories(Set<InstitutionHistory> institutionHistories) {
        this.institutionHistories = institutionHistories;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (longName != null ? !longName.equals(that.longName) : that.longName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (phone1 != null ? !phone1.equals(that.phone1) : that.phone1 != null) return false;
        if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return !(street != null ? !street.equals(that.street) : that.street != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (longName != null ? longName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }
}
