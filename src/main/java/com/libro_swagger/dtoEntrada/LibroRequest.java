package com.libro_swagger.dtoEntrada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear o actualizar un libro")
public class LibroRequest {
    @NotNull(message = "El nombre del libro no puede ser nulo")
    private String nombreLibro;

    @NotNull(message = "El código ISBN no puede ser nulo")
    private String codigoIsbn;

    @NotNull(message = "El año de publicación no puede ser nulo")
    private Integer añoPublicacion;

    @NotNull(message = "El autorId no puede ser nulo")
    private Long autorId;

    @NotNull(message = "El editorialId no puede ser nulo")
    private Long editorialId;

}
