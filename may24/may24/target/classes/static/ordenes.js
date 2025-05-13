

function cargaPagina() {
const inicio = document.getElementById('inicio');
const ingresarOrdenes = document.getElementById('ingresarOrdenes');
const ingresarOperaciones = document.getElementById('ver_Operaciones');
    window.location.hash = "inicio";
    inicio.style.display = 'block';
    ingresarOrdenes.style.display = 'none';
    ingresarOperaciones.style.display = 'none';
    verOrdenes();
}

function verOrdenes() {
//TODO #7
}
function verOperaciones() {
  fetch('/api/operaciones')
        .then(response => {
            if (!response.ok) {
                throw new Error('Fallo en la red');
            }
            return response.json();
        })
        .then(data => {
            const cuerpoTabla = document.getElementById('operacionesTabla').getElementsByTagName('tbody')[0];
            // Limpiar la tabla antes de añadir nuevas filas
            cuerpoTabla.innerHTML = '';

            data.forEach(operacion => {
                console.log(operacion);
                const row = cuerpoTabla.insertRow();
                const idOperacion = row.insertCell(0);
                const ordenVenta = row.insertCell(1);
                const ordenCompra = row.insertCell(2);
                const numAcciones = row.insertCell(3);
                idOperacion.textContent = operacion.id;
                ordenVenta.textContent = operacion.orden_venta.id;
                ordenCompra.textContent = operacion.orden_compra.id;
                numAcciones.textContent = operacion.num_accion;

            });
        })
        .catch(error => {
            console.error('Error al procesar la petición:', error.message);
        });
}



function enviarOrden() {
    const formulario = document.getElementById('formularioOrden');
    const formData = new FormData(formulario);
     const orden = {
            usuario: formData.get('usuario'),
            nombreAccion: formData.get('nombreAccion'),
            precio: Number(formData.get('precio')),
            numAcciones: Number(formData.get('numAcciones')),
            compraVenta: formData.get('compraVenta'),
            ejecutado: 'false'
        };
    //TODO #7

}

document.addEventListener('DOMContentLoaded', function() {
const inicio = document.getElementById('inicio');
const ingresarOrdenes = document.getElementById('ingresarOrdenes');
const ingresarOperaciones = document.getElementById('ver_Operaciones');
    const linkInicio = document.querySelector('nav a[href="#inicio"]');
    const linkIngresarOrdenes = document.querySelector('nav a[href="#ingresarOrdenes"]');
    const linkOperaciones = document.querySelector('nav a[href="#ver_Operaciones"]');

    linkInicio.addEventListener('click', function(e) {
        e.preventDefault();
        inicio.style.display = 'block';
        ingresarOrdenes.style.display = 'none';
        ingresarOperaciones.style.display = 'none';
    });

    linkIngresarOrdenes.addEventListener('click', function(e) {
        e.preventDefault();
        ingresarOrdenes.style.display = 'block';
        inicio.style.display = 'none';
        ingresarOperaciones.style.display = 'none';
    });

    linkOperaciones.addEventListener('click', function(e) {
        e.preventDefault();
        ingresarOperaciones.style.display = 'block';
        inicio.style.display = 'none';
        ingresarOrdenes.style.display = 'none';
    });
});
function enviarOrden() {
    const formulario = document.getElementById('formularioOrden');
    const formData = new FormData(formulario);
     const orden = {
            usuario: formData.get('usuario'),
            nombreAccion: formData.get('nombreAccion'),
            precio: Number(formData.get('precio')),
            numAcciones: Number(formData.get('numAcciones')),
            compraVenta: formData.get('compraVenta'),
            ejecutado: 'false'
        };

   fetch('/api/ordenes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orden)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar la orden');
        }
        return response.json();
    })
    .then(data => {
        // Limpiar el formulario después de enviar la orden
        formulario.reset();
        alert('Orden enviada con éxito');
    })
    .catch(error => {
        console.error('Error:', error.message);
        alert('Hubo un error al enviar la orden');
    });
}