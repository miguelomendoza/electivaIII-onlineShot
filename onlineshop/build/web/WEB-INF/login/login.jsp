<%-- 
    Document   : login
    Created on : 10/05/2021, 10:14:20 AM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OnlineShop | Login</title>
        <style><%@include file="/WEB-INF/login/styles.css"%></style>
        <script><%@include file="/WEB-INF/login/scripts.js"%></script>
        <script><%@include file="/WEB-INF/js-core/cookie.js"%></script>
    </head> 
    <body>
        <section class="login-box"> 
            <h3>Iniciar sesión</h3> 
            <section class="form-login"> 
                <h5>Username</h5> 
                <input type="text" id="username" autocomplete="off" onkeyup="if(event.keyCode == 13) login()"/>
                <br><br>
                <h5>Password</h5> 
                <input type="password" id="password" onkeyup="if(event.keyCode == 13) login()"/>
                <h5 class="error-login" id="error-message" hidden></h5> 
            </section>
            <button class="btn-primary" onclick="login()">Ingresar</button>
            <button class="btn-secondary" onclick="continueGuest()">Continuar como invitado</button>
        </section>      
    </body>
</html>
