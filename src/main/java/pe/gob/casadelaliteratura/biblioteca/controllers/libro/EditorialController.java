package pe.gob.casadelaliteratura.biblioteca.controllers.libro;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.casadelaliteratura.biblioteca.dtos.EditorialDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Editorial;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IEditorialService;
import pe.gob.casadelaliteratura.biblioteca.utils.converts.EditorialConvert;

import java.util.List;

@RestController
@RequestMapping("/api/v1/editoriales")
public class EditorialController {

    private final IEditorialService editorialService;

    public EditorialController(IEditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public ResponseEntity<List<EditorialDTO>> obtenerEditoriales() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(editorialService.getAll()
                        .stream().map(EditorialConvert::editorialModelToResponse)
                        .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialDTO> obtenerEditorialPorID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(EditorialConvert.editorialModelToResponse(
                        editorialService.getById(id)
                ));
    }

    @PostMapping
    public ResponseEntity<EditorialDTO> registrarEditorial(@Valid @RequestBody EditorialDTO editorial) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EditorialConvert.editorialModelToResponse(
                        editorialService.saveOrUpdate(EditorialConvert.responseToEditorialModel(editorial)))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditorialDTO> actualizarEditorial(@PathVariable Long id,
                                                            @Valid @RequestBody EditorialDTO editorial) {
        Editorial editorialToUpdate = editorialService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(EditorialConvert.editorialModelToResponse(
                        editorialService.saveOrUpdate(
                                EditorialConvert.setResponseToEditorialModel(editorial, editorialToUpdate))
                ));
    }

}
