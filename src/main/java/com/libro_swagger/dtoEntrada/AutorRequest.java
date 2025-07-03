package com.libro_swagger.dtoEntrada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta a la información del autor")
public class AutorRequest {
    private String nombreAutor;
    private String direccionAutor;
    private String correoAutor;
}
