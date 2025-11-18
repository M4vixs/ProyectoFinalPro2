// Espera a que todo el HTML esté cargado
document.addEventListener('DOMContentLoaded', () => {

    //  OBTENER LOS DATOS DEL USUARIO
    const usuarioJSON = sessionStorage.getItem('usuario');

    // Si no hay usuario, lo echamos al login.
    const usuario = getUsuarioYValidarRol("SOLDADO");
    if (!usuario) return;


    // MOSTRAR DATOS (Opción 1 del menú)
    document.getElementById('soldado-nombre').textContent = `${usuario.nombre} ${usuario.apellido}`;
    document.getElementById('soldado-rol').textContent = usuario.tipoUser;
    document.getElementById('soldado-cuerpo').textContent = usuario.tipoCuerpo.denominacion_cuerpo;
    document.getElementById('soldado-compania').textContent =usuario.comp.denominacion_compania ;// Ajusta si el nombre de la propiedad es otro
    document.getElementById('soldado-cuartel').textContent = usuario.cuart.nombre_cuartel;

    // Llamamos a la función para buscar los servicios de este usuario
    cargarServicios(usuario.codigo);

    // FUNCIONALIDAD DE SALIR (Opción 3 del menú)
    document.getElementById('btn-logout').addEventListener('click', () => {
        sessionStorage.removeItem('usuario'); // Borramos al usuario de la sesión
        window.location.href = '../login.html';
    });

    document.getElementById('btn-cambiar-password').addEventListener('click', cambiarPassword);
});




async function cambiarPassword() {
    const nuevoPass = document.getElementById('nuevo-password').value;
    const confirmarPass = document.getElementById('confirmar-password').value;
    const mensajeDiv = document.getElementById('mensaje-password');

    // --- Validación del Frontend ---
    if (!nuevoPass || !confirmarPass) {
        mostrarMensaje(mensajeDiv, 'Ambos campos son obligatorios.', 'error');
        return;
    }
    if (nuevoPass !== confirmarPass) {
        mostrarMensaje(mensajeDiv, 'Las contraseñas no coinciden.', 'error');
        return;
    }
    if (nuevoPass.length < 6) { // Ejemplo de regla simple
        mostrarMensaje(mensajeDiv, 'La contraseña debe tener al menos 6 caracteres.', 'error');
        return;
    }
    // --- Fin Validación ---

    // Obtenemos el ID del usuario de la sesión
    const usuario = JSON.parse(sessionStorage.getItem('usuario'));
    const idUsuario = usuario.codigo;

    const datos = {
        idUsuario: idUsuario,
        nuevoPassword: nuevoPass
    };

    try {
        const respuesta = await fetch('/api/usuario/cambiar-password', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        });

        if (respuesta.ok) {
            mostrarMensaje(mensajeDiv, '¡Contraseña actualizada con éxito!', 'exito');
            document.getElementById('nuevo-password').value = '';
            document.getElementById('confirmar-password').value = '';
        } else {
            mostrarMensaje(mensajeDiv, 'Error: No se pudo actualizar la contraseña.', 'error');
        }

    } catch (error) {
        mostrarMensaje(mensajeDiv, 'Error de conexión con el servidor.', 'error');
    }
}



