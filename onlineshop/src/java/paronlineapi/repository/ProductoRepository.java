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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import paronlineapi.entity.Categoria;
import paronlineapi.entity.Producto;
import paronlineapi.util.DBUtils;

/**
 *
 * @author mmendoza
 */
public class ProductoRepository {

    public boolean insertar(Producto p) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("insert into producto (descripcion,id_categoria,precio_unit,cantidad) values (?,?,?,?)");
            pstmt.setString(1, p.getDescripcion());
            pstmt.setInt(2, p.getIdCategoria().getIdCategoria());
            pstmt.setBigDecimal(3, p.getPrecioUnit());
            pstmt.setInt(4, p.getCantidad());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean update(Producto p) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("update producto set descripcion = ?, precio_unit = ?, cantidad = ?, id_categoria = ? where id_producto = ?");
            pstmt.setString(1, p.getDescripcion());
            pstmt.setBigDecimal(2, p.getPrecioUnit());
            pstmt.setInt(3, p.getCantidad());
            pstmt.setInt(4, p.getIdCategoria().getIdCategoria());            
            pstmt.setInt(5, p.getIdProducto());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Producto getProductoById(int id) {
        Producto retValue = null;
        Categoria retValueCategoria = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtCategoria = null;
        ResultSet rsCategoria = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria, precio_unit, cantidad from producto  where id_producto = ?");
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                pstmtCategoria = conn.prepareStatement("select id_categoria, descripcion from categoria where id_categoria = ?");
                pstmtCategoria.setInt(1, rs.getInt(3));
                rsCategoria = pstmtCategoria.executeQuery();

                if (rsCategoria.next()) {
                    retValueCategoria = new Categoria();
                    retValueCategoria.setIdCategoria(rs.getInt(1));
                    retValueCategoria.setDescripcion(rsCategoria.getString(2));
                }

                retValue = new Producto();
                retValue.setIdProducto(rs.getInt(1));
                retValue.setDescripcion(rs.getString(2));
                retValue.setIdCategoria(retValueCategoria);
                retValue.setPrecioUnit(rs.getBigDecimal(4));
                retValue.setCantidad(rs.getInt(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
    
    public List<Producto> getProductoByDescripcion(String descripcion) {
        List<Producto> productoList = new ArrayList<>();
        Producto retValue = null;
        Categoria retValueCategoria = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtCategoria = null;
        ResultSet rsCategoria = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria, precio_unit, cantidad from producto where UPPER(descripcion) LIKE UPPER('" + descripcion + "%') order by descripcion");

            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmtCategoria = conn.prepareStatement("select id_categoria, descripcion from categoria where id_categoria = ?");
                pstmtCategoria.setInt(1, rs.getInt(3));
                rsCategoria = pstmtCategoria.executeQuery();

                if (rsCategoria.next()) {
                    retValueCategoria = new Categoria();
                    retValueCategoria.setIdCategoria(rs.getInt(1));
                    retValueCategoria.setDescripcion(rsCategoria.getString(2));
                }

                retValue = new Producto();
                retValue.setIdProducto(rs.getInt(1));
                retValue.setDescripcion(rs.getString(2));
                retValue.setIdCategoria(retValueCategoria);
                retValue.setPrecioUnit(rs.getBigDecimal(4));
                retValue.setCantidad(rs.getInt(5));
                productoList.add(retValue);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return productoList;
    }
    
    public List<Producto> getProductoByIdCategoria(int idCategoria) {
        List<Producto> productoList = new ArrayList<>();
        Producto retValue = null;
        Categoria retValueCategoria = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtCategoria = null;
        ResultSet rsCategoria = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria, precio_unit, cantidad from producto  where id_categoria = ? order by descripcion");
            pstmt.setInt(1, idCategoria);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmtCategoria = conn.prepareStatement("select id_categoria, descripcion from categoria where id_categoria = ?");
                pstmtCategoria.setInt(1, rs.getInt(3));
                rsCategoria = pstmtCategoria.executeQuery();

                if (rsCategoria.next()) {
                    retValueCategoria = new Categoria();
                    retValueCategoria.setIdCategoria(rs.getInt(1));
                    retValueCategoria.setDescripcion(rsCategoria.getString(2));
                }

                retValue = new Producto();
                retValue.setIdProducto(rs.getInt(1));
                retValue.setDescripcion(rs.getString(2));
                retValue.setIdCategoria(retValueCategoria);
                retValue.setPrecioUnit(rs.getBigDecimal(4));
                retValue.setCantidad(rs.getInt(5));
                
                productoList.add(retValue);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return productoList;
    }
    
    public List<Producto> getProductoByIdCategoriaDescripcion(int idCategoria, String descripcion) {
        List<Producto> producto = new ArrayList<>();
        Categoria retValueCategoria = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtCategoria = null;
        ResultSet rsCategoria = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria, precio_unit, cantidad from producto  where id_categoria = ? and UPPER(descripcion) LIKE UPPER('" + descripcion + "%') order by descripcion");
            pstmt.setInt(1, idCategoria);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                pstmtCategoria = conn.prepareStatement("select id_categoria, descripcion from categoria where id_categoria = ?");
                pstmtCategoria.setInt(1, rs.getInt(3));
                rsCategoria = pstmtCategoria.executeQuery();

                if (rsCategoria.next()) {
                    retValueCategoria = new Categoria();
                    retValueCategoria.setIdCategoria(rs.getInt(1));
                    retValueCategoria.setDescripcion(rsCategoria.getString(2));
                }

                Producto retValue = new Producto();
                retValue.setIdProducto(rs.getInt(1));
                retValue.setDescripcion(rs.getString(2));
                retValue.setIdCategoria(retValueCategoria);
                retValue.setPrecioUnit(rs.getBigDecimal(4));
                retValue.setCantidad(rs.getInt(5));
                producto.add(retValue);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }
        return producto;
    }

    public ArrayList<Producto> getAll() {
        ArrayList<Producto> retValue = new ArrayList<>();

        Categoria retValueCategoria = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmtCategoria = null;
        ResultSet rsCategoria = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria, precio_unit, cantidad from producto order by descripcion");
            rs = pstmt.executeQuery();

            while (rs.next()) {

                pstmtCategoria = conn.prepareStatement("select id_categoria, descripcion from categoria where id_categoria = ?");
                pstmtCategoria.setInt(1, rs.getInt(3));
                rsCategoria = pstmtCategoria.executeQuery();

                if (rsCategoria.next()) {
                    retValueCategoria = new Categoria();
                    retValueCategoria.setIdCategoria(rs.getInt(1));
                    retValueCategoria.setDescripcion(rsCategoria.getString(2));
                }

                Producto c = new Producto();
                c.setIdProducto(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                c.setIdCategoria(retValueCategoria);
                c.setPrecioUnit(rs.getBigDecimal(4));
                c.setCantidad(rs.getInt(5));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean delete(Producto p) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("delete from producto where id_producto = ?");
            pstmt.setInt(1, p.getIdProducto());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
}
