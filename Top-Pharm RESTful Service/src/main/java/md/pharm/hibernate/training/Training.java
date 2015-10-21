package md.pharm.hibernate.training;

import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="Training")
public class Training {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "Subject")
    private String subject;

    @Column(name = "Description")
    private String Description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TaskID")
    private Task task;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingComment> trainingComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingHistory> trainingHistories;
}
