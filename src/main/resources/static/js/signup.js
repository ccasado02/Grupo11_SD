function signUp() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;

    // Simulamos una llamada al servidor para SignUp
    fetch('/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            'username': username,
            'password': password,
            'email': email
        })
    })
    .then(response => {
        if (response.ok) {
            alert("SignUp successful!");
        } else {
            alert("Error signing up!");
        }
    })
    .catch(error => console.error('Error:', error));
}
