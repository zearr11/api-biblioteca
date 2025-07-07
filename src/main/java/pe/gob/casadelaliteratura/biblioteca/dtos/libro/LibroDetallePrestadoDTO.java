package pe.gob.casadelaliteratura.biblioteca.dtos.libro;

import lombok.*;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.EstadoLibro;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LibroDetallePrestadoDTO implements LibroDetalleDTO {

    private String isbn;
    private String titulo;
    private Integer year;
    private String autor;
    private String editorial;
    private Integer numeroCopia;
    private String coleccion;
    private String sala;
    private EstadoLibro estado;
    private LocalDate fechaVencimiento;

}
