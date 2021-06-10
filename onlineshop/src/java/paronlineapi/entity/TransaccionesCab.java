/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmendoza
 */
@Entity
@Table(name = "transacciones_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransaccionesCab.findAll", query = "SELECT t FROM TransaccionesCab t")
    , @NamedQuery(name = "TransaccionesCab.findByIdTransaccion", query = "SELECT t FROM TransaccionesCab t WHERE t.idTransaccion = :idTransaccion")
    , @NamedQuery(name = "TransaccionesCab.findByFecha", query = "SELECT t FROM TransaccionesCab t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TransaccionesCab.findByTotal", query = "SELECT t FROM TransaccionesCab t WHERE t.total = :total")
    , @NamedQuery(name = "TransaccionesCab.findByDireccionEnvio", query = "SELECT t FROM TransaccionesCab t WHERE t.direccionEnvio = :direccionEnvio")
    , @NamedQuery(name = "TransaccionesCab.findByIdMedioPago", query = "SELECT t FROM TransaccionesCab t WHERE t.idMedioPago = :idMedioPago")
    , @NamedQuery(name = "TransaccionesCab.findByNroTarjeta", query = "SELECT t FROM TransaccionesCab t WHERE t.nroTarjeta = :nroTarjeta")
    , @NamedQuery(name = "TransaccionesCab.findByEstado", query = "SELECT t FROM TransaccionesCab t WHERE t.estado = :estado")})
public class TransaccionesCab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "direccion_envio")
    private String direccionEnvio;
    @Column(name = "id_medio_pago")
    private Integer idMedioPago;
    @Column(name = "nro_tarjeta")
    private BigInteger nroTarjeta;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccionesCab")
    private List<TransaccionesDet> transaccionesDetCollection;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public TransaccionesCab() {
    }

    public TransaccionesCab(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public TransaccionesCab(Integer idTransaccion, Date fecha, BigDecimal total) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Integer getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(Integer idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public BigInteger getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(BigInteger nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<TransaccionesDet> getTransaccionesDetCollection() {
        return transaccionesDetCollection;
    }

    public void setTransaccionesDetCollection(List<TransaccionesDet> transaccionesDetCollection) {
        this.transaccionesDetCollection = transaccionesDetCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionesCab)) {
            return false;
        }
        TransaccionesCab other = (TransaccionesCab) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paronlineapi.entity.TransaccionesCab[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
