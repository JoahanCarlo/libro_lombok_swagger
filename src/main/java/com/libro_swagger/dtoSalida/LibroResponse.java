package com.libro_swagger.dtoSalida;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.libro_swagger.config.BooleanToIntegerSerializer;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDateTime fechaCreacion;
    @JsonSerialize(using = BooleanToIntegerSerializer.class)
    private Boolean estado;
}
