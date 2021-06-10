<%-- 
    Document   : formProduct
    Created on : 17/05/2019, 09:40:17 AM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section class="modal-container">
            <section class="modal-header">
                <h1>Datos del producto</h1>
            </section>
            <section class="modal-body">
                <section style="margin-top: 10px">
                    <h3>Descripcion</h3>
                    <input type="text" id="formProduct-descripcion"/>
                </section>
                <section style="margin-top: 10px">
                    <h3>Categoria</h3>
                    <select id="formProduct-categoria">
                        <option>Entretenimiento</option>
                    </select>
                </section>
                <section style="margin-top: 10px">
                    <h3>Precio unitario</h3>
                    <input type="number" id="formProduct-precioUnit"/>                    
                </section>
                <section style="margin-top: 10px">
                    <h3>Cantidad</h3>
                    <input type="number" id="formProduct-cantidad"/>
                </section>
            </section>
            <section class="modal-footer">
                <section id="btn-guardar-product" hidden>
                    <button onclick="saveProducto()">Guardar</button>                                    
                    <button onclick="closeFormProduct()">Cerrar</button>                    
                </section>
                <section  id="btn-editar-product" hidden>
                    <button id="btn-editar-product-ok">Editar</button>                    
                    <button onclick="closeFormProduct()">Cerrar</button>                    
                </section>
            </section>
        </section>            
    </body>
</html>
