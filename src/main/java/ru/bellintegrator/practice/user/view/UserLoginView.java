package ru.bellintegrator.practice.user.view;

public class UserLoginView {

    public String login;
    public String password;

    public UserLoginView() {
    }

    public UserLoginView(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginView{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
