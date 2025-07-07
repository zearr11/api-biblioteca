package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.Devolucion;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.DevolucionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IDevolucionService;
import java.util.List;

@Service
public class DevolucionService implements IDevolucionService {

    private final DevolucionRepository devolucionRepository;

    public DevolucionService(DevolucionRepository devolucionRepository) {
        this.devolucionRepository = devolucionRepository;
    }

    @Override
    public List<Devolucion> getAll() {
        return devolucionRepository.findAll();
    }

    @Override
    public Devolucion getById(Long id) {
        return devolucionRepository.findById(id).orElse(null);
    }

    @Override
    public Devolucion saveOrUpdate(Devolucion entity) {
        return devolucionRepository.save(entity);
    }

}
