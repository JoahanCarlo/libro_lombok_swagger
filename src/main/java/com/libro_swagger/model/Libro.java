package com.libro_swagger.model;

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
    private Long id;

    @NotBlank
    private String nombreLibro;

    @NotBlank
    private String codigoIsbn;

    @NotNull
    private Integer añoPublicacion;

    @Column(name = "autor_id")
    private Long autorId;

    @Column(name = "editorial_id")
    private Long editorialId;

    @CreationTimestamp
    @Column(name = "fecha_creacion",updatable = false)
    private LocalDateTime fechaCreacion;
}
