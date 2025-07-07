package pe.gob.casadelaliteratura.biblioteca.utils.validations;

import org.springframework.web.multipart.MultipartFile;

public class Validaciones {

    public static void validarImagen(MultipartFile archivo, String nombreCampo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " no fue proporcionado o está vacío.");
        }

        String contentType = archivo.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException(nombreCampo + " debe ser una imagen (jpeg, png, etc).");
        }
    }

}
