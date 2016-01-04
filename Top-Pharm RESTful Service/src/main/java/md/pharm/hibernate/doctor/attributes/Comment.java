package md.pharm.hibernate.doctor.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Andrei on 1/4/2016.
 */

@Entity
@Table(name="DoctorComments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "comment")
    @NotNull
    @Size(max = 512)
    private String comment;

    @Column(name = "timeAdded")
    private Date timeAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    @JsonIgnore
    private Doctor doctor;

    public Comment(){}

    public Comment(String comment, Date timeAdded, Doctor doctor) {
        this.comment = comment;
        this.timeAdded = timeAdded;
        this.doctor = doctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (comment != null ? !comment.equals(comment.comment) : comment.comment != null) return false;
        return !(timeAdded != null ? !timeAdded.equals(comment.timeAdded) : comment.timeAdded != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        return result;
    }
}
