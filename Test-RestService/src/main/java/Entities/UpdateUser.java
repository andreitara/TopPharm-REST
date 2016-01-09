package Entities;

import java.util.Date;

/**
 * Created by Andrei on 9/3/2015.
 */


public class UpdateUser {

    private Integer id;
    private String rights;
    private String name;
    private String address;
    private Date birthDate;
    private String username;
    private String email;
    private String phone1;
    private String phone2;
    public UpdateUser() {
    }

    public UpdateUser(String rights, String name, String address, Date birthDate, String username, String email, String phone1, String phone2) {
        this.rights = rights;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
