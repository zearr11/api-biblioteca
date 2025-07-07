package pe.gob.casadelaliteratura.biblioteca.repositories.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.casadelaliteratura.biblioteca.models.libro.LibroDetalle;

public interface LibroDetalleRepository extends JpaRepository<LibroDetalle, Long> {
}
