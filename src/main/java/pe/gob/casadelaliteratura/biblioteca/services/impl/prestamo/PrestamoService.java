package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.Prestamo;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.PrestamoRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IPrestamoService;
import java.util.List;

@Service
public class PrestamoService implements IPrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public List<Prestamo> getAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo getById(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo saveOrUpdate(Prestamo entity) {
        return prestamoRepository.save(entity);
    }

}
