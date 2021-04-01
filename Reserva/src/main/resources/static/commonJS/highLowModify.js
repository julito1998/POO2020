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

function redirectButtonToLink(link, id='#0'){
       const elem= document.querySelector(id);
        elem.location.href=link;
}

function PriceBooking(idCheck,idLabel){
        //var price =idLabel;
        var price = parseFloat(document.getElementById(idLabel).textContent.replace(/[$,a-z,A-Z,()]/g,"").trim());
        var priceFinal = parseFloat(document.getElementById("PriceFinal").textContent.replace(/[$,:,a-z,A-Z,()]/g,"").trim());

        if(document.getElementById(idCheck).checked){
                priceFinal=priceFinal + price;
        }else{
                priceFinal=priceFinal - price;
        }

        document.getElementById("PriceFinal").textContent=parseFloat(priceFinal.toFixed(2));
}