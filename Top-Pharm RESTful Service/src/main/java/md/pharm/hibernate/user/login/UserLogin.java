package md.pharm.hibernate.user.login;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Andrei on 10/20/2015.
 */
public class UserLogin {
    private String username;
    private String password;

    public UserLogin(){}

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (username != null ? !username.equals(userLogin.username) : userLogin.username != null) return false;
        return !(password != null ? !password.equals(userLogin.password) : userLogin.password != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
