package pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro;

import pe.gob.casadelaliteratura.biblioteca.models.libro.Coleccion;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;

import java.util.List;

public interface IColeccionService {
    List<Coleccion> getAll();
    List<Coleccion> getBySala(Sala sala);
    Coleccion getById(Integer id);
    Coleccion saveOrUpdate(Coleccion entity);
}
