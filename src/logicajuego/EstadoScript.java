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
public class EstadoScript {
    private Boolean scriptValido;
    private String detalle;
    private String lineaInvalida;

    public EstadoScript() {
    }

    public Boolean getScriptValido() {
        return scriptValido;
    }

    public void setScriptValido(Boolean scriptValido) {
        this.scriptValido = scriptValido;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getLineaInvalida() {
        return lineaInvalida;
    }

    public void setLineaInvalida(String lineaInvalida) {
        this.lineaInvalida = lineaInvalida;
    }
    
    
}
