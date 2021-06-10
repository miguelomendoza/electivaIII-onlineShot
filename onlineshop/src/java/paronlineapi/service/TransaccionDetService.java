/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.ArrayList;
import paronlineapi.entity.TransaccionesDet;
import paronlineapi.repository.TransaccionDetRepository;

/**
 *
 * @author mmendoza
 */
public class TransaccionDetService {

    TransaccionDetRepository transaccionDetRepository = new TransaccionDetRepository();

    public ArrayList<TransaccionesDet> getAll() {
        ArrayList<TransaccionesDet> resultado;
        resultado = transaccionDetRepository.getAll();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public ArrayList<TransaccionesDet> getByTransaccionId(int idTransaccion) {
        ArrayList<TransaccionesDet> resultado;
        if (String.valueOf(idTransaccion) != null) {
            resultado = transaccionDetRepository.getByTransaccionId(idTransaccion);
            return resultado;
        }
        return null;
    }
}
