const currentLang = document.body.dataset.lang || 'es';
const provinciasData = JSON.parse(document.getElementById('map-data').textContent);
const infoBox = document.getElementById('info-box');
const timerBox = document.getElementById('timer-box');
const svg = document.querySelector('#map-container svg');
let respuestasCorrectas = 0;
let respuestasIncorrectas = 0;
let inicioTimer = 0;
let intervaloTimer = null;
let timerActivo = false;
let tiempoTotalMs = 0;

function enviarContadores() {
    if (!window.AndroidBridge) return;
    window.AndroidBridge.onCorrectas(respuestasCorrectas);
    window.AndroidBridge.onIncorrectas(respuestasIncorrectas);
}

function formatoTiempo(tiempoMs) {
    const minutos = Math.floor(tiempoMs / 60000);
    const segundos = Math.floor((tiempoMs % 60000) / 1000);
    const milisegundos = tiempoMs % 1000;
    return `${String(minutos).padStart(2, '0')}:${String(segundos).padStart(2, '0')}.${String(milisegundos).padStart(3, '0')}`;
}

function actualizarTimer() {
    if (timerActivo) {
        timerBox.textContent = formatoTiempo(Math.floor(performance.now() - inicioTimer));
    }
}

function iniciarTimer() {
    window.clearInterval(intervaloTimer);
    inicioTimer = performance.now();
    tiempoTotalMs = 0;
    timerActivo = true;
    actualizarTimer();
    intervaloTimer = window.setInterval(actualizarTimer, 31);
}

function detenerTimer() {
    if (!timerActivo) return tiempoTotalMs;
    timerActivo = false;
    window.clearInterval(intervaloTimer);
    tiempoTotalMs = Math.floor(performance.now() - inicioTimer);
    timerBox.textContent = formatoTiempo(tiempoTotalMs);
    return tiempoTotalMs;
}

function normalizar(valor) {
    return String(valor)
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/[_-]+/g, ' ')
        .trim()
        .toLocaleLowerCase();
}

function obtenerEtiquetaRegion(elemento) {
    let actual = elemento;
    while (actual && actual !== svg) {
        const etiqueta = actual.getAttribute('inkscape:label') ||
            actual.getAttributeNS('http://www.inkscape.org/namespaces/inkscape', 'label');
        if (etiqueta) return etiqueta;
        actual = actual.parentElement;
    }
    return null;
}

const regionPorEtiqueta = new Map();
Object.entries(provinciasData).forEach(([clave, region]) => {
    [clave, region.id, ...Object.values(region.name || {})]
        .filter(Boolean)
        .forEach(alias => regionPorEtiqueta.set(normalizar(alias), clave));
});

const elementosPorRegion = new Map();
svg.querySelectorAll('path').forEach(path => {
    const etiqueta = obtenerEtiquetaRegion(path);
    const clave = etiqueta && regionPorEtiqueta.get(normalizar(etiqueta));
    if (!clave) return;

    path.classList.add('interactiva');
    if (!elementosPorRegion.has(clave)) elementosPorRegion.set(clave, []);
    elementosPorRegion.get(clave).push(path);
});

const regionesDelJson = Object.keys(provinciasData);
const regionesSinForma = regionesDelJson.filter(clave => !elementosPorRegion.has(clave));
let secuenciaRegiones = mezclar(regionesDelJson);

function mezclar(regiones) {
    const resultado = [...regiones];
    for (let i = resultado.length - 1; i > 0; i -= 1) {
        const j = Math.floor(Math.random() * (i + 1));
        [resultado[i], resultado[j]] = [resultado[j], resultado[i]];
    }
    return resultado;
}

if (regionesSinForma.length > 0) {
    infoBox.textContent = `Faltan regiones en el SVG: ${regionesSinForma.join(', ')}`;
} else if (secuenciaRegiones.length === 0) {
    infoBox.textContent = 'No se encontraron regiones enlazadas con este mapa';
} else {
    let indiceRegion = 0;
    let respuestaAcertada = false;
    iniciarTimer();
    mostrarObjetivo();

    elementosPorRegion.forEach((paths, clave) => {
        paths.forEach(path => path.addEventListener('click', () => {
            if (respuestaAcertada || indiceRegion >= secuenciaRegiones.length) return;

            const regionObjetivoId = secuenciaRegiones[indiceRegion];
            elementosPorRegion.forEach(elementos => {
                elementos.forEach(elemento => {
                    elemento.classList.remove('seleccionada', 'correcta', 'incorrecta');
                });
            });

            if (clave === regionObjetivoId) {
                respuestasCorrectas += 1;
                elementosPorRegion.get(clave).forEach(elemento => {
                    elemento.classList.add('correcta');
                });
                respuestaAcertada = true;
                infoBox.textContent = '¡Correcto!';
                if (indiceRegion === secuenciaRegiones.length - 1) detenerTimer();
                window.setTimeout(avanzarRegion, 900);
            } else {
                respuestasIncorrectas += 1;
                elementosPorRegion.get(clave).forEach(elemento => {
                    elemento.classList.add('seleccionada');
                });
                elementosPorRegion.get(regionObjetivoId).forEach(elemento => {
                    elemento.classList.add('incorrecta');
                });
                const nombre = provinciasData[regionObjetivoId].name[currentLang] ||
                    provinciasData[regionObjetivoId].name.es || regionObjetivoId;
                respuestaAcertada = true;
                infoBox.textContent = `¡Fallaste!`;
                if (indiceRegion === secuenciaRegiones.length - 1) detenerTimer();
                window.setTimeout(avanzarRegion, 1200);
            }
        }));
    });

    function mostrarObjetivo() {
        respuestaAcertada = false;
        elementosPorRegion.forEach(elementos => {
            elementos.forEach(elemento => {
                elemento.classList.remove('seleccionada', 'correcta', 'incorrecta');
            });
        });
        const regionObjetivoId = secuenciaRegiones[indiceRegion];
        const regionObjetivo = provinciasData[regionObjetivoId];
        const nombre = regionObjetivo.name[currentLang] || regionObjetivo.name.es || regionObjetivoId;
        infoBox.textContent = `Busca en el mapa: ${nombre}`;
    }

    function avanzarRegion() {
        indiceRegion += 1;
        if (indiceRegion >= secuenciaRegiones.length) {
            const tiempoFinalMs = detenerTimer();
            enviarContadores();
            if (window.AndroidBridge) {
                window.AndroidBridge.onTiempoFinalizado(tiempoFinalMs);
            }
            infoBox.textContent = 'Mapa completado';
            return;
        }
        mostrarObjetivo();
    }
}
