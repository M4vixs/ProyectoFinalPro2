
async function intentarLogin() {
    const email = document.getElementById('email').value;
    const pass = document.getElementById('password').value;
    const resultadoEl = document.getElementById('resultado');

    // Preparamos los datos en el formato que Java espera
    const datosLogin = {
        email: email,
        contrasenia: pass
    };

    // Usamos 'fetch' para llamar a nuestra API
    const respuesta = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosLogin)
    });

    if (respuesta.ok) {
        const data = await respuesta.json();
        sessionStorage.setItem('usuario', JSON.stringify(data));
        // 1. EL JS LEE EL ROL QUE MANDÓ JAVA
        if (data.tipoUser === "OFICIAL") {
            window.location.href = "../menuOficial.html";

        } else if (data.tipoUser === "SOLDADO") {
            window.location.href = "../menuSoldado.html";

        } else if (data.tipoUser === "SUBOFICIAL") {
            window.location.href = "../menuSuboficial.html";
        }
    } else {
        const errorTexto = await respuesta.text();
        resultadoEl.textContent = `ERROR ${respuesta.status}:\n${errorTexto}`;
    }
}