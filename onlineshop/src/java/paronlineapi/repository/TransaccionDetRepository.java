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
import paronlineapi.entity.Producto;
import paronlineapi.entity.TransaccionesDet;
import paronlineapi.entity.TransaccionesDetPK;
import paronlineapi.util.DBUtils;

/**
 *
 * @author mmendoza
 */
public class TransaccionDetRepository {

    public ArrayList<TransaccionesDet> getAll() {
        ArrayList<TransaccionesDet> retValueList = new ArrayList<>();

        TransaccionesDet retValue = null;
        TransaccionesDetPK retValueDetPk = null;
        Producto retValueProducto = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtDetPk = null;
        PreparedStatement pstmtProducto = null;
        ResultSet rsTransaccionDetPk = null;
        ResultSet rsProducto = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_transaccion, item, id_producto, cantidad, precio, sub_total from transacciones_det");
            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmtProducto = conn.prepareStatement("select id_producto, descripcion, precio_unit, cantidad from producto  where id_producto = ?");
                pstmtProducto.setInt(1, rs.getInt(3));
                rsProducto = pstmtProducto.executeQuery();

                if (rsProducto.next()) {
                    retValueProducto = new Producto();
                    retValueProducto.setIdProducto(rsProducto.getInt(1));
                    retValueProducto.setDescripcion(rsProducto.getString(2));
                    retValueProducto.setPrecioUnit(rsProducto.getBigDecimal(3));
                    retValueProducto.setCantidad(rsProducto.getInt(4));
                }

                retValue = new TransaccionesDet();
                retValue.setTransaccionesDetPK(new TransaccionesDetPK(rs.getInt(1), rs.getInt(2)));
                retValue.setIdProducto(retValueProducto);
                retValue.setCantidad(rs.getInt(4));
                retValue.setPrecio(rs.getBigDecimal(5));
                retValue.setSubTotal(rs.getBigDecimal(6));
                retValueList.add(retValue);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValueList;
    }

    public ArrayList<TransaccionesDet> getByTransaccionId(int idTransaccion) {
        ArrayList<TransaccionesDet> retValueList = new ArrayList<>();

        TransaccionesDet retValue = null;
        TransaccionesDetPK retValueDetPk = null;
        Producto retValueProducto = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtDetPk = null;
        PreparedStatement pstmtProducto = null;
        ResultSet rsTransaccionDetPk = null;
        ResultSet rsProducto = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_transaccion, item, id_producto, cantidad, precio, sub_total from transacciones_det WHERE id_transaccion = ?");
            pstmt.setInt(1, idTransaccion);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmtProducto = conn.prepareStatement("select id_producto, descripcion, precio_unit, cantidad from producto  where id_producto = ?");
                pstmtProducto.setInt(1, rs.getInt(3));
                rsProducto = pstmtProducto.executeQuery();

                if (rsProducto.next()) {
                    retValueProducto = new Producto();
                    retValueProducto.setIdProducto(rsProducto.getInt(1));
                    retValueProducto.setDescripcion(rsProducto.getString(2));
                    retValueProducto.setPrecioUnit(rsProducto.getBigDecimal(3));
                    retValueProducto.setCantidad(rsProducto.getInt(4));
                }

                retValue = new TransaccionesDet();
                retValue.setTransaccionesDetPK(new TransaccionesDetPK(rs.getInt(1), rs.getInt(2)));
                retValue.setIdProducto(retValueProducto);
                retValue.setCantidad(rs.getInt(4));
                retValue.setPrecio(rs.getBigDecimal(5));
                retValue.setSubTotal(rs.getBigDecimal(6));
                retValueList.add(retValue);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return retValueList;
    }
}
