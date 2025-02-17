package finance.finanza.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIdentificacion;

    @Column(unique = true)
    private String numeroIdentificacion;

    private String nombres;

    private String apellidos;

    private String correoElectronico;

    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDate.now();
    }

    public boolean esMayorDeEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}

