package pe.gob.casadelaliteratura.biblioteca.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.TipoDoc;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede superar los 100 caracteres")
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo no puede estar vacío")
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    @Email(message = "El formato del correo no es válido")
    private String correo;

    @NotBlank(message = "El número principal no puede estar vacío")
    @Size(min = 9, max = 9, message = "El número principal debe tener exactamente 9 dígitos")
    private String numeroPrincipal;

    @NotBlank(message = "El número secundario no puede estar vacío")
    @Size(min = 9, max = 9, message = "El número secundario debe tener exactamente 9 dígitos")
    private String numeroSecundario;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    private String direccion;

    @NotNull(message = "Debe indicar un tipo de documento (DNI o CE)")
    private TipoDoc tipoDoc;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(max = 20, message = "El número de documento no puede superar los 20 caracteres")
    private String numeroDoc;

}
