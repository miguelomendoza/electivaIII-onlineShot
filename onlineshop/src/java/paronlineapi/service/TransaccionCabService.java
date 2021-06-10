/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.ArrayList;
import java.util.List;
import paronlineapi.entity.TransaccionesCab;
import paronlineapi.repository.TransaccionCabRepository;

/**
 *
 * @author mmendoza
 */
public class TransaccionCabService {

    TransaccionCabRepository transaccionCabRepository = new TransaccionCabRepository();

    public boolean insertar(TransaccionesCab transaccionesCab) {
        boolean resultado = false;
        if (transaccionesCab.getFecha() != null && transaccionesCab.getIdCliente() != null && transaccionesCab.getTotal() != null
                && transaccionesCab.getDireccionEnvio() != null && transaccionesCab.getIdMedioPago() != null && transaccionesCab.getEstado() != null) {
            if(transaccionesCab.getIdMedioPago() == 2){
                if(transaccionesCab.getNroTarjeta() != null){
                    resultado = transaccionCabRepository.insertar(transaccionesCab);                                    
                }  
            }
            else{
                resultado = transaccionCabRepository.insertar(transaccionesCab);                
            }
        }
        return resultado;
    }

    public ArrayList<TransaccionesCab> getAll() {
        ArrayList<TransaccionesCab> resultado;
        resultado = transaccionCabRepository.getAll();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public List<TransaccionesCab> getByTransaccionesCabId(int transaccionId) {
        List<TransaccionesCab> resultado;
        if (String.valueOf(transaccionId) != null) {
            resultado = transaccionCabRepository.getByTransaccionesCabId(transaccionId);
            return resultado;
        }
        return null;
    }
}
