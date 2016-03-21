package Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.message.Message;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.UserComment;
import md.pharm.hibernate.user.UserHistory;
import md.pharm.hibernate.user.permission.Permission;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 9/3/2015.
 */


public class CreateUser {

    private Integer id;
    private String rights;
    private String name;
    private String address;
    private Date birthDate;
    private String username;
    private String password;
    private String email;
    private String phone1;
    private String phone2;
    public List<Integer> institutionIds;
    public CreateUser() {
    }

    public CreateUser(String rights, String name, String address, Date birthDate, String username, String password, String email, String phone1, String phone2) {
        this.rights = rights;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Integer> getInstitutionIds() {
        return institutionIds;
    }

    public void setInstitutionIds(List<Integer> institutionIds) {
        this.institutionIds = institutionIds;
    }
}
