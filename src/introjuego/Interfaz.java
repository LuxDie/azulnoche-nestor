/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introjuego;

import dato.ComandoDato;
import dato.HistoriaDato;
import dato.MensajeDato;
import dato.TareaDato;
import dato.TextoComandoDato;
import dato.TextoMensajeDato;
import entidad.Comando;
import entidad.Historia;
import entidad.Mensaje;
import entidad.Tarea;
import entidad.TextoComando;
import entidad.TextoMensaje;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logicajuego.EstadoScript;
import logicajuego.HistoriaLogica;
import logicajuego.MensajeLogica;
import logicajuego.ObjetoX;
import logicajuego.TareaLogica;
//import narracion.Historia;
//import narracion.Mensaje;
//import narracion.Tarea;
import utils.ManipulacionDeTextos;
import org.xml.sax.Attributes;

/**
 *
 * @author Nacho
 */
public class Interfaz extends javax.swing.JFrame {

    private final static String KEY_CANDADO = "9531";
    private boolean isCandadoAbierto = false;
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        jLabel2.requestFocus();
        setSize(815, 600);
        setLocationRelativeTo(null);
        jTextArea1.setOpaque(false);
        jTextArea1.setEditable(false);
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        spn_pass1.setText("");
        spn_pass2.setText("");
        spn_pass3.setText("");
        spn_pass4.setText("");
        textoAcciones.setVisible(true);
        
        ///
        
        ///
        historia = new HistoriaLogica();
        //--------------------------------
        //se carga la historia desde la clase HistoriaLogica
        historia.cargarHistoriaPorDefecto();
        
        //Si ya se grabo la historia por defecto en la base de dato...
        //cargarHistoriaPorDefectoDesdeBaseDato();
        
        //si ya se qiere cargar la historia por defecto desde el archivo de script
        //cargarHistoriaPorDefectoDesdeScript();
        
        //---------------------------------
        numerosEncontrados = 0;//0
        indicarIngresoNumeros = false;
        candadoValor1 = candadoValor2 = candadoValor3 = candadoValor4 = false;
        mostrarProximoMensaje();
        
        ///////////////////////
        //////////////////////
        /////////////////////
        /*
        Mensaje mensaje = historia.getMensaje();
        String textoMensaje = mensaje.getMensaje();



        if (mensaje.getMostrarMensaje()) {
            jTextArea1.append(textoMensaje + "\n");//SE DEBE DEFINIR SI ES NECESARIO VOLVER A ACTIVAR  
        }
        */
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        spn_pass1 = new javax.swing.JTextField();
        spn_pass2 = new javax.swing.JTextField();
        spn_pass3 = new javax.swing.JTextField();
        spn_pass4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_candado = new javax.swing.JLabel();
        textoAcciones = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTextField1.setText("Escriba aquí");
        jTextField1.setFocusable(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(50, 480, 220, 30);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 60, 330, 310);

        jButton1.setText("Enviar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(280, 480, 80, 30);

        spn_pass1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        spn_pass1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spn_pass1.setText("num1");
        spn_pass1.setFocusable(false);
        getContentPane().add(spn_pass1);
        spn_pass1.setBounds(460, 310, 50, 50);

        spn_pass2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        spn_pass2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spn_pass2.setText("num2");
        spn_pass2.setFocusable(false);
        getContentPane().add(spn_pass2);
        spn_pass2.setBounds(520, 310, 50, 50);

        spn_pass3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        spn_pass3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spn_pass3.setText("num3");
        spn_pass3.setFocusable(false);
        getContentPane().add(spn_pass3);
        spn_pass3.setBounds(580, 310, 50, 50);

        spn_pass4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        spn_pass4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spn_pass4.setText("num4");
        spn_pass4.setFocusable(false);
        getContentPane().add(spn_pass4);
        spn_pass4.setBounds(640, 310, 50, 50);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/correccion2.png"))); // NOI18N
        jLabel8.setText("Inventario");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 430, 170, 140);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/correccion2.png"))); // NOI18N
        jLabel7.setText("Inventario");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(200, 470, 180, 60);

        lbl_candado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/candadoCerradoPNG.png"))); // NOI18N
        getContentPane().add(lbl_candado);
        lbl_candado.setBounds(420, 10, 370, 490);

        textoAcciones.setBackground(new java.awt.Color(0, 0, 0));
        textoAcciones.setText("texto de acciones");
        getContentPane().add(textoAcciones);
        textoAcciones.setBounds(40, 390, 310, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/resize.jpg"))); // NOI18N
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel2KeyPressed(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 840, 564);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pedirProximoComando();
        //insertarHistoria();//ok
        //insertarMensaje();//ok
        //cargarHistoriasTodas();//ok
        //cargarMensajesDeIdHistoriaIdIdioma();//ok
        //insertarTextoMensaje();// ok
        //cargarTextoMensajePorIdMensajeIdIdioma();
        //cargarComandosDeIdMensaje();//ok
        //insertarTextoComando();
        //cargarTextoComadoPorIdComandoIdIdioma();
        //insertarTarea();
        //cargarTareaPorIdMensaje();
        //insertarHIstoriaCompletaEnBaseDato();
        //cargarHistoriaPorDefectoDesdeScript();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            pedirProximoComando();
        }
    }//GEN-LAST:event_jLabel2KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            pedirProximoComando();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
                
            }
        });
    }

    private HistoriaLogica historia;
    private MensajeLogica ultimoMensajeMostrado;
    private String codigoMensajeAnterior="";
    
    private Boolean candadoValor1, candadoValor2, candadoValor3, candadoValor4;
    private Integer numerosEncontrados;
    private Boolean indicarIngresoNumeros;
    
    private void mostrarProximoMensaje() {
        MensajeLogica mensajeLogica = historia.getMensaje();
        if (mensajeLogica != null) {
            ArrayList<ObjetoX> listaObjetoX = mensajeLogica.getListaObjetoX();
            if (listaObjetoX.size() > 0) {
                procesarObjetoX(mensajeLogica, listaObjetoX);
            } else {
                mostrarProximoMensaje2(mensajeLogica);
            }
        }
        
        /*
        ultimoMensajeMostrado = historia.getMensaje();
        if (ultimoMensajeMostrado != null) {
            procesarObjetoX();

            if (ultimoMensajeMostrado.getMostrarMensaje() && !(ultimoMensajeMostrado.getCodigoMensaje().equals(codigoMensajeAnterior))) {
                jTextArea1.append(ultimoMensajeMostrado.getMensaje() + "\n\n");//SE DEBE DEFINIR SI ES NECESARIO VOLVER A ACTIVAR  
                codigoMensajeAnterior=ultimoMensajeMostrado.getCodigoMensaje();
            }

            if (!ultimoMensajeMostrado.getProcesarRespuesta()) {
                textoAcciones.setText("Presione ENTER para continuar.");
            }
        }
        */
        
    }
    
    private void mostrarProximoMensaje2(MensajeLogica mensajeLogica) {
        ultimoMensajeMostrado = mensajeLogica;
        if (ultimoMensajeMostrado.getMostrarMensaje() && !(ultimoMensajeMostrado.getCodigoMensaje().equals(codigoMensajeAnterior))) {
                jTextArea1.append(ultimoMensajeMostrado.getMensaje() + "\n\n");//SE DEBE DEFINIR SI ES NECESARIO VOLVER A ACTIVAR  
                codigoMensajeAnterior=ultimoMensajeMostrado.getCodigoMensaje();
            }

            if (!ultimoMensajeMostrado.getProcesarRespuesta()) {
                if (!indicarIngresoNumeros) {
                    textoAcciones.setText("Presione ENTER para continuar.");
                    jTextField1.setEnabled(false);
                    jTextField1.setFocusable(false);
                    jLabel2.requestFocus();
                } else {
                    //indicarIngresoNumeros = false;
                    textoAcciones.setText("Ingrese combinacion en el candado");
                }
                
            }
    }
    
    
    private void cambiarEstadoBosque() {
        /*
        estado2_bosque
        this.narracion.agregarComandoAMensaje("casa34","iralbosuqe", "IR AL BOSQUE", "bosque");
        this.narracion.agregarComandoAMensaje("men7", "iralbosuqe", "IR AL BOSQUE", "bosque");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","iralbosuqe", "IR AL BOSQUE", "bosque");
        */
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa34", "iralbosuqe", "estado2_bosque");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("men7", "iralbosuqe", "estado2_bosque");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_callePrincipal", "iralbosuqe", "estado2_bosque");
    }
    
    private void cambiarEstadoBar() {
        /*
        estado2_men1
        this.narracion.agregarComandoAMensaje("casa34","ingresaralbar", "INGRESAR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("bosqueConDatosCandadoInCompleto","irBar", "IR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresaralbar", "INGRESAR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("estado2_bosqueConDatosCandadoInCompleto","irBar", "IR AL BAR", "men1");
        */
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa34", "ingresaralbar", "estado2_men1");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("bosqueConDatosCandadoInCompleto", "irBar", "estado2_men1");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_callePrincipal", "ingresaralbar", "estado2_men1");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_bosqueConDatosCandadoInCompleto", "irBar", "estado2_men1");
        
    }
    
    private void cambiarEstadoCallePrincipal() {
        /*
        this.narracion.agregarComandoAMensaje("bosqueConDatosCandadoInCompleto","ircalleprincipal", "IR A CALLE PRINCIPAL", "callePrincipal");
        this.narracion.agregarComandoAMensaje("estado2_bosqueConDatosCandadoInCompleto","ircalleprincipal", "IR A CALLE PRINCIPAL", "callePrincipal");
        this.narracion.agregarComandoAMensaje("recuerdo6", "irCallePrincipal", "IR A CALLE PRINCIPAL","callePrincipal");
        
        this.narracion.agregarComandoAMensaje("casa1h", "irCallePrincipal", "IR A CALLE PRINCIPAL","callePrincipal");
        this.narracion.agregarComandoAMensaje("men7","irCallePrincipal", "IR CALLE PRINCIPAL", "callePrincipal");
        
        this.narracion.agregarMensaje("estado2_men1", "Ingresas al bar, miras un poco y sales rapidamente, tu instinto te dice que no encontraras nuevas pistas", "callePrincipal");
        this.narracion.agregarMensaje("estado2_casa1a", "Entras a la casa revisas en algunos lugares que no revisaste antes, como no encuentras nada vuelves a salir", "callePrincipal");
        this.narracion.agregarMensaje("estado2_casa2", "Entras a la casa revisas en algunos lugares que no revisaste antes, como no encuentras nada vuelves a salir", "callePrincipal");
        this.narracion.agregarMensaje("estado2_casa3", "Entras a la casa revisas en algunos lugares que no revisaste antes, como no encuentras nada vuelves a salir", "callePrincipal");
        this.narracion.agregarMensaje("estado2_bosque", "Caminas por el bosque y te diriges rapidamente a la cabana, necesitas abrir ese candado", "callePrincipal");
        */
        
        historia.setProximoMensajeDeComandoDeMensajeEnLista("bosqueConDatosCandadoInCompleto", "ircalleprincipal", "estado2_callePrincipal");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_bosqueConDatosCandadoInCompleto", "ircalleprincipal", "estado2_callePrincipal");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("recuerdo6", "irCallePrincipal", "estado2_callePrincipal");
        
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa1h", "irCallePrincipal", "estado2_callePrincipal");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("men7", "irCallePrincipal", "estado2_callePrincipal");
        
        historia.setProximoMensajeDeMensajeEnLista("estado2_men1", "estado2_callePrincipal");
        historia.setProximoMensajeDeMensajeEnLista("estado2_casa1a", "estado2_callePrincipal");
        historia.setProximoMensajeDeMensajeEnLista("estado2_casa2", "estado2_callePrincipal");
        historia.setProximoMensajeDeMensajeEnLista("estado2_casa3", "estado2_callePrincipal");
        historia.setProximoMensajeDeMensajeEnLista("estado2_bosque", "estado2_callePrincipal");
        
    }
    
    private void cambiarEstadoCasa1() {
        /*
        estado2_casa1a
        
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa1", "INGRESAR CASA 1", "casa1a");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa1", "INGRESAR CASA 1", "casa1a");
        this.narracion.agregarComandoAMensaje("recuerdo6", "seguirAdelante", "CAMINAR ADELANTE","casa1a");
        */
        historia.setProximoMensajeDeComandoDeMensajeEnLista("callePrincipal5", "ingresarcasa1", "estado2_casa1a");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_callePrincipal", "ingresarcasa1", "estado2_casa1a");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("recuerdo6", "seguirAdelante", "estado2_casa1a");
    }
    
    private void cambiarEstadoCasa3() {
        /*
        estado2_casa3
        this.narracion.agregarComandoAMensaje("men7","ingresarcasa3", "INGRESAR CASA 3", "casa3");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa3", "INGRESAR CASA 3", "casa3");
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa3", "INGRESAR CASA 3", "casa3");
        */
        //historia.setProximoMensajeDeComandoDeMensajeEnLista("men7", "ingresarcasa3", "estado2_casa3");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_callePrincipal", "ingresarcasa3", "estado2_casa3");
        
        historia.setProximoMensajeDeComandoDeMensajeEnLista("callePrincipal5", "ingresarcasa3", "estado2_casa3");
    }
    
    private void cambiarEstadoCasa2() {
        /*
        estado2_casa2
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa2", "INGRESAR CASA 2", "casa2");
        this.narracion.agregarComandoAMensaje("casa34","ingresarcasa2", "INGRESAR CASA 2", "casa2");
        this.narracion.agregarComandoAMensaje("casa1h", "seguirAdelante", "CAMINAR ADELANTE","casa2");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa2", "INGRESAR CASA 2", "casa2");
        
        this.narracion.agregarComandoAMensaje("casa1h", "seguirAdelante", "CAMINAR ADELANTE","casa2");
        */
        historia.setProximoMensajeDeComandoDeMensajeEnLista("callePrincipal5", "ingresarcasa2", "estado2_casa2");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa34", "ingresarcasa2", "estado2_casa2");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa1h", "seguirAdelante", "estado2_casa2");
        historia.setProximoMensajeDeComandoDeMensajeEnLista("estado2_callePrincipal", "ingresarcasa2", "estado2_casa2");
        
        historia.setProximoMensajeDeComandoDeMensajeEnLista("casa1h", "seguirAdelante", "estado2_casa2");
        
    }
    
    private void procesarObjetoX(MensajeLogica mensajeLogica, ArrayList<ObjetoX> listaObjetoX) {
        //ArrayList<ObjetoX> listaObjetoX = ultimoMensajeMostrado.getListaObjetoX();
        for (ObjetoX objetoX : listaObjetoX) {
            String codigoObjetoX = objetoX.getCodigoObjetoX();
            switch (codigoObjetoX) {
                case "candadoValor1":
                    if (!candadoValor1) {
                        candadoValor1 = true;
                        numerosEncontrados = numerosEncontrados + 1;
                    }
                    cambiarEstadoBar();
                    mostrarProximoMensaje2(mensajeLogica);
                    spn_pass1.setText(Integer.toString(objetoX.getValorEntero()));
                    break;
                case "candadoValor2":
                    if (!candadoValor2) {
                        candadoValor2 = true;
                        numerosEncontrados = numerosEncontrados + 1;
                    }
                    cambiarEstadoCallePrincipal();
                    mostrarProximoMensaje2(mensajeLogica);
                    spn_pass2.setText(Integer.toString(objetoX.getValorEntero()));
                    break;
                case "candadoValor3":
                    if (!candadoValor3) {
                        candadoValor3 = true;
                        numerosEncontrados = numerosEncontrados + 1;
                    }
                    cambiarEstadoCasa1();
                    mostrarProximoMensaje2(mensajeLogica);
                    spn_pass3.setText(Integer.toString(objetoX.getValorEntero()));
                    break;    
                case "candadoValor4":
                    if (!candadoValor4) {
                        candadoValor4 = true;
                        numerosEncontrados = numerosEncontrados + 1;
                    }
                    cambiarEstadoCasa3();
                    mostrarProximoMensaje2(mensajeLogica);
                    spn_pass4.setText(Integer.toString(objetoX.getValorEntero()));
                    break;
                case "casa2":
                    
                    cambiarEstadoCasa2();
                    mostrarProximoMensaje2(mensajeLogica);
                    //spn_pass4.setText(Integer.toString(objetoX.getValorEntero()));
                    break;
                case "bosqueConDatosCandadoCompleto":
                    //indicarIngresoNumeros = true;
                    mostrarProximoMensaje2(mensajeLogica);
                    break;
                case "cabana":
                    intentarAbrirCandado();
                    indicarIngresoNumeros = false;
                    mostrarProximoMensaje2(mensajeLogica);
                    break;
                case "finhistoria":
                    mostrarProximoMensaje2(mensajeLogica);
                    finhistoria();
                    break;
                case "controlCandado":
                    historia.setProximoMensajeDeNarracion("bosquecontrolCandado");
                    if (numerosEncontrados == 4) {
                        historia.setProximoMensajeDeMensajeEnLista("bosquecontrolCandado", "bosqueConDatosCandadoCompleto");
                        //intentarAbrirCandado(); // Se llama a este método de forma provisoria para poder abrir el candado y continuar con la historia.
                    } else {
                        historia.setProximoMensajeDeMensajeEnLista("bosquecontrolCandado", "bosqueConDatosCandadoInCompleto");
                    }
                    
                    cambiarEstadoBosque();
                    mostrarProximoMensaje();
                    break; 
                case "estado2_controlCandado":
                    historia.setProximoMensajeDeNarracion("estado2_bosquecontrolCandado");
                    if (numerosEncontrados == 4) {
                        historia.setProximoMensajeDeMensajeEnLista("estado2_bosquecontrolCandado", "estado2_bosqueConDatosCandadoCompleto");
                        
                    } else {
                        historia.setProximoMensajeDeMensajeEnLista("estado2_bosquecontrolCandado", "estado2_bosqueConDatosCandadoInCompleto");
                    }
                    
                    
                    mostrarProximoMensaje();
                    break;
            }
        }
    }
    
    private void entrarACabana() {
        historia.setProximoMensajeDeNarracion("cabana");
        mostrarProximoMensaje();
    }
    
    private void procesarComando(String textoComando) {
        if (textoComando.equals("tarea pendiente")) {
            TareaLogica tareaLogica = historia.obtenerPrimerTareaPendiente();
            if (tareaLogica.getTareaValida()) {
                //System.out.println("TAREA PENDIENTE");
                jTextArea1.append("TAREA PENDIENTE" + "\n");
                
                jTextArea1.append(tareaLogica.getDetalleTarea() + "\n");
            } else {
                jTextArea1.append("No hay tarea pendiente" + "\n");
            }
        } else if (textoComando.equals("ayuda")) {
            textoAcciones.setText(comandosAyuda());
        } else {
            if (ultimoMensajeMostrado.getProcesarRespuesta()) {
                if (historia.procesarTextoComando(textoComando)) {
                    //System.out.println("comando no valido...");
                    //break;
                    mostrarProximoMensaje();
                    jTextField1.setText("");
                } else {
                    textoAcciones.setText("Comando no válido");
                    textoAcciones.setVisible(true);
                }
            }
        }
    }
    
    private String comandosAyuda() {
        String texto = "<html>Los comandos disponibles son: [";
        for (int i = 0;i<ultimoMensajeMostrado.getComandos().size();i++) {
            for (int j=0;j<ultimoMensajeMostrado.getComandos().get(i).getListaComandoTexto().size();j++) {
                texto+=ultimoMensajeMostrado.getComandos().get(i).getListaComandoTexto().get(j).toUpperCase();
            }
            if (i+1<ultimoMensajeMostrado.getComandos().size()) {
                texto+="], [";
            }
        }
        texto+="].<br>Ingrese el comando correspondiente, y presione ENTER para continuar.</html>";
        
        return texto;
    }
    
    private void insertarHistoria() {
        HistoriaDato historiaDato = new HistoriaDato();
        Historia historia = new Historia();
        historia.setTitulo("historia 1");
        historia.setIdIdioma(1);
        historia.setDescripcion("descripcion 1");
        historia.setCodigoMensajeInicio("mensaje1");
        historiaDato.insertarHistoria(historia);
    }
    
    private void cargarHistoriasTodas() {
        HistoriaDato historiaDato = new HistoriaDato();
        ArrayList<Historia> listaHistorias = historiaDato.obtenerListHistoriasTodo();
        Historia historia = listaHistorias.get(1);
        System.out.println(historia.getDescripcion());
    }
    
    private void insertarMensaje() {
        MensajeDato mensajeDato = new MensajeDato();
        Mensaje mensaje = new Mensaje();
        mensaje.setIdHistoria(1);
        mensaje.setCodigoMensaje("mensaje1");
        //mensaje.setCodigoMensajeSiguiente("null");
        mensaje.setMensajePopUp(false);
        mensaje.setMostrarMensaje(true);
        mensaje.setObligatorio(false);
        mensajeDato.insertarMensaje(mensaje);
    }
    
    private void cargarMensajesDeIdHistoriaIdIdioma() {
        MensajeDato mensajeDato = new MensajeDato();
        ArrayList<Mensaje> listaMensajes = mensajeDato.obtenerListaMensajesPorIdHistoria(1);
        Mensaje mensaje = listaMensajes.get(0);
        System.out.println(mensaje.getCodigoMensaje());
    }
    
    private void insertarTextoMensaje() {
        TextoMensajeDato textoMensajeDato = new TextoMensajeDato();
        TextoMensaje textoMensaje = new TextoMensaje();
        textoMensaje.setIdMensaje(1);
        textoMensaje.setIdIdioma(1);
        textoMensaje.setTextoMensaje("mensaje 1...");
        textoMensajeDato.insertarTextoMensaje(textoMensaje);
    }
    
    private void cargarTextoMensajePorIdMensajeIdIdioma() {
        TextoMensajeDato textoMensajeDato = new TextoMensajeDato();
        ArrayList<TextoMensaje> listaTextoMensaje = textoMensajeDato.obtenerListaTextoMensajePorIdMensajeIdIdioma(1, 1);
        TextoMensaje textoMensaje = listaTextoMensaje.get(0);
        System.out.println(textoMensaje.getTextoMensaje());
    }
    
    private void insertarComando() {
        ComandoDato comandoDato = new ComandoDato();
        Comando comando = new Comando();
        comando.setIdMensaje(1);
        comando.setCodigoComando("comando1");
        comando.setCodigoMensajeSiguiente("mensaje1");
        comandoDato.insertarComando(comando);
    }
    
    private void cargarComandosDeIdMensaje() {
        ComandoDato comandoDato = new ComandoDato();
        ArrayList<Comando> listaComandos = comandoDato.obtenerListaComandosDeIdMensaje(1);
        Comando comando = listaComandos.get(0);
        System.out.println(comando.getCodigoComando());
    }
    
    private void insertarTextoComando() {
        TextoComandoDato textoComandoDato = new TextoComandoDato();
        TextoComando textoComando = new TextoComando();
        textoComando.setIdComando(1);
        textoComando.setIdIdioma(1);
        textoComando.setTextoComando("DOBLAR IZQUIERDA");
        textoComandoDato.insertarTextoComando(textoComando);
    }
    
    private void cargarTextoComadoPorIdComandoIdIdioma() {
        TextoComandoDato textoComandoDato = new TextoComandoDato();
        ArrayList<TextoComando> listaTextoComando = textoComandoDato.obtenerListaTextoComando(1, 1);
        TextoComando textoComando = listaTextoComando.get(0);
        System.out.println(textoComando.getTextoComando());
    }
    
    private void insertarTarea() {
        TareaDato tareaDato = new TareaDato();
        Tarea tarea = new Tarea();
        tarea.setIdMensaje(1);
        tarea.setNumeroTarea(1);
        tarea.setDetalleTarea("primer tarea");
        tareaDato.insertarTarea(tarea);
    }
    
    private void cargarTareaPorIdMensaje() {
        TareaDato tareaDato = new TareaDato();
        ArrayList<Tarea> listaTarea = tareaDato.obtenerListaTareaPorIdMensaje(1);
        Tarea tarea = listaTarea.get(0);
        System.out.println(tarea.getDetalleTarea());
    }
    
    private void insertarHIstoriaCompletaEnBaseDato() {
        historia.insertarHistoriaCompleta();
    }
    
    private void cargarHistoriaPorDefectoDesdeBaseDato() {
        historia.obtenerHistoriaCompletaPorId(1, 1, "intro_manejasAuto");
    }
    
    private void cargarHistoriaPorDefectoDesdeScript() {
        EstadoScript estadoScript = historia.cargarHistoriaDesceScript("historia2.txt", 1, "primer titulo", "descripcion 1");
        if (!estadoScript.getScriptValido()) {
            jTextArea1.append(estadoScript.getDetalle() + "\n");
            jTextArea1.append(estadoScript.getLineaInvalida());
        }
    }

    private boolean isKeyCandado(){
        String auxiliar="";
        auxiliar += String.valueOf(spn_pass1.getText());
        auxiliar += String.valueOf(spn_pass2.getText());
        auxiliar += String.valueOf(spn_pass3.getText());
        auxiliar += String.valueOf(spn_pass4.getText());
        if(KEY_CANDADO.equalsIgnoreCase(auxiliar))System.out.println("Contraseña Correcta");
        return KEY_CANDADO.equalsIgnoreCase(auxiliar);
    }
    
    private void intentarAbrirCandado(){
        if(isKeyCandado()){
            lbl_candado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/candadoAbiertoPNG.png")));
            isCandadoAbierto = true;
            spn_pass1.removeAll();
            spn_pass2.removeAll();
            spn_pass3.removeAll();
            spn_pass4.removeAll();
            
            //entrarACabana();
        }
    }
    
    private void pedirProximoComando () {
        if (!indicarIngresoNumeros) {
            textoAcciones.setText("<html>Ingrese el comando correspondiente, y presione ENTER para continuar.</html>");
            jTextField1.setEnabled(true);
            jTextField1.setFocusable(true);
            jTextField1.requestFocus();
            String texto = jTextField1.getText();
            if (texto.equals("")) {
                mostrarProximoMensaje();
            } else {
                procesarComando(jTextField1.getText());
                jTextField1.selectAll();
            }

            jTextArea1.setCaretPosition(jTextArea1.getText().length());
        }
        
    }
    
    private void finhistoria() {
        textoAcciones.setText("");
        indicarIngresoNumeros = true;//para no usar otra bandera
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_candado;
    private javax.swing.JTextField spn_pass1;
    private javax.swing.JTextField spn_pass2;
    private javax.swing.JTextField spn_pass3;
    private javax.swing.JTextField spn_pass4;
    private javax.swing.JLabel textoAcciones;
    // End of variables declaration//GEN-END:variables
}

