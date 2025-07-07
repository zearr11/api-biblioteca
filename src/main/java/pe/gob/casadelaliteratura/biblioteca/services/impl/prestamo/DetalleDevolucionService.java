package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.DetalleDevolucion;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.DetalleDevolucionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IDetalleDevolucionService;
import java.util.List;

@Service
public class DetalleDevolucionService implements IDetalleDevolucionService {

    private final DetalleDevolucionRepository detalleDevolucionRepository;

    public DetalleDevolucionService(DetalleDevolucionRepository detalleDevolucionRepository) {
        this.detalleDevolucionRepository = detalleDevolucionRepository;
    }

    @Override
    public List<DetalleDevolucion> getAll() {
        return detalleDevolucionRepository.findAll();
    }

    @Override
    public DetalleDevolucion getById(Long id) {
        return detalleDevolucionRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleDevolucion saveOrUpdate(DetalleDevolucion entity) {
        return detalleDevolucionRepository.save(entity);
    }
}
