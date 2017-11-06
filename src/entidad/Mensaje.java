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
public class Mensaje implements java.io.Serializable {
    private Integer idMensaje;
    private Integer idHistoria;
    //private Integer idMensajeSiguiente;
    private String codigoMensaje;//leng 32
    private Boolean obligatorio;
    private Boolean mostrarMensaje;
    private Boolean mensajePopUp;
    private String codigoMensajeSiguiente;//lengh 32

    public Mensaje() {
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }

    public String getCodigoMensajeSiguiente() {
        return codigoMensajeSiguiente;
    }

    public void setCodigoMensajeSiguiente(String codigoMensajeSiguiente) {
        this.codigoMensajeSiguiente = codigoMensajeSiguiente;
    }

    
    
    /*
    public Integer getIdMensajeSiguiente() {
        return idMensajeSiguiente;
    }

    public void setIdMensajeSiguiente(Integer idMensajeSiguiente) {
        this.idMensajeSiguiente = idMensajeSiguiente;
    }

    */

    public String getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public Boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public Boolean getMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(Boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public Boolean getMensajePopUp() {
        return mensajePopUp;
    }

    public void setMensajePopUp(Boolean mensajePopUp) {
        this.mensajePopUp = mensajePopUp;
    }

    
    
    
        
    
}
