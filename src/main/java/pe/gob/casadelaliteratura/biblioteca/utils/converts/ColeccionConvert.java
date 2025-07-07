package pe.gob.casadelaliteratura.biblioteca.utils.converts;

import pe.gob.casadelaliteratura.biblioteca.dtos.ColeccionDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Coleccion;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;

public class ColeccionConvert {

    public static ColeccionDTO coleccionModelToResponse(Coleccion coleccion) {
        return ColeccionDTO.builder()
                .descripcion(coleccion.getDescripcion())
                .nombreSala(coleccion.getSala().getNombreSala())
                .build();
    }

    public static Coleccion responseToColeccionModel(ColeccionDTO coleccion, Sala sala) {
        return new Coleccion(null,
                coleccion.getDescripcion(), sala);
    }

    public static Coleccion setResponseToColeccionModel(ColeccionDTO coleccionRequest,
                                              Coleccion coleccionToUpdate, Sala sala) {
        coleccionToUpdate.setDescripcion(coleccionRequest.getDescripcion());
        coleccionToUpdate.setSala(sala);

        return coleccionToUpdate;
    }

}
