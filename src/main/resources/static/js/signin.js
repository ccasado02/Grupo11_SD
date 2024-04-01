function signIn() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // Simulamos una llamada al servidor para SignIn
    fetch('/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            'username': username,
            'password': password
        })
    })
    .then(response => {
        if (response.ok) {
            alert("SignIn successful!");
        } else {
            alert("Incorrect username or password!");
        }
    })
    .catch(error => console.error('Error:', error));
}
