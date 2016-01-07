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

public class TaskCreate {

    private Integer id;
    private String name;
    private String category;
    private Integer typeID;
    private String repeat;
    private boolean isSubmitted;
    private boolean isCapital;
    private Date startDate;
    private Date endDate;
    private String description;
    private String address;
    private Integer userID;
    private Integer customerID;
    private Integer institutionID;

    public TaskCreate(){}

    public TaskCreate(Integer id, String name, String category, Integer typeID, String repeat, boolean isSubmitted, boolean isCapital, Date startDate, Date endDate, String description, String address, Integer userID, Integer customerID, Integer institutionID) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.typeID = typeID;
        this.repeat = repeat;
        this.isSubmitted = isSubmitted;
        this.isCapital = isCapital;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.address = address;
        this.userID = userID;
        this.customerID = customerID;
        this.institutionID = institutionID;
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

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    //public boolean isSubmitted() {
    //    return isSubmitted;
    //}

    public boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    //public boolean isCapital() {
    //    return isCapital;
    //}

    public boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(boolean isCapital) {
        this.isCapital = isCapital;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(Integer institutionID) {
        this.institutionID = institutionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskCreate that = (TaskCreate) o;

        if (isSubmitted != that.isSubmitted) return false;
        if (isCapital != that.isCapital) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (typeID != null ? !typeID.equals(that.typeID) : that.typeID != null) return false;
        if (repeat != null ? !repeat.equals(that.repeat) : that.repeat != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (customerID != null ? !customerID.equals(that.customerID) : that.customerID != null) return false;
        return !(institutionID != null ? !institutionID.equals(that.institutionID) : that.institutionID != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (typeID != null ? typeID.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        result = 31 * result + (isSubmitted ? 1 : 0);
        result = 31 * result + (isCapital ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (customerID != null ? customerID.hashCode() : 0);
        result = 31 * result + (institutionID != null ? institutionID.hashCode() : 0);
        return result;
    }
}
