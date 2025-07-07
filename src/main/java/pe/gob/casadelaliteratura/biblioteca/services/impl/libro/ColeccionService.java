package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ResourceNotFoundException;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Coleccion;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.ColeccionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IColeccionService;
import java.util.List;

@Service
public class ColeccionService implements IColeccionService {

    private final ColeccionRepository coleccionRepository;

    public ColeccionService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    @Override
    public List<Coleccion> getAll() {
        return coleccionRepository.findAll();
    }

    @Override
    public List<Coleccion> getBySala(Sala sala) {
        return coleccionRepository.findBySala(sala)
                .orElseThrow(() -> new ResourceNotFoundException
                                ("No se encontraron colecciones en " + sala.getNombreSala())
                );
    }

    @Override
    public Coleccion getById(Integer id) {
        return coleccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Coleccion con id = " + id + " no existe.")
        );
    }

    @Override
    public Coleccion saveOrUpdate(Coleccion entity) {
        return coleccionRepository.save(entity);
    }

}
