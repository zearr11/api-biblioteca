package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Autor;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.AutorRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IAutorService;
import java.util.List;

@Service
public class AutorService implements IAutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor getById(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    @Override
    public Autor saveOrUpdate(Autor entity) {
        return autorRepository.save(entity);
    }
}
