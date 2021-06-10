<%-- 
    Document   : confirmPurchase
    Created on : 16/05/2021, 01:47:50 PM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style><%@include file="/WEB-INF/modals/confirmPurchase/styles.css"%></style>
    </head>
    <body>
        <section class="modal-container">
            <section class="modal-header">
                <h1>Confirmación de la compra</h1>
            </section>
            <section class="modal-body">
                <section>
                    <h3>Total<span class="total-modal" id="total-modal"></span></h3>
                </section>
                <section style="margin-top: 10px">
                    <h3>Método de pago</h3>
                    <select id="metodoPago-input" onchange="validateCardInput()">
                        <option value="1" selected>Efectivo</option>
                        <option value="2">Tarjeta</option>
                    </select>
                </section>
                <section style="margin-top: 10px">
                    <h3>Número de tarjeta</h3>
                    <input type="number" id="tarjeta-input" disabled/>
                </section>
                <section style="margin-top: 10px">
                    <h3>Dirección de envío</h3>
                    <input type="text" id="direccion-input"/>
                </section>
                <section class="error-confirmacion-compra" id="error-confirmacion-compra" hidden>
                </section>                
            </section>
            <section class="modal-footer">
                <button onclick="handleConfirmTransaction()">Confirmar</button>
                <button onclick="hiddenConfirmPurchase()">Cerrar</button>
            </section>            
        </section>            
    </body>
</html>
