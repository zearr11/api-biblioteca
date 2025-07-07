package pe.gob.casadelaliteratura.biblioteca.controllers.libro;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.casadelaliteratura.biblioteca.dtos.AutorDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Autor;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IAutorService;
import pe.gob.casadelaliteratura.biblioteca.utils.converts.AutorConvert;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    private final IAutorService autorService;

    public AutorController(IAutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> obtenerAutores() {
        return ResponseEntity.ok(
                autorService.getAll().stream()
                        .map(AutorConvert::autorModelToResponse).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obtenerAutorPorID(@PathVariable Long id) {
        return ResponseEntity.ok(
                AutorConvert.autorModelToResponse(autorService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<AutorDTO> registrarAutor(@Valid @RequestBody AutorDTO autor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AutorConvert.autorModelToResponse(
                        autorService.saveOrUpdate(AutorConvert.responseToAutorModel(autor)))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> actualizarAutor(@Valid @RequestBody AutorDTO autor,
                                                   @PathVariable Long id) {
        Autor autorToUpdate = autorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AutorConvert.autorModelToResponse(
                        autorService.saveOrUpdate(
                                AutorConvert.setResponseToAutorModel(autor, autorToUpdate))
                ));
    }

}
