package pe.gob.casadelaliteratura.biblioteca.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.casadelaliteratura.biblioteca.dtos.cliente.ClienteRequest;
import pe.gob.casadelaliteratura.biblioteca.dtos.cliente.ClienteResponse;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ValidacionException;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Cliente;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente.IClienteService;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.cliente.IDocumentacionService;
import pe.gob.casadelaliteratura.biblioteca.utils.converts.ClienteConvert;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final IClienteService clienteService;
    private final IDocumentacionService documentacionService;
    private final Validator validator;

    public ClienteController(IClienteService clienteService,
                             IDocumentacionService documentacionService, Validator validator) {
        this.clienteService = clienteService;
        this.documentacionService = documentacionService;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obtenerClientes() {
        return ResponseEntity.ok(
                    clienteService.getAll().stream()
                            .map(ClienteConvert::clienteModelToResponse).toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerClientePorID(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        return ResponseEntity.ok(
                ClienteConvert.clienteModelToResponse(cliente)
        );

    }

    @PostMapping
    public ResponseEntity<ClienteResponse> registrarCliente(@RequestPart ClienteRequest clienteRequest,
                                                            @RequestPart MultipartFile imgDocIdentidad,
                                                            @RequestPart MultipartFile imgRecServicio) {
        Set<ConstraintViolation<ClienteRequest>>
                errores = validator.validate(clienteRequest);
        if (!errores.isEmpty())
            throw new ConstraintViolationException(errores);

        Cliente cliente = clienteService.saveOrUpdate(
            ClienteConvert.clienteRequestToClienteModel(clienteRequest,
                    documentacionService.createModel(imgDocIdentidad, imgRecServicio)
            )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ClienteConvert.clienteModelToResponse(cliente)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(@PathVariable Long id,
                                                             @RequestPart ClienteRequest clienteRequest,
                                                             @RequestPart MultipartFile imgDocIdentidad,
                                                             @RequestPart MultipartFile imgRecServicio) {
        Set<ConstraintViolation<ClienteRequest>>
                errores = validator.validate(clienteRequest);
        if (!errores.isEmpty())
            throw new ConstraintViolationException(errores);
        if (id == null)
            throw new ValidacionException("Se debe especificar el id.");

        Cliente clienteToUpdate = clienteService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK).body(
                        ClienteConvert.clienteModelToResponse(clienteService.saveOrUpdate(
                                ClienteConvert.setClienteRequestToCliente(clienteRequest, clienteToUpdate,
                                        documentacionService.createModel(imgDocIdentidad, imgRecServicio))
                                )
                        )
                );
    }
}
