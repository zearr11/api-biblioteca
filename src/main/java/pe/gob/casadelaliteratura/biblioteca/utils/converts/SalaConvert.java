package pe.gob.casadelaliteratura.biblioteca.utils.converts;

import pe.gob.casadelaliteratura.biblioteca.dtos.SalaDTO;
import pe.gob.casadelaliteratura.biblioteca.models.libro.Sala;

public class SalaConvert {

    public static SalaDTO salaModelToResponse(Sala sala) {
        return new SalaDTO(sala.getNombreSala());
    }

    public static Sala responseToSalaModel(SalaDTO sala) {
        return new Sala(null, sala.getNombreSala());
    }

    public static Sala setResponseToSalaModel(SalaDTO salaRequest,
                                              Sala salaToUpdate) {
        salaToUpdate.setNombreSala(salaRequest.getNombreSala());
        return salaToUpdate;
    }

}
