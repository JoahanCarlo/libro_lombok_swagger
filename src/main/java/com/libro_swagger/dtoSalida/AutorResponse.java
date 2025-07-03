package com.libro_swagger.dtoSalida;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.libro_swagger.config.BooleanToIntegerSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con información de un autor")
public class AutorResponse {
    private Long id;
    private String nombreAutor;
    private String direccionAutor;
    private String correoAutor;
    @JsonSerialize(using = BooleanToIntegerSerializer.class)
    private Boolean estado;
}
