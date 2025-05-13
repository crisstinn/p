package edu.comillas.icai.gitt.pat.spring.may24.controlador;

import edu.comillas.icai.gitt.pat.spring.may24.entidades.Operacion;
import edu.comillas.icai.gitt.pat.spring.may24.entidades.Orden;
import edu.comillas.icai.gitt.pat.spring.may24.servicios.ServicioAcciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
//TODO #4

/**TODO #4 (10/85): Modificar los métodos de ControladorAcciones para que hagan la llamada a los servicios,
 * en este momento los test #3 siguen sin funcionar
 *
 */

@RestController
public class ControladorAcciones {
    @Autowired
    ServicioAcciones servicioAcciones;

    @PostMapping("/api/ordenes")
    @ResponseStatus(HttpStatus.CREATED)
    public Orden creaOrden(@RequestBody Orden orden) {
        try {
            return servicioAcciones.creaOrdenes(orden);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
    @GetMapping("/api/ordenes")
    public List<Orden> leeOrdenes() {
        return servicioAcciones.leeOrdenes();

    }
    @GetMapping("/api/ordenes/{id_orden}")
    public Orden leeOrden(@PathVariable Long id_orden) {
        return servicioAcciones.leeOrden(id_orden);
    }

    @PutMapping("/api/ordenes/actualiza")
    public Orden actualizaOrden(@RequestBody Orden orden) {
        return servicioAcciones.actualizaOrden(orden);
    }

    @DeleteMapping("/api/ordenes/{id_orden}")
    public void borrarOrden(@PathVariable Long id_orden)  {
        servicioAcciones.borra(id_orden);
        return;
    }
    @Scheduled(fixedRate = 60000) // cada 60.000 ms = 1 minuto
    @PostMapping("/api/operacion/cruzar") @ResponseStatus(HttpStatus.CREATED)
    public void cruzarOrdenes() {
        servicioAcciones.ejecutaOrdenes();
        return;
    }
    @GetMapping("/api/operaciones")
    public List<Operacion> leerOperacion() {
        return servicioAcciones.leeOperaciones();
    }


}
