package pe.gob.casadelaliteratura.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AutorDTO {

    @NotBlank(message = "El nombre del autor no puede estar vacía")
    @Size(max = 100, message = "El nombre del autor no puede superar los 100 caracteres")
    private String autor;

    @NotBlank(message = "El campo nacionalidad no puede estar vacío")
    @Size(max = 100, message = "El campo nacionalidad no puede superar los 100 caracteres")
    private String nacionalidad;
}
