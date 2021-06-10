<%-- 
    Document   : home
    Created on : 10/05/2021, 02:01:42 PM
    Author     : mmendoza
--%>

<%@page import="paronlineapi.entity.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    session.setAttribute("productos", request.getAttribute("productos"));
    session.setAttribute("categorias", request.getAttribute("categorias"));
    String currentPage = (String) request.getAttribute("page");
    Cookie[] cookieList = request.getCookies();
    Boolean existeSesion = false;
    String userId = null;
    String userEmail = null; 
    String userRol = null;
    for (int i = 0; i < cookieList.length; i++) {
        System.out.println(cookieList[i].getName()); 
        String name = cookieList[i].getName();
        if (name.equals("userId")) {
            userId = cookieList[i].getValue();
            existeSesion = true;
        }
        if (name.equals("userEmail")) {
            userEmail = cookieList[i].getValue();
            existeSesion = true;
        } 
        if (name.equals("userRol")){
            userRol = cookieList[i].getValue();
            existeSesion = true; 
        }
    } 
%> 
<!DOCTYPE html> 
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>OnlineShop | Home</title>
        <style><%@include file="/WEB-INF/home/styles.css"%></style>
        <script><%@include file="/WEB-INF/js-core/cookie.js"%></script>
        <script><%@include file="/WEB-INF/home/scripts.js"%></script>
    </head>
    <body onload="loadResources()">
        <div class="container-home" id="container-home">  
            <header> 
                <h3><img src="img/kart.svg"/>&nbsp;&nbsp;<span>Shop Online</span></h3>
                <%if (existeSesion) {
                        out.println("<section class=" + "elements-header" + ">");
                        out.println("<h3 class=" + "title-user" + ">" + userEmail + "</h3>");
                        out.println("<button class=" + "btn-logout" + " onclick=" + "logout()" + ">Cerrar sesión</button>");
                        out.println("</section>");
                    } else {
                        out.println("<button class=" + "btn-login" + " onclick=" + "goLogin()" + ">Iniciar sesión</button>");
                    }%>
            </header>
            <section class="sidebar">
                <%@ include file="/WEB-INF/kart/kart.jsp"%>
            </section> 
            <section class="content"> 
                <section class="navigation-bar">
                    <%if (currentPage.equals("productos")) {%> 
                    <a href="#" id="nav-products" onclick="showProducts()" class="nav-selected">Productos</a>&nbsp;
                    <a href="#" id="nav-purchases" onclick="showPurchases()">Compras anteriores</a>
                    <%} else {%>
                    <a href="#" onclick="showProducts()">Productos</a>&nbsp;
                    <a href="#" onclick="showPurchases()" class="nav-selected">Compras anteriores</a>
                    <%}%>
                    <%if (userRol != null && userRol.equals("1")) {%>
                        &nbsp;<a href="#" id="nav-abmClientes" onclick="showABMClientes()">ABM Clientes</a>&nbsp;
                        <a href="#" id="nav-abmProductos" onclick="showABMProductos()">ABM Productos</a>
                    <%}%>
                </section>
                <section class="page">  
                    <%if (currentPage.equals("productos")) {%> 
                    <section id="products-page"> 
                        <%@ include file="/WEB-INF/products/products.jsp"%>                        
                    </section> 
                    <section id="purchases-page" hidden>
                        <%@ include file="/WEB-INF/purchases/purchases.jsp"%>
                    </section> 
                    <%} else {%> 
                    <section id="products-page" hidden>
                        <%@ include file="/WEB-INF/products/products.jsp"%>                        
                    </section>
                    <section id="purchases-page">
                        <%@ include file="/WEB-INF/purchases/purchases.jsp"%>
                    </section> 
                    <%}%>
                    <section id="abmClientes-page" hidden>
                        <%@ include file="/WEB-INF/abm/clientes/clientes.jsp"%>
                    </section>
                    <section id="abmProductos-page" hidden>
                        <%@ include file="/WEB-INF/abm/productos/productos.jsp"%>
                    </section>
                </section>
                <input type="text" hidden id="list-kart"/>
            </section>
        </div> 
        <section id="confirm-purchase-modal" hidden>
            <%@ include file="/WEB-INF/modals/confirmPurchase/confirmPurchase.jsp"%>
        </section> 
        <section id="form-clients-modal" hidden>
            <%@ include file="/WEB-INF/modals/formClient/formClient.jsp"%>
        </section>
        <section id="form-products-modal" hidden> 
            <%@ include file="/WEB-INF/modals/formProduct/formProduct.jsp"%>
        </section>
        <section id="login-modal" hidden>
            <%@ include file="/WEB-INF/login/login.jsp"%>
        </section>
        <section id="kart-modal" hidden> 
            <%@ include file="/WEB-INF/modals/kartModal/kartModal.jsp"%>
        </section>
        <section id="snackbar-modal" hidden>
            <%@ include file="/WEB-INF/modals/snackbar/snackbar.jsp"%>
        </section>
    </body>
</html>
