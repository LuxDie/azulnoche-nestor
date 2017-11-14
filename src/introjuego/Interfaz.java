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
        jTextField1.requestFocus();
        setSize(815, 600);
        setLocationRelativeTo(null);
        jTextArea1.setOpaque(false);
        jTextArea1.setEditable(false);
        jTextField1.setText("");
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
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spn_pass1 = new javax.swing.JSpinner();
        spn_pass2 = new javax.swing.JSpinner();
        spn_pass3 = new javax.swing.JSpinner();
        spn_pass4 = new javax.swing.JSpinner();
        lbl_candado = new javax.swing.JLabel();
        textoAcciones = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTextField1.setText("Escriba aquí");
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
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 60, 330, 310);

        jButton1.setText("Enviar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(280, 480, 80, 30);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/correccion2.png"))); // NOI18N
        jLabel8.setText("Inventario");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 430, 170, 140);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/correccion2.png"))); // NOI18N
        jLabel7.setText("Inventario");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(200, 470, 180, 60);

        spn_pass1.setFont(new java.awt.Font("Year supply of fairy cakes", 1, 24)); // NOI18N
        spn_pass1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9, 1));
        spn_pass1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spn_pass1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_pass1StateChanged(evt);
            }
        });
        getContentPane().add(spn_pass1);
        spn_pass1.setBounds(460, 300, 55, 70);

        spn_pass2.setFont(new java.awt.Font("Year supply of fairy cakes", 1, 24)); // NOI18N
        spn_pass2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9, 1));
        spn_pass2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spn_pass2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_pass2StateChanged(evt);
            }
        });
        getContentPane().add(spn_pass2);
        spn_pass2.setBounds(520, 300, 55, 70);

        spn_pass3.setFont(new java.awt.Font("Year supply of fairy cakes", 1, 24)); // NOI18N
        spn_pass3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9, 1));
        spn_pass3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spn_pass3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_pass3StateChanged(evt);
            }
        });
        getContentPane().add(spn_pass3);
        spn_pass3.setBounds(580, 300, 55, 70);

        spn_pass4.setFont(new java.awt.Font("Year supply of fairy cakes", 1, 24)); // NOI18N
        spn_pass4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9, 1));
        spn_pass4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spn_pass4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_pass4StateChanged(evt);
            }
        });
        getContentPane().add(spn_pass4);
        spn_pass4.setBounds(640, 300, 55, 70);

        lbl_candado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/candadoCerradoPNG.png"))); // NOI18N
        getContentPane().add(lbl_candado);
        lbl_candado.setBounds(420, 10, 370, 490);

        textoAcciones.setBackground(new java.awt.Color(0, 0, 0));
        textoAcciones.setText("texto de acciones");
        getContentPane().add(textoAcciones);
        textoAcciones.setBounds(40, 390, 310, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/introjuego/resize.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 840, 564);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(jTextField1.getText().equals(""))
        {
            mostrarProximoMensaje();
        } else {
            procesarComando(jTextField1.getText());
        }
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
    }//GEN-LAST:event_jButton1MouseClicked

    private void spn_pass1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_pass1StateChanged
        // TODO add your handling code here:
        intentarAbrirCandado();
    }//GEN-LAST:event_spn_pass1StateChanged

    private void spn_pass2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_pass2StateChanged
        // TODO add your handling code here:
        intentarAbrirCandado();
    }//GEN-LAST:event_spn_pass2StateChanged

    private void spn_pass3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_pass3StateChanged
        // TODO add your handling code here:
        intentarAbrirCandado();
    }//GEN-LAST:event_spn_pass3StateChanged

    private void spn_pass4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_pass4StateChanged
        // TODO add your handling code here:
        intentarAbrirCandado();
    }//GEN-LAST:event_spn_pass4StateChanged

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            textoAcciones.setText("Ingrese el comando correspondiente.");
            String texto = jTextField1.getText();
            if (texto.equals("")) {
                mostrarProximoMensaje();
            } else {
                procesarComando(jTextField1.getText());
                jTextField1.setText("");
            }
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
    
    private void mostrarProximoMensaje() {
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
        
        
    }
    
    private void procesarObjetoX() {
        ArrayList<ObjetoX> listaObjetoX = ultimoMensajeMostrado.getListaObjetoX();
        for (ObjetoX objetoX : listaObjetoX) {
            String codigoObjetoX = objetoX.getCodigoObjetoX();
            switch (codigoObjetoX) {
                case "candadoValor1":
                    spn_pass1.setValue(9);//o ejecutar el metodo adecuado...
                    break;
                case "candadoValor2":
                    spn_pass2.setValue(5);//o ejecutar el metodo adecuado...
                    break;
                case "candadoValor3":
                    spn_pass3.setValue(3);//o ejecutar el metodo adecuado...
                    break;    
                case "candadoValor4":
                    spn_pass4.setValue(1);//o ejecutar el metodo adecuado...
                    break;
                case "llegadabosque":
                    if (isCandadoAbierto) {
                        historia.setProximoMensajeDeNarracion("bosqueCompleto");
                        historia.setProximoMensajeDeMensajeEnLista("bosqueCompleto", "cabana");
                    } else {
                        historia.setProximoMensajeDeNarracion("bosqueIncompleto");
                    }
                    mostrarProximoMensaje();
                    break; 
            }
        }
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
        } else {
            if (ultimoMensajeMostrado.getProcesarRespuesta()) {
                if (historia.procesarTextoComando(textoComando)) {
                    //System.out.println("comando no valido...");
                    //break;
                    mostrarProximoMensaje();
                } else {
                    textoAcciones.setText("Comando no válido");
                    textoAcciones.setVisible(true);
                }
            }
        }
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
        auxiliar += String.valueOf(spn_pass1.getValue());
        auxiliar += String.valueOf(spn_pass2.getValue());
        auxiliar += String.valueOf(spn_pass3.getValue());
        auxiliar += String.valueOf(spn_pass4.getValue());
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
        }
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
    private javax.swing.JSpinner spn_pass1;
    private javax.swing.JSpinner spn_pass2;
    private javax.swing.JSpinner spn_pass3;
    private javax.swing.JSpinner spn_pass4;
    private javax.swing.JLabel textoAcciones;
    // End of variables declaration//GEN-END:variables
}

