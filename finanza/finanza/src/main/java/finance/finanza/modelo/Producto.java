package finance.finanza.modelo;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Random;



@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private String tipoCuenta;

    @Column(unique = true, length = 10)
    private String numeroCuenta;

    private String estado;
    private double saldo;
    private boolean exentaGMF;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
        this.estado = "activa";
        this.numeroCuenta = generarNumeroCuenta();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDate.now();
    }

    private String generarNumeroCuenta() {
        Random random = new Random();
        String prefijo = tipoCuenta.equals("ahorros") ? "53" : "33";
        int numeroAleatorio = 1000000 + random.nextInt(9000000);
        return prefijo + numeroAleatorio;
    }
}
