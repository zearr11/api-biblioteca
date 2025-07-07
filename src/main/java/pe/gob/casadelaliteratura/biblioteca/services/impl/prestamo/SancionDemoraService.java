package pe.gob.casadelaliteratura.biblioteca.services.impl.prestamo;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.prestamo.SancionDemora;
import pe.gob.casadelaliteratura.biblioteca.repositories.prestamo.SancionDemoraRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.prestamo.ISancionDemoraService;
import java.util.List;

@Service
public class SancionDemoraService implements ISancionDemoraService {

    private final SancionDemoraRepository sancionDemoraRepository;

    public SancionDemoraService(SancionDemoraRepository sancionDemoraRepository) {
        this.sancionDemoraRepository = sancionDemoraRepository;
    }

    @Override
    public List<SancionDemora> getAll() {
        return sancionDemoraRepository.findAll();
    }

    @Override
    public SancionDemora getById(Long id) {
        return sancionDemoraRepository.findById(id).orElse(null);
    }

    @Override
    public SancionDemora saveOrUpdate(SancionDemora entity) {
        return sancionDemoraRepository.save(entity);
    }

}
