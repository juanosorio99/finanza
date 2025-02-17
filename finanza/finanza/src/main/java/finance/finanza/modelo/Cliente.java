package finance.finanza.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de identificación es obligatorio")
    private String tipoIdentificacion;

    @NotBlank(message = "El número de identificación es obligatorio")
    @Column(unique = true)
    private String numeroIdentificacion;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    @Email(message = "Correo electrónico no válido")
    private String correoElectronico;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
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

