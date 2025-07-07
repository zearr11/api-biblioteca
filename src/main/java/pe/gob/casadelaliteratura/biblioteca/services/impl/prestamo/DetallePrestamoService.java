package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.DetallePrestamo;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.DetallePrestamoRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IDetallePrestamoService;
import java.util.List;

@Service
public class DetallePrestamoService implements IDetallePrestamoService {

    private final DetallePrestamoRepository detallePrestamoRepository;

    public DetallePrestamoService(DetallePrestamoRepository detallePrestamoRepository) {
        this.detallePrestamoRepository = detallePrestamoRepository;
    }

    @Override
    public List<DetallePrestamo> getAll() {
        return detallePrestamoRepository.findAll();
    }

    @Override
    public DetallePrestamo getById(Long id) {
        return detallePrestamoRepository.findById(id).orElse(null);
    }

    @Override
    public DetallePrestamo saveOrUpdate(DetallePrestamo entity) {
        return detallePrestamoRepository.save(entity);
    }

}
