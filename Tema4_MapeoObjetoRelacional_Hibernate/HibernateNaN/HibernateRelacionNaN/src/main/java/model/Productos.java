package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

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

    /********* RELACION 1 A N ***********/
    // RELACION OneToMany CON Producto_Proveedor
    // EL MAPPED BY RELACIONA EL NOMBRE DEL ATRIBUTO QUE TIENE LA RELACION INVERSA (ManyToOne)
    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    private Set<Producto_Proveedor> listaProductos = new HashSet<>();

    /********* RELACION N A N ***********/
    /*/// LA DEFINICION VA TODA JUNTA
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "producto_proveedor", //OJO es donde le digo que se crea la tabla
            joinColumns = @JoinColumn(name = "idproducto"),
            inverseJoinColumns = @JoinColumn(name = "idproveedor"))
    private List<Proveedores> proveedores;*/

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

    /*public List<Proveedores> getProvedores() {
        return proveedores;
    }

    public void setProvedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }*/

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

    public Set<Producto_Proveedor> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Set<Producto_Proveedor> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "Productos [idproducto=" + idproducto + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", stock=" + stock + ", categor�a=" + categoria + "]";
    }

    // METODO PARA AGREGAR UN Producto_Proveedor
    public void addProductoProveedor(Producto_Proveedor productoProveedor) {
        this.listaProductos.add(productoProveedor);
    }

    /*public void addProveedor(Proveedores proveedor) {
        if (proveedores == null) {
            proveedores = new ArrayList<Proveedores>();
            //mas eficiente utilizando set
            //private Set<Proveedores> proveedores=new HashSet();
        }
        proveedores.add(proveedor);
    }*/
}