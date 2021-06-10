/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import paronlineapi.entity.Cliente;
import paronlineapi.repository.ClienteRepository;

/**
 *
 * @author mmendoza
 */
public class ClienteService{
    
    ClienteRepository clienteRepository = new ClienteRepository();
    
    public Cliente getByid(String loginName, String password) {
        Cliente resultado;
        if (loginName != null && password != null) {
            String contraseña = getMd5(password);
            resultado = clienteRepository.getByid(loginName, contraseña);
            return resultado;
        }
        return null;
    }
    
    public boolean insertar(Cliente cliente) {
        boolean resultado = false;
        if (cliente.getNombre() != null && cliente.getApellido() != null && cliente.getEmail() != null && cliente.getLoginName() != null && cliente.getPasswd() != null && String.valueOf(cliente.getTipoCliente()) != null) {
            String contraseña = getMd5(cliente.getPasswd());
            resultado = clienteRepository.insertar(cliente,contraseña);
        }
        return resultado;
    }
    
    public boolean update(Cliente cliente) {
        boolean resultado = false;
        if (String.valueOf(cliente.getIdCliente()) != null) {
            String contraseña = getMd5(cliente.getPasswd());
            resultado = clienteRepository.update(cliente,contraseña);
        }
        return resultado;
    }
    
    public boolean delete(int clienteId) {
        boolean resultado = false;
        if (String.valueOf(clienteId) != null) {
            resultado = clienteRepository.delete(clienteId);
        }
        return resultado;
    }
    
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> resultado;
        resultado = clienteRepository.getAll();
        if (!resultado.isEmpty() && resultado != null) {
            return resultado;
        }
        return null;
    }
    
    private static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
