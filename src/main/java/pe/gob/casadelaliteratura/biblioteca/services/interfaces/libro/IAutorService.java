package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.models.libro.Autor;
import java.util.List;

public interface IAutorService {
    List<Autor> getAll();
    Autor getById(Long id);
    Autor saveOrUpdate(Autor entity);
}
