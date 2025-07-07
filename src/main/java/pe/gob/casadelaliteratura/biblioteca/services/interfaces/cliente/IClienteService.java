package pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente;

import pe.gob.casadelaliteratura.biblioteca.models.cliente.Cliente;
import java.util.List;

public interface IClienteService {
    List<Cliente> getAll();
    Cliente getById(Long id);
    Cliente saveOrUpdate(Cliente entity);
    Cliente getByNumeroDoc(String numeroDoc);
}
