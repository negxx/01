package biblio1.teca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio1.teca.modelo.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
}