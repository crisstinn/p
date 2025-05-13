package edu.comillas.icai.gitt.pat.spring.may24.entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

//TODO #2 (10/85): Implementar las entidades Orden y Operación según la estructura de data.sql Nota:
// Dentro de la Entidad Orden en el atributo compraVenta hay que indicar la siguiente anotación:
// @Pattern(regexp = "^(compra|venta)$", message = "El valor debe ser 'compra' o 'venta'")
// En este momento probar los test 1 para comprobar su funcionamiento

@Entity

//TODO 2: Rellenar la entidad Orden
@Table(name = "orden")
public class Orden
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(nullable = false)
    public String usuario;

    @Column(nullable = false)
    public String nombreAccion;

    @Column(nullable = false)
    public int precio;

    @Column(nullable = false)
    public int numAcciones;

    @Column(nullable = false)
    @Pattern(regexp = "^(compra|venta)$", message = "El valor debe ser 'compra' o 'venta'")
    public String compraventa;

    @Column(nullable = false)
    public boolean ejecutado;

    public Orden(){}

    public Orden(long id, String usuario, String nombreAccion,  int precio, int numAcciones,  String compraventa, Boolean ejecutado) {
        this.id = id;
        this.ejecutado = ejecutado;
        this.compraventa = compraventa;
        this.numAcciones = numAcciones;
        this.precio = precio;
        this.nombreAccion = nombreAccion;
        this.usuario = usuario;
    }
    public Orden(String usuario, String nombreAccion,  int precio, int numAcciones,  String compraventa, Boolean ejecutado) {
        this.ejecutado = ejecutado;
        this.compraventa = compraventa;
        this.numAcciones = numAcciones;
        this.precio = precio;
        this.nombreAccion = nombreAccion;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getEjecutado() {
        return ejecutado;
    }

    public void setEjecutado(boolean ejecutado) {
        this.ejecutado = ejecutado;
    }

    public String getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(String compraventa) {
        this.compraventa = compraventa;
    }

    public int getNumAcciones() {
        return numAcciones;
    }

    public void setNumAcciones(int numAcciones) {
        this.numAcciones = numAcciones;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
