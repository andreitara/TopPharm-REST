package md.pharm.hibernate.training;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="TrainingComment")
public class TrainingComment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainingID")
    private Training training;

    @Column(name = "comment")
    private String comment;

    public TrainingComment(){}

    public TrainingComment(Date date, Training training, String comment) {
        this.date = date;
        this.training = training;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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

        TrainingComment that = (TrainingComment) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(comment != null ? !comment.equals(that.comment) : that.comment != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
