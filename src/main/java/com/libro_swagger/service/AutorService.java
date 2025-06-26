package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.AutorRequest;
import com.libro_swagger.dtoSalida.AutorResponse;
import com.libro_swagger.model.Autor;
import com.libro_swagger.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    private AutorResponse mapToResponse(Autor autor){
        return new AutorResponse(
                autor.getId(),
                autor.getNombreAutor(),
                autor.getDireccionAutor(),
                autor.getCorreoAutor()
        );
    }
    public AutorResponse crearAutor(AutorRequest autorRequest){
        Autor autor = Autor.builder()
                .nombreAutor(autorRequest.getNombreAutor())
                .direccionAutor(autorRequest.getDireccionAutor())
                .correoAutor(autorRequest.getCorreoAutor())
                .build();

        Autor autorGuardado = autorRepository.save(autor);
        return mapToResponse(autorGuardado);
    }

    public List<AutorResponse> listarAutores(){
        return autorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AutorResponse actualizarAutor(Long id, AutorRequest autorRequest){
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("El autor con ID "+id+" no existe"));
        autorExistente.setNombreAutor(autorRequest.getNombreAutor());
        autorExistente.setDireccionAutor(autorRequest.getDireccionAutor());
        autorExistente.setCorreoAutor(autorRequest.getCorreoAutor());

        Autor autorAcutalizado = autorRepository.save(autorExistente);
        return mapToResponse(autorAcutalizado);
    }
}
