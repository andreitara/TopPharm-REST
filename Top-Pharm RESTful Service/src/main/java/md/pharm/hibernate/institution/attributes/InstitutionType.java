package md.pharm.hibernate.institution.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Andrei on 12/22/2015.
 */

@Entity
@Table(name="InstitutionType")
public class InstitutionType {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Institution> institutions;

    public InstitutionType(){}

    public InstitutionType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<Institution> institutions) {
        this.institutions = institutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionType that = (InstitutionType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
