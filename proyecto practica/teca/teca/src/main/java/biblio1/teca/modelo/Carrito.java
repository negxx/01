package biblio1.teca.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con los items del carrito
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items;

    // Aquí podrías relacionarlo con un usuario cuando lo tengas
    // @ManyToOne
    // private Usuario usuario;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
    @Transient
public double getTotal() {
    return items.stream()
                .mapToDouble(item -> item.getCantidad() * item.getLibro().getPrecio())
                .sum();
}
}
