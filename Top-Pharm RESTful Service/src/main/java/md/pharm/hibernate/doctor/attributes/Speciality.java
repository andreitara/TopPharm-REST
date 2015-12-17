package md.pharm.hibernate.doctor.attributes;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by c-andrtara on 12/17/2015.
 */

@Entity
@Table(name="Speciality")
public class Speciality {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 100)
    private String name;

    private Doctor doctor;
}
