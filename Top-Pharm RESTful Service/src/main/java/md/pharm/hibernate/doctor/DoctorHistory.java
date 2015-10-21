package md.pharm.hibernate.doctor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="DoctorHistory")
public class DoctorHistory {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    @Column(name = "date")
    private Date date;

    @Column(name = "action")
    private String action;

    public DoctorHistory(){}

    public DoctorHistory(Doctor doctor, Date date, String action) {
        this.doctor = doctor;
        this.date = date;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

        DoctorHistory that = (DoctorHistory) o;

        if (id != that.id) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(action != null ? !action.equals(that.action) : that.action != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }
}
