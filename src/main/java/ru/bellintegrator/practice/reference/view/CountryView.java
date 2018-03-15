package ru.bellintegrator.practice.reference.view;

public class CountryView {

    public String name;
    public Integer code;

    public CountryView() {
    }

    public CountryView(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", code:" + code +
                '}';
    }
}
