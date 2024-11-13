package model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @Column(name = "idproducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private int stock;

    // LA DEFINICION VA TODA JUNTA
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategoria")
    private Categorias categoria;


    // LA DEFINICION VA TODA JUNTA
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "producto_proveedor", //OJO es donde le digo que se crea la tabla
            joinColumns = @JoinColumn(name = "idproducto"),
            inverseJoinColumns = @JoinColumn(name = "idproveedor"))
    private List<Proveedores> proveedores;

    public Productos() {

    }

    public Productos(String nombre, String descripcion, int stock) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<Proveedores> getProvedores() {
        return proveedores;
    }

    public void setProvedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Productos [idproducto=" + idproducto + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", stock=" + stock + ", categor�a=" + categoria + "]";
    }
}