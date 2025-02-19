package finance.finanza.DTO;

import lombok.Data;
import java.time.LocalDate;

public class ClienteDTO {
    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private LocalDate fechaNacimiento;
}
