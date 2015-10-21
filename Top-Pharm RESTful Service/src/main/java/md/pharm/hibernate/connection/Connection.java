package md.pharm.hibernate.connection;

import md.pharm.hibernate.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Andrei on 9/3/2015.
 */

@Entity
@Table(name="Connection", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ConnectionKey")})
public class Connection {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "connectionKey")
    private String connectionKey;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    private User user;

    public Connection() {
        connectionKey = UUID.randomUUID().toString();
    }

    public Connection(Date date, String status, String connectionKey, User user) {
        this.date = date;
        this.status = status;
        this.connectionKey = connectionKey;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConnectionKey() {
        return connectionKey;
    }

    public void setConnectionKey(String connectionKey) {
        this.connectionKey = connectionKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return !(connectionKey != null ? !connectionKey.equals(that.connectionKey) : that.connectionKey != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (connectionKey != null ? connectionKey.hashCode() : 0);
        return result;
    }
}
