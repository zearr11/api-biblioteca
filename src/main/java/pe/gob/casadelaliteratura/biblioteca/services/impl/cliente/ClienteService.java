package pe.gob.casadelaliteratura.biblioteca.services.impl.cliente;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ResourceNotFoundException;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ValidacionException;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Cliente;
import pe.gob.casadelaliteratura.biblioteca.repositories.cliente.ClienteRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente.IClienteService;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente.IDocumentacionService;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final IDocumentacionService documentacionService;

    public ClienteService(ClienteRepository clienteRepository, IDocumentacionService documentacionService) {
        this.clienteRepository = clienteRepository;
        this.documentacionService = documentacionService;
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Cliente con id = " + id + " no existe.")
        );
    }

    @Override
    public Cliente saveOrUpdate(Cliente entity) {
        Cliente existente = clienteRepository
                .findByNumeroDoc(entity.getNumeroDoc())
                .orElse(null);

        if (existente != null && entity.getIdCliente() == null)
            throw new ValidacionException("Ya existe un cliente con ese número de documento.");

        if (existente != null
                && entity.getIdCliente() != null
                && !entity.getIdCliente().equals(existente.getIdCliente()))
            throw new ValidacionException("Número de documento ya registrado por otro cliente.");

        entity.setDocumentacion(documentacionService.saveOrUpdate(entity.getDocumentacion()));
        return clienteRepository.save(entity);
    }

    @Override
    public Cliente getByNumeroDoc(String numeroDoc) {
        return clienteRepository.findByNumeroDoc(numeroDoc)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Cliente con numero de documento = " + numeroDoc + " no existe.")
                );
    }

}
