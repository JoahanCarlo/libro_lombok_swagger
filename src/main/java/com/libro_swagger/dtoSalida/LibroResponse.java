package com.libro_swagger.dtoSalida;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con informacion de un libro")
public class LibroResponse {
    private Long id;
    private String nombreLibro;
    private String codigoIsbn;
    private Integer añoPublicacion;
    private String nombreAutor;
    private String nombreEditorial;
    private LocalDateTime fechaCreacion;
}
