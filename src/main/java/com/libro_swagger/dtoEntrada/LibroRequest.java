package com.libro_swagger.dtoEntrada;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear o actualizar un libro")
public class LibroRequest {
    @Schema(example = "La ciudad y los perros")
    private String nombreLibro;

    @Schema(example = "Código del libro 978-84-978-117-2")
    private String codigoIsbn;

    @Schema(example = "Año de la publicación 1996")
    private Integer añoPublicacion;

    @Schema(example = "1", description = "ID del autor ya existente")
    private Long autorId;

    @Schema(example = "1", description = "ID del editorial ya existente")
    private Long editorialId;

}
