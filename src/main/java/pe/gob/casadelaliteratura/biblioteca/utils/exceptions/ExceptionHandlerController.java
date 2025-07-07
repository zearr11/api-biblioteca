package pe.gob.casadelaliteratura.biblioteca.utils.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage<String> resourceNotFoundException(ResourceNotFoundException exception,
                                                  WebRequest request) {
        return new ErrorMessage<>(LocalDate.now(),
                exception.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ValidacionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage<String> handleValidacionException(ValidacionException ex, WebRequest request) {
        return new ErrorMessage<>(
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage<List<String>> handleValidationException(ConstraintViolationException ex) {
        List<String> detalles = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return new ErrorMessage<>(
                LocalDate.now(),
                "Errores de validación en los campos solicitados",
                detalles
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage<String> illegalArgumentException(IllegalArgumentException ex,
                                                         WebRequest request) {
        return
                new ErrorMessage<>(LocalDate.now(),
                    ex.getMessage(), request.getDescription(false)
                );
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage<String> handleMultipartException(MultipartException ex) {
        return new ErrorMessage<>(
                LocalDate.now(),
                "Error al procesar los archivos enviados.",
                "Verifica que los archivos no estén vacíos, sean imágenes válidas y que la solicitud sea de tipo multipart."
            );
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage<String> handleMissingServletRequestPartException(
            MissingServletRequestPartException ex) {
        return new ErrorMessage<>(
                LocalDate.now(),
                "Falta una parte obligatoria en la solicitud.",
                "Parte faltante: " + ex.getRequestPartName()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                             WebRequest request) {
        String mensaje;

        Throwable rootCause = ex.getCause();
        if (rootCause != null && rootCause.getMessage() != null && rootCause.getMessage().contains("TipoDoc")) {
            mensaje = "Tipo de documento no válido. Debe ser DNI o CE.";
        } else if (rootCause != null && rootCause.getMessage().contains("LocalDate")) {
            mensaje = "La fecha debe tener el formato correcto (yyyy-MM-dd).";
        } else {
            mensaje = "Error en el formato de la solicitud. Verifica los datos enviados.";
        }

        return new ErrorMessage<>(
                LocalDate.now(),
                mensaje,
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return new ErrorMessage<>(
                LocalDate.now(),
                "Error de validación",
                errores
        );
    }

}
