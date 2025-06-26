package com.libro_swagger.dtoSalida;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con información de un autor")
public class AutorResponse {
    private Long id;
    private String nombreAutor;
    private String direccionAutor;
    private String correoAutor;
}
