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
import java.util.Set;

/**
 * Created by Andrei on 9/3/2015.
 */

@Entity
@Table(name="[CreateUser]", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class CreateUser {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    @Size(max = 20)
    private String type;

    @Column(name = "firstName")
    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @Size(min = 1, max = 40)
    private String lastName;

    @Column(name = "fatherName")
    @Size(min = 1, max = 40)
    private String fatherName;

    @Column(name = "BirthDate")
    private Date birthDate;

    @Column(name = "username")
    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must contains at least one number and one letter")
    private String password;

    @Column(name = "email")
    @Email
    @Size(max = 320)
    private String email;

    @Column(name = "phone1")
    @Pattern(regexp = "^\\+?([0-9])+$", message = "Invalid phone number")
    @Size(max = 20)
    private String phone1;

    @Column(name = "phone2")
    @Pattern(regexp = "^\\+?([0-9])+$", message = "Invalid phone number")
    @Size(max = 20)
    private String phone2;

    @Column(name = "hasUnreadMessages")
    private boolean hasUnreadMessages;

    @Column(name = "country")
    @Size(min = 2, max = 5)
    private String country;


    public CreateUser() {
    }

    public CreateUser(String type, String firstName, String lastName, String fatherName, Date birthDate, String username, String password, String email, String phone1, String phone2, String country) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isHasUnreadMessages() {
        return hasUnreadMessages;
    }

    public void setHasUnreadMessages(boolean hasUnreadMessages) {
        this.hasUnreadMessages = hasUnreadMessages;
    }


}
