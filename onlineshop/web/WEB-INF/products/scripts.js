/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function searchProducts(){
    var productName = document.getElementById("productName").value;
    var categoryId = document.getElementById("category").value;    
    if(categoryId === "0" || categoryId === "-1"){
        if(!isEmpty(productName)){
            searchForDescription(productName);
        }
        else{
            searchAll();
        }
    }
    else{        
        if(!isEmpty(productName)){
            searchForDescriptionAndCategory(productName, categoryId);
        }
        else{
            searchForCategory(categoryId);
        }
    }
}

function searchForDescription(description){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/producto/desc/" + description, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");    
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            addProductsToDOM(response.Producto);
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
        }
    };
    xhr.send();
    console.log("searchForDescription : " + description);
}

function searchForCategory(idCategory){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/producto/categoria/" + idCategory, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            addProductsToDOM(response.Producto);
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
        }
    };
    xhr.send();
    console.log("searchForCategory : " + idCategory);    
}

function searchForDescriptionAndCategory(description, category){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/producto/categoriaDescripcion/" + category + "/" + description, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            addProductsToDOM(response.Producto);
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
        }
    };
    xhr.send();
    console.log("searchForDescriptionAndCategory : " + description + " | " + category);
}

function searchAll(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/listaProducto", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            addProductsToDOM(response.ProductoList);
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
        }
    };
    xhr.send();
    console.log("searchAll");
}

function isEmpty(string){
    if(string !== undefined){
        string = string.trim();
        if(string === ""){
            return true;
        }
        else{
            return false;
        }
    }
    else{
        return true;
    }
}

function addProductsToDOM(products){
    if(products !== undefined && products.length > 0){
        console.log(products);
        var productsBlock = document.getElementById("product-block");
        removeAllChilds(productsBlock);
        for(var i=0; i < products.length; i++){
            
            console.log(products[i]);
            
            let newProduct = document.createElement("section");
            newProduct.setAttribute("class", "product-card");

            let productTitle = document.createElement("h2");
            productTitle.setAttribute("class", "product-title");
            productTitle.innerText = products[i].descripcion;
            
            let productCategory = document.createElement("h4");
            productCategory.setAttribute("class", "product-category");
            productCategory.innerText = products[i].idCategoria.descripcion;

            let productQty = document.createElement("h4");
            productQty.setAttribute("class", "product-quantity");
            productQty.innerText = "Stock: " + new Intl.NumberFormat("de-DE").format(products[i].cantidad);
            
            let productPrice = document.createElement("h3");
            productPrice.setAttribute("class", "product-price");
            productPrice.innerText = new Intl.NumberFormat("de-DE").format(products[i].precioUnit) + " GS.";
            
            let buttonAdd = document.createElement("button");
            buttonAdd.setAttribute("class", "product-buy");
            buttonAdd.setAttribute("onclick", "addProductToKart(" + products[i].idProducto + ")");
            buttonAdd.innerText = "Comprar";
            
            newProduct.appendChild(productTitle); newProduct.appendChild(productCategory);
            newProduct.appendChild(productQty);
            newProduct.appendChild(productPrice); newProduct.appendChild(buttonAdd);
            
            productsBlock.appendChild(newProduct);                        
        }
    }
}

function getProductById(productId){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/productoRest/producto/" + productId, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response.Producto);
            addProductToDOM(response.Producto);
            return response.Producto;
        }
        else{
            console.log(xhr.responseText);            
            console.log("ERROR");            
        }
    };
    xhr.send();
}

function removeAllChilds(element){
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function addProductToKart(product){
    if(!exists(product)){
        getProductById(product);        
    }
}

function exists(idProducto){
    var kart = document.getElementById("list-kart").value;
    if(kart === ""){
        return false;
    }
    kart = JSON.parse(kart);
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        if(element.idProducto === idProducto){
            return true;
            break;
        }
    }
    return false;
}

function addProductToDOM(product){
    product.cantidadReq = 1;
    var kart = document.getElementById("list-kart").value;
    if(kart !== ""){
        kart = JSON.parse(kart);
    }
    if(!Array.isArray(kart)){
        console.log("No es array");
        kart = [];
    }
    kart.push(JSON.stringify(product));
    document.getElementById("list-kart").value = JSON.stringify(kart);
    eraseCookie("kart");
    createCookie("kart", JSON.stringify(kart), 1);
    addItemToKart(product);
}

function addItemToKart(item){
    var items = document.getElementById("items");
    var newItem = document.createElement("section");
    newItem.setAttribute("class", "kart-item");
    
    var productTitle = document.createElement("h2");
    productTitle.innerText = item.descripcion;
    
    var deleteBtn = document.createElement("img");
    deleteBtn.setAttribute("src", "img/delete.svg");
    
    //var deleteBtn = document.createElement("button")
    deleteBtn.setAttribute("class", "btn-eliminar-item");
    deleteBtn.setAttribute("onclick", "deleteItemToKart(" + item.idProducto + ")");
    
    productTitle.appendChild(deleteBtn);
    
    var cantSelect = document.createElement("input");
    cantSelect.setAttribute("min", "1");
    cantSelect.setAttribute("max",item.cantidad);
    cantSelect.setAttribute("type", "number");
    cantSelect.setAttribute("value", item.cantidadReq);
    cantSelect.setAttribute("class", "cant-input");
    cantSelect.setAttribute("id", "product-" + item.idProducto);
    cantSelect.setAttribute("onchange", "addCantidad(" + item.idProducto + ")");
    
    var productQuantity = document.createElement("h3");
    productQuantity.innerText = "Cantidad";
    productQuantity.setAttribute("class", "kart-item-label");
    productQuantity.appendChild(cantSelect);
    
    var productUnitPriceLabel = document.createElement("h3");
    productUnitPriceLabel.setAttribute("class", "kart-item-label");
    productUnitPriceLabel.innerHTML = "Precio unitario";
    
    var productUnitPrice = document.createElement("h3");
    productUnitPrice.innerText = new Intl.NumberFormat("de-DE").format(item.precioUnit) + " GS.";
    productUnitPrice.setAttribute("class", "kart-item-text");
    
    var subtotalLabel = document.createElement("h3");
    subtotalLabel.setAttribute("class", "kart-item-label");
    subtotalLabel.innerText = "Subtotal";
    
    var subTotal = document.createElement("h3");
    subTotal.setAttribute("id", "subtotal-" + item.idProducto);
    subTotal.setAttribute("class", "kart-item-text");
    subTotal.innerText = new Intl.NumberFormat("de-DE").format(item.precioUnit * item.cantidadReq) + " GS.";
    
    newItem.appendChild(productTitle);
    newItem.appendChild(productQuantity);
    newItem.appendChild(productUnitPriceLabel);
    newItem.appendChild(productUnitPrice);
    newItem.appendChild(subtotalLabel);
    newItem.appendChild(subTotal);
    items.appendChild(newItem);
    document.getElementById("total").innerText = new Intl.NumberFormat("de-DE").format(sumItems()) + " GS.";
}

function sumItems(){
    var sum = 0;
    var kart = document.getElementById("list-kart").value;
    kart = JSON.parse(kart);
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        console.log(element);
        sum += element.precioUnit * element.cantidadReq;
    }
    return sum;
}

function addCantidad(productId){
    var productKart = document.getElementById("product-" + productId);
    console.log(productKart);
    product = searchProductInKart(productId);
    console.log(product);    
    product.cantidadReq = parseInt(productKart.value);
    document.getElementById("subtotal-" + productId).innerText = new Intl.NumberFormat("de-DE").format(product.precioUnit * product.cantidadReq) + " GS.";
    updateKartList(product);
}

function searchProductInKart(productId){
    var kart = document.getElementById("list-kart").value;
    kart = JSON.parse(kart);
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        if(element.idProducto === productId){
            return element;
        }
    }
}

function updateKartList(product){
    var kart = document.getElementById("list-kart").value;
    kart = JSON.parse(kart);
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        if(element.idProducto === product.idProducto){
            console.log(element);
            kart[i] = JSON.stringify(product);
            document.getElementById("list-kart").value = JSON.stringify(kart);
            document.getElementById("total").innerText = new Intl.NumberFormat("de-DE").format(sumItems()) + " GS.";
            break;
        }
    }
    kart = document.getElementById("list-kart").value;
    eraseCookie("kart");
    createCookie("kart", kart, 1);
}

function deleteItemToKart(idProduct){
    var kart = document.getElementById("list-kart").value;
    kart = JSON.parse(kart);
    var newKart = [];
    for(var i = 0; i < kart.length; i++){
        var element = JSON.parse(kart[i]);
        console.log(element);
        if(element.idProducto !== idProduct){
            newKart.push(JSON.stringify(element));
        }
    }
    document.getElementById("list-kart").value = JSON.stringify(newKart);
    document.getElementById("total").innerText = new Intl.NumberFormat("de-DE").format(sumItems()) + " GS.";
    kart = document.getElementById("list-kart").value;
    eraseCookie("kart");
    createCookie("kart", kart, 1);
    window.location.href = "home";
}