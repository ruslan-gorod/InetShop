package model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import utils.HashUtil;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "roleid")
    private int roleId;

    @Column(name = "salt")
    private String salt;

    public User() {
    }

    public User(int id, String login, String password, String name, String email, int roleId, String salt) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.salt = salt;
    }

    public User(String login, String pass, String name, String email, int roleId, String salt) {
        this.name = name;
        this.login = login;
        this.password = HashUtil.getSHA512SecurePassword(pass, salt);
        this.email = email;
        this.roleId = roleId;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getRoleId() == user.getRoleId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getSalt(), user.getSalt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword(), getEmail(), getRoleId(), getSalt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
