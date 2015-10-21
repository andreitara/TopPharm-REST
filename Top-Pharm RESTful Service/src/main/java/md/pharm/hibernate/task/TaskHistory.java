package md.pharm.hibernate.task;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="TaskHistory")
public class TaskHistory {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskID")
    private Task task;

    @Column(name = "date")
    private Date date;

    @Column(name = "action")
    private String action;

    public TaskHistory(){}

    public TaskHistory(Task task, Date date, String action) {
        this.task = task;
        this.date = date;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

        TaskHistory that = (TaskHistory) o;

        if (id != that.id) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(action != null ? !action.equals(that.action) : that.action != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }
}
