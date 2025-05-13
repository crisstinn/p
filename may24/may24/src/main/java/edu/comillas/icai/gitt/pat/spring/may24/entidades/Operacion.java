package edu.comillas.icai.gitt.pat.spring.may24.entidades;


import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Operacion {
    //TODO 2: Rellenar la entidad Operación
    //TODO #2 (10/85): Implementar las entidades Orden y Operación según la estructura de data.sql Nota:
    // Dentro de la Entidad Orden en el atributo compraVenta hay que indicar la siguiente anotación:
    // @Pattern(regexp = "^(compra|venta)$", message = "El valor debe ser 'compra' o 'venta'")
    // En este momento probar los test 1 para comprobar su funcionamiento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    //clave foranea le tienes que poner nombre a la columna a la que se une
    @ManyToOne //varias operaciones pueden estar asociadas a una unica orden
    @JoinColumn(name="ope_compra_id", nullable = false)
    public Orden operacion_compra_id;

    //clave foranea, le tienes que poner nombre a la columna a la que se une
    @ManyToOne //varias operaciones pueden estar asociadas a una unica orden
    @JoinColumn(name="ope_venta_id", nullable = false)
    public Orden operacion_venta_id;

    public int num_accion;

    public Operacion(){}

    public Operacion(long id, Orden operacion_compra_id, Orden operacion_venta_id, int num_accion) {
        this.id = id;
        this.operacion_compra_id = operacion_compra_id;
        this.operacion_venta_id = operacion_venta_id;
        this.num_accion = num_accion;
    }
    public Operacion(Orden operacion_compra_id, Orden operacion_venta_id, int num_accion) {
        this.operacion_compra_id = operacion_compra_id;
        this.operacion_venta_id = operacion_venta_id;
        this.num_accion = num_accion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Orden getOperacion_compra_id() {
        return operacion_compra_id;
    }

    public void setOperacion_compra_id(Orden operacion_compra_id) {
        this.operacion_compra_id = operacion_compra_id;
    }

    public Orden getOperacion_venta_id() {
        return operacion_venta_id;
    }

    public void setOperacion_venta_id(Orden operacion_venta_id) {
        this.operacion_venta_id = operacion_venta_id;
    }

    public int getNum_accion() {
        return num_accion;
    }

    public void setNum_accion(int num_accion) {
        this.num_accion = num_accion;
    }

}
