package ru.bellintegrator.practice.user.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Boolean isActive;

    @Version
    private Integer version;

    public User() {
    }

    public User(String login, String password, String name, String email, String code, Boolean isActive) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.code = code;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
