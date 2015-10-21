package md.pharm.hibernate.institution;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created by Andrei on 10/4/2015.
 */

@Entity
@Table(name="Office")
public class Office {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "block")
    @Size(max = 10)
    private String block;

    @Column(name = "floor")
    private int floor;

    @Column(name = "officeNumber")
    @Size(max = 10)
    private String officeNumber;

    @Column(name = "descripiton")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionID")
    @Valid
    private Institution institution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorID")
    @Valid
    private Doctor doctor;

    public Office(){}

    public Office(String block, int floor, String officeNumber, String description) {
        this.block = block;
        this.floor = floor;
        this.officeNumber = officeNumber;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
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

        Office office = (Office) o;

        if (id != office.id) return false;
        if (floor != office.floor) return false;
        if (block != null ? !block.equals(office.block) : office.block != null) return false;
        if (officeNumber != null ? !officeNumber.equals(office.officeNumber) : office.officeNumber != null)
            return false;
        if (description != null ? !description.equals(office.description) : office.description != null) return false;
        if (institution != null ? !institution.equals(office.institution) : office.institution != null) return false;
        return !(doctor != null ? !doctor.equals(office.doctor) : office.doctor != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + floor;
        result = 31 * result + (officeNumber != null ? officeNumber.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (institution != null ? institution.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        return result;
    }
}
