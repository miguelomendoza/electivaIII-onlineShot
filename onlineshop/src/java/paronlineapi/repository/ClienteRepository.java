/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paronlineapi.entity.Cliente;
import paronlineapi.util.DBUtils;

/**
 *
 * @author mmendoza
 */
public class ClienteRepository {

    public Cliente getByid(String loginName, String password) {

        Cliente retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_cliente, nombre, apellido, email, login_name, passwd, tipo_cliente from cliente where passwd = ? and login_name = ?");
            pstmt.setString(1, password);
            pstmt.setString(2, loginName);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = new Cliente();
                retValue.setIdCliente(rs.getInt(1));
                retValue.setNombre(rs.getString(2));
                retValue.setApellido(rs.getString(3));
                retValue.setEmail(rs.getString(4));
                retValue.setLoginName(rs.getString(5));
                retValue.setPasswd(rs.getString(6));
                retValue.setTipoCliente(rs.getInt(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean insertar(Cliente cliente, String password) {

        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("insert into cliente (nombre, apellido, email, login_name, passwd, tipo_cliente) values (?,?,?,?,?,?)");
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getLoginName());
            pstmt.setString(5, password);
            pstmt.setInt(6, cliente.getTipoCliente());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }
        return retValue;
    }

    public boolean update(Cliente cliente, String password) {

        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("update cliente set nombre = ?, apellido = ?, email = ?, login_name = ?, tipo_cliente= ? WHERE id_cliente = ?");
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getLoginName());
            pstmt.setInt(5, cliente.getTipoCliente());
            pstmt.setInt(6, cliente.getIdCliente());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }
        return retValue;
    }

    public boolean delete(int clienteId) {

        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("delete from cliente where id_cliente = ?");
            pstmt.setInt(1, clienteId);
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }
        return retValue;
    }

    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> retValue = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_cliente, nombre, apellido, email, login_name, passwd, tipo_cliente from cliente");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3).trim());
                c.setEmail(rs.getString(4));
                c.setLoginName(rs.getString(5));
                c.setPasswd(rs.getString(6));
                c.setTipoCliente(rs.getInt(7));
                retValue.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return retValue;
    }
}
