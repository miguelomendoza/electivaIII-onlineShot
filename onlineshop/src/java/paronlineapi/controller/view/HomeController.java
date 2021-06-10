/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.controller.view;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paronlineapi.entity.Categoria;
import paronlineapi.entity.Producto;
import paronlineapi.repository.CategoriaRepository;
import paronlineapi.repository.ProductoRepository;

/**
 *
 * @author mmendoza
 */

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeController extends HttpServlet{

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/home.jsp");
        ProductoRepository productoRepository = new ProductoRepository();
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        List<Producto> productos = productoRepository.getAll();
        List<Categoria> categorias = categoriaRepository.getAll();
        request.setAttribute("page", "productos");
        request.setAttribute("categorias", categorias);
        request.setAttribute("productos", productos);
        dispatcher.forward(request, response);
    }
    
}
