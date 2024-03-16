window.onload = function() {
    var user = sessionStorage.getItem('user');
    if (user) {
        document.getElementByClass('navbar').style.display = 'none';
        var userElement = document.createElement('span');
        userElement.textContent = 'Bienvenido, ' + user;
        document.querySelector('.navbar').appendChild(userElement);
    }
};