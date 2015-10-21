package md.pharm.hibernate.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.user.User;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by Andrei on 9/5/2015.
 */
@Entity
@Table(name="Task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 256)
    private String name;

    @Column(name = "type")
    @Size(max = 50)
    private String type;

    @Column(name = "status")
    @Size(max = 20)
    private String status;

    @Column(name = "visitNumbers")
    private int visitNumbers;

    @Column(name = "startDate")
    @NotNull
    private Date startDate;

    @Column(name = "endDate")
    @NotNull
    private Date endDate;

    @Column(name = "description")
    @Size(max = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTask")
    @JsonIgnore
    private Task parentTask;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTask")
    @JsonIgnore
    private Set<Task> childTasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="UserTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="userID"))
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="DoctorTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="doctorID"))
    private Set<Doctor> doctors;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionID")
    private Institution institution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskComment> taskComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskHistory> taskHistories;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    //private Set<Training> trainings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ProductTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="productID"))
    private Set<Product> products;

    public Task(){}

    public Task(String name, String type, String status, int visitNumbers, Date startDate, Date endDate, String description) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.visitNumbers = visitNumbers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVisitNumbers() {
        return visitNumbers;
    }

    public void setVisitNumbers(int visitNumbers) {
        this.visitNumbers = visitNumbers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Set<Task> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(Set<Task> childTasks) {
        this.childTasks = childTasks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Set<TaskComment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(Set<TaskComment> taskComments) {
        this.taskComments = taskComments;
    }

    public Set<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(Set<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }

   // public Set<Training> getTrainings() {
   //     return trainings;
   // }

   // public void setTrainings(Set<Training> trainings) {
   //     this.trainings = trainings;
   // }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (visitNumbers != task.visitNumbers) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (type != null ? !type.equals(task.type) : task.type != null) return false;
        if (status != null ? !status.equals(task.status) : task.status != null) return false;
        if (startDate != null ? !startDate.equals(task.startDate) : task.startDate != null) return false;
        if (endDate != null ? !endDate.equals(task.endDate) : task.endDate != null) return false;
        return !(description != null ? !description.equals(task.description) : task.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + visitNumbers;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
