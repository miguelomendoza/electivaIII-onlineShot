/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Application;
import paronlineapi.entity.Categoria;
import paronlineapi.entity.Cliente;
import paronlineapi.entity.Producto;
import paronlineapi.service.CategoriaService;
import paronlineapi.service.ClienteService;
import paronlineapi.service.ProductoService;

/**
 *
 * @author mmendoza
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        initializerProyect();
        return resources;
    }


    private void initializerProyect() {
        ClienteService clienteService = new ClienteService();
        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();

        List<Cliente> resultadoCliente = clienteService.getAll();
        List<Categoria> resultadoCategoria = categoriaService.getAll();
        List<Producto> resultadoProducto = productoService.getAll();

        if (resultadoCliente == null || resultadoCliente.isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNombre("Miguel");
            cliente.setApellido("Mendoza");
            cliente.setEmail("mmendoza@FPUNA.com.py");
            cliente.setLoginName("mmendoza");
            cliente.setPasswd("mmendoza");
            cliente.setTipoCliente(1);
            clienteService.insertar(cliente);
        }
        if (resultadoCategoria == null || resultadoCategoria.isEmpty()) {
            List<String> categoriaBody = new ArrayList<>();
            String entretenimiento = "Entretenimiento";
            String oficina = "Oficina";
            String limpieza = "Limpieza";
            String libreria = "Libreria";
            String ferreteria = "Ferreteria";
            categoriaBody.add(entretenimiento);
            categoriaBody.add(oficina);
            categoriaBody.add(limpieza);
            categoriaBody.add(libreria);
            categoriaBody.add(ferreteria);
            categoriaService.insertar(categoriaBody);
        }
        if(resultadoProducto == null || resultadoProducto.isEmpty()){
            Producto producto = new Producto();
            producto.setCantidad(5);
            producto.setDescripcion("Paleta de Ping-Pong");
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(1);
            producto.setIdCategoria(categoria);
            producto.setIdProducto(1);
            producto.setPrecioUnit(BigDecimal.valueOf(100000L));
            productoService.insertar(producto);
        }
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(paronlineapi.controller.AccesoController.class);
        resources.add(paronlineapi.controller.CategoriaController.class);
        resources.add(paronlineapi.controller.ClienteController.class);
        resources.add(paronlineapi.controller.ProductoController.class);
        resources.add(paronlineapi.controller.TransaccionCabController.class);
        resources.add(paronlineapi.controller.TransaccionDetController.class);
    }
}
