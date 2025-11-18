document.addEventListener('DOMContentLoaded', () => {

    // VALIDACIÓN DE USUARIO Y ROL
    const usuario = getUsuarioYValidarRol("SUBOFICIAL");
    if (!usuario) return;

    // Validar que sea SUBOFICIAL
    if (usuario.tipoUser.trim().toUpperCase() !== "SUBOFICIAL") {
        alert("Acceso denegado.");
        window.location.href = 'login.html';
        return;
    }

    //  CARGAR DATOS DEL PERFIL
    document.getElementById('sub-nombre').textContent = `${usuario.nombre} ${usuario.apellido}`;
    document.getElementById('sub-rol').textContent = usuario.tipoUser;
    if (usuario.cuart) {
        document.getElementById('sub-cuartel').textContent = usuario.cuart.nombre_cuartel;
    }
    if (usuario.comp) {
        document.getElementById('sub-compania').textContent = usuario.comp.denominacion_compania;
    }
    if (usuario.tipoCuerpo) {
        document.getElementById('sub-cuerpo').textContent = usuario.tipoCuerpo.denominacion_cuerpo;
    }

    // 3. EVENTOS DE BOTONES
    setupLogout('btn-logout')

    document.getElementById('btn-crear-servicio').addEventListener('click', crearServicio);
    document.getElementById('btn-cargar-soldados').addEventListener('click', cargarSoldados);
    document.getElementById('btn-asignar-servicio').addEventListener('click', asignarServicio);
    document.getElementById('btn-search-servicios').addEventListener('click', buscarServiciosUsuario);

    // Cargar soldados automáticamente al entrar
    cargarSoldados();
    cargarServicios(usuario.codigo);
});

async function crearServicio() {
    const nombre = document.getElementById('nombre-servicio').value;
    const descripcion = document.getElementById('desc-servicio').value;
    const mensajeDiv = document.getElementById('mensaje-servicio');

    if (!nombre || !descripcion) {
        mostrarMensaje(mensajeDiv, "Completa todos los campos", "error");
        return;
    }

    const datos = { nombre_servicio: nombre, descripcion: descripcion };

    try {
        const respuesta = await fetch('/api/suboficial/crear-servicio', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        });

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, "Servicio creado con éxito", "exito");
            document.getElementById('nombre-servicio').value = "";
            document.getElementById('desc-servicio').value = "";
        } else {
            mostrarMensaje(mensajeDiv, "Error al crear servicio", "error");
        }
    } catch (e) {
        mostrarMensaje(mensajeDiv, "Error de conexión", "error");
    }
}

async function cargarSoldados() {
    const lista = document.getElementById('lista-soldados');
    lista.innerHTML = "<li>Cargando...</li>";

    try {
        const respuesta = await fetch('/api/suboficial/listar-soldados');
        if (respuesta.ok) {
            const soldados = await respuesta.json();
            lista.innerHTML = ""; // Limpiar

            if (soldados.length === 0) {
                lista.innerHTML = "<li>No hay soldados registrados.</li>";
                return;
            }

            soldados.forEach(s => {
                // Creamos un elemento de lista para cada soldado
                // Muestra: ID - Nombre Apellido (Email)
                const li = document.createElement('li');
                li.textContent = `#${s.codigo} - ${s.nombre} ${s.apellido} (${s.email})`;
                li.style.padding = "5px 0";
                li.style.borderBottom = "1px solid #555";
                lista.appendChild(li);
            });
        } else {
            lista.innerHTML = "<li>Error al cargar soldados.</li>";
        }
    } catch (e) {
        lista.innerHTML = "<li>Error de conexión.</li>";
    }
}

async function asignarServicio() {
    const idUsuario = document.getElementById('asig-id-usuario').value;
    const idServicio = document.getElementById('asig-id-servicio').value;
    const mensajeDiv = document.getElementById('mensaje-asignar');

    if (!idUsuario || !idServicio) {
        mostrarMensaje(mensajeDiv, "Ambos IDs son obligatorios", "error");
        return;
    }

    const datos = { idUsuario: parseInt(idUsuario), idServicio: parseInt(idServicio) };

    try {
        const respuesta = await fetch('/api/suboficial/asignar-servicio', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        });

        const textoRespuesta = await respuesta.text();

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, textoRespuesta, "exito");
            // Limpiar campos
            document.getElementById('asig-id-usuario').value = "";
            document.getElementById('asig-id-servicio').value = "";
        } else {
            // El backend nos envía el error (Ej: "No tiene permiso...")
            mostrarMensaje(mensajeDiv, textoRespuesta, "error");
        }
    } catch (e) {
        mostrarMensaje(mensajeDiv, "Error de conexión", "error");
    }
}

async function buscarServiciosUsuario() {
    const id = document.getElementById('search-user-id').value;
    const lista = document.getElementById('lista-servicios-busqueda');
    const mensajeDiv = document.getElementById('mensaje-busqueda');

    // Limpiar mensajes anteriores
    lista.innerHTML = '<li>Cargando...</li>';
    mostrarMensaje(mensajeDiv, '', 'exito'); // Oculta el mensaje
    mensajeDiv.style.display = 'none';

    if (!id) {
        lista.innerHTML = ''; // Limpia el "Cargando..."
        return;
    }

    try {
        // Llama al endpoint específico del SUBOFICIAL
        const respuesta = await fetch(`/api/suboficial/servicios-usuario/${id}`);

        if (respuesta.ok) {
            // El backend devolvió un 200 (OK) y la lista de servicios
            const servicios = await respuesta.json();

            // Usamos la función de shared.js para "dibujar" la lista
            renderServiciosList(servicios, lista);

        } else {
            // El backend devolvió un error (403, 404, 500)
            // (ej. "No tiene permiso para consultar...")
            const error = await respuesta.text();
            mostrarMensaje(mensajeDiv, error, 'error');
            lista.innerHTML = ''; // Limpia el "Cargando..."
        }
    } catch(e) {
        // Error de red (servidor caído)
        mostrarMensaje(mensajeDiv, 'Error de conexión con el servidor.', 'error');
        lista.innerHTML = '';
    }
}







