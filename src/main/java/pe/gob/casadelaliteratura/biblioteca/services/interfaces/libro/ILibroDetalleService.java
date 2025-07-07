package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.models.libro.LibroDetalle;
import java.util.List;

public interface ILibroDetalleService {
    List<LibroDetalle> getAll();
    LibroDetalle getById(Long id);
    LibroDetalle saveOrUpdate(LibroDetalle entity);
}
