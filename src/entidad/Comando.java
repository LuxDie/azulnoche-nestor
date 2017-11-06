/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author fabian
 */
public class Comando implements java.io.Serializable {
    private Integer idComando;
    private Integer idMensaje;
    private String codigoComando;//lengh 32
    //private Integer idMensajeSiguiente;
    private String codigoMensajeSiguiente;//lengh 32

    public Comando() {
    }

    public Integer getIdComando() {
        return idComando;
    }

    public void setIdComando(Integer idComando) {
        this.idComando = idComando;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getCodigoComando() {
        return codigoComando;
    }

    public void setCodigoComando(String codigoComando) {
        this.codigoComando = codigoComando;
    }

    /*
    public Integer getIdMensajeSiguiente() {
        return idMensajeSiguiente;
    }

    public void setIdMensajeSiguiente(Integer idMensajeSiguiente) {
        this.idMensajeSiguiente = idMensajeSiguiente;
    }
    */

    public String getCodigoMensajeSiguiente() {
        return codigoMensajeSiguiente;
    }

    public void setCodigoMensajeSiguiente(String codigoMensajeSiguiente) {
        this.codigoMensajeSiguiente = codigoMensajeSiguiente;
    }
    
    

    
    
    
        
    
}
