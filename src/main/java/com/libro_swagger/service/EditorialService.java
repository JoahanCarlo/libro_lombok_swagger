package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoSalida.EditorialResponse;
import com.libro_swagger.mapper.EditorialMapper;
import com.libro_swagger.model.Editorial;
import com.libro_swagger.repository.EditorialRepository;
import com.libro_swagger.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditorialService {
    private final EditorialRepository editorialRepository;
    private final EditorialMapper editorialMapper;
    private final LibroRepository libroRepository;

    public EditorialService(EditorialRepository editorialRepository, EditorialMapper editorialMapper, LibroRepository libroRepository) {
        this.editorialRepository = editorialRepository;
        this.editorialMapper = editorialMapper;
        this.libroRepository = libroRepository;
    }


    public EditorialResponse crearEditorial(EditorialRequest editorialRequest){
        boolean existe = editorialRepository.existsByNombreEditorial(editorialRequest.getNombreEditorial());
        if (existe){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Ya existe el editorial con el nombre : "+editorialRequest.getNombreEditorial()
            );
        }
        Editorial editorial = editorialMapper.toEntity(editorialRequest);
        editorial.setEstado(true);
        Editorial editorialGuardado = editorialRepository.save(editorial);
        return editorialMapper.toDTO(editorialGuardado);
    }

    public List<EditorialResponse> listarEditorial(){
        List<Editorial> editoriales = editorialRepository.findByEstadoTrue();
        return editoriales.stream()
                .map(editorialMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EditorialResponse actualizarEditorial(Long id, EditorialRequest editorialRequest) {
        Editorial editorialExistente = editorialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Editorial con el ID : "+id+" no encontrado."));
        Editorial editorialActualizado = editorialMapper.toEntity(editorialRequest);
        editorialExistente.setNombreEditorial(editorialActualizado.getNombreEditorial());
        editorialExistente.setNombreDireccion(editorialActualizado.getNombreDireccion());
        editorialExistente.setNombreCorreo(editorialActualizado.getNombreCorreo());
        Editorial editorialGuardado = editorialRepository.save(editorialExistente);
        return editorialMapper.toDTO(editorialGuardado);
    }

    public void eliminarEditorial(Long id){
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Editorial con el ID" +id+ "  no existe"));
        long cantidadLibros = libroRepository.countByEditorialId(id);

        if (cantidadLibros == 0){
            editorial.setEstado(false);
            editorialRepository.save(editorial);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede eliminar: La editorial tiene libros registrados");
        }
    }
}