package finance.finanza.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductoDTO {
    private Long id;
    private Long clienteId;
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private double saldo;
    private boolean exentaGMF;
    private LocalDate fechaCreacion;
}












