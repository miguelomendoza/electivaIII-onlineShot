/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mmendoza
 */
@Embeddable
public class TransaccionesDetPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_transaccion")
    private int idTransaccion;
    @Basic(optional = false)
    @Column(name = "item")
    private int item;

    public TransaccionesDetPK() {
    }

    public TransaccionesDetPK(int idTransaccion, int item) {
        this.idTransaccion = idTransaccion;
        this.item = item;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTransaccion;
        hash += (int) item;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionesDetPK)) {
            return false;
        }
        TransaccionesDetPK other = (TransaccionesDetPK) object;
        if (this.idTransaccion != other.idTransaccion) {
            return false;
        }
        if (this.item != other.item) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paronlineapi.entity.TransaccionesDetPK[ idTransaccion=" + idTransaccion + ", item=" + item + " ]";
    }
    
}
