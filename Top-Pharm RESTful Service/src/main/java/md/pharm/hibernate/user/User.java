package md.pharm.hibernate.user;

import com.fasterxml.jackson.annotation.*;
import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.message.Message;
import md.pharm.hibernate.task.Task;
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
@Table(name="[User]", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User{

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Connection connection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserComment> userComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserHistory> userHistories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="users", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Permission permission;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "from", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Message> sendMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Message> receivedMessages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="UserDoctor", joinColumns=@JoinColumn(name="userID"), inverseJoinColumns=@JoinColumn(name="doctorID"))
    @JsonIgnore
    private Set<Doctor> doctors;

    public User() {
    }

    public User(String type, String firstName, String lastName, String fatherName, Date birthDate, String username, String password, String email, String phone1, String phone2, String country) {
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

    public void createDefaultPermission(){
        Permission permission = new Permission(this,true,false,true,false,true,false,false);
        this.permission = permission;
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

    @JsonIgnore
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Set<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<UserComment> userComments) {
        this.userComments = userComments;
    }

    public Set<UserHistory> getUserHistories() {
        return userHistories;
    }

    public void setUserHistories(Set<UserHistory> userHistories) {
        this.userHistories = userHistories;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Set<Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set<Message> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public boolean isHasUnreadMessages() {
        return hasUnreadMessages;
    }

    public void setHasUnreadMessages(boolean hasUnreadMessages) {
        this.hasUnreadMessages = hasUnreadMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (type != null ? !type.equals(user.type) : user.type != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (fatherName != null ? !fatherName.equals(user.fatherName) : user.fatherName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone1 != null ? !phone1.equals(user.phone1) : user.phone1 != null) return false;
        return !(phone2 != null ? !phone2.equals(user.phone2) : user.phone2 != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        return result;
    }
}
