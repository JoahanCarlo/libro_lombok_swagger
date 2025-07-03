package com.libro_swagger.service;

import com.libro_swagger.dtoEntrada.LibroRequest;
import com.libro_swagger.dtoSalida.LibroResponse;
import com.libro_swagger.mapper.LibroMapper;
import com.libro_swagger.model.Autor;
import com.libro_swagger.model.Editorial;
import com.libro_swagger.model.Libro;
import com.libro_swagger.repository.AutorRepository;
import com.libro_swagger.repository.EditorialRepository;
import com.libro_swagger.repository.LibroRepository;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;
    private final LibroMapper libroMapper;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository, EditorialRepository editorialRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
        this.libroMapper = libroMapper;
    }

    public Page<LibroResponse> listarLibrosPorEditorial(String nombreEditorial,Integer page,Integer size) {
        page = (page != null )? page : 0;
        size = (size != null )? size : 2;
        nombreEditorial = (nombreEditorial != null && !nombreEditorial.isBlank()) ? nombreEditorial : "Editorial Alfaguara";

        Sort sort = Sort.by(
                Sort.Order.asc("nombreLibro"),
                Sort.Order.asc("codigoIsbn"),
                Sort.Order.desc("añoPublicacion"),
                Sort.Order.desc("fechaCreacion"));

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Libro> libros = libroRepository.findByEditorial_NombreEditorial(nombreEditorial,pageable);

        return libros.map(libroMapper::toDTO);
    }

    public LibroResponse crearLibro(LibroRequest libroRequest){
        Autor autor = autorRepository.findById(libroRequest.getAutorId())
                .orElseThrow(()->new RuntimeException("Autor no encontrado con ID : "+libroRequest.getAutorId()+" no existe."));
        Editorial editorial = editorialRepository.findById(libroRequest.getEditorialId())
                .orElseThrow(()->new RuntimeException(("Editorial no encontrado con ID : "+libroRequest.getEditorialId()+" no existe")));

        Libro libro = libroMapper.toEntity(libroRequest);

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEstado(true);
        Libro libroGuardado = libroRepository.save(libro);

        return libroMapper.toDTO(libroGuardado);
    }

    public LibroResponse actualizarLibro(Long id, LibroRequest libroRequest){
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Libro no encontrado con ID : "+id));

        Autor autor = autorRepository.findById(libroRequest.getAutorId())
                .orElseThrow(()->new RuntimeException("Autor no encontrado con ID : "+libroRequest.getAutorId()+" no existe."));
        Editorial editorial = editorialRepository.findById(libroRequest.getEditorialId())
                .orElseThrow(()->new RuntimeException(("Editorial no encontrado con ID : "+libroRequest.getEditorialId()+" no existe")));

        libroExistente.setNombreLibro(libroRequest.getNombreLibro());
        libroExistente.setCodigoIsbn(libroRequest.getCodigoIsbn());
        libroExistente.setAñoPublicacion(libroRequest.getAñoPublicacion());
        libroExistente.setAutor(autor);
        libroExistente.setEditorial(editorial);

        Libro libroGuardado = libroRepository.save(libroExistente);

        return libroMapper.toDTO(libroGuardado);
    }

    public List<LibroResponse> obtenerLibrosPorEditorialNombre(String nombreEditorial){
        Editorial editorial = editorialRepository.findByNombreEditorial(nombreEditorial)
                .orElseThrow(()->new RuntimeException("Editorial no encontrada por nombre : "+nombreEditorial));
        return libroRepository.findByEditorialId(editorial.getId()).stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void eliminarLibro(Long id){
        Libro libro = libroRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Libro con el ID" +id+ "  no existe"));
        libro.setEstado(false);
        libroRepository.save(libro);
    }

}
