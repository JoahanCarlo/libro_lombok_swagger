package com.libro_swagger.mapper;

import com.libro_swagger.dtoEntrada.AutorRequest;
import com.libro_swagger.dtoSalida.AutorResponse;
import com.libro_swagger.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    Autor toEntity(AutorRequest autorRequest);

    AutorResponse toDTO(Autor autor);
}
