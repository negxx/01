package biblio1.teca.repositorio;

import biblio1.teca.modelo.Libro;
import biblio1.teca.modelo.Libro.Resena;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Métodos derivados simples
    List<Libro> findByAutor(String autor);
    List<Libro> findByTituloContaining(String titulo);
    List<Libro> findByIsbn(String isbn);
    List<Libro> findByPrecioBetween(double minPrecio, double maxPrecio);
    List<Libro> findByAutorAndTituloContaining(String autor, String titulo);
    List<Libro> findByAutorAndIsbn(String autor, String isbn);
    List<Libro> findByAutorAndPrecioBetween(String autor, double minPrecio, double maxPrecio);
    List<Libro> findByTituloContainingAndPrecioBetween(String titulo, double minPrecio, double maxPrecio);
    List<Libro> findByTituloContainingAndAutor(String titulo, String autor);
    List<Libro> findByTituloContainingAndIsbn(String titulo, String isbn);
    List<Libro> findByTituloContainingAndAutorAndPrecioBetween(String titulo, String autor, double minPrecio, double maxPrecio);

    // Consulta avanzada con filtros opcionales
    @Query("""
        SELECT l FROM Libro l
        WHERE (:autor IS NULL OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :autor, '%')))
          AND (:genero IS NULL OR LOWER(l.genero) = LOWER(:genero))
          AND (:minPrecio IS NULL OR l.precio >= :minPrecio)
          AND (:maxPrecio IS NULL OR l.precio <= :maxPrecio)
          AND (:anio IS NULL OR l.anioPublicacion = :anio)
          AND (:disponible IS NULL OR l.disponible = :disponible)
          AND (:enOferta IS NULL OR l.enOferta = :enOferta)
          AND (:minValoracion IS NULL OR l.valoracion >= :minValoracion)
    """)
    List<Libro> buscarLibrosAvanzado(
        @Param("autor") String autor,
        @Param("genero") String genero,
        @Param("minPrecio") Double minPrecio,
        @Param("maxPrecio") Double maxPrecio,
        @Param("anio") Integer anio,
        @Param("disponible") Boolean disponible,
        @Param("enOferta") Boolean enOferta,
        @Param("minValoracion") Double minValoracion
    );
    public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByLibroId(Long libroId);
}
}
