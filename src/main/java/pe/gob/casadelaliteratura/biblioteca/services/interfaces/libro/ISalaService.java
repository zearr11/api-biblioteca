package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;
import java.util.List;

public interface ISalaService {
    List<Sala> getAll();
    Sala getById(Integer id);
    Sala saveOrUpdate(Sala entity);
    Sala getByNombreSala(String nombreSala);
}
