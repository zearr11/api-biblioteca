package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Editorial;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.EditorialRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.IEditorialService;
import java.util.List;

@Service
public class EditorialService implements IEditorialService {

    private final EditorialRepository editorialRepository;

    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @Override
    public List<Editorial> getAll() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial getById(Long id) {
        return editorialRepository.findById(id).orElse(null);
    }

    @Override
    public Editorial saveOrUpdate(Editorial entity) {
        return editorialRepository.save(entity);
    }

}
