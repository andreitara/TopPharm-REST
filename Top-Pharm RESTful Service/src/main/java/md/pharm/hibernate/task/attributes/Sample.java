package md.pharm.hibernate.task.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Column(name = "name")
    @NotNull
    @Size(max = 256)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskID")
    @JsonIgnore
    private Task task;
}
