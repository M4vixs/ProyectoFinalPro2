

// --- SEGURIDAD Y SESIÓN ---
// Esta función combina la validación y la obtención del usuario.
function getUsuarioYValidarRol(rolEsperado) {
    const usuarioJSON = sessionStorage.getItem('usuario');
    if (!usuarioJSON) {
        window.location.href = 'login.html';
        return null; // Detiene la ejecución
    }
    const usuario = JSON.parse(usuarioJSON);

    // Valida el rol
    if (usuario.tipoUser.trim().toUpperCase() !== rolEsperado) {
        alert("Acceso denegado.");
        window.location.href = 'login.html';
        return null; // Detiene la ejecución
    }
    return usuario; // Devuelve el usuario si todo está bien
}

// ---  LÓGICA DE SERVICIOS ---
async function cargarServicios(idUsuario) {
    const listaUl = document.getElementById('lista-servicios');
    if (!listaUl) return; // Si la página no tiene <ul>, no hace nada

    listaUl.innerHTML = '<li>Cargando...</li>';
    try {
        const respuesta = await fetch(`/api/soldado/${idUsuario}/servicios`);
        if (!respuesta.ok) {
            listaUl.innerHTML = '<li>Error al cargar los servicios.</li>';
            return;
        }

        const servicios = await respuesta.json();
        if (servicios.length === 0) {
            listaUl.innerHTML = '<li>No tienes servicios asignados.</li>';
            return;
        }

        listaUl.innerHTML = '';
        servicios.forEach(servicio => {
            const li = document.createElement('li');
            li.style.display = 'flex';
            li.style.justifyContent = 'space-between';
            li.style.alignItems = 'center';
            li.style.marginBottom = '10px';
            li.style.padding = '10px';
            li.style.border = '1px solid #555'; // (Asumiendo tema oscuro)

            const spanTexto = document.createElement('span');
            spanTexto.textContent = `${servicio.nombre_servicio} - ${servicio.descripcion}`; // desc tiene [ESTADO]
            li.appendChild(spanTexto);

            // SI NO ESTÁ REALIZADO, AGREGAMOS EL BOTÓN
            if (!servicio.descripcion.includes('[REALIZADO]')) {
                const btnRealizar = document.createElement('button');
                btnRealizar.textContent = "✔ Marcar Realizado";
                btnRealizar.className = "btn";
                btnRealizar.style.backgroundColor = "#27ae60";

                // ¡Importante! Llama a la función compartida 'marcarServicio'
                btnRealizar.onclick = () => marcarServicio(idUsuario, servicio.codigo);

                li.appendChild(btnRealizar);
            } else {
                const spanCheck = document.createElement('span');
                spanCheck.textContent = "✅ Completado";
                spanCheck.style.color = "#27ae60";
                li.appendChild(spanCheck);
            }
            listaUl.appendChild(li);
        });
    } catch (error) {
        console.error(error);
        listaUl.innerHTML = '<li>Error de conexión.</li>';
    }
}

async function marcarServicio(idUsuario, idServicio) {
    if(!confirm("¿Confirmas que has completado este servicio?")) return;

    const datos = { idUsuario, idServicio };

    try {
        const respuesta = await fetch('/api/soldado/marcar-realizado', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        });

        if (respuesta.ok) {
            alert("¡Servicio marcado!");
            cargarServicios(idUsuario); // Vuelve a llamar a la función compartida
        } else {
            alert("Error al actualizar.");
        }
    } catch (e) {
        alert("Error de conexión.");
    }
}

// --- FUNCIONES UTILITARIAS ---
function setupLogout(buttonId) {
    document.getElementById(buttonId).addEventListener('click', () => {
        sessionStorage.removeItem('usuario');
        window.location.href = 'login.html';
    });
}

function mostrarMensaje(elemento, texto, tipo) {
    if (typeof elemento === 'string') {
        elemento = document.getElementById(elemento);
    }
    if (!elemento) return;

    elemento.textContent = texto;
    elemento.style.display = 'block';
    elemento.style.padding = '10px';
    elemento.style.marginTop = '10px';
    elemento.style.borderRadius = '5px';
    elemento.style.color = 'white';

    if (tipo === 'exito') elemento.style.backgroundColor = '#27ae60'; // Verde
    else elemento.style.backgroundColor = '#e74c3c'; // Rojo
}

// dibuja una lista de servicios en un <ul>
function renderServiciosList(servicios, listaElement) {
    listaElement.innerHTML = ''; // Limpia "Cargando..."

    if (servicios.length === 0) {
        listaElement.innerHTML = '<li>Este usuario no tiene servicios asignados.</li>';
        return;
    }

    servicios.forEach(s => {
        // Crea: <li>Guardia - Descripción [ESTADO]</li>
        const li = document.createElement('li');
        li.textContent = `${s.nombre_servicio} - ${s.descripcion}`;
        li.style.padding = "5px 0";
        li.style.borderBottom = "1px solid #555";
        listaElement.appendChild(li);
    });
}









