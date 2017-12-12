/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicajuego;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alumno
 */
public class ComandoLogicaTest {
    
    public ComandoLogicaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getListaComandoTexto method, of class ComandoLogica.
     */
    @Test
    public void testGetListaComandoTexto() {
        System.out.println("getListaComandoTexto");
        ComandoLogica instance = new ComandoLogica();
        ArrayList<String> expResult = new ArrayList<>();
        ArrayList<String> result = instance.getListaComandoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getCodigoMensaje method, of class ComandoLogica.
     */
    @Test
    public void testGetCodigoMensaje() {
        System.out.println("getCodigoMensaje");
        ComandoLogica instance = new ComandoLogica();
        String expResult = null;
        String result = instance.getCodigoMensaje();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCodigoComando method, of class ComandoLogica.
     */
    @Test
    public void testSetCodigoComando() {
        System.out.println("setCodigoComando");
        String codigoComando = "";
        ComandoLogica instance = new ComandoLogica();
        instance.setCodigoComando(codigoComando);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCodigoComando method, of class ComandoLogica.
     */
    @Test
    public void testGetCodigoComando() {
        System.out.println("getCodigoComando");
        ComandoLogica instance = new ComandoLogica();
        String expResult = null;
        String result = instance.getCodigoComando();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCodigoMensaje method, of class ComandoLogica.
     */
    @Test
    public void testSetCodigoMensaje() {
        System.out.println("setCodigoMensaje");
        String codigoMensaje = "";
        ComandoLogica instance = new ComandoLogica();
        instance.setCodigoMensaje(codigoMensaje);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of agregarTextoComando method, of class ComandoLogica.
     */
    @Test
    public void testAgregarTextoComando() {
        System.out.println("agregarTextoComando");
        String textoComando = "";
        ComandoLogica instance = new ComandoLogica();
        instance.agregarTextoComando(textoComando);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of comandoValido method, of class ComandoLogica.
     */
    @Test
    public void testComandoValido() {
        System.out.println("comandoValido");
        String textoComando = "";
        ComandoLogica instance = new ComandoLogica();
        boolean expResult = false;
        boolean result = instance.comandoValido(textoComando);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
