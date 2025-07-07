package pe.gob.casadelaliteratura.biblioteca.utils.converts;

import pe.gob.casadelaliteratura.biblioteca.dtos.EditorialDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Editorial;

public class EditorialConvert {

    public static EditorialDTO editorialModelToResponse(Editorial editorial) {
        return new EditorialDTO(editorial.getDescripcion());
    }

    public static Editorial responseToEditorialModel(EditorialDTO editorial) {
        return new Editorial(null, editorial.getEditorial());
    }

    public static Editorial setResponseToEditorialModel(EditorialDTO editorialRequest,
                                                        Editorial editorialToUpdate) {
        editorialToUpdate.setDescripcion(editorialRequest.getEditorial());
        return editorialToUpdate;
    }

}
