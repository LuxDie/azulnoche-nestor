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
public class TextoMensaje implements java.io.Serializable{
    private Integer idTextoMensaje;
    private Integer idMensaje;
    private Integer idIdioma;
    private String textoMensaje;//lengh 1024 privosioriamente

    public TextoMensaje() {
    }

    public Integer getIdTextoMensaje() {
        return idTextoMensaje;
    }

    public void setIdTextoMensaje(Integer idTextoMensaje) {
        this.idTextoMensaje = idTextoMensaje;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idioma) {
        this.idIdioma = idioma;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }
    
    

    
    
        
    
}
