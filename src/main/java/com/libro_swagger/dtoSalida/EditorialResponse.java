package com.libro_swagger.dtoSalida;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con información de la editorial")
public class EditorialResponse {
    private Long id;
    private String nombreEditorial;
    private String nombreDireccion;
    private String nombreCorreo;
}
