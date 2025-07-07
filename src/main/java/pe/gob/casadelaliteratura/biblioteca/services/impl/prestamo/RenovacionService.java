package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.Renovacion;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.RenovacionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IRenovacionService;
import java.util.List;

@Service
public class RenovacionService implements IRenovacionService {

    private final RenovacionRepository renovacionRepository;

    public RenovacionService(RenovacionRepository renovacionRepository) {
        this.renovacionRepository = renovacionRepository;
    }

    @Override
    public List<Renovacion> getAll() {
        return renovacionRepository.findAll();
    }

    @Override
    public Renovacion getById(Long id) {
        return renovacionRepository.findById(id).orElse(null);
    }

    @Override
    public Renovacion saveOrUpdate(Renovacion entity) {
        return renovacionRepository.save(entity);
    }

}
