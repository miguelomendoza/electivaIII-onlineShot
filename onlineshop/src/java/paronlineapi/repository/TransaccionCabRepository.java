/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import paronlineapi.entity.Cliente;
import paronlineapi.entity.Producto;
import paronlineapi.entity.TransaccionesCab;
import paronlineapi.entity.TransaccionesDet;
import paronlineapi.util.DBUtils;

/**
 *
 * @author mmendoza
 */
public class TransaccionCabRepository {

    ProductoRepository productoRepository = new ProductoRepository();

    public boolean insertar(TransaccionesCab transaccionesCab) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtTransaccionesDet = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("insert into transacciones_cab (fecha, id_cliente, total, direccion_envio, id_medio_pago, nro_tarjeta, estado) values (?,?,?,?,?,?,?)");
            java.util.Date date = transaccionesCab.getFecha();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pstmt.setDate(1, sqlDate);
            pstmt.setInt(2, transaccionesCab.getIdCliente().getIdCliente());
            pstmt.setBigDecimal(3, transaccionesCab.getTotal());
            pstmt.setString(4, transaccionesCab.getDireccionEnvio());
            pstmt.setInt(5, transaccionesCab.getIdMedioPago());
            if (transaccionesCab.getNroTarjeta() != null) {
                pstmt.setBigDecimal(6, BigDecimal.valueOf(transaccionesCab.getNroTarjeta().intValue()));
            } else {
                pstmt.setBigDecimal(6, null);
            }
            pstmt.setString(7, transaccionesCab.getEstado());
            pstmt.execute();
            if (!transaccionesCab.getTransaccionesDetCollection().isEmpty()) {
                conn = DBUtils.getConnection();
                pstmt = conn.prepareStatement("select MAX(id_transaccion) from transacciones_cab");
                Integer transaccionCabId = null;
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    transaccionCabId = rs.getInt(1);
                }
                for (TransaccionesDet listTransaccionesDet : transaccionesCab.getTransaccionesDetCollection()) {
                    pstmtTransaccionesDet = conn.prepareStatement("insert into transacciones_det (item, id_producto, cantidad, precio, sub_total, id_transaccion) values (?,?,?,?,?,?)");
                    pstmtTransaccionesDet.setInt(1, listTransaccionesDet.getTransaccionesDetPK().getItem());
                    pstmtTransaccionesDet.setInt(2, listTransaccionesDet.getIdProducto().getIdProducto());
                    pstmtTransaccionesDet.setInt(3, listTransaccionesDet.getCantidad());
                    pstmtTransaccionesDet.setBigDecimal(4, listTransaccionesDet.getPrecio());
                    pstmtTransaccionesDet.setBigDecimal(5, listTransaccionesDet.getSubTotal());
                    pstmtTransaccionesDet.setInt(6, transaccionCabId);
                    pstmtTransaccionesDet.execute();

                    //actualiza la cantidad de stock
                    //obtiene la cantidad 
                    Producto producto = productoRepository.getProductoById(listTransaccionesDet.getIdProducto().getIdProducto());
                    Integer cantidad = producto.getCantidad() - listTransaccionesDet.getCantidad();
                    producto.setCantidad(cantidad);
                    //actualiza la cantidad restante
                    productoRepository.update(producto);
                }
            }
        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(TransaccionCabRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaccionCabRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public ArrayList<TransaccionesCab> getAll() {
        ArrayList<TransaccionesCab> retValueList = new ArrayList<>();
        TransaccionesCab transaccionesCab = null;
        Cliente cliente = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rsCliente = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_transaccion, fecha, id_cliente, total, direccion_envio, id_medio_pago, nro_tarjeta, estado from transacciones_cab");
            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmt = conn.prepareStatement("select id_cliente, nombre, apellido from cliente");
                rsCliente = pstmt.executeQuery();
                if (rsCliente.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rsCliente.getInt(1));
                    cliente.setNombre(rsCliente.getString(2));
                    cliente.setApellido(rsCliente.getString(3));
                }

                transaccionesCab = new TransaccionesCab();
                transaccionesCab.setIdTransaccion(rs.getInt(1));
                transaccionesCab.setFecha(rs.getDate(2));
                transaccionesCab.setIdCliente(cliente);
                transaccionesCab.setTotal(rs.getBigDecimal(4));
                transaccionesCab.setDireccionEnvio(rs.getString(5));
                transaccionesCab.setIdMedioPago(rs.getInt(6));
                transaccionesCab.setNroTarjeta(BigInteger.valueOf(Long.parseLong(String.valueOf(rs.getInt(7)))));
                transaccionesCab.setEstado(rs.getString(8));
                retValueList.add(transaccionesCab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValueList;
    }

    public List<TransaccionesCab> getByTransaccionesCabId(int transaccionId) {
        List<TransaccionesCab> transaccionesCabList = new ArrayList<>();
        TransaccionesCab transaccionesCab = null;
        Cliente cliente = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rsCliente = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_transaccion, fecha, id_cliente, total, direccion_envio, id_medio_pago, nro_tarjeta, estado from transacciones_cab where id_cliente = ?");
            pstmt.setInt(1, transaccionId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                pstmt = conn.prepareStatement("select id_cliente, nombre, apellido from cliente");
                rsCliente = pstmt.executeQuery();
                if (rsCliente.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rsCliente.getInt(1));
                    cliente.setNombre(rsCliente.getString(2));
                    cliente.setApellido(rsCliente.getString(3));
                }

                transaccionesCab = new TransaccionesCab();
                transaccionesCab.setIdTransaccion(rs.getInt(1));
                transaccionesCab.setFecha(rs.getDate(2));
                transaccionesCab.setIdCliente(cliente);
                transaccionesCab.setTotal(rs.getBigDecimal(4));
                transaccionesCab.setDireccionEnvio(rs.getString(5));
                transaccionesCab.setIdMedioPago(rs.getInt(6));
                transaccionesCab.setNroTarjeta(BigInteger.valueOf(Long.parseLong(String.valueOf(rs.getInt(7)))));
                transaccionesCab.setEstado(rs.getString(8));
                transaccionesCabList.add(transaccionesCab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return transaccionesCabList;
    }
}
