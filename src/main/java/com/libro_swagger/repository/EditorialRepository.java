package com.libro_swagger.repository;

import com.libro_swagger.model.Editorial;
import com.libro_swagger.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long> {
    Optional<Editorial> findByNombreEditorial(String nombreEditorial);
    List<Editorial> findByEstadoTrue();
    boolean existsByNombreEditorial(String nombreEditorial);
}
