package edu.comillas.icai.gitt.pat.spring.may24.model;

import edu.comillas.icai.gitt.pat.spring.may24.entidades.Operacion;
import edu.comillas.icai.gitt.pat.spring.may24.entidades.Orden;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        //TODO #1
        Orden ord= new Orden("atilanof","TELEFONICA",150,100,"compra", true);
        Set<ConstraintViolation<Orden>> violations = validator.validate(ord);
        Assertions.assertTrue(violations.isEmpty());

    }

    @Test
    public void testValidarCompraVenta() {
        //TODO #1
        Orden ord= new Orden("atilanof","TELEFONICA",150,100,"pepe", true);
        Set<ConstraintViolation<Orden>> violations = validator.validate(ord);

        assertFalse(violations.isEmpty());
        List<ConstraintViolation<Orden>> errores = new ArrayList<>(violations);
        assertEquals("compraventa", errores.get(0).getPropertyPath().toString());
        assertEquals("pepe", errores.get(0).getInvalidValue());


        Assertions.assertEquals(1, violations.size());

    }

}
