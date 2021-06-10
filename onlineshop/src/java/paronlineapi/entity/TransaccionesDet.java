/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmendoza
 */
@Entity
@Table(name = "transacciones_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransaccionesDet.findAll", query = "SELECT t FROM TransaccionesDet t")
    , @NamedQuery(name = "TransaccionesDet.findByIdTransaccion", query = "SELECT t FROM TransaccionesDet t WHERE t.transaccionesDetPK.idTransaccion = :idTransaccion")
    , @NamedQuery(name = "TransaccionesDet.findByItem", query = "SELECT t FROM TransaccionesDet t WHERE t.transaccionesDetPK.item = :item")
    , @NamedQuery(name = "TransaccionesDet.findByCantidad", query = "SELECT t FROM TransaccionesDet t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "TransaccionesDet.findByPrecio", query = "SELECT t FROM TransaccionesDet t WHERE t.precio = :precio")
    , @NamedQuery(name = "TransaccionesDet.findBySubTotal", query = "SELECT t FROM TransaccionesDet t WHERE t.subTotal = :subTotal")})
public class TransaccionesDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionesDetPK transaccionesDetPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TransaccionesCab transaccionesCab;

    public TransaccionesDet() {
    }

    public TransaccionesDet(TransaccionesDetPK transaccionesDetPK) {
        this.transaccionesDetPK = transaccionesDetPK;
    }

    public TransaccionesDet(int idTransaccion, int item) {
        this.transaccionesDetPK = new TransaccionesDetPK(idTransaccion, item);
    }

    public TransaccionesDetPK getTransaccionesDetPK() {
        return transaccionesDetPK;
    }

    public void setTransaccionesDetPK(TransaccionesDetPK transaccionesDetPK) {
        this.transaccionesDetPK = transaccionesDetPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public TransaccionesCab getTransaccionesCab() {
        return transaccionesCab;
    }

    public void setTransaccionesCab(TransaccionesCab transaccionesCab) {
        this.transaccionesCab = transaccionesCab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccionesDetPK != null ? transaccionesDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionesDet)) {
            return false;
        }
        TransaccionesDet other = (TransaccionesDet) object;
        if ((this.transaccionesDetPK == null && other.transaccionesDetPK != null) || (this.transaccionesDetPK != null && !this.transaccionesDetPK.equals(other.transaccionesDetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paronlineapi.entity.TransaccionesDet[ transaccionesDetPK=" + transaccionesDetPK + " ]";
    }
    
}
