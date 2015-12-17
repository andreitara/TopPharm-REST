package md.pharm.hibernate.doctor.attributes;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by c-andrtara on 12/17/2015.
 */

@Entity
@Table(name="PersonalInfo")
public class PersonalInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 256)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Doctor doctor;
}
