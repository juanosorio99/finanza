package finance.finanza.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import finance.finanza.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
