package com.libro_swagger.controller;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoEntrada.LibroRequest;
import com.libro_swagger.dtoSalida.LibroResponse;
import com.libro_swagger.mapper.LibroMapper;
import com.libro_swagger.service.LibroService;
import com.libro_swagger.config.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones relacionadas con los libros")
public class LibroController {
    private final LibroService libroService;
    private final LibroMapper libroMapper;

    public LibroController(LibroService libroService, LibroMapper libroMapper) {
        this.libroService = libroService;
        this.libroMapper = libroMapper;
    }

    @Operation(summary = "Crear libros")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LibroResponse> crearLibro(@Valid @RequestBody LibroRequest libroRequest){
        LibroResponse libroResponse = libroService.crearLibro(libroRequest);
        return new ResponseEntity<>(libroResponse,HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar los libros por id")
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizarLibros(@PathVariable Long id,
                                                          @RequestBody LibroRequest libroRequest){
        LibroResponse actualizarLibro = libroService.actualizarLibro(id,libroRequest);
        return ResponseEntity.ok(actualizarLibro);
    }

    @Operation(summary = "Obtener libros por editorial")
    @GetMapping("/editorial/{nombreEditorial}")
    public ResponseEntity<List<LibroResponse>> obtenerLibrosPorEditorial(
            @PathVariable String nombreEditorial){
        List<LibroResponse> libros = libroService.obtenerLibrosPorEditorialNombre(nombreEditorial);
        return ResponseEntity.ok(libros);
    }

    @Operation(summary = "Lista de libros por páginas")
    @GetMapping("/por-editorial")
    public Page<LibroResponse> listarLibros(
            @RequestParam(required = false) String nombreEditorial,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        return libroService.listarLibrosPorEditorial(nombreEditorial,page,size);
    }

    @PatchMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar los libros registrados")
    public ResponseEntity<ApiResponse> eliminarLibro(@PathVariable Long id){
        libroService.eliminarLibro(id);
        return ResponseEntity.ok(new ApiResponse("Libro eliminado correctamente",HttpStatus.OK));
    }
}
