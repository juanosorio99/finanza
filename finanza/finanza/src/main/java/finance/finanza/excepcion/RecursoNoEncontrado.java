package finance.finanza.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontrado extends RuntimeException{
    public RecursoNoEncontrado(String mensaje){
        super(mensaje);
    }
}
