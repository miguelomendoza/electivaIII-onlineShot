/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.ArrayList;
import java.util.List;
import paronlineapi.entity.Producto;
import paronlineapi.repository.ProductoRepository;

/**
 *
 * @author mmendoza
 */
public class ProductoService {

    ProductoRepository productoRepository = new ProductoRepository();

    public boolean insertar(Producto p) {
        boolean resultado = false;
        if (p.getDescripcion() != null && String.valueOf(p.getCantidad()) != null && p.getIdCategoria() != null && p.getPrecioUnit() != null) {
            resultado = productoRepository.insertar(p);
        }
        return resultado;
    }

    public boolean update(Producto p) {
        boolean resultado = false;
        if (String.valueOf(p.getIdProducto()) != null) {
            resultado = productoRepository.update(p);
        }
        return resultado;
    }

    public Producto getProductoById(int id) {
        Producto resultado;
        if (String.valueOf(id) != null) {
            resultado = productoRepository.getProductoById(id);
            return resultado;
        }
        return null;
    }

    public List<Producto> getProductoByDescripcion(String descripcion) {
        List<Producto> resultado;
        if (descripcion != null) {
            resultado = productoRepository.getProductoByDescripcion(descripcion);
            return resultado;
        }
        return null;
    }

    public List<Producto> getProductoByIdCategoria(int idCategoria) {
        List<Producto> resultado;
        if (String.valueOf(idCategoria) != null) {
            resultado = productoRepository.getProductoByIdCategoria(idCategoria);
            return resultado;
        }
        return null;
    }

    public List<Producto> getProductoByIdCategoriaDescripcion(int idCategoria, String descripcion) {
        List<Producto> resultado;
        if (String.valueOf(idCategoria) != null && descripcion != null) {
            resultado = productoRepository.getProductoByIdCategoriaDescripcion(idCategoria, descripcion);
            return resultado;
        }
        return null;
    }

    public ArrayList<Producto> getAll() {
        ArrayList<Producto> resultado;
        resultado = productoRepository.getAll();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public boolean delete(Producto p) {
        boolean resultado = false;
        if (String.valueOf(p.getIdProducto()) != null) {
            resultado = productoRepository.delete(p);
        }
        return resultado;
    }

}
