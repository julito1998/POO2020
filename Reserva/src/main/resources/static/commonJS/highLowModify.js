function limpiarPassword(){
        document.getElementById("password").textContent="";
}

function errorMessage() {
        const error = document.querySelector("#error");

        if (isNaN(document.querySelector("#email").value) || isNaN(document.querySelector("#password").value) ) {
                error.style.color="red";
                error.innerText= "¡Usuario o password incorrecto, intente nuevamente!";
        }
        else {
                error.innerText = "";
        }


}
