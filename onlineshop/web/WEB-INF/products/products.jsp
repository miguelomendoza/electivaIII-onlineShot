<%-- 
    Document   : products
    Created on : 10/05/2021, 03:49:00 PM
    Author     : mmendoza
--%>

<%@page import="paronlineapi.entity.Categoria"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="paronlineapi.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <style><%@include file="/WEB-INF/products/styles.css"%></style>
        <script><%@include file="/WEB-INF/products/scripts.js"%></script>
    </head>
    <body>
        <section class="container">
            <section class="search-block">
                <section class="description-block">
                    <input type="text" placeholder="Escriba el nombre del producto que desea encontrar" id="productName" onchange="searchProducts()" onkeyup="searchProducts()"/>
                    <button class="search-btn" onclick="searchProducts()">Buscar</button>
                </section>
                <section class="category-block">
                    <select id="category" onchange="searchProducts()">
                        <%
                            List<Categoria> categorias = (List<Categoria>) session.getAttribute("categorias");
                            if(categorias == null || categorias.isEmpty()){
                                out.println("<option>No hay categorías</option>");
                            }
                            else{
                                out.println("<option selected disabled value=" + -1 +">Seleccione una categoría</option>");
                                for(Categoria categoria : categorias){
                                    out.println("<option " + "value=" + categoria.getIdCategoria()  + ">" + categoria.getDescripcion() + "</option>");
                                }
                                out.println("<option value="+ 0 + ">Todas las categorías</option>");
                            }
                        %>
                    </select>
                </section>
            </section>
            <section class="product-block" id="product-block">
            <%
                List<Producto> productos = (List<Producto>) session.getAttribute("productos");
                if(productos == null || productos.isEmpty()){
                    out.println("<h2>No se han encontrado productos ... ");
                }
                else{
                    NumberFormat numberFormat = NumberFormat.getNumberInstance();
                    numberFormat.setMaximumFractionDigits(0);
                    for(Producto producto : productos){              
                        out.println("<section class=" + "product-card" + ">");
                            out.println("<h2 class=" + "product-title" + ">" + producto.getDescripcion() + "</h2>");
                            out.println("<h3 class=" + "product-category" + ">" + producto.getIdCategoria().getDescripcion() + "</h3>");
                            out.println("<h4 class=" + "product-quantity" + "> Stock: " + numberFormat.format(producto.getCantidad()) + "</h4>");
                            out.println("<h3 class=" + "product-price" + ">" + numberFormat.format(producto.getPrecioUnit()) + " GS.</h3>");
                            out.println("<button class=" + "product-buy" + " onclick=" + "addProductToKart(" + producto.getIdProducto() + ")" + ">Agregar</button>");
                        out.println("</section>");
                    }
                }    
            %>
            </section>
        </section>       
    </body>
</html>
