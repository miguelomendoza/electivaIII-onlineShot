/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function login(){
    document.getElementById("error-message").hidden = true;    
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var loginObj = {
        login_name: username.value,
        passwd: password.value
    };
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/paronlineapi/webresources/acceso", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            createCookie("userId", response.Retorno.idCliente, 1);
            createCookie("userEmail", response.Retorno.email, 1);            
            createCookie("userRol", response.Retorno.tipoCliente, 1);
            window.location.href = "home";            
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            document.getElementById("error-message").innerHTML = "El nombre de usuario o la contrase\u00F1a son incorrectos";
            document.getElementById("error-message").hidden = false;
        }
    };
    xhr.send(JSON.stringify(loginObj));
}

function continueGuest(){
    window.location.href = "home";
}