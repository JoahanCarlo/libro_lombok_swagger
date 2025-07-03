package com.libro_swagger.repository;

import com.libro_swagger.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Page<Libro> findByEditorial_NombreEditorial(String nombreEditorial, Pageable pageable);

    List<Libro> findByEditorial_NombreEditorial(String nombreEditorial);

    List<Libro> findByEditorialId(Long editorialId);

    List<Libro> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

    long countByEditorialId(Long editorualId);

    long countByAutorId(Long editorualId);
}
