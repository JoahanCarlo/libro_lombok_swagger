package com.libro_swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NotBlank
    private String nombreLibro;

    @NotBlank
    private String codigoIsbn;

    @NotNull
    private Integer añoPublicacion;

    @Column(name = "autor_id", nullable = false)
    private Long autorId;

    @Column(name = "editorial_id", nullable = false)
    private Long editorialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id",insertable = false,updatable = false,nullable = false)
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editorial_id",insertable = false,updatable = false,nullable = false)
    private Editorial editorial;

    @CreationTimestamp
    @Column(name = "fecha_creacion",updatable = false)
    @Schema(hidden = true)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean estado;
}
