package pe.gob.casadelaliteratura.biblioteca.repositories.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Libro;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = """
        SELECT ld.isbn, ld.titulo, ld.year, a.nombre AS autor,
               e.descripcion AS editorial, l.numero_copia, c.descripcion AS coleccion,
               s.nombre_sala AS sala, l.estado,
               CASE
        		   WHEN l.estado = 'PRESTADO'
        		   THEN COALESCE(r.nueva_fecha_vencimiento, p.fecha_vencimiento)
                   ELSE NULL
        	   END AS fecha_vencimiento
        FROM libro_detalle ld
        INNER JOIN libro l ON l.id_libro_detalle = ld.id_libro_detalle
        INNER JOIN autor a ON a.id_autor = ld.id_autor
        INNER JOIN editorial e ON e.id_editorial = ld.id_editorial
        INNER JOIN coleccion c ON c.id_coleccion = ld.id_coleccion
        INNER JOIN sala s ON s.id_sala = c.id_sala
        LEFT JOIN detalle_prestamo dp ON dp.id_libro = l.id_libro
        LEFT JOIN prestamo p ON p.id_prestamo = dp.id_prestamo
        LEFT JOIN (
            SELECT r1.id_prestamo, r1.nueva_fecha_vencimiento
            FROM renovacion r1
            INNER JOIN (
                SELECT id_prestamo, MAX(fecha_solicitud) AS max_fecha
                FROM renovacion
                GROUP BY id_prestamo
            ) r2 ON r1.id_prestamo = r2.id_prestamo AND r1.fecha_solicitud = r2.max_fecha
        ) r ON r.id_prestamo = p.id_prestamo
        WHERE (:titulo IS NULL OR ld.titulo LIKE %:titulo%)
            AND (:isbn IS NULL OR ld.isbn LIKE :isbn%)
            AND (:estado IS NULL OR l.estado = :estado)
        """, nativeQuery = true)
    List<Object[]> obtenerDetalleLibros(@Param("titulo") String titulo,
                                 @Param("isbn") String isbn,
                                 @Param("estado") String estado);

    @Query(value = """
        SELECT ld.isbn, ld.titulo, ld.year, a.nombre AS autor,
               e.descripcion AS editorial, c.descripcion AS coleccion,
               s.nombre_sala AS sala, COUNT(*) AS cantidad_copias,
               SUM(CASE WHEN l.estado = 'DISPONIBLE' THEN 1 ELSE 0 END),
               SUM(CASE WHEN l.estado = 'PRESTADO' THEN 1 ELSE 0 END),
               SUM(CASE WHEN l.estado = 'SOLO_PARA_LECTURA_EN_SALA' THEN 1 ELSE 0 END)
        FROM libro_detalle ld
        INNER JOIN libro l ON l.id_libro_detalle = ld.id_libro_detalle
        INNER JOIN autor a ON a.id_autor = ld.id_autor
        INNER JOIN editorial e ON e.id_editorial = ld.id_editorial
        INNER JOIN coleccion c ON c.id_coleccion = ld.id_coleccion
        INNER JOIN sala s ON s.id_sala = c.id_sala
        WHERE (:titulo IS NULL OR ld.titulo LIKE %:titulo%)
          AND (:isbn IS NULL OR ld.isbn LIKE %:isbn%)
        GROUP BY ld.isbn, ld.titulo, ld.year, a.nombre, e.descripcion,
                 c.descripcion, s.nombre_sala
        """, nativeQuery = true)
    List<Object[]> obtenerCantidadesLibros(@Param("titulo") String titulo,
                                        @Param("isbn") String isbn);

    @Query(value = """
        SELECT ld.isbn, ld.titulo, ld.year, a.nombre AS autor,
               e.descripcion AS editorial, l.numero_copia, c.descripcion AS coleccion,
               s.nombre_sala AS sala, l.estado,
               CASE
        		   WHEN l.estado = 'PRESTADO'
        		   THEN COALESCE(r.nueva_fecha_vencimiento, p.fecha_vencimiento)
                   ELSE NULL
        	   END AS fecha_vencimiento
        FROM libro_detalle ld
        INNER JOIN libro l ON l.id_libro_detalle = ld.id_libro_detalle
        INNER JOIN autor a ON a.id_autor = ld.id_autor
        INNER JOIN editorial e ON e.id_editorial = ld.id_editorial
        INNER JOIN coleccion c ON c.id_coleccion = ld.id_coleccion
        INNER JOIN sala s ON s.id_sala = c.id_sala
        LEFT JOIN detalle_prestamo dp ON dp.id_libro = l.id_libro
        LEFT JOIN prestamo p ON p.id_prestamo = dp.id_prestamo
        LEFT JOIN (
            SELECT r1.id_prestamo, r1.nueva_fecha_vencimiento
            FROM renovacion r1
            INNER JOIN (
                SELECT id_prestamo, MAX(fecha_solicitud) AS max_fecha
                FROM renovacion
                GROUP BY id_prestamo
            ) r2 ON r1.id_prestamo = r2.id_prestamo AND r1.fecha_solicitud = r2.max_fecha
        ) r ON r.id_prestamo = p.id_prestamo
        WHERE (:isbn IS NULL OR ld.isbn = :isbn)
            AND (:numeroCopia IS NULL OR l.numero_copia = :numeroCopia)
        """, nativeQuery = true)
    Object obtenerDetalleLibrosPorIsbnYnumeroCopia(@Param("isbn") String isbn,
                                                     @Param("numeroCopia") Integer numeroCopia);

    @Query(value = """
        SELECT ld.isbn, ld.titulo, ld.year, a.nombre AS autor,
               e.descripcion AS editorial, c.descripcion AS coleccion,
               s.nombre_sala AS sala, COUNT(*) AS cantidad_copias,
               SUM(CASE WHEN l.estado = 'DISPONIBLE' THEN 1 ELSE 0 END),
               SUM(CASE WHEN l.estado = 'PRESTADO' THEN 1 ELSE 0 END),
               SUM(CASE WHEN l.estado = 'SOLO_PARA_LECTURA_EN_SALA' THEN 1 ELSE 0 END)
        FROM libro_detalle ld
        INNER JOIN libro l ON l.id_libro_detalle = ld.id_libro_detalle
        INNER JOIN autor a ON a.id_autor = ld.id_autor
        INNER JOIN editorial e ON e.id_editorial = ld.id_editorial
        INNER JOIN coleccion c ON c.id_coleccion = ld.id_coleccion
        INNER JOIN sala s ON s.id_sala = c.id_sala
        WHERE (:isbn IS NULL OR ld.isbn = :isbn)
        GROUP BY ld.isbn, ld.titulo, ld.year, a.nombre, e.descripcion,
                 c.descripcion, s.nombre_sala
        """, nativeQuery = true)
    Object obtenerCantidadesLibrosPorIsbn(@Param("isbn") String isbn);
}
