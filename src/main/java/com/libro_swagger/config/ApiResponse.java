package com.libro_swagger.config;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Respuesta estándar con mensaje, timestamp y status")
public class ApiResponse {
    private String mensaje;
    private LocalDateTime timestamp;
    private int status;

    public ApiResponse(String mensaje, HttpStatus status) {
        this.mensaje = mensaje;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
