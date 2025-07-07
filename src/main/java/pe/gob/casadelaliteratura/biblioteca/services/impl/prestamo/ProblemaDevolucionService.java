package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.ProblemaDevolucion;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.ProblemaDevolucionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.IProblemaDevolucionService;
import java.util.List;

@Service
public class ProblemaDevolucionService implements IProblemaDevolucionService {

    private final ProblemaDevolucionRepository problemaDevolucionRepository;

    public ProblemaDevolucionService(ProblemaDevolucionRepository problemaDevolucionRepository) {
        this.problemaDevolucionRepository = problemaDevolucionRepository;
    }

    @Override
    public List<ProblemaDevolucion> getAll() {
        return problemaDevolucionRepository.findAll();
    }

    @Override
    public ProblemaDevolucion getById(Long id) {
        return problemaDevolucionRepository.findById(id).orElse(null);
    }

    @Override
    public ProblemaDevolucion saveOrUpdate(ProblemaDevolucion entity) {
        return problemaDevolucionRepository.save(entity);
    }
}
