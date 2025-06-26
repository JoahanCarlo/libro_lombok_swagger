package com.libro_swagger.controller;

import com.libro_swagger.dtoEntrada.EditorialRequest;
import com.libro_swagger.dtoSalida.EditorialResponse;
import com.libro_swagger.model.Editorial;
import com.libro_swagger.service.EditorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
@Tag(name = "Editoriales", description = "API para registro de editoriales")
public class EditorialController {
    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @Operation(summary = "Crear una nueva editorial")
    @PostMapping
    public ResponseEntity<EditorialResponse> crearEditorial(@RequestBody EditorialRequest editorialRequest){
        EditorialResponse editorialCreada = editorialService.crearEditorial(editorialRequest);
        return new ResponseEntity<>(editorialCreada,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EditorialResponse>> listarEditoriales(){
        List<EditorialResponse> editorialResponses = editorialService.listarEditorial();
        return ResponseEntity.ok(editorialResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditorialResponse> actualizarEditorial(@PathVariable Long id,
                                                                 @RequestBody EditorialRequest editorialRequest){
        EditorialResponse actualizarEditorial = editorialService.actualizarEditorial(id,editorialRequest);
        return ResponseEntity.ok(actualizarEditorial);
    }

}
