package pe.gob.casadelaliteratura.biblioteca.controllers.libro;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.casadelaliteratura.biblioteca.dtos.SalaDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ISalaService;
import pe.gob.casadelaliteratura.biblioteca.utils.converts.SalaConvert;
import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {

    private final ISalaService salaService;

    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> obtenerSalas() {
        return ResponseEntity.ok(
                salaService.getAll().stream()
                        .map(SalaConvert::salaModelToResponse).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> obtenerSalaPorID(@PathVariable Integer id) {
        return ResponseEntity.ok(
                SalaConvert.salaModelToResponse(salaService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<SalaDTO> registrarSala(@Valid @RequestBody SalaDTO sala) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SalaConvert.salaModelToResponse(
                        salaService.saveOrUpdate(SalaConvert.responseToSalaModel(sala)))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> actualizarSala(@Valid @RequestBody SalaDTO sala,
                                                  @PathVariable Integer id) {
        Sala salaToUpdate = salaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(SalaConvert.salaModelToResponse(
                        salaService.saveOrUpdate(
                                SalaConvert.setResponseToSalaModel(sala, salaToUpdate))
                ));
    }
}
