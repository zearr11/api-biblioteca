package pe.gob.casadelaliteratura.biblioteca.services.impl.libro;

import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleCantidadesDTO;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleDTO;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetallePrestadoDTO;
import pe.gob.casadelaliteratura.biblioteca.dtos.libro.LibroDetalleRegularDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Libro;
import pe.gob.casadelaliteratura.biblioteca.repositories.libro.LibroRepository;
import pe.gob.casadelaliteratura.biblioteca.services.interfaces.libro.ILibroService;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.EstadoLibro;
import pe.gob.casadelaliteratura.biblioteca.utils.exceptions.ResourceNotFoundException;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> getAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro getById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public Libro saveOrUpdate(Libro entity) {
        return libroRepository.save(entity);
    }

    @Override
    public List<LibroDetalleDTO> getDetalleLibros(String titulo, String isbn, String estado) {
        List<LibroDetalleDTO> lst = libroRepository
                .obtenerDetalleLibros(titulo, isbn, estado).stream()
                .map(obj -> {
                    Date fechaVencimiento = (Date) obj[9];

                    if (fechaVencimiento == null) {
                        return new LibroDetalleRegularDTO(
                                (String) obj[0], // isbn
                                (String) obj[1], // titulo
                                (Integer) obj[2], // año
                                (String) obj[3], // autor
                                (String) obj[4], // editorial
                                (Integer) obj[5], // numero_copia
                                (String) obj[6], // coleccion
                                (String) obj[7], // sala
                                EstadoLibro.valueOf((String) obj[8]) // estado
                        );
                    } else {
                        return new LibroDetallePrestadoDTO(
                                (String) obj[0],
                                (String) obj[1],
                                (Integer) obj[2],
                                (String) obj[3],
                                (String) obj[4],
                                (Integer) obj[5],
                                (String) obj[6],
                                (String) obj[7],
                                EstadoLibro.valueOf((String) obj[8]),
                                fechaVencimiento.toLocalDate()
                        );
                    }
                })
                .toList();

        if (lst.isEmpty())
            throw new ResourceNotFoundException("No se encontraron resultados.");

        return lst;
    }

    @Override
    public List<LibroDetalleCantidadesDTO> getCantidadesLibros(String titulo, String isbn) {
        List<LibroDetalleCantidadesDTO> lst = libroRepository.obtenerCantidadesLibros(titulo, isbn).stream()
                .map(obj -> new LibroDetalleCantidadesDTO(
                        (String) obj[0], // isbn
                        (String) obj[1], // titulo
                        (Integer) obj[2], // año
                        (String) obj[3], // autor
                        (String) obj[4], // editorial
                        (String) obj[5], // coleccion
                        (String) obj[6], // sala
                        ((Number) obj[7]).intValue(), // cantidad_copias
                        ((Number) obj[8]).intValue(), // cantidad_disponibles
                        ((Number) obj[9]).intValue(), // cantidad_prestados
                        ((Number) obj[10]).intValue() // cantidad_solo_lectura_en_sala
                ))
                .toList();

        if (lst.isEmpty())
            throw new ResourceNotFoundException("No se encontraron resultados.");

        return lst;
    }

    @Override
    public LibroDetalleDTO getDetalleLibroPorIsbnYnumeroCopia(String isbn, Integer numeroCopia) {

        Object[] obj = (Object[]) libroRepository
                .obtenerDetalleLibrosPorIsbnYnumeroCopia(isbn, numeroCopia);

        if (obj == null)
            throw new ResourceNotFoundException("No se encontraron resultados.");

        Date fechaVencimiento = (Date) obj[9];

        if (fechaVencimiento == null) {
            return new LibroDetalleRegularDTO(
                    (String) obj[0], // isbn
                    (String) obj[1], // titulo
                    (Integer) obj[2], // año
                    (String) obj[3], // autor
                    (String) obj[4], // editorial
                    (Integer) obj[5], // numero_copia
                    (String) obj[6], // coleccion
                    (String) obj[7], // sala
                    EstadoLibro.valueOf((String) obj[8]) // estado
            );
        } else {
            return new LibroDetallePrestadoDTO(
                    (String) obj[0],
                    (String) obj[1],
                    (Integer) obj[2],
                    (String) obj[3],
                    (String) obj[4],
                    (Integer) obj[5],
                    (String) obj[6],
                    (String) obj[7],
                    EstadoLibro.valueOf((String) obj[8]),
                    fechaVencimiento.toLocalDate()
            );
        }
    }

    @Override
    public LibroDetalleCantidadesDTO getCantidadesLibrosPorIsbn(String isbn) {

        Object[] obj = (Object[]) libroRepository.obtenerCantidadesLibrosPorIsbn(isbn);

        if (obj == null)
            throw new ResourceNotFoundException("No se encontraron resultados.");

        return new LibroDetalleCantidadesDTO(
                (String) obj[0], // isbn
                (String) obj[1], // titulo
                (Integer) obj[2], // año
                (String) obj[3], // autor
                (String) obj[4], // editorial
                (String) obj[5], // coleccion
                (String) obj[6], // sala
                ((Number) obj[7]).intValue(), // cantidad total
                ((Number) obj[8]).intValue(), // disponibles
                ((Number) obj[9]).intValue(), // prestados
                ((Number) obj[10]).intValue() // solo lectura
        );

    }

}
