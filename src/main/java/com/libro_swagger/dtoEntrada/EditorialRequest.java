package com.libro_swagger.dtoEntrada;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta a la información de editorial")
public class EditorialRequest {
    private String nombreEditorial;
    private String nombreDireccion;
    private String nombreCorreo;
}
