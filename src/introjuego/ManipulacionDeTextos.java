/*
 * Clase creada para todos los metodos necesarios sobre la modificacion de cadenas de texto
 */
package introjuego;

/**
 *
 * @author Catardi Nicolas
 */
public class ManipulacionDeTextos {
    
    public ManipulacionDeTextos(){
        
        
    }
    
    /**
     * Toma un texto para darle el salto de linea segun el largo que se requiera
     * 
     * @param cadena String // Cadena de caracteres para cortar linea de texto
     * @param cantCharts int // pasar la cantidad de caracteres por linea
     * @return String Devuelve a cadena ingresada con los saltos de linea
     */
    public String formatearTexto(String cadena, int cantCharts){
        String cadenaModif="";
        
        if(cadena.length()>cantCharts){
            
            for(int i=0;i<cadena.length();i+=cantCharts){
                
                if(cadena.length()>i+cantCharts){
                    
                    cadenaModif += cadena.substring(i, i+cantCharts)+"\n";
                    
                }else{
                    cadenaModif += cadena.substring(i, Math.abs(cadena.length()));
                }
            }
            
        }
        return cadenaModif;
    }
}
