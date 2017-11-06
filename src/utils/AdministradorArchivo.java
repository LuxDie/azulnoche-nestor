/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author fabian
 */
public class AdministradorArchivo {
    public static ArrayList<String> leerlineas (String archivo) {
        BufferedReader br = null;
        String line = "";
        
        
        ArrayList<String> lineas = new ArrayList<String>();
        
        try {

            br = new BufferedReader(new FileReader(archivo));
            while ((line = br.readLine()) != null) {

                lineas.add(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lineas;
    }
    
    public static Boolean archivoExiste(String archivo) {
        File f = new File(archivo);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        } else {
            return false;
        }
    }
}
