package com.nta.slack.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ApiResponse {

    public ApiResponse(HttpStatus httpStatus, boolean success, Object content) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.content = content;
    }

    public ApiResponse(HttpStatus httpStatus, boolean success, String error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.error = error;
    }

    public ApiResponse() {
    }

    protected HttpStatus httpStatus;
    protected boolean success;
    protected Object content;
    protected String error;


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getContent() {
        return content;
    }

    public String getError() {
        return error;
    }


    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setError(String error) {
        this.error = error;
    }
}
