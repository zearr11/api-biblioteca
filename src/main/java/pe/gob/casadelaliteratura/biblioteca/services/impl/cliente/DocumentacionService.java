package pe.gob.casadelaliteratura.biblioteca.services.impl.cliente;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ValidacionException;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Documentacion;
import pe.gob.casadelaliteratura.biblioteca.repositories.cliente.DocumentacionRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente.IDocumentacionService;
import pe.gob.casadelaliteratura.biblioteca.utils.validations.Validaciones;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentacionService implements IDocumentacionService {

    private final DocumentacionRepository documentacionRepository;

    public DocumentacionService(DocumentacionRepository documentacionRepository) {
        this.documentacionRepository = documentacionRepository;
    }

    @Override
    public List<Documentacion> getAll() {
        return documentacionRepository.findAll();
    }

    @Override
    public Documentacion getById(Long id) {
        return documentacionRepository.findById(id).orElse(null);
    }

    @Override
    public Documentacion saveOrUpdate(Documentacion entity) {
        return documentacionRepository.save(entity);
    }

    @Override
    public Documentacion createModel(MultipartFile imgDocIden, MultipartFile imgRecServ) {

        Validaciones.validarImagen(imgDocIden, "La imagen del documento de identidad");
        Validaciones.validarImagen(imgRecServ, "La imagen del recibo de servicio");

        Documentacion documentacion;
        try {
            documentacion = Documentacion.builder()
                    .imgRecServicio(imgRecServ.getBytes())
                    .imgDocIdentidad(imgDocIden.getBytes())
                    .build();
        }
        catch (IOException ex) {
            throw new ValidacionException("No se pudieron leer los archivos de imagenes");
        }
        return documentacion;

    }

}
