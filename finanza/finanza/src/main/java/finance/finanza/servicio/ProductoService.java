package finance.finanza.servicio;
import finance.finanza.DTO.ProductoDTO;
import finance.finanza.modelo.Cliente;
import finance.finanza.modelo.Producto;
import finance.finanza.repositorio.ClienteRepository;
import finance.finanza.repositorio.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public ProductoService(ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public Optional<ProductoDTO> obtenerPorId(Long id) {
        return productoRepository.findById(id).map(this::convertirADTO);
    }

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Cliente cliente = clienteRepository.findById(productoDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Producto producto = new Producto();
        producto.setCliente(cliente);
        producto.setTipoCuenta(productoDTO.getTipoCuenta());
        producto.setSaldo(productoDTO.getSaldo());
        producto.setExentaGMF(productoDTO.isExentaGMF());

        if (producto.getTipoCuenta().equals("ahorros") && producto.getSaldo() < 0) {
            throw new IllegalArgumentException("Las cuentas de ahorro no pueden tener saldo negativo");
        }

        return convertirADTO(productoRepository.save(producto));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setClienteId(producto.getCliente().getId());
        dto.setTipoCuenta(producto.getTipoCuenta());
        dto.setNumeroCuenta(producto.getNumeroCuenta());
        dto.setEstado(producto.getEstado());
        dto.setSaldo(producto.getSaldo());
        dto.setExentaGMF(producto.isExentaGMF());
        dto.setFechaCreacion(producto.getFechaCreacion());
        return dto;
    }
}
