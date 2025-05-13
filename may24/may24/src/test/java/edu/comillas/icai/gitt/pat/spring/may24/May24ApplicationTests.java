package edu.comillas.icai.gitt.pat.spring.may24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**TODO #3 (10/85): Implementar los test RepositoryIntegrationTest y May24ApplicationTests:
 * a) May24ApplicationTests.- Se ha definido en setup como se configura el test y este método se ejecuta al comenzar el test, es decir, se carga la prueba con una orden:
 * a.1) altaOrdenDuplicada --> Rellenar el then para que al cargar una orden idéntica al setup de un error de conflicto
 * a.2) busquedaOrden --> Buscando la orden cargada en setup la devuelve correctamente.
 *
 * b) RepositoryIntegrationTest.- Realizar los siguientes test de integración:
 * b.1) guardarTest --> Se guarda correctamente una orden, una persistida buscándola y el resultado sea no nulo
 * b.2) integridadTest -->Dar de alta una operación sin tener dadas de alta sus ordenes de compra y de venta da una violación de integridad
 * */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

class May24ApplicationTests {
	private static final int ID= 1;
	private static final String USUARIO="atilanof";
	private static final String NOMBREAACCION="Endesa";
	private static final int PRECIO = 1000;
	private static final int NUMACCION = 50;
	private static final String COMPRAVENTA= "compra";
	private static final Boolean EJECUTADO = false;

	@Autowired
	TestRestTemplate client;

	@BeforeEach
	public void setup() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String orden = "{" +
				"\"usuario\":\"" + USUARIO + "\"," +
				"\"nombreAccion\":\"" + NOMBREAACCION + "\"," +
				"\"precio\":\"" + PRECIO + "\"," +
				"\"numAcciones\":\"" + NUMACCION + "\"," +
				"\"compraVenta\":\"" + COMPRAVENTA + "\"," +
				"\"ejecutado\":\"" + EJECUTADO + "\"}";
		ResponseEntity<String> response = client.exchange(
				"http://localhost:8080/api/ordenes",
				HttpMethod.POST, new HttpEntity<>(orden, headers), String.class);
	}
	@Test
	public void altaOrdenDuplicada() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String orden= "{" +
				"\"usuario\":\"" + USUARIO + "\"," +
				"\"nombreAccion\":\"" + NOMBREAACCION + "\"," +
				"\"precio\":\"" + PRECIO + "\"," +
				"\"numAcciones\":\"" + NUMACCION + "\"," +
				"\"compraVenta\":\"" + COMPRAVENTA + "\"," +
				"\"ejecutado\":\"" + EJECUTADO + "\"}";
		ResponseEntity<String> response = client.exchange(
				"http://localhost:8080/api/ordenes",
				HttpMethod.POST, new HttpEntity<>(orden, headers), String.class);

		// Then ...
		Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		//TODO #3

	}
	@Test
	public void busquedaOrden() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = client.exchange(
				"http://localhost:8080/api/ordenes/1",
				HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

		// Then ...
		//TODO #3
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("atilanof"));
	}
}
