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
@Table(name="Product", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
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
    @JsonIgnore
    private String category;

    @Column(name = "message")
    @Size(max = 512)
    private String message;

    @Column(name = "priority")
    private String priority;

    @Column(name = "slideURL")
    @Size(max = 1024)
    private String slideURL;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="products", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    public Product(){}

    public Product(String name, String category, String boxQuantity, Double averagePrice, String message, String priority, String slideURL) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSlideURL() {
        return slideURL;
    }

    public void setSlideURL(String slideURL) {
        this.slideURL = slideURL;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (boxQuantity != null ? !boxQuantity.equals(product.boxQuantity) : product.boxQuantity != null) return false;
        if (averagePrice != null ? !averagePrice.equals(product.averagePrice) : product.averagePrice != null)
            return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (message != null ? !message.equals(product.message) : product.message != null) return false;
        return !(slideURL != null ? !slideURL.equals(product.slideURL) : product.slideURL != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (boxQuantity != null ? boxQuantity.hashCode() : 0);
        result = 31 * result + (averagePrice != null ? averagePrice.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (slideURL != null ? slideURL.hashCode() : 0);
        return result;
    }
}
