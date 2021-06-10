/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showFormProduct(){
    cleanProductsFields();
    getAllCategorias();
    document.getElementById("btn-guardar-product").hidden = false;
    document.getElementById("btn-editar-product").hidden = true;
    document.getElementById("form-products-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");
}

function cleanProductsFields(){
    document.getElementById("formProduct-descripcion").value = "";
    document.getElementById("formProduct-categoria").value = "";
    document.getElementById("formProduct-precioUnit").value = "";
    document.getElementById("formProduct-cantidad").value = "";
}

function getAllCategorias(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/paronlineapi/webresources/categoriaRest/listaCategoria", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            return loadCategoryInForm(response.CategoriaList);
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");            
            showNoData();
        }
    };
    xhr.send();    
}

function loadCategoryInForm(categorias){
    console.log(categorias);
    var selectCat = document.getElementById("formProduct-categoria");
    removeAllChilds(selectCat);
    for(var i = 0; i < categorias.length; i++){
        var option = document.createElement("option");
        option.setAttribute("value", JSON.stringify(categorias[i]));
        option.innerText = categorias[i].descripcion;
        selectCat.appendChild(option);
    }
}

function closeFormProduct(){
    document.getElementById("form-products-modal").hidden = true;
    document.getElementById("container-home").setAttribute("style", "opacity:1");    
}

function saveProducto(){
    
    var object = validateProductFields();
    
    if(object === null || object === undefined){
        console.log("Campos invalidos");
        return;
    }
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/paronlineapi/webresources/productoRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            closeFormProduct();
            showABMProductos();
            showSnackbar("Producto registrado correctamente", "success");
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");            
        }
    };
    console.log(object);
    xhr.send(JSON.stringify(object));
}

function validateProductFields(){
    var descripcion = document.getElementById("formProduct-descripcion").value;
    var idCategoria = document.getElementById("formProduct-categoria").value;
    var precioUnit = document.getElementById("formProduct-precioUnit").value;
    var cantidad = document.getElementById("formProduct-cantidad").value;
    var object = {
        descripcion: descripcion,
        idCategoria: JSON.parse(idCategoria),
        precioUnit: precioUnit,
        cantidad: cantidad
    };
    return object;
}

function deleteProduct(idProducto){
    console.log("Eliminar: ", idProducto);
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "/paronlineapi/webresources/productoRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            showABMProductos();
            showSnackbar("Producto eliminado exitosamente", "success");                        
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");                        
        }
    };
    xhr.send(idProducto.toString());
}

function editarProduct(producto){
    console.log("Editar: ", producto);
    document.getElementById("formProduct-descripcion").value = producto.descripcion;
    document.getElementById("formProduct-categoria").value = producto.idCategoria.categoria;
    document.getElementById("formProduct-precioUnit").value = producto.precioUnit;
    document.getElementById("formProduct-cantidad").value = producto.cantidad;
    document.getElementById("btn-guardar-product").hidden = true;
    document.getElementById("btn-editar-product").hidden = false;
    getAllCategorias();
    document.getElementById("form-products-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");
    var btnEditar = document.getElementById("btn-editar-product-ok");
    btnEditar.setAttribute("onclick", "updateProduct(" + producto.idProducto + ")");
}

function updateProduct(producto){
    var object = validateProductFields();
    if(object === null || object === undefined){
        console.log("Campos invalidos");
        return;
    }
    object.idProducto = producto;
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "/paronlineapi/webresources/productoRest", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var response = xhr.responseText;
            response = JSON.parse(response);
            console.log(response);
            closeFormProduct();
            showABMProductos();
            showSnackbar("Datos actualizados correctamente", "success");                        
        }
        else{
            console.log(xhr.responseText);
            console.log("ERROR");
            showSnackbar("Ha ocurrido un error al procesar la solicitud", "error");                        
        }
    };
    console.log(object);
    xhr.send(JSON.stringify(object));    
}