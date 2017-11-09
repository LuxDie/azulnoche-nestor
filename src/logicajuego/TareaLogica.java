/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicajuego;

/**
 *
 * @author fabian
 */
public class TareaLogica implements java.io.Serializable{
    private Integer idTarea;
    private Boolean tareaValida;
    
    private Integer numeroTarea;
    private String detalleTarea;

    public TareaLogica() {
    }

    
    
    public TareaLogica(Integer numeroTarea, String detalleTarea) {
        this.numeroTarea = numeroTarea;
        this.detalleTarea = detalleTarea;
    }

    public Boolean getTareaValida() {
        return tareaValida;
    }

    public Integer getNumeroTarea() {
        return numeroTarea;
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }

    public void setTareaValida(Boolean tareaValida) {
        this.tareaValida = tareaValida;
    }

    public void setNumeroTarea(Integer numeroTarea) {
        this.numeroTarea = numeroTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }
    
    
    
    
}
