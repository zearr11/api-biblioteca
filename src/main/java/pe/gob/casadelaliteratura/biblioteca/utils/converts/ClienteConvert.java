package pe.gob.casadelaliteratura.biblioteca.utils.converts;

import pe.gob.casadelaliteratura.biblioteca.dtos.cliente.ClienteRequest;
import pe.gob.casadelaliteratura.biblioteca.dtos.cliente.ClienteResponse;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Cliente;
import pe.gob.casadelaliteratura.biblioteca.models.cliente.Documentacion;

public class ClienteConvert {

    public static ClienteResponse clienteModelToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .correo(cliente.getCorreo())
                .numeroPrincipal(cliente.getNumeroPrincipal())
                .numeroSecundario(cliente.getNumeroSecundario())
                .direccion(cliente.getDireccion())
                .tipoDoc(cliente.getTipoDoc())
                .numeroDoc(cliente.getNumeroDoc())
                .imgDocIdentidad(cliente.getDocumentacion().getImgDocIdentidad())
                .imgRecServicio(cliente.getDocumentacion().getImgRecServicio())
                .build();
    }

    public static Cliente clienteRequestToClienteModel(ClienteRequest request, Documentacion nuevaDoc) {
        return Cliente.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .correo(request.getCorreo())
                .numeroPrincipal(request.getNumeroPrincipal())
                .numeroSecundario(request.getNumeroSecundario())
                .direccion(request.getDireccion())
                .tipoDoc(request.getTipoDoc())
                .numeroDoc(request.getNumeroDoc())
                .documentacion(nuevaDoc)
                .build();
    }

    public static Cliente setClienteRequestToCliente(ClienteRequest clienteRequest,
                                                     Cliente clienteToUpdate,
                                                     Documentacion documentacion) {

        clienteToUpdate.setNombres(clienteRequest.getNombres());
        clienteToUpdate.setApellidos(clienteRequest.getApellidos());
        clienteToUpdate.setFechaNacimiento(clienteRequest.getFechaNacimiento());
        clienteToUpdate.setCorreo(clienteRequest.getCorreo());
        clienteToUpdate.setNumeroPrincipal(clienteRequest.getNumeroPrincipal());
        clienteToUpdate.setNumeroSecundario(clienteRequest.getNumeroSecundario());
        clienteToUpdate.setDireccion(clienteRequest.getDireccion());
        clienteToUpdate.setTipoDoc(clienteRequest.getTipoDoc());
        clienteToUpdate.setNumeroDoc(clienteRequest.getNumeroDoc());
        clienteToUpdate.getDocumentacion().setImgDocIdentidad(documentacion.getImgDocIdentidad());
        clienteToUpdate.getDocumentacion().setImgRecServicio(documentacion.getImgRecServicio());

        return clienteToUpdate;
    }
}
