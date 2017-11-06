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
public class Historia implements java.io.Serializable {
    private Integer idHistoria;
    private Integer idIdioma;
    private String titulo;//lengh 64
    private String descripcion;// leng256
    private String codigoMensajeInicio;//leng 32
    

    public Historia() {
    }

    public Integer getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoMensajeInicio() {
        return codigoMensajeInicio;
    }

    public void setCodigoMensajeInicio(String codigoMensajeInicio) {
        this.codigoMensajeInicio = codigoMensajeInicio;
    }

    
    
        
    
}
