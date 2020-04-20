package Pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Userinfo {
    private int userId;
    @NotEmpty(message = "Username can't be null")
    private String username;
    @NotEmpty(message = "Password can't be null")
    private String password;
    private String rright;
    private Collection<Orders> ordersByUserId;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Rright")
    public String getRright() {
        return rright;
    }

    public void setRright(String rright) {
        this.rright = rright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userinfo userinfo = (Userinfo) o;

        if (userId != userinfo.userId) return false;
        if (username != null ? !username.equals(userinfo.username) : userinfo.username != null) return false;
        if (password != null ? !password.equals(userinfo.password) : userinfo.password != null) return false;
        if (rright != null ? !rright.equals(userinfo.rright) : userinfo.rright != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (rright != null ? rright.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userinfoByUserId")
    public Collection<Orders> getOrdersByUserId() {
        return ordersByUserId;
    }

    public void setOrdersByUserId(Collection<Orders> ordersByUserId) {
        this.ordersByUserId = ordersByUserId;
    }
}
