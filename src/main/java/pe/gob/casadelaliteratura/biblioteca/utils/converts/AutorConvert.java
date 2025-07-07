package pe.gob.casadelaliteratura.biblioteca.utils.converts;

import pe.gob.casadelaliteratura.biblioteca.dtos.AutorDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Autor;

public class AutorConvert {

    public static AutorDTO autorModelToResponse(Autor autor) {
        return new AutorDTO(autor.getNombre(), autor.getNacionalidad());
    }

    public static Autor responseToAutorModel(AutorDTO autor) {
        return new Autor(null, autor.getAutor(), autor.getNacionalidad());
    }

    public static Autor setResponseToAutorModel(AutorDTO autorRequest,
                                                Autor autorToUpdate) {
        autorToUpdate.setNombre(autorRequest.getAutor());
        autorToUpdate.setNacionalidad(autorRequest.getNacionalidad());
        return autorToUpdate;
    }

}
