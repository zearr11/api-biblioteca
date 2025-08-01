package pe.gob.casadelaliteratura.biblioteca.models.prestamo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevolucion;

    @Column(nullable = false)
    private LocalDate fechaDevolucion;

    @OneToOne
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamo prestamo;

}
