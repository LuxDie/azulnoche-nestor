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
public class Tarea implements java.io.Serializable {
    private Integer idTarea;
    private Integer idMensaje;
    private Integer numeroTarea;
    private String detalleTarea;//lengh 125

    public Tarea() {
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getNumeroTarea() {
        return numeroTarea;
    }

    public void setNumeroTarea(Integer numeroTarea) {
        this.numeroTarea = numeroTarea;
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }
    
    
    
}
