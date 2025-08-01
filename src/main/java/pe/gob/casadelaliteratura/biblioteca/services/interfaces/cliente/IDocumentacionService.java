package pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente;

import org.springframework.web.multipart.MultipartFile;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Documentacion;
import java.util.List;

public interface IDocumentacionService {
    List<Documentacion> getAll();
    Documentacion getById(Long id);
    Documentacion saveOrUpdate(Documentacion entity);
    Documentacion createModel(MultipartFile imgDocIden, MultipartFile imgRecServ);
}
