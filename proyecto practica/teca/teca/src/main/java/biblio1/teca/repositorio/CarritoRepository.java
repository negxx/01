package biblio1.teca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio1.teca.modelo.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}

