/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.model;

/**
 *
 * @author mmendoza
 */
public class MessageErrors {
    
    private Integer codigoRetorno = null;
    private String mensaje = null;
    
    public MessageErrors codigoRetorno(Integer codigoRetorno){
        this.codigoRetorno = codigoRetorno;
        return this;
    }
    
    public MessageErrors(Integer codigoRetorno, String mensaje){
        this.codigoRetorno = codigoRetorno;
        this.mensaje = mensaje;
    }
    
    public MessageErrors mensaje(String mensaje){
        this.mensaje = mensaje;
        return this;
    }

    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("MessageErrors{");
        string.append("\ncodigoRetorno= ");
        string.append(codigoRetorno).append(",");
        string.append("\nmensaje= ");
        string.append(mensaje).append("\n}");
        return string.toString();
    }
}
