package md.pharm.hibernate.institution;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="InstitutionComment")
public class InstitutionComment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int ID;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institutionID")
    private Institution institution;

    @Column(name = "comment")
    private String comment;


    public InstitutionComment(){
    }

    public InstitutionComment(Date date, Institution institution, String comment) {
        this.date = date;
        this.institution = institution;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }


}
