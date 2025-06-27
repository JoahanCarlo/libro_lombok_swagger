package com.libro_swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Biblioteca de Huaraz",
                version = "1.0.0",
                description = "Documento API para la gestión de libros en la biblioteca"
        )
)
public class OpenApiConfig {
}
