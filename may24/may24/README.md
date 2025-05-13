# Examen mayo 24 (85/100 puntos)
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de acciones.
Existirán tres tablas, una de acciones, otra de órdenes y una última de operaciones (ver data.sql para ver estructura)
La lógica de la aplicación es la siguiente:
    1) En la tabla de acciones se guardan las acciones que tiene el usuario (el número de acciones puede ser 0)
    2) En la tabla órdenes se almacenan las órdenes que pone el usuario con sus acciones con la siguiente información:
        2.1) Número de acciones que participaran en la operación.
        2.2) El precio de la operación en pesetas, por tanto, no hay decimales lo que facilita el resto de operaciones.
        2.3) Si la orden es de compra o de venta (true/false respectivamente).
        2.4) Si la operación se ha ejecutado o no (true/false respectivamente).
        2.5) No puede existir más de una orden compra o de venta del mismo usuario sobre la misma acción.
        2.6) Si la orden es de compra y el usuario todavía no tenía esas acciones se crea una entrada en acciones de
        ese usuario, sobre esa acción con número 0. 
        2.7) La orden de venta no puede ser mayor que el número de acciones que tenga ese usuario de esa acción.        
    3) En la tabla de operaciones se guardan los cruces de las órdenes de compra venta con la siguiente lógica:
        3.1) Debe existir una orden de compra y una orden de venta sobre la misma acción, de diferente usuario y con 
        el mismo precio. 
        3.2) Cuando se cumpla el punto 3.1 se procederá a realizar una entrada en la tabla de operaciones indicando
        el precio de la operación y el número de acciones que han entrado en la operación, que será el mínimo entre
        la operación de compra y de venta, se actualizará las dos filas de tabla órdenes con dos posibles casuísticas:
                3.2.1) Si la orden (compra y/o venta) se ha ejecutado completamente, se debe actualizar el
                    campo ejecución a true.
                3.2.2) Si una de las órdenes (compra y/o venta) no se ha ejecutado completamente, se actualiza el nº 
                de acciones que quedan disponible para otra operación.
        3.3) Se actualiza para el usuario el número de acciones que tiene: Si la operación ha sido de compra, se 
        incrementa, si ha sido de venta se decrementa.
        3.4) Esta lógica se implementa en una tarea programada que se ejecutará cada minuto. 

## Endpoints

// TODO#1
// Rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta                   | Descripción                                                                                                                                  | Respuestas                                                                                                   |
|--------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| POST   | /api/ordenes           | Crea una orden: {"usuario": "atilanof","nombreAccion": "Endesa","precio": 1000,"numAcciones": 50,"compraVenta": "compra","ejecutado": false} | 201 si OK, 401 si es venta y no hay suficientes acciones, 409 ya existía otra orden                          |
| PUT    | /api/ordenes/actualiza | Actualiza una orden, se adjunta la orden a modificar.                                                                                        | 200 si OK: Actualiza la orden que se ha facilitado en el json                                                |
| GET    | /api/ordenes           | Devuelve las acciones de un usuario                                                                                                          | 200 si OK: Lista con todas las ordenes                                                                       |
| GET    | /api/ordenes/{id_orden} | Obtiene los una orden                                                                                                                        | 200 si OK: {"id_accion": 4, "precio": 2500, "num_acciones": 15, "compra": "true"}, 401 si no existe la orden |
| DELETE | /api/ordenes/{id_orden} | Borra la orden                                                                                                                               | 204 si OK, 401 si no existe la orden                                                                         |
| POST   | /api/operaciones       | Ejecuta el servicio que cruza las operaciones                                                                                                | 200 si OK                                                                                                    |
| GET    | /api/operaciones       | Devuelve todas la operaciones                                                                                                                | 200 si OK, 401 si no existe la orden                                                                         |

Existe un fichero api.http donde el alumno puede probar sus end-point

A continuación, se citan los TODO's a realizar por el alumno.

TODO #1 (5/85): Implementar los test RegisterRquestUnitTest para probar el modelo definido anteriormente:
    a) testValidRequest --> Se da de alta una orden correcta y no se viola ninguna restricción
    b) testValidarCompraVenta --> Se da de alta una orden informando en el campo compraVenta "pepe" la prueba valida que:
        b.1) Únicamente existe una violación
        b.2) Esa violación se da en el campo "compraVenta"
        b.3) El valor erróneo es "pepe"

TODO #2 (10/85): Implementar las entidades Orden y Operación según la estructura de data.sql
Nota: Dentro de la Entidad Orden en el atricuto compraVenta hay que indicar la siguiente anotación:
@Pattern(regexp = "^(compra|venta)$", message = "El valor debe ser 'compra' o 'venta'")
En este momento probar los test 1 para comprobar su funcionamiento

TODO #3 (10/85): Implementar los test RepositoryIntegrationTest y May24ApplicationTests:
    a) May24ApplicationTests.- Se ha definido en setup como se configura el test y este método se ejecuta al comenzar el test, es decir, se carga la prueba con una orden:
        a.1)  altaOrdenDuplicada --> Rellenar el then para que al cargar una orden idéntica al setup de un error de conflicto
        a.2) busquedaOrden --> Buscando la orden cargada en setup la devuelve correctamente. 
    b) RepositoryIntegrationTest.- Realizar los siguientes test de integración:
        b.1) guardarTest --> Se guarda correctamente una orden, una persistida buscándola y el resultado sea no nulo
        b.2) integridadTest -->Dar de alta una operación sin tener dadas de alta sus ordenes de compra y de venta da una violación de integridad


TODO #4 (10/85): Modificar los métodos de ControladorAcciones para que hagan la llamada a los servicios, en este momento los test #3 siguen sin funcionar

TODO #4 (15/85): Modificar las clases ServiciosAcciones (menos el método ejecutaOrdenes()) y los RepoOperacion y RepoOrden para que pasen los test y cumplimentar todos los endpoint menos /api/operacion/cruzar 
Ejecutar en este momentos la cobertura de pruebas e indicar el % de cobertura de clase, métodos y líneas: XX%| XX% | XX% (sustituir las XXX por el valor mostrado por el IDE)

TODO #5 (15/85): Implementar el método ejecutaOrdenes() de serviciosOrdnees con la lógica definida la comienzo del enunciado

TODO #6 (10/85): Implementar desde 0 la clase CruzarOrdenes para crear una tarea programada que llame al método ejecutarOrdenes cada minuto y modificar las anotaciones para habilitar su ejecución

TODO #7 (10/85): Modificar el fichero JavaScript ordenes.js para que:
    a) verOrdenes() --> Cargue la tabla ordenesTabla con las ordenes que existan en el sistema. Se recomienda encarecidamente al alumne que revise el método verOperaciones() para facilitar su inspiración
    b) enviarOrden() --> Complementar el método para lanzar una petición POST con la informacion de una orden. 