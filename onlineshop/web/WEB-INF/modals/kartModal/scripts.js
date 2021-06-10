/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function closeKartView(){
    document.getElementById("kart-modal").hidden = true;
    document.getElementById("container-home").setAttribute("style", "opacity:1");    
}

function showKartView(transactionCab){
    console.log(transactionCab);
    document.getElementById("kart-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");
    getKartDetails(transactionCab);
}

function getKartDetails(idTransaccion){
    console.log(idTransaccion);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/transaccionDetRest/listaDetalle/" + idTransaccion, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");    
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response.DetailList);
            loadKartDetailDOM(response.DetailList);
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            showNoData();
        }
    };
    xhr.send();
}

function loadKartDetailDOM(details){
    var detailModal = document.getElementById("kart-detail-body");

    removeAllChilds(detailModal);

    let table = document.createElement("table");
    table.setAttribute("class", "purchases-table");
    
    //cabeceras
    let cabecera = document.createElement("thead");

    let cabeceraId = document.createElement("th");
    cabeceraId.innerText = "Item";
    
    let cabeceraProducto = document.createElement("th");
    cabeceraProducto.innerText = "Producto";
    
    let cabeceraCantidad = document.createElement("th");
    cabeceraCantidad.innerText = "Cantidad";
    
    let cabeceraPrecio = document.createElement("th");
    cabeceraPrecio.innerText = "Precio";
    
    let cabeceraSubtotal = document.createElement("th");
    cabeceraSubtotal.innerText = "Subtotal";
    
    cabecera.appendChild(cabeceraId);
    cabecera.appendChild(cabeceraProducto);
    cabecera.appendChild(cabeceraCantidad);
    cabecera.appendChild(cabeceraPrecio);
    cabecera.appendChild(cabeceraSubtotal);

    //body
    var bodyTable = document.createElement("tbody");
    
    for(var i = 0; i < details.length; i++){
        var fila = document.createElement("tr");        
        if((i+1) % 2 === 0){
            fila.setAttribute("style", "background: transparent");
        }
        
        var itemColumn = document.createElement("td");
        itemColumn.innerText = details[i].transaccionesDetPK.item;

        var descripcionColumn = document.createElement("td");
        descripcionColumn.innerText = details[i].idProducto.descripcion;
        
        var cantidadColumn = document.createElement("td");
        cantidadColumn.innerText = details[i].cantidad;
        
        var precioColumn = document.createElement("td");
        precioColumn.innerText = new Intl.NumberFormat("de-DE").format(details[i].precio) + " GS.";
        
        var subtotalColumn = document.createElement("td");
        subtotalColumn.innerText = new Intl.NumberFormat("de-DE").format(details[i].subTotal) + " GS.";
        
        fila.appendChild(itemColumn);
        fila.appendChild(descripcionColumn);
        fila.appendChild(cantidadColumn);
        fila.appendChild(precioColumn);
        fila.appendChild(subtotalColumn);
        
        bodyTable.appendChild(fila);
    }
    
    table.appendChild(cabecera);
    table.appendChild(bodyTable);
    detailModal.appendChild(table);     
    
}