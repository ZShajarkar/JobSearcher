package com.example.demo.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RestResponse<T> implements Serializable {
    private String traceId;
    private String traceTime;
    private T body;
    private String message;
    private int code;
    private boolean hasError;

    public RestResponse() {
        this.traceId = UUID.randomUUID().toString();
        this.traceTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }

    public String getTraceId() {
        return traceId;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTraceTime() {
        return traceTime;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "traceId='" + traceId + '\'' +
                ", body=" + body +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", traceTime=" + traceTime +
                '}';
    }
}
