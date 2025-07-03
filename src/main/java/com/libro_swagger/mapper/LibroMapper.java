package com.libro_swagger.mapper;

import com.libro_swagger.dtoEntrada.LibroRequest;
import com.libro_swagger.dtoSalida.LibroResponse;
import com.libro_swagger.model.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    Libro toEntity(LibroRequest libroRequest);
    @Mapping(source = "autor.nombreAutor",target = "nombreAutor")
    @Mapping(source = "editorial.nombreEditorial",target = "nombreEditorial")
    LibroResponse toDTO(Libro libro);
}
