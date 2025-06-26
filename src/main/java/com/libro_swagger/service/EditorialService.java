package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoSalida.EditorialResponse;
import com.libro_swagger.model.Editorial;
import com.libro_swagger.repository.EditorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditorialService {
    private final EditorialRepository editorialRepository;

    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    private EditorialResponse mapToResponse(Editorial editorial){
        return new EditorialResponse(
                editorial.getId(),
                editorial.getNombreEditorial(),
                editorial.getNombreDireccion(),
                editorial.getNombreCorreo()
        );
    }

    public EditorialResponse crearEditorial(EditorialRequest editorialRequest){
        Editorial editorial = Editorial.builder()
                .nombreEditorial(editorialRequest.getNombreEditorial())
                .nombreDireccion(editorialRequest.getNombreDireccion())
                .nombreCorreo(editorialRequest.getNombreCorreo())
                .build();
        Editorial editorialGuardado = editorialRepository.save(editorial);
        return mapToResponse(editorialGuardado);
    }

    public List<EditorialResponse> listarEditorial(){
        return editorialRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EditorialResponse actualizarEditorial(Long id,EditorialRequest editorialRequest){
        Editorial editorialExistente = editorialRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("El editorial con ID : "+id+" no existe."));
        editorialExistente.setNombreEditorial(editorialRequest.getNombreEditorial());
        editorialExistente.setNombreDireccion(editorialRequest.getNombreDireccion());
        editorialExistente.setNombreCorreo(editorialRequest.getNombreCorreo());

        Editorial editorialGuardado = editorialRepository.save(editorialExistente);
        return mapToResponse(editorialGuardado);
    }
}