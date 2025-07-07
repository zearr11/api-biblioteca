package pe.gob.casadelaliteratura.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalaDTO {

    @NotBlank(message = "El nombre de la sala no puede estar vacía")
    @Size(max = 100, message = "El nombre de la sala no puede superar los 100 caracteres")
    private String nombreSala;

}
