package ru.bellintegrator.practice.response.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private Logger log = LoggerFactory.getLogger(ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //return methodParameter.getContainingClass().getPackage().getName().equals("ru.bellintegrator.practice.organization.controller.impl");
        return methodParameter.getContainingClass().getName().endsWith("Impl"); //без этого swagger не работает
        //return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseView view = new ResponseView();

        if(methodParameter.getParameterType().getSimpleName().equals("void")) {
            view.result = "success";
            return view;
        }

        view.data = o;
        return view;/**/
    }
}
