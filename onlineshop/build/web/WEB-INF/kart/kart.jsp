<%-- 
    Document   : kart
    Created on : 10/05/2021, 03:55:38 PM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
        <style><%@include file="/WEB-INF/kart/styles.css"%></style>
        <script><%@include file="/WEB-INF/kart/scripts.js"%></script>
    </head>
    <body>
        <section class="container">
            <section class="header">
                <h2>Mi carrito</h2>
            </section>
            <footer> 
                <button class="btn-confirm" onclick="showConfirmModal()">Confirmar compra</button>                
                <h3>Total: <span id="total" class="total-number">0</span></h3>
            </footer>            
            <section id="items" class="kart-items">
            </section>
        </section>
    </body>
</html>
