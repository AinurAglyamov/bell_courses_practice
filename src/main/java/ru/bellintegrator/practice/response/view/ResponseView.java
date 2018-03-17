package ru.bellintegrator.practice.response.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseView {

    public Object data;
    public String result;
    public String error;

    public ResponseView() {
    }



}
