package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "producto_proveedor")
public class Producto_Proveedor implements Serializable {
    // LA PRIMARY KEY ESTA CONFORMADA POR EL ID DE PROVEEDORES Y EL ID DE PRODUCTO
    @Id
    @ManyToOne // RELACION INVERSA
    @JoinColumn(name = "idproveedor", referencedColumnName = "idproveedor")
    private Proveedores proveedores; // SE TRABAJA MAS FACIL CON OBJETOS

    @Id
    @ManyToOne // RELACION INVERSA
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    private Productos productos; // SE TRABAJA MAS FACIL CON OBJETOS

    @Column(name = "cantidad")
    private int cantidad;

    public Producto_Proveedor() {
    }

    public Producto_Proveedor(Proveedores proveedores, Productos productos, int cantidad) {
        this.proveedores = proveedores;
        this.productos = productos;
        this.cantidad = cantidad;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto_Proveedor [proveedores=" + proveedores + ", productos=" + productos + ", cantidad=" + cantidad + "]";
    }
}