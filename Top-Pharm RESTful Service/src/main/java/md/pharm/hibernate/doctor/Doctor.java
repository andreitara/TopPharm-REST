package md.pharm.hibernate.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.attributes.*;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.User;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.Date;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="Doctor")
public class Doctor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 256)
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phone1")
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$", message = "Invalid phone number")
    @Size(max = 40)
    private String phone1;

    @Column(name = "phone2")
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$", message = "Invalid phone number")
    @Size(max = 40)
    private String phone2;

    @Column(name = "officePhone")
    @Pattern(regexp = "^\\+?([\\(\\) 0-9])+$", message = "Invalid phone number")
    @Size(max = 40)
    private String officePhone;

    @Column(name = "email")
    @Email
    @Size(max = 320)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="doctors", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Institution> institutions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DoctorComment> doctorComments;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="attendees", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasksAsAttendees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Task> tasksAsCustomers;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="doctors", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Habit> habits;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PersonalInfo> personalInfos ;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialityID")
    private Speciality speciality;

    @Column(name = "type")
    @JsonIgnore
    private String type;

    @Column(name = "subType")
    @JsonIgnore
    private String subType;

    public Doctor(){}

    public Doctor(Integer id){this.id = id;}

    public Doctor(String name, String city, String address, Date birthDate, String phone1, String phone2, String officePhone, String email, Speciality speciality, String type, String subType) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.birthDate = birthDate;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.officePhone = officePhone;
        this.email = email;
        this.speciality = speciality;
        this.type = type;
        this.subType = subType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<Institution> institutions) {
        this.institutions = institutions;
    }

    public Set<DoctorComment> getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(Set<DoctorComment> doctorComments) {
        this.doctorComments = doctorComments;
    }

    public Set<Task> getTasksAsAttendees() {
        return tasksAsAttendees;
    }

    public void setTasksAsAttendees(Set<Task> tasksAsAttendees) {
        this.tasksAsAttendees = tasksAsAttendees;
    }

    public Set<Task> getTasksAsCustomers() {
        return tasksAsCustomers;
    }

    public void setTasksAsCustomers(Set<Task> tasksAsCustomers) {
        this.tasksAsCustomers = tasksAsCustomers;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Habit> getHabits() {
        return habits;
    }

    public void setHabits(Set<Habit> habits) {
        this.habits = habits;
    }

    public Set<PersonalInfo> getPersonalInfos() {
        return personalInfos;
    }

    public void setPersonalInfos(Set<PersonalInfo> personalInfos) {
        this.personalInfos = personalInfos;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != null ? !id.equals(doctor.id) : doctor.id != null) return false;
        if (name != null ? !name.equals(doctor.name) : doctor.name != null) return false;
        if (city != null ? !city.equals(doctor.city) : doctor.city != null) return false;
        if (address != null ? !address.equals(doctor.address) : doctor.address != null) return false;
        if (birthDate != null ? !birthDate.equals(doctor.birthDate) : doctor.birthDate != null) return false;
        if (email != null ? !email.equals(doctor.email) : doctor.email != null) return false;
        if (type != null ? !type.equals(doctor.type) : doctor.type != null) return false;
        return !(subType != null ? !subType.equals(doctor.subType) : doctor.subType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (subType != null ? subType.hashCode() : 0);
        return result;
    }
}
