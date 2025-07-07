package pe.gob.casadelaliteratura.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColeccionDTO {

    @NotBlank
    @Size(max = 100)
    private String descripcion;

    @NotBlank
    @Size(max = 100)
    private String nombreSala;

}
