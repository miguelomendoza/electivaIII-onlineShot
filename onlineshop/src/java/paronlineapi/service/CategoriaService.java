/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.ArrayList;
import java.util.List;
import paronlineapi.entity.Categoria;
import paronlineapi.repository.CategoriaRepository;

/**
 *
 * @author mmendoza
 */
public class CategoriaService {

    CategoriaRepository categoriaRepository = new CategoriaRepository();

    public boolean insertar(List<String> categoriaBody) {
        boolean resultado = false;
        if (!categoriaBody.isEmpty()) {
            resultado = categoriaRepository.insertar(categoriaBody);
        }
        return resultado;
    }

    public boolean update(Categoria c) {
        boolean resultado = false;
        if (c.getIdCategoria() != null && c.getDescripcion() != null && !"".equals(c.getDescripcion())) {
            resultado = categoriaRepository.update(c);
        }
        return resultado;
    }

    public Categoria getCategoriaById(int id) {
        Categoria resultado;
        resultado = categoriaRepository.getCategoriaById(id);
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public ArrayList<Categoria> getAll() {
        ArrayList<Categoria> resultado;
        resultado = categoriaRepository.getAll();
        if (!resultado.isEmpty() && resultado != null) {
            return resultado;
        }
        return null;
    }

    public boolean delete(Categoria c) {
        boolean resultado = false;
        if (c.getIdCategoria() != null) {
            resultado = categoriaRepository.delete(c);
        }
        return resultado;
    }
}
