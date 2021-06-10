<%-- 
    Document   : formClient
    Created on : 17/05/2019, 09:40:17 AM
    Author     : mmendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style><%@include file="/WEB-INF/modals/formClient/styles.css"%></style>
    </head>
    <body>
        <section class="modal-container">
            <section class="modal-header">
                <h1>Datos del cliente</h1>
            </section>
            <section class="modal-body">
                <section style="margin-top: 10px">
                    <h3>Nombre</h3>
                    <input type="text" id="formClient-nombre"/>
                </section>
                <section style="margin-top: 10px">
                    <h3>Apellido</h3>
                    <input type="text" id="formClient-apellido"/>                    
                </section>
                <section style="margin-top: 10px">
                    <h3>Email</h3>
                    <input type="text" id="formClient-email"/>                    
                </section>
                <section style="margin-top: 10px">
                    <h3>Login name</h3>
                    <input type="text" id="formClient-loginName"/>
                </section>
                <section id="formClient-passwd-section" style="margin-top: 10px">
                    <h3>Contraseña</h3>
                    <input type="password" id="formClient-passwd"/>
                </section>
                <section style="margin-top: 10px">
                    <h3>Tipo de cliente</h3>
                    <select id="formClient-tipoCliente">
                        <option value="1">Administrador</option>
                        <option value="2">Cliente</option>
                    </select>
                </section>
            </section>
            <section class="modal-footer">
                <section id="btn-guardar-client" hidden>
                    <button onclick="saveCliente()">Guardar</button>                                    
                    <button onclick="closeFormClient()">Cerrar</button>                    
                </section>
                <section  id="btn-editar-client" hidden>
                    <button id="btn-editar-client-ok">Editar</button>                    
                    <button onclick="closeFormClient()">Cerrar</button>                    
                </section>
            </section>
        </section>             
    </body>
</html>
