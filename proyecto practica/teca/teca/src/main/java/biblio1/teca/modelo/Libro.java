package biblio1.teca.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private double precio;
    private double valoracion;
    private String genero;
    private int anioPublicacion;
    private boolean disponible;
    private boolean enOferta;

    // Constructores
    public Libro() {
    }

    public Libro(String titulo, String autor, String isbn, double precio,
                 double valoracion, String genero, int anioPublicacion,
                 boolean disponible, boolean enOferta) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.precio = precio;
        this.valoracion = valoracion;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.disponible = disponible;
        this.enOferta = enOferta;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isEnOferta() {
        return enOferta;
    }

    public Boolean getEnOferta() {
        return enOferta;
    }

    public void setEnOferta(boolean enOferta) {
        this.enOferta = enOferta;
    }
    @Entity
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String comentario;
    private double puntuacion;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public Resena() {}

    public Resena(String usuario, String comentario, double puntuacion, Libro libro) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
}

