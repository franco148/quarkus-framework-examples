package com.francofral.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Schema(description = "Response model")
public class ResponseModel {

    @Schema(description = "Message", example = "CREATED")
    private String message;
    @Schema(description = "HTTP code", example = "201")
    private int code;
    @Schema(description = "Timestamp of operation", example = "2025-09-09 13:04:01")
    private String timestamp;

    public ResponseModel(String message, int code) {
        this.message = message;
        this.code = code;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = now.format(formatter);
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
