package md.pharm.hibernate.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.attributes.GeneralType;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.task.attributes.Memo;
import md.pharm.hibernate.task.attributes.NextObjective;
import md.pharm.hibernate.task.attributes.PromoItem;
import md.pharm.hibernate.task.attributes.Sample;
import md.pharm.hibernate.user.User;

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
public class TaskGet {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "category")
    @Size(max = 50)
    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeID")
    @JsonIgnore
    private GeneralType type;

    @Column(name = "repeatField")
    @Size(max = 100)
    private String repeat;

    @Column(name = "isSubmitted")
    private boolean isSubmitted;

    @Column(name = "isCapital")
    private boolean isCapital;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "description")
    @Size(max = 512)
    @JsonIgnore
    private String description;

    @Column(name = "address")
    @Size(max = 256)
    @JsonIgnore
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="DoctorTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="doctorID"))
    @JsonIgnore
    private Set<Doctor> attendees;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID")
    @JsonIgnore
    private Doctor customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionID")
    @JsonIgnore
    private Institution institution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskComment> taskComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskHistory> taskHistories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ProductTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="productID"))
    @JsonIgnore
    private Set<Product> products;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Memo> memos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="PromoItemTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="promoitemID"))
    @JsonIgnore
    private Set<PromoItem> promoItems;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SampleTask", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="sampleID"))
    @JsonIgnore
    private Set<Sample> samples;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<NextObjective> objectives;

    public TaskGet(){}

    public TaskGet(String name, String category, GeneralType type, String repeat, boolean isSubmitted, boolean isCapital, int visitNumbers, Date startDate, Date endDate, String description) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.repeat = repeat;
        this.isSubmitted = isSubmitted;
        this.isCapital = isCapital;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public TaskGet(TaskCreate taskCreate){
        this.id = taskCreate.getId();
        this.name = taskCreate.getName();
        this.category = taskCreate.getCategory();
        this.repeat = taskCreate.getRepeat();
        this.isSubmitted = taskCreate.getIsSubmitted();
        this.isCapital = taskCreate.getIsCapital();
        this.startDate = taskCreate.getStartDate();
        this.endDate = taskCreate.getEndDate();
        this.description = taskCreate.getDescription();
        this.address = taskCreate.getAddress();

        if(taskCreate.getTypeID()!=null)
            this.type = new GeneralType(taskCreate.getTypeID());

        if(taskCreate.getUserID()!=null)
            this.user = new User(taskCreate.getUserID());

        if(taskCreate.getCustomerID()!=null)
            this.customer = new Doctor(taskCreate.getCustomerID());

        if(taskCreate.getInstitutionID()!=null)
            this.institution = new Institution(taskCreate.getInstitutionID());
    }

    public String getInstitutionName(){
        if(institution!=null)
            return institution.getShortName();
        else
            return null;
    }

    public String getCustomerName(){
        if(customer!=null)
            return customer.getName();
        else
            return null;
    }

    public String getTypeName(){
        if(type!=null)
            return type.getName();
        else
            return null;
    }

    public String getUserName(){
        if(user!=null)
            return user.getName();
        else
            return null;
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

    public GeneralType getType() {
        return type;
    }

    public void setType(GeneralType type) {
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Doctor> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Doctor> attendees) {
        this.attendees = attendees;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setIsCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public Doctor getCustomer() {
        return customer;
    }

    public void setCustomer(Doctor customer) {
        this.customer = customer;
    }

    public Set<Memo> getMemos() {
        return memos;
    }

    public void setMemos(Set<Memo> memos) {
        this.memos = memos;
    }

    public Set<PromoItem> getPromoItems() {
        return promoItems;
    }

    public void setPromoItems(Set<PromoItem> promoItems) {
        this.promoItems = promoItems;
    }

    public Set<Sample> getSamples() {
        return samples;
    }

    public void setSamples(Set<Sample> samples) {
        this.samples = samples;
    }

    public Set<NextObjective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<NextObjective> objectives) {
        this.objectives = objectives;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskGet task = (TaskGet) o;

        if (isSubmitted != task.isSubmitted) return false;
        if (isCapital != task.isCapital) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (category != null ? !category.equals(task.category) : task.category != null) return false;
        if (type != null ? !type.equals(task.type) : task.type != null) return false;
        if (repeat != null ? !repeat.equals(task.repeat) : task.repeat != null) return false;
        if (startDate != null ? !startDate.equals(task.startDate) : task.startDate != null) return false;
        if (endDate != null ? !endDate.equals(task.endDate) : task.endDate != null) return false;
        return !(description != null ? !description.equals(task.description) : task.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        result = 31 * result + (isSubmitted ? 1 : 0);
        result = 31 * result + (isCapital ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
