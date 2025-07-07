package pe.gob.casadelaliteratura.biblioteca.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySqlConfig implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public MySqlConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM documentacion", Integer.class);

        if (count != null && count == 0) {
            crearTriggerPrestamo();
            crearTriggerRenovacion();
            insertarDocumentacion();
            insertarClientes();
            insertarSalas();
            insertarColecciones();
            insertarEditoriales();
            insertarAutores();
            insertarLibrosDetalle();
            insertarLibros();
            insertarPrestamos();
            insertarDetallePrestamos();
        }
    }

    private void crearTriggerPrestamo() {
        jdbcTemplate.execute("""
            CREATE TRIGGER trg_set_fecha_vencimiento_prestamo
            BEFORE INSERT ON prestamo
            FOR EACH ROW
            BEGIN
                IF NEW.fecha_vencimiento IS NULL THEN
                    SET NEW.fecha_vencimiento = DATE_ADD(NEW.fecha_prestamo, INTERVAL 14 DAY);
                END IF;
            END
        """);
    }

    private void crearTriggerRenovacion() {
        jdbcTemplate.execute("""
            CREATE TRIGGER trg_set_fecha_vencimiento_renovacion
            BEFORE INSERT ON renovacion
            FOR EACH ROW
            BEGIN
                DECLARE fecha_actual_vencimiento DATE;
                
                SELECT MAX(nueva_fecha_vencimiento)
                INTO fecha_actual_vencimiento
                FROM renovacion
                WHERE id_prestamo = NEW.id_prestamo;
                
                IF fecha_actual_vencimiento IS NULL THEN
                    SELECT fecha_vencimiento
                    INTO fecha_actual_vencimiento
                    FROM prestamo
                    WHERE id_prestamo = NEW.id_prestamo;
                END IF;

                IF NEW.nueva_fecha_vencimiento IS NULL THEN
                    SET NEW.nueva_fecha_vencimiento = DATE_ADD(fecha_actual_vencimiento, INTERVAL 14 DAY);
                END IF;
            END;
        """);
    }

    private void insertarDocumentacion() {
        jdbcTemplate.update("""
                INSERT INTO documentacion(img_doc_identidad, img_rec_servicio) VALUES
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png')),
                (LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\DNI_peruano.jpg'), LOAD_FILE('C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\recibo.png'));
           """);
    }

    private void insertarClientes() {
        jdbcTemplate.update("""
                INSERT INTO cliente(apellidos, correo, direccion, fecha_nacimiento, nombres, numero_doc, numero_principal, numero_secundario, tipo_doc, id_documentacion) VALUES
                ('Ramírez Huamán', 'carlos.ramirez@example.com', 'Av. Los Álamos 123, San Juan de Lurigancho, Lima', '1995-04-15', 'Carlos Andrés', '12345678', '987654321', '912345678', 'DNI', 1),
                ('Gonzales Rojas', 'ana.gonzales@example.com', 'Calle Las Rosas 456, Miraflores, Lima', '1992-08-20', 'Ana María', '87654321', '998877665', '987654320', 'DNI', 2),
                ('Fernández López', 'jorge.fernandez@example.com', 'Jr. Amazonas 789, Trujillo, La Libertad', '1988-12-01', 'Jorge Luis', '11223344', '912345678', '987654322', 'DNI', 3),
                ('Torres Meza', 'maria.torres@example.com', 'Av. Grau 1025, Piura', '1990-05-10', 'María Elena', '33445566', '987654323', '912345679', 'DNI', 4),
                ('Vega Castillo', 'diego.vega@example.com', 'Calle Libertad 321, Cusco', '1993-07-19', 'Diego Armando', '55667788', '999888777', '911223344', 'DNI', 5),
                ('Sánchez Quispe', 'luz.sanchez@example.com', 'Jr. San Martín 55, Cajamarca', '1991-09-09', 'Luz Milagros', '66778899', '966554433', '977665544', 'DNI', 6),
                ('Paredes Inga', 'hugo.paredes@example.com', 'Av. El Sol 420, Huancayo', '1987-11-23', 'Hugo César', '77889900', '944556677', '911122233', 'DNI', 7),
                ('Reyes Salas', 'veronica.reyes@example.com', 'Calle Unión 777, Arequipa', '1996-03-30', 'Verónica Alejandra', '88990011', '933445566', '922334455', 'DNI', 8),
                ('Morales Díaz', 'luis.morales@example.com', 'Av. Túpac Amaru 1001, Iquitos', '1989-06-05', 'Luis Alberto', '99001122', '988776655', '933221100', 'DNI', 9),
                ('Chávez Ramos', 'karla.chavez@example.com', 'Jr. Puno 200, Ayacucho', '1994-10-17', 'Karla Yessenia', '10111213', '911122233', '900011223', 'DNI', 10);
            """);
    }

    private void insertarSalas() {
        jdbcTemplate.update("""
                INSERT INTO sala(nombre_sala) VALUES
                ('Sala de Literatura Infantil Cota Carvallo'),
                ('Biblioteca Mario Vargas Llosa');
        """);
    }

    private void insertarColecciones() {
        jdbcTemplate.update("""
                INSERT INTO coleccion(descripcion, id_sala) VALUES
                ('El Pájaro Niño', 1),
                ('Oshta y el duende', 1),
                ('Rutsí, el pequeño alucinado', 1),
                ('La flor del tiempo', 1),
                ('Mario Vargas Llosa', 2),
                ('Interdisciplinaria', 2),
                ('Lima y Mapa Literario', 2),
                ('Historieta y Novela Gráfica', 2),
                ('Literatura Juvenil ', 2),
                ('Publicaciones Casa de la Literatura', 2),
                ('Literatura Peruana', 2),
                ('Literatura Hispanoamericana', 2),
                ('Literatura Universal', 2),
                ('Estudios Literarios', 2),
                ('Colección José María Arguedas', 2),
                ('Publicaciones Periódicas', 2);
        """);
    }

    private void insertarEditoriales() {
        jdbcTemplate.update("""
                INSERT INTO editorial(descripcion) VALUES
                ('Andina'), ('Planeta'), ('Santillana'), ('Alfaguara'), ('Anagrama'),
                ('SM'), ('Norma'), ('Ediciones B'), ('Peisa'), ('Lumen');
        """);
    }

    private void insertarAutores() {
        jdbcTemplate.update("""
                INSERT INTO autor(nacionalidad, nombre) VALUES
                ('Peruana', 'Mario Vargas Llosa'),
                ('Chilena', 'Isabel Allende'),
                ('Colombiana', 'Gabriel García Márquez'),
                ('Argentina', 'Jorge Luis Borges'),
                ('Mexicana', 'Juan Rulfo'),
                ('Española', 'Carlos Ruiz Zafón'),
                ('Peruana', 'Julio Ramón Ribeyro'),
                ('Uruguaya', 'Eduardo Galeano'),
                ('Peruana', 'César Vallejo'),
                ('Nicaragüense', 'Rubén Darío');
        """);
    }

    private void insertarLibrosDetalle() {
        jdbcTemplate.update("""
                INSERT INTO libro_detalle(isbn, titulo, year, id_autor, id_coleccion, id_editorial) VALUES
                ('978-84-376-0494-7', 'La ciudad y los perros', 1963, 1, 5, 1),
                ('978-84-204-6724-6', 'La casa de los espíritus', 1982, 2, 12, 2),
                ('978-84-376-0495-4', 'Cien años de soledad', 1967, 3, 13, 3),
                ('978-84-339-1222-1', 'Ficciones', 1944, 4, 13, 4),
                ('978-607-11-2001-0', 'Pedro Páramo', 1955, 5, 12, 5),
                ('978-84-08-03759-3', 'La sombra del viento', 2001, 6, 13, 6),
                ('978-84-339-7226-3', 'La palabra del mudo', 1974, 7, 11, 7),
                ('978-84-663-4450-9', 'Las venas abiertas de América Latina', 1971, 8, 14, 8),
                ('978-84-206-3410-7', 'Los Heraldos Negros', 1919, 9, 15, 9),
                ('978-84-397-2174-1', 'Azul...', 1888, 10, 12, 10),
                ('978-84-376-0494-8', 'Conversación en La Catedral', 1969, 1, 6, 1),
                ('978-84-204-6724-7', 'Paula', 1994, 2, 11, 2),
                ('978-84-376-0495-5', 'El amor en los tiempos del cólera', 1985, 3, 13, 3),
                ('978-84-339-1222-2', 'El Aleph', 1949, 4, 13, 4),
                ('978-607-11-2001-1', 'El llano en llamas', 1953, 5, 14, 5),
                ('978-84-08-03759-4', 'El juego del ángel', 2008, 6, 13, 6),
                ('978-84-339-7226-4', 'Prosas apátridas', 1975, 7, 11, 7),
                ('978-84-663-4450-0', 'Memoria del fuego', 1986, 8, 14, 8),
                ('978-84-206-3410-8', 'Poemas humanos', 1939, 9, 15, 9),
                ('978-84-397-2174-2', 'Cantos de vida y esperanza', 1905, 10, 12, 10);
        """);
    }

    private void insertarLibros() {
        jdbcTemplate.update("""
                INSERT INTO libro(estado, numero_copia, id_libro_detalle) VALUES
                ('DISPONIBLE', 1, 1), ('PRESTADO', 2, 1), ('SOLO_PARA_LECTURA_EN_SALA', 3, 1), ('DISPONIBLE', 4, 1), ('DISPONIBLE', 5, 1),
                ('DISPONIBLE', 1, 2), ('PRESTADO', 2, 2), ('DISPONIBLE', 3, 2), ('SOLO_PARA_LECTURA_EN_SALA', 4, 2), ('PRESTADO', 5, 2),
                ('SOLO_PARA_LECTURA_EN_SALA', 1, 3), ('DISPONIBLE', 2, 3), ('DISPONIBLE', 3, 3), ('PRESTADO', 4, 3), ('DISPONIBLE', 5, 3),
                ('DISPONIBLE', 1, 4), ('DISPONIBLE', 2, 4), ('SOLO_PARA_LECTURA_EN_SALA', 3, 4), ('DISPONIBLE', 4, 4), ('PRESTADO', 5, 4),
                ('PRESTADO', 1, 5), ('DISPONIBLE', 2, 5), ('DISPONIBLE', 3, 5), ('DISPONIBLE', 4, 5), ('SOLO_PARA_LECTURA_EN_SALA', 5, 5),
                ('DISPONIBLE', 1, 6), ('PRESTADO', 2, 6), ('DISPONIBLE', 3, 6), ('DISPONIBLE', 4, 6), ('SOLO_PARA_LECTURA_EN_SALA', 5, 6),
                ('DISPONIBLE', 1, 7), ('SOLO_PARA_LECTURA_EN_SALA', 2, 7), ('PRESTADO', 3, 7), ('DISPONIBLE', 4, 7), ('DISPONIBLE', 5, 7),
                ('DISPONIBLE', 1, 8), ('DISPONIBLE', 2, 8), ('PRESTADO', 3, 8), ('SOLO_PARA_LECTURA_EN_SALA', 4, 8), ('DISPONIBLE', 5, 8),
                ('PRESTADO', 1, 9), ('DISPONIBLE', 2, 9), ('DISPONIBLE', 3, 9), ('SOLO_PARA_LECTURA_EN_SALA', 4, 9), ('DISPONIBLE', 5, 9),
                ('DISPONIBLE', 1, 10), ('SOLO_PARA_LECTURA_EN_SALA', 2, 10), ('DISPONIBLE', 3, 10), ('DISPONIBLE', 4, 10), ('PRESTADO', 5, 10),
                ('DISPONIBLE', 1, 11), ('PRESTADO', 2, 11), ('DISPONIBLE', 3, 11), ('SOLO_PARA_LECTURA_EN_SALA', 4, 11), ('DISPONIBLE', 5, 11),
                ('DISPONIBLE', 1, 12), ('SOLO_PARA_LECTURA_EN_SALA', 2, 12), ('DISPONIBLE', 3, 12), ('PRESTADO', 4, 12), ('DISPONIBLE', 5, 12),
                ('PRESTADO', 1, 13), ('DISPONIBLE', 2, 13), ('DISPONIBLE', 3, 13), ('SOLO_PARA_LECTURA_EN_SALA', 4, 13), ('DISPONIBLE', 5, 13),
                ('DISPONIBLE', 1, 14), ('SOLO_PARA_LECTURA_EN_SALA', 2, 14), ('DISPONIBLE', 3, 14), ('PRESTADO', 4, 14), ('DISPONIBLE', 5, 14),
                ('PRESTADO', 1, 15), ('DISPONIBLE', 2, 15), ('DISPONIBLE', 3, 15), ('DISPONIBLE', 4, 15), ('SOLO_PARA_LECTURA_EN_SALA', 5, 15),
                ('DISPONIBLE', 1, 16), ('PRESTADO', 2, 16), ('DISPONIBLE', 3, 16), ('SOLO_PARA_LECTURA_EN_SALA', 4, 16), ('DISPONIBLE', 5, 16),
                ('PRESTADO', 1, 17), ('DISPONIBLE', 2, 17), ('DISPONIBLE', 3, 17), ('SOLO_PARA_LECTURA_EN_SALA', 4, 17), ('DISPONIBLE', 5, 17),
                ('DISPONIBLE', 1, 18), ('DISPONIBLE', 2, 18), ('SOLO_PARA_LECTURA_EN_SALA', 3, 18), ('PRESTADO', 4, 18), ('DISPONIBLE', 5, 18),
                ('PRESTADO', 1, 19), ('DISPONIBLE', 2, 19), ('SOLO_PARA_LECTURA_EN_SALA', 3, 19), ('DISPONIBLE', 4, 19), ('DISPONIBLE', 5, 19),
                ('DISPONIBLE', 1, 20), ('SOLO_PARA_LECTURA_EN_SALA', 2, 20), ('DISPONIBLE', 3, 20), ('PRESTADO', 4, 20), ('DISPONIBLE', 5, 20);
        """);
    }

    private void insertarPrestamos() {
        jdbcTemplate.update("""
                INSERT INTO prestamo(fecha_prestamo, id_cliente, estado_devolucion) VALUES
                ('2015-05-15', 1, 'DEVOLUCION_PENDIENTE'),
                ('2015-05-27', 2, 'DEVOLUCION_PENDIENTE'),
                ('2015-05-29', 3, 'DEVOLUCION_PENDIENTE'),
                ('2015-06-12', 4, 'DEVOLUCION_PENDIENTE'),
                ('2015-06-26', 5, 'DEVOLUCION_PENDIENTE'),
                ('2015-07-10', 6, 'DEVOLUCION_PENDIENTE'),
                ('2015-07-24', 7, 'DEVOLUCION_PENDIENTE');
        """);
    }

    private void insertarDetallePrestamos() {
        jdbcTemplate.update("""
                INSERT INTO detalle_prestamo(id_libro, id_prestamo) VALUES
                (2, 1), (7, 1), (10, 1), (14, 1),
                (20, 2), (21, 2),
                (27, 3),
                (33, 4), (38, 4), (41, 4), (50, 4),
                (52, 5), (59, 5), (61, 5),
                (69, 6), (71, 6), (77, 6),
                (81, 7), (89, 7), (91, 7), (99, 7);
        """);
    }
}
