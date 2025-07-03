package com.libro_swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotBlank
    private String nombreEditorial;

    @NotBlank
    private String nombreDireccion;

    @NotBlank
    private String nombreCorreo;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean estado = true;
}
