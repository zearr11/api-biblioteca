package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.libro.LibroDetalle;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.LibroDetalleRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ILibroDetalleService;
import java.util.List;

@Service
public class LibroDetalleService implements ILibroDetalleService {

    private final LibroDetalleRepository libroDetalleRepository;

    public LibroDetalleService(LibroDetalleRepository libroDetalleRepository) {
        this.libroDetalleRepository = libroDetalleRepository;
    }

    @Override
    public List<LibroDetalle> getAll() {
        return libroDetalleRepository.findAll();
    }

    @Override
    public LibroDetalle getById(Long id) {
        return libroDetalleRepository.findById(id).orElse(null);
    }

    @Override
    public LibroDetalle saveOrUpdate(LibroDetalle entity) {
        return libroDetalleRepository.save(entity);
    }

}
