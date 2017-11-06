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
public class TextoComando implements java.io.Serializable{
    private Integer idTextoComando;
    private Integer idComando;
    private Integer idIdioma;
    private String textoComando;//lengh 64

    public TextoComando() {
    }

    public Integer getIdTextoComando() {
        return idTextoComando;
    }

    public void setIdTextoComando(Integer idTextoComando) {
        this.idTextoComando = idTextoComando;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    
    public Integer getIdComando() {
        return idComando;
    }

    public void setIdComando(Integer idComando) {
        this.idComando = idComando;
    }
    

    public String getTextoComando() {
        return textoComando;
    }

    public void setTextoComando(String textoComando) {
        this.textoComando = textoComando;
    }

    
    
    
        
    
}
