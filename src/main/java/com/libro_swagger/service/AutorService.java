package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.AutorRequest;
import com.libro_swagger.dtoSalida.AutorResponse;
import com.libro_swagger.mapper.AutorMapper;
import com.libro_swagger.model.Autor;
import com.libro_swagger.repository.AutorRepository;
import com.libro_swagger.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;
    private final LibroRepository libroRepository;

    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
        this.libroRepository = libroRepository;
    }


    public AutorResponse crearAutor(AutorRequest autorRequest){
        Autor autor = autorMapper.toEntity(autorRequest);
        autor.setEstado(true);
        Autor autorGuardado = autorRepository.save(autor);
        return autorMapper.toDTO(autorGuardado);
    }

    public List<AutorResponse> listarAutores(){
        List<Autor> autores = autorRepository.findByEstadoTrue();
        return autores.stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AutorResponse actualizarAutor(Long id,AutorRequest autorRequest){
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Autor con el ID : "+id+" no encontrado"));

        Autor autorActualizado = autorMapper.toEntity(autorRequest);
        autorExistente.setNombreAutor(autorActualizado.getNombreAutor());
        autorExistente.setDireccionAutor(autorActualizado.getDireccionAutor());
        autorExistente.setCorreoAutor(autorActualizado.getCorreoAutor());

        Autor autorGuardado = autorRepository.save(autorExistente);
        return autorMapper.toDTO(autorGuardado);
    }

    public void eliminarAutor(Long id){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Autor con ID: "+id+" no existe."));
        long cantidadLibros = libroRepository.countByAutorId(id);

        if (cantidadLibros == 0){
            autor.setEstado(false);
            autorRepository.save(autor);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede eliminar: El autor tiene libros registrados");
        }
    }

}
