package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ResourceNotFoundException;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.SalaRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ISalaService;
import java.util.List;

@Service
public class SalaService implements ISalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public List<Sala> getAll() {
        return salaRepository.findAll();
    }

    @Override
    public Sala getById(Integer id) {
        return salaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sala con id = " + id + " no existe.")
        );
    }

    @Override
    public Sala saveOrUpdate(Sala entity) {
        return salaRepository.save(entity);
    }

    @Override
    public Sala getByNombreSala(String nombreSala) {
        return salaRepository.findByNombreSala(nombreSala)
                .orElseThrow(()-> new ResourceNotFoundException("La sala " + nombreSala + " no existe."));
    }

}
