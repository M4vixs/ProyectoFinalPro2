// Espera a que todo el HTML esté cargado
document.addEventListener('DOMContentLoaded', () => {

    // OBTENER Y VALIDAR AL USUARIO
    const usuario = getUsuarioYValidarRol("OFICIAL");
    if (!usuario) return;

    // VERIFICACIÓN DE ROL (¡MUY IMPORTANTE!)
    if (usuario.tipoUser.trim().toUpperCase() !== "OFICIAL") {
        alert("Acceso denegado. Esta página es solo para Oficiales.");
        window.location.href = 'login.html';
        return;
    }

    // CARGAR DATOS DEL OFICIAL (Sección "Mis Datos")
    document.getElementById('oficial-nombre').textContent = `${usuario.nombre} ${usuario.apellido}`;
    document.getElementById('oficial-rol').textContent = usuario.tipoUser;

    // (Asegúrate de que estas propiedades existan en tu JSON de 'usuario')
    if (usuario.tipoCuerpo) {
        document.getElementById('oficial-cuerpo').textContent = usuario.tipoCuerpo.denominacion_cuerpo;
    }
    if (usuario.comp) {
        document.getElementById('oficial-compania').textContent = usuario.comp.denominacion_compania;
    }
    if (usuario.cuart) {
        document.getElementById('oficial-cuartel').textContent = usuario.cuart.nombre_cuartel;
    }

    cargarServicios(usuario.codigo);

    // ASIGNAR EVENTOS A LOS BOTONES

    // Botón de Salir (Logout)
    document.getElementById('btn-logout').addEventListener('click', () => {
        sessionStorage.removeItem('usuario');
        window.location.href = 'login.html';
    });

    // --- Eventos de Gestión de Entidades ---
    document.getElementById('btn-crear-usuario').addEventListener('click', crearUsuario);
    document.getElementById('btn-crear-cuerpo').addEventListener('click', crearCuerpo);
    document.getElementById('btn-crear-cuartel').addEventListener('click', crearCuartel);
    document.getElementById('btn-crear-compania').addEventListener('click', crearCompania);
    document.getElementById('btn-search-servicios').addEventListener('click', buscarServiciosUsuario);

    // --- Evento de Asignar Servicio ---
    document.getElementById('btn-asignar-servicio').addEventListener('click', asignarServicio);
});




async function crearCuerpo() {
    const nombre = document.getElementById('nombre-cuerpo').value;
    const mensajeDiv = document.getElementById('mensaje-gestion');

    if (!nombre) {
        mostrarMensaje(mensajeDiv, 'El nombre del cuerpo no puede estar vacío.', 'error');
        return;
    }

    const datosCuerpo = { denominacion_cuerpo: nombre };

    const respuesta = await fetch('http://localhost:8080/api/oficial/crear-cuerpo', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datosCuerpo)
    });

    if (respuesta.ok) {
        mostrarMensaje(mensajeDiv, '¡Cuerpo creado con éxito!', 'exito');
        document.getElementById('nombre-cuerpo').value = ''; // Limpiar campo
    } else {
        mostrarMensaje(mensajeDiv, 'Error al crear el cuerpo.', 'error');
    }
}

async function crearCuartel() {
    const nombre = document.getElementById('nombre-cuartel').value;
    const ubicacion = document.getElementById('ubicacion-cuartel').value;
    const mensajeDiv = document.getElementById('mensaje-gestion');

    if (!nombre || !ubicacion) {
        mostrarMensaje(mensajeDiv, 'Nombre y ubicación son obligatorios.', 'error');
        return;
    }

    const datosCuartel = { nombre_cuartel: nombre, ubicacion: ubicacion };

    const respuesta = await fetch('http://localhost:8080/api/oficial/crear-cuartel', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datosCuartel)
    });

    if (respuesta.ok) {
        mostrarMensaje(mensajeDiv, '¡Cuartel creado con éxito!', 'exito');
        document.getElementById('nombre-cuartel').value = '';
        document.getElementById('ubicacion-cuartel').value = '';
    } else {
        mostrarMensaje(mensajeDiv, 'Error al crear el cuartel.', 'error');
    }
}

async function crearCompania() {
    const nombre = document.getElementById('nombre-compania').value;
    const mensajeDiv = document.getElementById('mensaje-gestion');

    if (!nombre) {
        mostrarMensaje(mensajeDiv, 'El nombre de la compañía no puede estar vacío.', 'error');
        return;
    }

    const datosCompania = {
        denominacion_compania: nombre
    };

    try {
        const respuesta = await fetch('http://localhost:8080/api/oficial/crear-compania', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datosCompania)
        });

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, '¡Compañía creada con éxito!', 'exito');
            document.getElementById('nombre-compania').value = ''; // Limpiar campo
        } else {
            mostrarMensaje(mensajeDiv, 'Error al crear la compañía.', 'error');
        }

    } catch (error) {
        mostrarMensaje(mensajeDiv, 'Error de conexión con el servidor.', 'error');
    }
}

async function asignarServicio() {
    const idUsuario = document.getElementById('id-usuario-servicio').value;
    const idServicio = document.getElementById('id-servicio-asignar').value;
    const mensajeDiv = document.getElementById('mensaje-asignar');

    if (!idUsuario || !idServicio) {
        mostrarMensaje(mensajeDiv, 'ID de Usuario y ID de Servicio son obligatorios.', 'error');
        return;
    }

    // Preparamos los datos que enviaremos en el body
    const datosAsignacion = {
        idUsuario: idUsuario,
        idServicio: idServicio
    };

    // ¡NECESITAREMOS CREAR ESTE ENDPOINT EN JAVA!
    try {
        const respuesta = await fetch('/api/oficial/asignar-servicio', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datosAsignacion)
        });

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, '¡Servicio asignado con éxito!', 'exito');
            document.getElementById('id-usuario-servicio').value = '';
            document.getElementById('id-servicio-asignar').value = '';
        } else {
            const error = await respuesta.text();
            mostrarMensaje(mensajeDiv, `Error: ${error}`, 'error');
        }
    } catch (error) {
        mostrarMensaje(mensajeDiv, 'Error de conexión con el servidor.', 'error');
    }
}

async function crearUsuario() {
    const nombre = document.getElementById('new-nombre').value;
    const apellido = document.getElementById('new-apellido').value;
    const email = document.getElementById('new-email').value;
    const password = document.getElementById('new-pass').value;
    const rol = document.getElementById('new-rol').value;
    const idCuerpo = document.getElementById('new-id-cuerpo').value;
    const idCompania = document.getElementById('new-id-compania').value;
    const idCuartel = document.getElementById('new-id-cuartel').value;

    const mensajeDiv = document.getElementById('mensaje-usuario');

    // Validación simple
    if (!nombre || !email || !password || !idCuerpo) {
        mostrarMensaje(mensajeDiv, 'Por favor completa los campos obligatorios', 'error');
        return;
    }

    const datos = {
        nombre: nombre,
        apellido: apellido,
        email: email,
        password: password,
        rol: rol,
        idCuerpo: idCuerpo,
        idCompania: idCompania,
        idCuartel: idCuartel
    };

    try {
        const respuesta = await fetch('/api/oficial/crear-usuario', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        });

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, 'Usuario registrado con éxito', 'exito');
            // Limpiar campos si quieres...
        } else {
            mostrarMensaje(mensajeDiv, 'Error al registrar. Verifica los IDs.', 'error');
        }
    } catch (e) {
        mostrarMensaje(mensajeDiv, 'Error de conexión', 'error');
    }
}

async function buscarServiciosUsuario() {
    const id = document.getElementById('search-user-id').value;
    const lista = document.getElementById('lista-servicios-busqueda');
    const mensajeDiv = document.getElementById('mensaje-busqueda');

    if (!id) return;
    lista.innerHTML = '<li>Cargando...</li>';

    try {
        // ¡Llama al endpoint del OFICIAL!
        const respuesta = await fetch(`/api/oficial/servicios-usuario/${id}`);

        if (respuesta.ok) {
            const servicios = await respuesta.json();
            // Usamos la función de shared.js para dibujar la lista
            renderServiciosList(servicios, lista);
        } else {
            const error = await respuesta.text();
            mostrarMensaje(mensajeDiv, error, 'error');
            lista.innerHTML = '';
        }
    } catch(e) {
        mostrarMensaje(mensajeDiv, 'Error de conexión', 'error');
        lista.innerHTML = '';
    }
}
