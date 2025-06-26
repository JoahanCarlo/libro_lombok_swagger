package com.libro_swagger.controller;

import com.libro_swagger.dtoEntrada.AutorRequest;
import com.libro_swagger.dtoSalida.AutorResponse;
import com.libro_swagger.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@Tag(name = "Autores", description = "Operaciones relacionadas con el autor")
public class AutorController {
        private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo autor")
    public ResponseEntity<AutorResponse> crearAutor(@Valid @RequestBody AutorRequest autorRequest){
        AutorResponse autorResponse = autorService.crearAutor(autorRequest);
        return  ResponseEntity.status(201).body(autorResponse);
    }

    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarAutores(){
        List<AutorResponse> autorResponses =  autorService.listarAutores();
        return ResponseEntity.ok(autorResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> modificarAutor(@PathVariable Long id,
                                                        @RequestBody AutorRequest autorRequest){
        AutorResponse autorActualizado = autorService.actualizarAutor(id,autorRequest);
        return ResponseEntity.ok(autorActualizado);
    }
}
