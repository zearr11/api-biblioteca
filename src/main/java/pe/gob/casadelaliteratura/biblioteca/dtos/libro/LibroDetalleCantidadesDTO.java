package pe.gob.casadelaliteratura.biblioteca.dtos.libro;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LibroDetalleCantidadesDTO {

    private String isbn;
    private String titulo;
    private Integer year;
    private String autor;
    private String editorial;
    private String coleccion;
    private String sala;

    private Integer cantidadCopias;
    private Integer cantidadDisponibles;
    private Integer cantidadPrestados;
    private Integer cantidadSoloLecturaEnSala;

}
