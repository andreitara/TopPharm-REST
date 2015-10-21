package md.pharm.hibernate.product;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 10/4/2015.
 */
@Entity
@Table(name="ProductComment")
public class ProductComment{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int ID;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Product product;

    @Column(name = "comment")
    private String comment;

    public ProductComment(){}

    public ProductComment(Date date, Product product, String comment) {
        this.date = date;
        this.product = product;
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductComment that = (ProductComment) o;

        if (ID != that.ID) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return !(comment != null ? !comment.equals(that.comment) : that.comment != null);

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
