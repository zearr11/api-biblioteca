package pe.gob.casadelaliteratura.biblioteca.dtos.cliente;

import lombok.*;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.TipoDoc;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correo;
    private String numeroPrincipal;
    private String numeroSecundario;
    private String direccion;
    private TipoDoc tipoDoc;
    private String numeroDoc;

    private byte[] imgDocIdentidad;
    private byte[] imgRecServicio;

}
