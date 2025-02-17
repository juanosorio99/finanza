package finance.finanza.servicio;

import finance.finanza.modelo.Cliente;
import finance.finanza.repositorio.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardarCliente(Cliente cliente) {
        if (!cliente.esMayorDeEdad()) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setTipoIdentificacion(clienteActualizado.getTipoIdentificacion());
            cliente.setNumeroIdentificacion(clienteActualizado.getNumeroIdentificacion());
            cliente.setNombres(clienteActualizado.getNombres());
            cliente.setApellidos(clienteActualizado.getApellidos());
            cliente.setCorreoElectronico(clienteActualizado.getCorreoElectronico());
            cliente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
