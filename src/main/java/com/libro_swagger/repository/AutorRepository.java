package com.libro_swagger.repository;

import com.libro_swagger.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    List<Autor> findByEstadoTrue();
}
