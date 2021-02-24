package com.oozeander.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"timestamp", "error", "status_code", "message", "path"})
public class ExceptionResponse {
    @JsonFormat(pattern = "dd/MM/yyyy - hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonProperty("status_code")
    private Integer statusCode;
    private String error;
    private List<String> message;
    private String path;

    public ExceptionResponse() {
    }

    public ExceptionResponse(LocalDateTime timestamp, Integer statusCode, String error, List<String> message,
                             String path) {
        super();
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}