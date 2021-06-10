<%-- 
    Document   : kartModal
    Created on : 17/05/2019, 02:25:53 PM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script><%@include file="/WEB-INF/modals/kartModal/scripts.js"%></script>
    </head>
<body>
        <section class="modal-container" style="width: 80%">
            <section class="modal-header">
                <h1>Carrito</h1>
            </section>
            <section class="modal-body" id="kart-detail-body">
            </section>
            <section class="modal-footer">
                <button onclick="closeKartView()">Cerrar</button>
            </section>
        </section>
    </body>
</html>
