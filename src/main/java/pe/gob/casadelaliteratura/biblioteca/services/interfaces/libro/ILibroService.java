package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleCantidadesDTO;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Libro;
import java.util.List;

public interface ILibroService {
    List<Libro> getAll();
    Libro getById(Long id);
    Libro saveOrUpdate(Libro entity);
    List<LibroDetalleDTO> getDetalleLibros(String titulo, String isbn, String estado);
    List<LibroDetalleCantidadesDTO> getCantidadesLibros(String titulo, String isbn);
    LibroDetalleDTO getDetalleLibroPorIsbnYnumeroCopia(String isbn, Integer numeroCopia);
    LibroDetalleCantidadesDTO getCantidadesLibrosPorIsbn(String isbn);
}
