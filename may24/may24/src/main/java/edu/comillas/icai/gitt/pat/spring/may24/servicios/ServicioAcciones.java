package edu.comillas.icai.gitt.pat.spring.may24.servicios;


import edu.comillas.icai.gitt.pat.spring.may24.entidades.Operacion;
import edu.comillas.icai.gitt.pat.spring.may24.entidades.Orden;
import edu.comillas.icai.gitt.pat.spring.may24.repositorios.RepoOperacion;
import edu.comillas.icai.gitt.pat.spring.may24.repositorios.RepoOrden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAcciones {
    @Autowired RepoOrden repoOrden;
    @Autowired
    private RepoOperacion repoOperacion;


    //TODO #4
    public Orden creaOrdenes(Orden nuevaOrden) {
        Orden ord=new Orden();
        ord=nuevaOrden;
        return ord;
    }

    //TODO #4
    public Orden leeOrden(Long id_orden) {
        Optional<Orden> optionalOrden=repoOrden.findById(id_orden);
        if(optionalOrden.isPresent())
        {
           return optionalOrden.get();
        }

      return null;
    }
    //TODO #4
    public List<Orden> leeOrdenes()
    {
        Iterable<Orden> iterableOrden=repoOrden.findAll();
        List <Orden> lista=new ArrayList<>();
        for (Orden o : iterableOrden) {
            lista.add(o);
        }
        return lista;
    }
    //TODO #4
    public void borra(Long id_orden)  {

        Optional<Orden> optionalOrden=repoOrden.findById(id_orden);
        if(optionalOrden.isPresent()){
            repoOrden.delete(optionalOrden.get());
        }
            return;
    }
    //TODO #4
    public Orden actualizaOrden(Orden orden) {
        String newusuario=orden.getUsuario();
        String newnombreAccion= orden.getNombreAccion();
        int newprecio= orden.getPrecio();
        int newnumAcciones= orden.getNumAcciones();
        String newcompraventa=orden.getCompraventa();
        boolean newejecutado= orden.getEjecutado();
        Orden ord= new Orden(newusuario, newnombreAccion, newprecio, newnumAcciones,  newcompraventa, newejecutado);

        return ord;
    }
    //TODO #5
    @Transactional
    public void ejecutaOrdenes() {
        List<Orden> ordenesTotales = leeOrdenes();
        List<Orden> compras = new ArrayList<>();
        List<Orden> ventas = new ArrayList<>();

        for (Orden o : ordenesTotales) {
            if (!o.getEjecutado()) {
                if (o.getCompraventa().equals("compra")) compras.add(o);
                else if (o.getCompraventa().equals("venta")) ventas.add(o);
            }
        }

        for (Orden compra : compras) {
            for (Orden venta : ventas) {
                if (!compra.getEjecutado() && !venta.getEjecutado()
                        && compra.getNombreAccion().equals(venta.getNombreAccion())
                        && compra.getPrecio() >= venta.getPrecio()) {

                    int numCruce = Math.min(compra.getNumAcciones(), venta.getNumAcciones());

                    Operacion op = new Operacion(compra, venta, numCruce);
                    repoOperacion.save(op);

                    compra.setNumAcciones(compra.getNumAcciones() - numCruce);
                    venta.setNumAcciones(venta.getNumAcciones() - numCruce);

                    if (compra.getNumAcciones() == 0) compra.setEjecutado(true);
                    if (venta.getNumAcciones() == 0) venta.setEjecutado(true);

                    repoOrden.save(compra);
                    repoOrden.save(venta);
                }
            }
        }
    }


    //TODO #4
    public List<Operacion> leeOperaciones() {
        Iterable<Operacion> iterableOperacion = repoOperacion.findAll();
        List<Operacion> lista = new ArrayList<>();
        for (Operacion o: iterableOperacion) {
            lista.add(o);
        }
        return lista;
    }
}
