package com.libro_swagger.controller;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoEntrada.LibroRequest;
import com.libro_swagger.dtoSalida.LibroResponse;
import com.libro_swagger.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones relacionadas con los libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LibroResponse> crearLibro(@Valid @RequestBody LibroRequest libroRequest){
        LibroResponse libroResponse = libroService.crearLibro(libroRequest);
        return new ResponseEntity<>(libroResponse,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LibroResponse>> listarLibros(){
        List<LibroResponse> libroResponses = libroService.listarLibros();
        return ResponseEntity.ok(libroResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizarLibros(@PathVariable Long id,
                                                          @RequestBody LibroRequest libroRequest){
        LibroResponse actualizarLibro = libroService.actualizarLibro(id,libroRequest);
        return ResponseEntity.ok(actualizarLibro);
    }

    @Operation(summary = "Obtener libros por editorial")
    @ApiResponse(responseCode = "200", description = "Lista de libros por editorial")
    @GetMapping("/editorial/{id}")
    public ResponseEntity<List<LibroResponse>> obtenerLibrosPorEditorial(
            @PathVariable Long id){
        List<LibroResponse> libros = libroService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libros);
    }
}
