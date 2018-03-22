package ru.bellintegrator.practice.user.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {

    public String login;
    public String password;
    public String name;
    public String email;

    public UserView() {
    }


    @Override
    public String toString() {
        return "UserView{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + name + '\'' +
                '}';
    }
}
