document.addEventListener("DOMContentLoaded", function() {
    // Obtener el nombre de la página actual
    var currentPage = location.pathname.split("/").pop();

    // Obtener todos los enlaces de la barra de navegación
    var navLinks = document.querySelectorAll(".navbar a");

    // Iterar sobre los enlaces y comparar con la página actual
    navLinks.forEach(function(link) {
        var linkPage = link.getAttribute("href").split("/").pop();

        if (linkPage === currentPage) {
            // Aplicar estilo al enlace de la página actual
            link.style.color = "#fff"; // Cambiar el color del texto
            link.style.background = "#6ca254"; // Cambiar el color de fondo
        }
    });
});

