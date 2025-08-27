package biblio1.teca.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private double precio;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}


    
