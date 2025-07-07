package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.SolucionDevolucion;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.SolucionDevolucionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.ISolucionDevolucionService;
import java.util.List;

@Service
public class SolucionDevolucionService implements ISolucionDevolucionService {

    private final SolucionDevolucionRepository solucionDevolucionRepository;

    public SolucionDevolucionService(SolucionDevolucionRepository solucionDevolucionRepository) {
        this.solucionDevolucionRepository = solucionDevolucionRepository;
    }

    @Override
    public List<SolucionDevolucion> getAll() {
        return solucionDevolucionRepository.findAll();
    }

    @Override
    public SolucionDevolucion getById(Long id) {
        return solucionDevolucionRepository.findById(id).orElse(null);
    }

    @Override
    public SolucionDevolucion saveOrUpdate(SolucionDevolucion entity) {
        return solucionDevolucionRepository.save(entity);
    }
}
