package pe.gob.casadelaliteratura.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditorialDTO {

    @NotBlank(message = "El nombre de la editorial no puede estar vacía")
    @Size(max = 100, message = "El nombre de la editorial no puede superar los 100 caracteres")
    private String editorial;

}
