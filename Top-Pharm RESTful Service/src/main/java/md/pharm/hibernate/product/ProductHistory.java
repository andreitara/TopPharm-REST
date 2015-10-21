package md.pharm.hibernate.product;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 10/4/2015.
 */
@Entity
@Table(name="ProductHistory")
public class ProductHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Product product;

    @Column(name = "date")
    private Date date;

    @Column(name = "action")
    private String action;

    public ProductHistory(){}

    public ProductHistory(Product product, Date date, String action) {
        this.product = product;
        this.date = date;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setDoctor(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductHistory that = (ProductHistory) o;

        if (id != that.id) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(action != null ? !action.equals(that.action) : that.action != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }
}
