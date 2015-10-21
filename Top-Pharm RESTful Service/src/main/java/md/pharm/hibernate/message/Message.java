package md.pharm.hibernate.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="Message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromID")
    @JsonIgnore
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toID")
    @JsonIgnore
    private User to;

    @Column(name = "date")
    private Date date;

    @Column(name = "message")
    @Size(max = 512)
    @NotNull
    private String message;

    @Transient
    private Integer fromID;

    @Transient
    private Integer toID;

    public Message(){}

    public Message(User from, User to, Date date, String message) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
        if(from!=null) fromID = from.getId();
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
        if(to!=null) toID = to.getId();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFromID() {
        if(from!=null) return from.getId();
        return fromID;
    }

    public void setFromID(Integer fromID) {
        this.fromID = fromID;
    }

    public Integer getToID() {
        if(to!=null) return to.getId();
        return toID;
    }

    public void setToID(Integer toID) {
        this.toID = toID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != null ? !id.equals(message1.id) : message1.id != null) return false;
        if (from != null ? !from.equals(message1.from) : message1.from != null) return false;
        if (to != null ? !to.equals(message1.to) : message1.to != null) return false;
        if (date != null ? !date.equals(message1.date) : message1.date != null) return false;
        return !(message != null ? !message.equals(message1.message) : message1.message != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
