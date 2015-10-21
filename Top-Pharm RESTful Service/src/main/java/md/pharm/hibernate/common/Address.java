package md.pharm.hibernate.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * Created by Andrei on 10/4/2015.
 */

@Entity
@Table(name="Address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "street")
    @Size(max = 50)
    private String street;

    @Column(name = "district")
    @Size(max = 30)
    private String district;

    @Column(name = "city")
    @Size(max = 30)
    private String city;

    @Column(name = "state")
    @Size(max = 30)
    private String state;

    @Column(name = "country")
    @Size(max = 30)
    private String country;

    @Column(name = "postalCode")
    @Size(max = 30)
    private String postalCode;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "institutionID")
    @JsonIgnore
    private Institution institution;

    public Address(){}

    public Address(String street, String district, String city, String state, String country, String postalCode) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonIgnore
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (district != null ? !district.equals(address.district) : address.district != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        return !(postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
