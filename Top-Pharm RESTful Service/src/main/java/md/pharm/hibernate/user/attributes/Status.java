package md.pharm.hibernate.user.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by c-andrtara on 12/17/2015.
 */

@Entity
@Table(name="Status")
public class Status {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(max = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;

    public Status(){}

    public Status(String name) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status that = (Status) o;

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
