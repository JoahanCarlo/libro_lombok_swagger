package com.libro_swagger.dtoEntrada;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta a la información del autor")
public class AutorRequest {
    private Long id;
    @Schema(description = "Nombre del autor", example = "Mario Vargas Llosa")
    private String nombreAutor;

    @Schema(description = "Nombre de la direccion", example = "Av. Pedro Villon")
    private String direccionAutor;

    @Schema(description = "Nombre del correo", example = "mario@gmail.com")
    private String correoAutor;
}
