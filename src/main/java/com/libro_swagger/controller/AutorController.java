package com.libro_swagger.controller;

import com.libro_swagger.dtoEntrada.AutorRequest;
import com.libro_swagger.dtoSalida.AutorResponse;
import com.libro_swagger.service.AutorService;
import com.libro_swagger.config.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Crear un nuevo autor")
    @PostMapping
    public ResponseEntity<AutorResponse> crearAutor(@Valid @RequestBody AutorRequest autorRequest){
        AutorResponse autorResponse = autorService.crearAutor(autorRequest);
        return new ResponseEntity<>(autorResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar autores")
    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarAutores(){
        List<AutorResponse> autorResponses =  autorService.listarAutores();
        return ResponseEntity.ok(autorResponses);
    }

    @Operation(summary = "Modificar autores")
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> modificarAutor(@PathVariable Long id,
                                                        @RequestBody AutorRequest autorRequest){
        AutorResponse autorActualizado = autorService.actualizarAutor(id,autorRequest);
        return ResponseEntity.ok(autorActualizado);
    }

    @Operation(summary = "Eliminar autor")
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse> eliminarautor(@PathVariable Long id){
        autorService.eliminarAutor(id);
        ApiResponse response = new ApiResponse("Autor eliminado correctamente",HttpStatus.OK);
        return ResponseEntity.noContent().build();
    }
}
