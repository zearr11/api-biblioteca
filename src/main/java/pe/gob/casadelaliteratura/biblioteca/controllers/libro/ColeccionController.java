package pe.gob.casadelaliteratura.biblioteca.controllers.libro;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.casadelaliteratura.biblioteca.dtos.ColeccionDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Coleccion;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IColeccionService;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ISalaService;
import pe.gob.casadelaliteratura.biblioteca.utils.converts.ColeccionConvert;
import java.util.List;

@RestController
@RequestMapping("/api/v1/colecciones")
public class ColeccionController {

    private final IColeccionService coleccionService;
    private final ISalaService salaService;

    public ColeccionController(IColeccionService coleccionService, ISalaService salaService) {
        this.coleccionService = coleccionService;
        this.salaService = salaService;
    }

    @GetMapping
    public ResponseEntity<List<ColeccionDTO>> obtenerColecciones(@RequestParam(defaultValue = "", required = false)
                                                                     String nombreSala) {
        List<Coleccion> listaColecciones;
        if (!nombreSala.isEmpty())
            listaColecciones = coleccionService
                    .getBySala(salaService.getByNombreSala(nombreSala));
        else
            listaColecciones = coleccionService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaColecciones
                        .stream().map(ColeccionConvert::coleccionModelToResponse)
                        .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColeccionDTO> obtenerColeccionPorID(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ColeccionConvert.coleccionModelToResponse(
                        coleccionService.getById(id)
                ));
    }

    @PostMapping
    public ResponseEntity<ColeccionDTO> registrarColeccion(@Valid @RequestBody
                                                               ColeccionDTO coleccion) {
        Coleccion coleccionToInsert = ColeccionConvert
                .responseToColeccionModel(coleccion, salaService.getByNombreSala(coleccion.getNombreSala()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ColeccionConvert.coleccionModelToResponse(
                        coleccionService.saveOrUpdate(coleccionToInsert))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColeccionDTO> actualizarColeccion(@PathVariable Integer id,
                                                            @Valid @RequestBody ColeccionDTO coleccion) {

        Sala salaBusqueda = salaService.getByNombreSala(coleccion.getNombreSala());
        Coleccion coleccionToUpdate = ColeccionConvert.setResponseToColeccionModel
                (coleccion, coleccionService.getById(id), salaBusqueda);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ColeccionConvert.coleccionModelToResponse(
                        coleccionService.saveOrUpdate(coleccionToUpdate))
                );
    }
}
