<%-- 
    Document   : productos
    Created on : 16/05/2021, 05:14:56 PM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style><%@include file="/WEB-INF/abm/productos/styles.css"%></style>
        <script><%@include file="/WEB-INF/abm/productos/scripts.js"%></script>        
    </head>
    <body>
        <section class="container">
            <h1 class="products-title">Productos</h1>
            <section class="content-body">
                <button class="btn-new" onclick="showFormProduct()">Nuevo</button>
                <section id="productos-abm-tables" style="margin-top: 10px"></section>
            </section>
        </section>    
    </body>
</html>
