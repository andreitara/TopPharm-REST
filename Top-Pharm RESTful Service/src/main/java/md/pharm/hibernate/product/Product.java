package md.pharm.hibernate.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.DoctorComment;
import md.pharm.hibernate.doctor.DoctorHistory;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Andrei on 10/4/2015.
 */
@Entity
@Table(name="Product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "boxQuantity")
    @Size(max = 20)
    private String boxQuantity;

    @Column(name = "averagePrice")
    private Double averagePrice;

    @Column(name = "category")
    @Size(max = 50)
    private String category;

    @Column(name = "message")
    @Size(max = 512)
    private String message;

    @Column(name = "priority")
    private int priority;

    @Column(name = "slideURL")
    @Size(max = 1024)
    private String slideURL;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Objective> objectives;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="products", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductComment> productComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductHistory> productHistories;

    public Product(){}

    public Product(String name, String category, String boxQuantity, Double averagePrice, String message, int priority, String slideURL) {
        this.name = name;
        this.category = category;
        this.boxQuantity = boxQuantity;
        this.averagePrice = averagePrice;
        this.message = message;
        this.priority = priority;
        this.slideURL = slideURL;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(String boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSlideURL() {
        return slideURL;
    }

    public void setSlideURL(String slideURL) {
        this.slideURL = slideURL;
    }

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<Objective> objectives) {
        this.objectives = objectives;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<ProductComment> getProductComments() {
        return productComments;
    }

    public void setProductComments(Set<ProductComment> productComments) {
        this.productComments = productComments;
    }

    public Set<ProductHistory> getProductHistories() {
        return productHistories;
    }

    public void setProductHistories(Set<ProductHistory> productHistories) {
        this.productHistories = productHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (priority != product.priority) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (boxQuantity != null ? !boxQuantity.equals(product.boxQuantity) : product.boxQuantity != null) return false;
        if (averagePrice != null ? !averagePrice.equals(product.averagePrice) : product.averagePrice != null)
            return false;
        return !(message != null ? !message.equals(product.message) : product.message != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (boxQuantity != null ? boxQuantity.hashCode() : 0);
        result = 31 * result + (averagePrice != null ? averagePrice.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }
}
