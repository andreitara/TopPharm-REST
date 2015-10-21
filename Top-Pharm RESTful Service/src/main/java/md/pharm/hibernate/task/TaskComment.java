package md.pharm.hibernate.task;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="TaskComment")
public class TaskComment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int ID;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskID")
    private Task task;

    @Column(name = "comment")
    private String comment;

    public TaskComment(){}

    public TaskComment(Date date, Task task, String comment) {
        this.date = date;
        this.task = task;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

        TaskComment that = (TaskComment) o;

        if (ID != that.ID) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        return !(comment != null ? !comment.equals(that.comment) : that.comment != null);

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
