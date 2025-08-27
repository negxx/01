package biblio1.teca.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import biblio1.teca.modelo.Libro;
import biblio1.teca.repositorio.LibroRepository;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        return libroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro addLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroDetails.getTitulo());
            libro.setAutor(libroDetails.getAutor());
            libro.setIsbn(libroDetails.getIsbn());
            libro.setPrecio(libroDetails.getPrecio());
            libro.setGenero(libroDetails.getGenero());
            libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
            libro.setDisponible(libroDetails.getDisponible());
            libro.setEnOferta(libroDetails.getEnOferta());
            libro.setValoracion(libroDetails.getValoracion());
            Libro updatedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(updatedLibro);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
        return libroRepository.findById(id).map(libro -> {
            libroRepository.delete(libro);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Libro> buscarLibros(
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Double minPrecio,
            @RequestParam(required = false) Double maxPrecio,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Boolean disponible,
            @RequestParam(required = false) Boolean enOferta,
            @RequestParam(required = false) Double minValoracion
    ) {
        return libroRepository.buscarLibrosAvanzado(
                autor, genero, minPrecio, maxPrecio, anio, disponible, enOferta, minValoracion);
    }
}

