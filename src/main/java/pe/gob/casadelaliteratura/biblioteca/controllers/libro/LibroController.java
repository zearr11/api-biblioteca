package pe.gob.casadelaliteratura.biblioteca.controllers.libro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleCantidadesDTO;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleDTO;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ILibroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private final ILibroService libroService;

    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDetalleDTO>>
                    obtenerDetalleLibros(@RequestParam(required = false) String titulo,
                                        @RequestParam(required = false) String isbn,
                                        @RequestParam(required = false) String estado){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libroService.getDetalleLibros(titulo, isbn, estado));
    }

    @GetMapping("/{isbn}/copias/{numeroCopia}")
    public ResponseEntity<LibroDetalleDTO>
                    obtenerDetalleLibroPorIsbnYnumeroCopia(@PathVariable String isbn,
                                                           @PathVariable Integer numeroCopia) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.getDetalleLibroPorIsbnYnumeroCopia(isbn, numeroCopia));
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<LibroDetalleCantidadesDTO>>
                    obtenerDetalleCantidadesLibros(@RequestParam(required = false) String titulo,
                                                   @RequestParam(required = false) String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libroService.getCantidadesLibros(titulo, isbn));
    }

    @GetMapping("/resumen/{isbn}")
    public ResponseEntity<LibroDetalleCantidadesDTO>
                    obtenerDetalleCantidadesLibroPorIsbn(@PathVariable String isbn) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.getCantidadesLibrosPorIsbn(isbn));
    }

}
