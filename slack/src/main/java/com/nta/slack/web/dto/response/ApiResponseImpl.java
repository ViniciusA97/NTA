package com.nta.slack.web.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

public class ApiResponseImpl extends ApiResponse{

    public ApiResponseImpl(HttpStatus httpStatus, boolean success, Object content) {
        super(httpStatus, success, content);
    }

    public ApiResponseImpl(HttpStatus httpStatus, boolean success, String error) {
        super(httpStatus, success, error);
    }

    public ApiResponseImpl() {
        super();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return super.getHttpStatus();
    }

    @Override
    public boolean isSuccess() {
        return super.isSuccess();
    }

    @Override
    public Object getContent() {
        return super.getContent();
    }

    @Override
    public String getError() {
        return super.getError();
    }

    @Override
    public void setHttpStatus(HttpStatus httpStatus) {
        super.setHttpStatus(httpStatus);
    }

    @Override
    public void setSuccess(boolean success) {
        super.setSuccess(success);
    }

    @Override
    public void setContent(Object content) {
        super.setContent(content);
    }

    @Override
    public void setError(String error) {
        super.setError(error);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
