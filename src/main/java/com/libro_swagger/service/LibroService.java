package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.LibroRequest;
import com.libro_swagger.dtoSalida.LibroResponse;
import com.libro_swagger.model.Autor;
import com.libro_swagger.model.Editorial;
import com.libro_swagger.model.Libro;
import com.libro_swagger.repository.AutorRepository;
import com.libro_swagger.repository.EditorialRepository;
import com.libro_swagger.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository, EditorialRepository editorialRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
    }

    private LibroResponse mapToResponse(Libro libro){
        String nombreAutor = autorRepository.findById(libro.getAutorId())
                .map(Autor::getNombreAutor)
                .orElse("Desconocido");
        String nombreEditorial = editorialRepository.findById(libro.getEditorialId())
                .map(Editorial::getNombreEditorial)
                .orElse("Desconocido");
        return new LibroResponse(
                libro.getId(),
                libro.getNombreLibro(),
                libro.getCodigoIsbn(),
                libro.getAñoPublicacion(),
                nombreAutor,
                nombreEditorial,
                libro.getFechaCreacion()
        );
    }

    public LibroResponse crearLibro(LibroRequest libroRequest){
        if (!autorRepository.existsById(libroRequest.getAutorId())){
            throw new IllegalArgumentException("El autor no existe");
        }
        if (!editorialRepository.existsById(libroRequest.getEditorialId())){
            throw new IllegalArgumentException("El editorial no existe");
        }
        Libro libro = new Libro(null,libroRequest.getNombreLibro(),
                                     libroRequest.getCodigoIsbn(),
                                     libroRequest.getAñoPublicacion(),
                                     libroRequest.getAutorId(),
                                     libroRequest.getEditorialId(),
                                     LocalDateTime.now());
        Libro guardado = libroRepository.save(libro);
        return mapToResponse(guardado);
    }
    public List<LibroResponse> listarLibros(){
        return libroRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public LibroResponse actualizarLibro(Long id,LibroRequest libroRequest){
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("El libro con ID: "+id+" no existe"));
        libroExistente.setNombreLibro(libroRequest.getNombreLibro());
        libroExistente.setCodigoIsbn(libroRequest.getCodigoIsbn());
        libroExistente.setAñoPublicacion(libroRequest.getAñoPublicacion());
        libroExistente.setAutorId(libroRequest.getAutorId());

        Libro libroActualizado = libroRepository.save(libroExistente);
        return mapToResponse(libroActualizado);
    }

    public List<LibroResponse> obtenerLibroPorId(Long id){
        List<Libro> libros = libroRepository.findByEditorialId(id);
        return libros.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
