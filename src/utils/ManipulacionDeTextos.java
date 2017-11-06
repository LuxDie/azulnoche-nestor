/*
 * Clase creada para todos los metodos necesarios sobre la modificacion de cadenas de texto
 */
package utils;

/**
 *
 * @author Catardi Nicolas
 */
public class ManipulacionDeTextos {
    
       
    public static String formatearTexto(String cadena, int cantCharts){
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
