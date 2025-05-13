package edu.comillas.icai.gitt.pat.spring.may24.repository;

import edu.comillas.icai.gitt.pat.spring.may24.entidades.Operacion;
import edu.comillas.icai.gitt.pat.spring.may24.entidades.Orden;
import edu.comillas.icai.gitt.pat.spring.may24.repositorios.RepoOperacion;
import edu.comillas.icai.gitt.pat.spring.may24.repositorios.RepoOrden;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** * b) RepositoryIntegrationTest.- Realizar los siguientes test de integración:
 * b.1) guardarTest --> Se guarda correctamente una orden, una persistida buscándola y el resultado sea no nulo
 * b.2) integridadTest -->Dar de alta una operación sin tener dadas de alta sus ordenes de compra y de venta da una violación de integridad
 * */

@DataJpaTest

public class RepositoryIntegrationTest {
    @Autowired RepoOrden repoOrden;
    @Autowired RepoOperacion repoOperacion;


    @Test void guardarTest() {
        //Dado
        //TODO #3
        Orden ord= new Orden("atilanof","TELEFONICA",150,100,"pepe", true);
        ord = repoOrden.save(ord);
        Optional<Orden> recuperado = repoOrden.findById(ord.getId()); //lo buscas
        //chequeas que se haya guardado correctamente
        assertTrue(recuperado.isPresent());
        assertEquals("atilanof", recuperado.get().usuario);

    }
    @Test void integridadTest()
    {
        //Dado
        //TODO #3
        Orden compr=new Orden();
        Orden vent=new Orden();
        Operacion ope=new Operacion(compr,vent, 100);

        assertThrows(DataIntegrityViolationException.class, () -> {repoOperacion.save(ope);});

    }
}
