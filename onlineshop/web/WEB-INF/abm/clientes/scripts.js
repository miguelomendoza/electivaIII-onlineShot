/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showFormClient() {
    cleanClientFields();
    document.getElementById("formClient-passwd-section").hidden = false;
    document.getElementById("btn-guardar-client").hidden = false;
    document.getElementById("btn-editar-client").hidden = true;
    document.getElementById("form-clients-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");
}

function cleanClientFields(){
    document.getElementById("formClient-nombre").value = "";
    document.getElementById("formClient-apellido").value = "";
    document.getElementById("formClient-email").value = "";
    document.getElementById("formClient-loginName").value = "";
    document.getElementById("formClient-passwd").value = "";
}

function closeFormClient() {
    document.getElementById("form-clients-modal").hidden = true;
    document.getElementById("container-home").setAttribute("style", "opacity:1");
}

function saveCliente() {

    var object = validateClientFields();

    if (object === null || object === undefined) {
        console.log("Campos invalidos");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/paronlineapi/webresources/clienteRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            closeFormClient();
            showABMClientes();
            showSnackbar("El cliente ha sido registrado exitosamente", "success");
        } else {
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");
        }
    };
    console.log(object);
    xhr.send(JSON.stringify(object));
}

function validateClientFields() {
    var nombre = document.getElementById("formClient-nombre").value;
    var apellido = document.getElementById("formClient-apellido").value;
    var email = document.getElementById("formClient-email").value;
    var loginName = document.getElementById("formClient-loginName").value;
    var password = document.getElementById("formClient-passwd").value;
    var tipoCliente = document.getElementById("formClient-tipoCliente").value;
    var object = {
        nombre: nombre,
        apellido: apellido,
        email: email,
        loginName: loginName,
        passwd: password,
        tipoCliente: tipoCliente
    };
    return object;
}

function deleteClient(idClient) {
    console.log("Eliminar: ", idClient);
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "/paronlineapi/webresources/clienteRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            showABMClientes();
            showSnackbar("Cliente eliminado exitosamente", "success");            
        } else {
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");            
        }
    };
    xhr.send(idClient.toString());
}

function editarClient(cliente) {
    console.log("Editar: ", cliente);
    document.getElementById("formClient-passwd-section").hidden = true;
    document.getElementById("formClient-nombre").value = cliente.nombre;
    document.getElementById("formClient-apellido").value = cliente.apellido;
    document.getElementById("formClient-email").value = cliente.email;
    document.getElementById("formClient-loginName").value = cliente.loginName;
    document.getElementById("formClient-passwd").value = cliente.passwd;
    document.getElementById("formClient-tipoCliente").value = cliente.tipoCliente;

    document.getElementById("btn-guardar-client").hidden = true;
    document.getElementById("btn-editar-client").hidden = false;

    document.getElementById("form-clients-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");

    var btnEditar = document.getElementById("btn-editar-client-ok");
    btnEditar.setAttribute("onclick", "updateClient(" + cliente.idCliente + ")");
}

function updateClient(cliente) {
    var object = validateClientFields();
    if (object === null || object === undefined) {
        console.log("Campos invalidos");
        return;
    }
    object.idCliente = cliente;
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "/paronlineapi/webresources/clienteRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            closeFormClient();
            showABMClientes();
            showSnackbar("Datos actualizados correctamente", "success");            
        } else {
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");            
        }
    };
    console.log(object);
    xhr.send(JSON.stringify(object));
}