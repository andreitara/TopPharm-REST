package md.pharm.hibernate.institution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.quartz.SchedulerException;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

/**
 * Created by Andrei on 11/10/2015.
 */

@Entity
@Table(name="ScheduleDay")
public class ScheduleDay {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "start")
    private Time start;

    @Column(name = "end")
    private Time end;

    @Column(name = "day")
    private Integer day;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="scheduleDays", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Office> offices;

    public ScheduleDay(){}

    public ScheduleDay(Time start, Time end, int day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleDay that = (ScheduleDay) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        return !(day != null ? !day.equals(that.day) : that.day != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
