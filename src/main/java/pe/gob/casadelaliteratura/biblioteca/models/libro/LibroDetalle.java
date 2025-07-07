package pe.gob.casadelaliteratura.biblioteca.models.libro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibroDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroDetalle;

    @Column(nullable = false, length = 50, unique = true)
    private String isbn;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "id_coleccion", nullable = false)
    private Coleccion coleccion;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial", nullable = false)
    private Editorial editorial;

}
