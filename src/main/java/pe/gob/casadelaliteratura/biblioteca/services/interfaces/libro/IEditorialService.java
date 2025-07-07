package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.models.libro.Editorial;
import java.util.List;

public interface IEditorialService {
    List<Editorial> getAll();
    Editorial getById(Long id);
    Editorial saveOrUpdate(Editorial entity);
}
