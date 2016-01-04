package md.pharm.hibernate.task.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by c-andrtara on 12/17/2015.
 */

@Entity
@Table(name="Sample")
public class Sample {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    @NotNull
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="samples", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    public Sample(){}

    public Sample(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        Sample sample = (Sample) o;

        if (id != null ? !id.equals(sample.id) : sample.id != null) return false;
        return !(title != null ? !title.equals(sample.title) : sample.title != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
