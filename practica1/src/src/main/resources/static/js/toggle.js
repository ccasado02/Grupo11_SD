document.addEventListener("DOMContentLoaded", function() {
    var menuToggle = document.querySelector(".menu-toggle");
    var navbar = document.querySelector(".navbar");

    menuToggle.addEventListener("click", function() {
        navbar.classList.toggle("active"); // Alternar la clase "active" en la barra de navegación
    });
});