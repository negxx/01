package biblio1.teca.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import biblio1.teca.modelo.Resena;
import biblio1.teca.repositorio.ResenaRepository;
import biblio1.teca.repositorio.LibroRepository;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    private final ResenaRepository resenaRepository;
    private final LibroRepository libroRepository;

    public ResenaController(ResenaRepository resenaRepository, LibroRepository libroRepository) {
        this.resenaRepository = resenaRepository;
        this.libroRepository = libroRepository;
    }

    @GetMapping("/libro/{libroId}")
    public List<Resena> getResenasPorLibro(@PathVariable Long libroId) {
        return resenaRepository.findByLibroId(libroId);
    }

    @PostMapping("/libro/{libroId}")
    public ResponseEntity<Resena> addResena(@PathVariable Long libroId, @RequestBody Resena resena) {
        return libroRepository.findById(libroId).map(libro -> {
            resena.setLibro(libro);
            Resena nueva = resenaRepository.save(resena);

            // calcular el promedio
            List<Resena> resenasDelLibro = resenaRepository.findByLibroId(libroId);
            double promedio = resenasDelLibro.stream()
                    .mapToDouble(Resena::getPuntuacion)
                    .average()
                    .orElse(0.0);

            libro.setValoracion(promedio);
            libroRepository.save(libro);

            return ResponseEntity.ok(nueva);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
