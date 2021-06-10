<%-- 
    Document   : clientes
    Created on : 16/05/2021, 05:15:41 PM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style><%@include file="/WEB-INF/abm/clientes/styles.css"%></style>
        <script><%@include file="/WEB-INF/abm/clientes/scripts.js"%></script>
    </head>
    <body>
        <section class="container">
            <h1 class="clientes-title">Clientes</h1>
            <section class="content-body">
                <button class="btn-new" onclick="showFormClient()">Nuevo</button>
                <section id="clientes-abm-tables" style="margin-top: 10px"></section>
            </section>
        </section>    
    </body>
</html>
