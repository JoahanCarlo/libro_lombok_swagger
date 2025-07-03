package com.libro_swagger.mapper;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoSalida.EditorialResponse;
import com.libro_swagger.model.Editorial;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EditorialMapper {
    Editorial toEntity(EditorialRequest editorialRequest);

    EditorialResponse toDTO(Editorial editorial);

}
