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
    private String rights;

    @Column(name = "user_name")
    @NotNull
    @Size(min = 1, max = 256)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "username")
    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 8, max = 256)
    private String password;

    @Column(name = "email")
    @Email
    @Size(max = 320)
    private String email;

    @Column(name = "phone1")
    @Size(max = 40)
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$", message = "Invalid phone number")
    private String phone1;

    @Column(name = "phone2")
    @Size(max = 40)
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$", message = "Invalid phone number")
    private String phone2;

    @Column(name = "hasUnreadMessages")
    @JsonIgnore
    private boolean hasUnreadMessages;

    @Column(name = "country")
    @Size(min = 2, max = 5)
    @JsonIgnore
    private String country;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Connection connection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Task> tasks;

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

    public User(Integer id) {
        this.id = id;
    }

    public User(String rights, String name, String address, Date birthDate, String username, String password, String email, String phone1, String phone2, String country) {
        this.rights = rights;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
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

    @JsonProperty
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (rights != null ? !rights.equals(user.rights) : user.rights != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(username != null ? !username.equals(user.username) : user.username != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rights != null ? rights.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
