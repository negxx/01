package biblio1.teca.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import biblio1.teca.modelo.Carrito;
import biblio1.teca.modelo.ItemCarrito;
import biblio1.teca.modelo.Libro;
import biblio1.teca.repositorio.CarritoRepository;
import biblio1.teca.repositorio.ItemCarritoRepository;
import biblio1.teca.repositorio.LibroRepository;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final LibroRepository libroRepository;

    public CarritoController(CarritoRepository carritoRepository,
                             ItemCarritoRepository itemCarritoRepository,
                             LibroRepository libroRepository) {
        this.carritoRepository = carritoRepository;
        this.itemCarritoRepository = itemCarritoRepository;
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Long id) {
        return carritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @PostMapping("/{carritoId}/agregar/{libroId}")
    public ResponseEntity<Carrito> agregarLibroAlCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long libroId,
            @RequestParam(defaultValue = "1") int cantidad) {

        return carritoRepository.findById(carritoId).map(carrito -> {
            Libro libro = libroRepository.findById(libroId)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            ItemCarrito item = new ItemCarrito();
            item.setCarrito(carrito);
            item.setLibro(libro);
            item.setCantidad(cantidad);

            itemCarritoRepository.save(item);

            carrito.getItems().add(item);
            carritoRepository.save(carrito);

            return ResponseEntity.ok(carrito);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{carritoId}/eliminar/{itemId}")
    public ResponseEntity<?> eliminarItem(@PathVariable Long carritoId, @PathVariable Long itemId) {
        return carritoRepository.findById(carritoId).map(carrito -> {
            itemCarritoRepository.findById(itemId).ifPresent(item -> {
                if (item.getCarrito().getId().equals(carritoId)) {
                    itemCarritoRepository.delete(item);
                    carrito.getItems().remove(item);
                    carritoRepository.save(carrito);
                }
            });
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{carritoId}/vaciar")
public ResponseEntity<Carrito> vaciarCarrito(@PathVariable Long carritoId) {
    return carritoRepository.findById(carritoId).map(carrito -> {
        carrito.getItems().forEach(itemCarritoRepository::delete);
        carrito.getItems().clear();
        carritoRepository.save(carrito);
        return ResponseEntity.ok(carrito);
    }).orElseGet(() -> ResponseEntity.notFound().build());
}
}

