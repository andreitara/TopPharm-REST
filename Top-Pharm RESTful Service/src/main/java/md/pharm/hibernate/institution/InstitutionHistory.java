package md.pharm.hibernate.institution;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/4/2015.
 */
@Entity
@Table(name="InstitutionHistory")
public class InstitutionHistory {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institutionID")
    private Institution institution;

    @Column(name = "date")
    private Date date;

    @Column(name = "action")
    private String action;

    public InstitutionHistory(){}

    public InstitutionHistory(Institution institution, Date date, String action) {
        this.institution = institution;
        this.date = date;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
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

        InstitutionHistory that = (InstitutionHistory) o;

        if (id != that.id) return false;
        if (institution != null ? !institution.equals(that.institution) : that.institution != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(action != null ? !action.equals(that.action) : that.action != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }
}
