/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logout(){
    eraseCookie("userId");
    eraseCookie("userEmail");
    eraseCookie("userRol");
    window.location.href = "home";
}

function goLogin(){
    showLoginModal();
}

function showProducts(){
    document.getElementById("products-page").hidden = false;
    document.getElementById("purchases-page").hidden = true;
    if(document.getElementById("abmClientes-page") !== null){
        document.getElementById("abmClientes-page").hidden = true;
        document.getElementById("abmProductos-page").hidden = true;
    }
    if(document.getElementById("nav-abmClientes") !== null){
        document.getElementById("nav-abmClientes").setAttribute("class", null);
        document.getElementById("nav-abmProductos").setAttribute("class", null);        
    }
    document.getElementById("nav-products").setAttribute("class", "nav-selected");
    document.getElementById("nav-purchases").setAttribute("class", null);
    searchAll();
}


function showABMClientes(){
    document.getElementById("products-page").hidden = true;
    document.getElementById("purchases-page").hidden = true;
    document.getElementById("abmClientes-page").hidden = false;
    document.getElementById("abmProductos-page").hidden = true; 
    document.getElementById("nav-products").setAttribute("class", null);
    document.getElementById("nav-purchases").setAttribute("class", null);
    document.getElementById("nav-abmClientes").setAttribute("class", "nav-selected");
    document.getElementById("nav-abmProductos").setAttribute("class", null);    
    loadClientesAMBTable();
}

function showClientesGET(){
    console.log("SHOW CLIENTES");
}

function showABMProductos(){
    document.getElementById("products-page").hidden = true;
    document.getElementById("purchases-page").hidden = true;
    document.getElementById("abmClientes-page").hidden = true;
    document.getElementById("abmProductos-page").hidden = false; 
    document.getElementById("nav-products").setAttribute("class", null);
    document.getElementById("nav-purchases").setAttribute("class", null);
    document.getElementById("nav-abmClientes").setAttribute("class", null);
    document.getElementById("nav-abmProductos").setAttribute("class", "nav-selected");
    loadProductsAMBTable();
}

function loadProductsAMBTable(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/listaProducto", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");    
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            return loadProductsABMDOMTable(response.ProductoList);
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            showNoData();
            return loadProductsABMDOMTable([]);            
        }
    };
    xhr.send();
}

function loadClientesAMBTable(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/clienteRest/listaClientes", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");    
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            return loadClientesABMDOMTable(response.ClienteList);
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            showNoData();
        }
    };
    xhr.send();    
}

function loadClientesABMDOMTable(clients){
    console.log(clients);
    var clientesSectionTable = document.getElementById("clientes-abm-tables");
    removeAllChilds(clientesSectionTable);

    let table = document.createElement("table");
    table.setAttribute("class", "purchases-table");
    
    //cabeceras
    let cabecera = document.createElement("thead");

    let cabeceraId = document.createElement("th");
    cabeceraId.innerText = "ID";
    
    let cabeceraNombre = document.createElement("th");
    cabeceraNombre.innerText = "Nombre";
    
    let cabeceraApellido = document.createElement("th");
    cabeceraApellido.innerText = "Apellido";
    
    let cabeceraEmail = document.createElement("th");
    cabeceraEmail.innerText = "Email";
    
    let cabeceraTipoCliente = document.createElement("th");
    cabeceraTipoCliente.innerText = "Tipo cliente";    
    
    let cabeceraAcciones = document.createElement("th");
    cabeceraAcciones.innerText = "Acciones";
    
    cabecera.appendChild(cabeceraId);
    cabecera.appendChild(cabeceraNombre);
    cabecera.appendChild(cabeceraApellido);
    cabecera.appendChild(cabeceraEmail);
    cabecera.appendChild(cabeceraTipoCliente);    
    cabecera.appendChild(cabeceraAcciones);
    
    //body
    var bodyTable = document.createElement("tbody");
    
    for(var i = 0; i < clients.length; i++){
        var fila = document.createElement("tr");        
        if((i+1) % 2 === 0){
            fila.setAttribute("style", "background: transparent");
        }
        
        var idColumn = document.createElement("td");
        idColumn.innerText = clients[i].idCliente;

        var nombreColumn = document.createElement("td");
        nombreColumn.innerText = clients[i].nombre;
        
        var apellidoColumn = document.createElement("td");
        apellidoColumn.innerText = clients[i].apellido;
        
        var emailColumn = document.createElement("td");
        emailColumn.innerText = clients[i].email;
        
        var tipoClienteColumn = document.createElement("td");
        tipoClienteColumn.innerText = clients[i].tipoCliente;        
        
        var accionesColumn = document.createElement("td");
        
        var eliminarBtn = document.createElement("button");
        eliminarBtn.setAttribute("class", "btn-eliminar");        
        eliminarBtn.setAttribute("onclick", "deleteClient(" + clients[i].idCliente + ")");
        eliminarBtn.innerHTML = "<img src='img/trash.svg' style='vertical-align: middle'/>&nbsp;&nbsp;<span>Eliminar</span>";
        if(readCookie("userId") ==  clients[i].idCliente){
            eliminarBtn.setAttribute("disabled", "true");
        }
        
        accionesColumn.appendChild(eliminarBtn);
        
        var editarBtn = document.createElement("button");
        editarBtn.setAttribute("class", "btn-editar");                
        editarBtn.setAttribute("onclick", "editarClient(" + JSON.stringify(clients[i]) + ")");
        editarBtn.innerHTML = "<img src='img/edit.svg' style='vertical-align: middle'/>&nbsp;&nbsp;<span>Editar</span>";
        accionesColumn.appendChild(editarBtn);        
        
        fila.appendChild(idColumn);
        fila.appendChild(nombreColumn);
        fila.appendChild(apellidoColumn);
        fila.appendChild(emailColumn);
        fila.appendChild(tipoClienteColumn);        
        fila.appendChild(accionesColumn);
        
        bodyTable.appendChild(fila);
    }
    
    table.appendChild(cabecera);
    table.appendChild(bodyTable);
    clientesSectionTable.appendChild(table);    
}

function loadProductsABMDOMTable(products){
    console.log(products);
    var productSectionTable = document.getElementById("productos-abm-tables");
    removeAllChilds(productSectionTable);

    let table = document.createElement("table");
    table.setAttribute("class", "purchases-table");
    
    //cabeceras
    let cabecera = document.createElement("thead");

    let cabeceraId = document.createElement("th");
    cabeceraId.innerText = "ID";
    
    let cabeceraDescripcion = document.createElement("th");
    cabeceraDescripcion.innerText = "Descripcion";
    
    let cabeceraCategoria = document.createElement("th");
    cabeceraCategoria.innerText = "Categoria";
    
    let cabeceraPrecio = document.createElement("th");
    cabeceraPrecio.innerText = "Precio";
    
    let cabeceraCantidad = document.createElement("th");
    cabeceraCantidad.innerText = "Cantidad";
    
    let cabeceraAcciones = document.createElement("th");
    cabeceraAcciones.innerText = "Acciones";
    
    cabecera.appendChild(cabeceraId);
    cabecera.appendChild(cabeceraDescripcion);
    cabecera.appendChild(cabeceraCategoria);
    cabecera.appendChild(cabeceraPrecio);
    cabecera.appendChild(cabeceraCantidad);   
    cabecera.appendChild(cabeceraAcciones);
    
    //body
    var bodyTable = document.createElement("tbody");
    
    for(var i = 0; i < products.length; i++){
        var fila = document.createElement("tr");        
        if((i+1) % 2 === 0){
            fila.setAttribute("style", "background: transparent");
        }
        
        var idColumn = document.createElement("td");
        idColumn.innerText = products[i].idProducto;

        var descripcionColumn = document.createElement("td");
        descripcionColumn.innerText = products[i].descripcion;
        
        var categoriaColumn = document.createElement("td");
        categoriaColumn.innerText = products[i].idCategoria.descripcion;
        
        var precioColumn = document.createElement("td");
        precioColumn.innerText = new Intl.NumberFormat("de-DE").format(products[i].precioUnit) + " GS.";
        
        var cantidadColumn = document.createElement("td");
        cantidadColumn.innerText = products[i].cantidad;
        
        var accionesColumn = document.createElement("td");
        
        var eliminarProducto = document.createElement("button");
        eliminarProducto.setAttribute("onclick", "deleteProduct(" + products[i].idProducto + ")");
        eliminarProducto.setAttribute("class", "btn-eliminar");
        eliminarProducto.innerHTML = "<img src='img/trash.svg' style='vertical-align: middle'/>&nbsp;&nbsp;<span>Eliminar</span>";        
        accionesColumn.appendChild(eliminarProducto);
        
        var editarProducto = document.createElement("button");
        editarProducto.setAttribute("class", "btn-editar");
        editarProducto.innerHTML = "<img src='img/edit.svg' style='vertical-align: middle'/>&nbsp;&nbsp;<span>Editar</span>";
        editarProducto.setAttribute("onclick", "editarProduct(" + JSON.stringify(products[i]) + ")");
        accionesColumn.appendChild(editarProducto);
        
        fila.appendChild(idColumn);
        fila.appendChild(descripcionColumn);
        fila.appendChild(categoriaColumn);
        fila.appendChild(precioColumn);
        fila.appendChild(cantidadColumn);
        fila.appendChild(accionesColumn);
        
        bodyTable.appendChild(fila);
    }
    
    table.appendChild(cabecera);
    table.appendChild(bodyTable);
    productSectionTable.appendChild(table);    
}

function showPurchases(){
    document.getElementById("products-page").hidden = true;
    document.getElementById("purchases-page").hidden = false;
    if(document.getElementById("abmClientes-page") !== null){
        document.getElementById("abmClientes-page").hidden = true;
        document.getElementById("abmProductos-page").hidden = true;
    }
    if(document.getElementById("nav-abmClientes") !== null){
        document.getElementById("nav-abmClientes").setAttribute("class", null);
        document.getElementById("nav-abmProductos").setAttribute("class", null);         
    }
    document.getElementById("nav-products").setAttribute("class", null);
    document.getElementById("nav-purchases").setAttribute("class", "nav-selected");
    loadPurchasesTable();
}

function loadPurchasesTable(){
    var userId = readCookie("userId");
    console.log(userId);
    if(userId === undefined || userId === null){
        showNoUser();
        return;
    }
    else{
        getPurchasesByClientId(userId);
    }
}

function showNoUser(){
    var sectionTable = document.getElementById("compras-table");
    removeAllChilds(sectionTable);
    let message = document.createElement("h2");
    message.setAttribute("class", "message-01");
    message.innerText = "Debes iniciar sesion para consultar tus compras.";
    sectionTable.appendChild(message);
}

function showNoData(){
    var sectionTable = document.getElementById("compras-table");
    removeAllChilds(sectionTable);
    let message = document.createElement("h2");
    message.setAttribute("class", "message-01");
    message.innerText = "Aun no has realizado compras.";
    sectionTable.appendChild(message);
}

function removeAllChilds(element){
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function getPurchasesByClientId(idCliente){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/transaccionCabRest/listaTransaccion/" + idCliente, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");    
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            if(response.TransaccionesList.length === 0){
                showNoData();
            }
            else{
                addPurchasesToDOM(response.TransaccionesList);                
            }
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            showNoData();
        }
    };
    xhr.send();
}

function addPurchasesToDOM(purchases){
    var sectionTable = document.getElementById("compras-table");
    removeAllChilds(sectionTable);
    
    let table = document.createElement("table");
    table.setAttribute("class", "purchases-table");
    
    //cabeceras
    let cabecera = document.createElement("thead");

    let cabeceraId = document.createElement("th");
    cabeceraId.innerText = "ID";
    
    let cabeceraFecha = document.createElement("th");
    cabeceraFecha.innerText = "Fecha";
    
    let cabeceraDireccion = document.createElement("th");
    cabeceraDireccion.innerText = "Direccion envio";
    
    let cabeceraMedioPago = document.createElement("th");
    cabeceraMedioPago.innerText = "Medio pago";
    
    let cabeceraTotal = document.createElement("th");
    cabeceraTotal.innerText = "Total";
    
    let cabeceraAcciones = document.createElement("th");
    cabeceraAcciones.innerText = "Acciones";
    
    cabecera.appendChild(cabeceraId);
    cabecera.appendChild(cabeceraFecha);
    cabecera.appendChild(cabeceraDireccion);
    cabecera.appendChild(cabeceraMedioPago);
    cabecera.appendChild(cabeceraTotal);   
    cabecera.appendChild(cabeceraAcciones);
    
    //body
    var bodyTable = document.createElement("tbody");
    
    for(var i = 0; i < purchases.length; i++){
        var fila = document.createElement("tr");        
        if((i+1) % 2 === 0){
            fila.setAttribute("style", "background: transparent");
        }
        
        var idColumn = document.createElement("td");
        idColumn.innerText = purchases[i].idTransaccion;

        var fechaColumn = document.createElement("td");
        fechaColumn.innerText = purchases[i].fecha;
        
        var direccionColumn = document.createElement("td");
        direccionColumn.innerText = purchases[i].direccionEnvio;
        
        var medioPagoColumn = document.createElement("td");
        medioPagoColumn.innerText = purchases[i].idMedioPago;
        
        var totalColumn = document.createElement("td");
        totalColumn.innerText = new Intl.NumberFormat("de-DE").format(purchases[i].total) + " GS.";
        
        var accionesColumn = document.createElement("td");
        var verCarritoBtn = document.createElement("button");
        verCarritoBtn.setAttribute("class", "btn-ver-carrito");
        verCarritoBtn.innerHTML = "<img src='img/kart.svg' style='vertical-align: middle'/>&nbsp;&nbsp;<span>Ver carrito</span>";
        verCarritoBtn.setAttribute("onclick", "showKartView(" + purchases[i].idTransaccion + ")");
        
        accionesColumn.appendChild(verCarritoBtn);
        
        fila.appendChild(idColumn);
        fila.appendChild(fechaColumn);
        fila.appendChild(direccionColumn);
        fila.appendChild(medioPagoColumn);
        fila.appendChild(totalColumn);
        fila.appendChild(accionesColumn);
        
        bodyTable.appendChild(fila);
    }
    
    table.appendChild(cabecera);
    table.appendChild(bodyTable);
    sectionTable.appendChild(table);
}

function showLoginModal(){
    document.getElementById("login-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");    
}

function handleConfirmTransaction(){
    var kart = document.getElementById("list-kart").value;
    var idCliente = readCookie("userId");
    if(kart != null){if(kart.length <= 2){showSnackbar("El carrito esta vacio", "danger");return;}}
    else{showSnackbar("El carrito esta vacio", "danger");return;}
    kart = JSON.parse(kart);
    let detalleTransaccion = [];
    var transaccionCab = new TransaccionesCab();
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        var detalle = new TransaccionesDet();
        detalle.transaccionesDetPK = new TransaccionesDetPK(i+1);
        detalle.cantidad = element.cantidadReq;
        detalle.precio = element.precioUnit;
        detalle.subTotal = element.cantidadReq * element.precioUnit;
        delete element["cantidadReq"]; 
        detalle.idProducto = element;
        detalleTransaccion.push(detalle);
    }
    transaccionCab.transaccionesDetCollection = detalleTransaccion;
    transaccionCab.idCliente = parseInt(idCliente);
    transaccionCab.total = sumItems();
    transaccionCab.direccionEnvio = document.getElementById("direccion-input").value;
    transaccionCab.idMedioPago = document.getElementById("metodoPago-input").value;
    transaccionCab.nroTarjeta = document.getElementById("tarjeta-input").value;
    transaccionCab.estado = 'A';
    
    if(validateConfirmPurchaseFields(transaccionCab)){
        postTransaccion(transaccionCab);        
    }
    else{
        var errorBlock = document.getElementById("error-confirmacion-compra");
        errorBlock.innerText = "Por favor, complete todos los campos";
        errorBlock.hidden = false;
    }
   
}

function validateConfirmPurchaseFields(transaccionCab){
    if(transaccionCab.direccionEnvio === "" || transaccionCab.direccionEnvio === null){
        return false;
    }
    if(transaccionCab.idMedioPago === "2"){
        if(transaccionCab.nroTarjeta === null || transaccionCab.nroTarjeta === "" || transaccionCab.nroTarjeta === 0){
            return false;
        }
        else{
            return true;
        }
    }
    return true;
}

class TransaccionesCab { 
    constructor(){
        this.idTransaccion = null;
        this.fecha = new Date();
        this.idCliente = null;
        this.total = null;
        this.direccionEnvio = null;
        this.idMedioPago = null;
        this.nroTarjeta = null;
        this.estado = null;
        this.transaccionesDetCollection = [];
    }
}

class TransaccionesDet{
    constructor(){
        this.transaccionesDetPK = null;
        this.cantidad = null;
        this.precio = null;
        this.subTotal = null;
        this.idProducto = null;
        this.transaccionesCab = null;
    }
}

class TransaccionesDetPK{
    constructor(item){
        this.idTransaccion = null;
        this.item = item;
    }
}

function postTransaccion(transaccionCab){
    console.log(transaccionCab);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/paronlineapi/webresources/transaccionCabRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            hiddenConfirmPurchase();
            showPurchases();
            showSnackbar("Compra realizada exitosamente", "success");
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");            
        }
    };
    console.log(JSON.stringify(transaccionCab));
    xhr.send(JSON.stringify(transaccionCab));    
}

function loadResources(){
    var kart = readCookie("kart");
    if(kart === null || kart === undefined){
        return;
    }
    document.getElementById("list-kart").value = kart;
    kart = JSON.parse(kart);
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        addItemToKart(element);
    }
}

function validateCardInput(){
    var select = document.getElementById("metodoPago-input").value;
    console.log(select);
    if(parseInt(select) === 2){
       document.getElementById("tarjeta-input").disabled = false;
    }
    else{
       document.getElementById("tarjeta-input").disabled = true;  
    }
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function showSnackbar(message, type){
    
    document.getElementById("snackbar-modal").hidden = false;
    document.getElementById("snackbar-message").innerText = message;
    
    if(type === "success"){
        document.getElementById("modal-snackbar-header").style = "background: #43a047";        
    }
    else{
        document.getElementById("modal-snackbar-header").style = "background: #d32f2f";
    }
    
    sleep(2000).then(() => {
        document.getElementById("snackbar-modal").hidden = true; 
    });
}