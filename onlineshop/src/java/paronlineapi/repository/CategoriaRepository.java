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
import paronlineapi.util.DBUtils;

/**
 *
 * @author mmendoza
 */
public class CategoriaRepository {

    public boolean insertar(List<String> bodyCategoria) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            for (String categoria : bodyCategoria) {
                pstmt = conn.prepareStatement("insert into categoria (descripcion) values (?)");
                pstmt.setString(1, categoria);
                pstmt.execute();
            }
        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean update(Categoria c) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("update categoria set descripcion = ? where id_categoria = ?");
            pstmt.setString(1, c.getDescripcion());
            pstmt.setInt(2, c.getIdCategoria());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Categoria getCategoriaById(int id) {
        Categoria retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select descripcion from categoria where id_categoria = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = new Categoria();
                retValue.setIdCategoria(id);
                retValue.setDescripcion(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public ArrayList<Categoria> getAll() {
        ArrayList<Categoria> retValue = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_categoria, descripcion from categoria");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean delete(Categoria c) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("delete from categoria where id_categoria = ?");
            pstmt.setInt(1, c.getIdCategoria());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(CategoriaRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
}
