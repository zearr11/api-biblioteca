package pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo;

import pe.gob.casadelaliteratura.biblioteca.models.prestamo.Prestamo;
import java.util.List;

public interface IPrestamoService {
    List<Prestamo> getAll();
    Prestamo getById(Long id);
    Prestamo saveOrUpdate(Prestamo entity);
}
