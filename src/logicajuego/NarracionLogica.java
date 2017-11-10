package logicajuego;

import entidad.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import logicajuego.MensajeLogica;


public class NarracionLogica {
    
    private Integer indiceTareas;//se usa para recorrer el vector con las tareas
    private Integer[] indicesTareas;
    
    private MensajeLogica mensajeProcesandoRespuesta;
    private String codigoMensajeProcesandoMensaje;

    private String codigoMensajeUltimoMensajeMostrado;
    private String codigoMensajeProximo;
    //private ArrayList<Mensaje> listaMensajes;
    private Map<String, MensajeLogica> listaMensajes = new HashMap<String, MensajeLogica>();

    public NarracionLogica() {
            this.codigoMensajeUltimoMensajeMostrado = null;
            this.codigoMensajeProximo = null;
            //listaMensajes = new ArrayList<Mensaje>();
            //listaMensajes = new HashMap<Integer, MensajeLogica>();
    }

    //se utiliza generalmente para mostrar un mensaje y que el indice no se mueva, de tal forma que despues de mostrar el mensaje el mensaje mostrado previamente siga activo
    public void agregarMensajePopUp(String codigoMensaje, String texto) {

            MensajeLogica mensaje = new MensajeLogica(codigoMensaje, texto, true);
            //listaMensajes.add(mensaje);
            listaMensajes.put(codigoMensaje, mensaje);
    }

    public void agregarMensaje(MensajeLogica mensajeLogica) {

        listaMensajes.put(mensajeLogica.getCodigoMensaje(), mensajeLogica);
    }

    public void agregarMensaje(String codigoMensaje, String texto) {

            MensajeLogica mensaje = new MensajeLogica(codigoMensaje, texto);
            //listaMensajes.add(mensaje);
            listaMensajes.put(codigoMensaje, mensaje);
    }

    public void agregarMensaje(String codigoMensaje, String texto, String codigoMensajeAnterior) {


            MensajeLogica mensaje = new MensajeLogica(codigoMensaje, texto);
            //listaMensajes.add(mensaje);
            listaMensajes.put(codigoMensaje, mensaje);
            if (listaMensajes.containsKey(codigoMensajeAnterior)) {
                    MensajeLogica mensajeAnterior = listaMensajes.get(codigoMensajeAnterior);
                    mensajeAnterior.setCodigoMensajeSiguiente(codigoMensaje);
                    listaMensajes.put(codigoMensajeAnterior, mensajeAnterior);
            } else {
                    //mostrar error...
            }

            /*
            int indiceMensajeSiguiente = listaMensajes.size()-1;
            int indiceMensajeAnterior = getIndiceMensajeDeIdMensaje(idMensajeAnterior);
            if (indiceMensajeAnterior > -1) {
                    Mensaje mensajeAnterior = listaMensajes.get(indiceMensajeAnterior);
                    mensajeAnterior.setIndiceMensajeSiguiente(indiceMensajeSiguiente);
                    listaMensajes.set(indiceMensajeAnterior, mensajeAnterior);
            } else {
                    //mostrar error...
            }
            */

    }

    public void agregarComandoAMensaje(String codigoMensaje, String codigoComando, String textComando, String codigoMensajeEjecutar) {
            if (listaMensajes.containsKey(codigoMensaje)) {
                    if (listaMensajes.containsKey(codigoMensajeEjecutar)) {
                            MensajeLogica mensajeAModiicar = listaMensajes.get(codigoMensaje);
                            ComandoLogica comando = new ComandoLogica(codigoComando, textComando, codigoMensajeEjecutar);
                            mensajeAModiicar.agregarComando(comando);
                            listaMensajes.put(codigoMensaje, mensajeAModiicar);
                    } else {
                            //mostrar error
                    }
            } else {
                    //mostrar error
            }	

            /*
            int indiceMensaje = getIndiceMensajeDeIdMensaje(idMensaje);
            if (indiceMensaje > -1) {
                    int indiceMensajeEjecutar = getIndiceMensajeDeIdMensaje(idMensajeEjecutar);
                    if (indiceMensajeEjecutar > -1) {
                            Comando comando = new Comando(idComando, textComando, indiceMensajeEjecutar);
                            Mensaje mensaje = listaMensajes.get(indiceMensaje);
                            mensaje.agregarComando(comando);
                            listaMensajes.set(indiceMensaje, mensaje);
                    } else {
                            //mostrar error
                    }
            } else {
                    //mostrar error
            }
            */

    }
    
    public void agregarObjetoXAMensaje(String codigoMensaje, String codigoObjetoX, String nombre, Boolean valorbooleano, Integer valorEntero, String valorString) {
        ObjetoX objetoX = new ObjetoX();
        objetoX.setCodigoObjetoX(codigoObjetoX);
        objetoX.setNombre(nombre);
        objetoX.setValorBoleano(valorbooleano);
        objetoX.setValorEntero(valorEntero);
        objetoX.setValorTexto(valorString);
        
        if (listaMensajes.containsKey(codigoMensaje)) {
            MensajeLogica mensajeAModiicar = listaMensajes.get(codigoMensaje);
            mensajeAModiicar.agregarObjetoX(objetoX);
            listaMensajes.put(codigoMensaje, mensajeAModiicar);
            
        } else {
                //mostrar error
        }
    }

    public void agregarTarea(String codigoMensaje, Integer numeroTarea, String detalleTarea) {
            if (listaMensajes.containsKey(codigoMensaje)) {
                MensajeLogica mensaje = listaMensajes.get(codigoMensaje);
                mensaje.setNumeroTarea(numeroTarea);
                mensaje.setObligatorio(true);
                mensaje.setDetalleTarea(detalleTarea);
                mensaje.setMensajeMostrado(false);
                listaMensajes.put(codigoMensaje, mensaje);
            } else {
                    //mostrar error
            }

            /*
        int indiceMensaje = getIndiceMensajeDeIdMensaje(idMensaje);
        if (indiceMensaje > -1) {
                Mensaje mensaje = listaMensajes.get(indiceMensaje);
                mensaje.setNumeroTarea(numeroTarea);
                mensaje.setObligatorio(true);
                mensaje.setDetalleTarea(detalleTarea);
                mensaje.setMensajeMostrado(false);
                listaMensajes.set(indiceMensaje, mensaje);
        } else {
                //mostrar error
        }
        */

    }

    public TareaLogica obtenerPrimerTareaPendiente() {
        TareaLogica tareaLogica = new TareaLogica();
        String codigoMensajeConPrimerTareaPendiente = null;
        int numeroTarea = 1000000;

        for (String codigoMensaje : listaMensajes.keySet()) {
                        MensajeLogica mensaje = listaMensajes.get(codigoMensaje);
            if (mensaje.getObligatorio()) {
                if (!mensaje.getMensajeMostrado()) {
                    int auxNumeroTarea = mensaje.getNumeroTarea();
                    if (auxNumeroTarea < numeroTarea) {
                        numeroTarea = auxNumeroTarea;
                        codigoMensajeConPrimerTareaPendiente = codigoMensaje;
                    }
                }

            }
                    }

                    if (codigoMensajeConPrimerTareaPendiente != null) {
            MensajeLogica mensaje2 = listaMensajes.get(codigoMensajeConPrimerTareaPendiente);
            tareaLogica.setTareaValida(true);
            tareaLogica.setNumeroTarea(mensaje2.getNumeroTarea());
            tareaLogica.setDetalleTarea(mensaje2.getDetalleTarea());
        } else {
            tareaLogica.setTareaValida(false);
        }
        return tareaLogica;

        /*
        int cantidadMensajes = listaMensajes.size();
        for(int i = 0; i < cantidadMensajes; i++) {
            Mensaje mensaje = listaMensajes.get(i);
            if (mensaje.getObligatorio()) {
                if (!mensaje.getMensajeMostrado()) {
                    int auxNumeroTarea = mensaje.getNumeroTarea();
                    if (auxNumeroTarea < numeroTarea) {
                        numeroTarea = auxNumeroTarea;
                        indiceMensaje = i;
                    }
                }

            }
        }
        if (indiceMensaje > -1) {
            Mensaje mensaje2 = listaMensajes.get(indiceMensaje);
            tarea.setTareaValida(true);
            tarea.setNumeroTarea(mensaje2.getNumeroTarea());
            tarea.setDetalleTarea(mensaje2.getDetalleTarea());
        } else {
            tarea.setTareaValida(false);
        }
        return tarea;
        */
    }

    private String getCodigoMensajeUltimoMensajeMostrado() {
            //return this.indiceUltimoMensajeMostrado;
            return this.codigoMensajeUltimoMensajeMostrado;
    }

    /*
    private int getIndiceMensajeDeIdMensaje(String idMensaje) {
            int indiceMensaje = -1;
            int cantidadMensajes = listaMensajes.size();
            for(int i = 0; i < cantidadMensajes; i++) {
                    Mensaje mensaje = listaMensajes.get(i);
                    String auxIdMensaje = mensaje.getIdMensaje();
                    if (auxIdMensaje.equals(idMensaje)) {
                            indiceMensaje = i;
                            break;
                    }

            }
            return indiceMensaje;
    }
    */

    public void setProximoMensajeDeNarracion(String codigoMensaje) {
            if (listaMensajes.containsKey(codigoMensaje)) {
                    this.codigoMensajeProximo = codigoMensaje;
            } else {
                    //mostrar error
            }

            /*
            int indiceMensaje = getIndiceMensajeDeIdMensaje(idMensaje);
            if (indiceMensaje > -1) {
                    this.indiceMensajeProximo = indiceMensaje;
            } else {
                    //mostrar error
            }
            */
    }

    public void setProximoMensajeDeMensajeEnLista(String codigoMensaje, String codigoMensajeProximo) {
            if (listaMensajes.containsKey(codigoMensaje)) {
                    if (listaMensajes.containsKey(codigoMensajeProximo)) {
                            MensajeLogica mensaje = listaMensajes.get(codigoMensaje);
                            mensaje.setCodigoMensajeSiguiente(codigoMensajeProximo);
                            listaMensajes.put(codigoMensaje, mensaje);
                    } else {
                            //mostrar error
                    }
            } else {
                    //mostrar error
            }

            /*
            int indiceMensaje = getIndiceMensajeDeIdMensaje(idMensaje);
            if (indiceMensaje > -1) {
                    int indiceMensajeProximo = getIndiceMensajeDeIdMensaje(idMensajeProximo);
                    if (indiceMensajeProximo > -1) {
                            Mensaje mensaje = listaMensajes.get(indiceMensaje);
                            mensaje.setIndiceMensajeSiguiente(indiceMensajeProximo);
                            listaMensajes.set(indiceMensaje, mensaje);
                    } else {
                            //mostrar error
                    }

            } else {
                    //mostrar error
            }
            */
    }

    //ANALIZAR BIEN LA LOGICA DE ESTE METODO...
    public MensajeLogica getMensaje() {
        MensajeLogica mensaje = listaMensajes.get(codigoMensajeProximo);
        if (mensaje != null) {
            if (mensaje.getMensajePopUp()) {
                mensaje.setCodigoMensajeSiguiente(this.codigoMensajeProcesandoMensaje);
                this.mensajeProcesandoRespuesta.setMostrarMensaje(false);//VERIFICAR LOGICA CUANDO SE MUESTRAN LOS MENSAJES, PUEDE SER NECESARIO VOLVER A ACTIVAR
            }

            if (mensaje.getObligatorio()) {
                mensaje.setMensajeMostrado(true);
                listaMensajes.put(codigoMensajeProximo, mensaje);
            }

            if (!mensaje.getProcesarRespuesta()) {
                    this.codigoMensajeProximo = mensaje.getCodigoMensajeSiguiente();
            } else {
                this.mensajeProcesandoRespuesta = mensaje;
                this.codigoMensajeProcesandoMensaje = this.codigoMensajeProximo;
            }
        }
        
        

        return mensaje;
    }

    public boolean procesarTextoComando(String textoComando) {

    boolean comandoValido = false;
            //Mensaje mensaje = MenajeProcesandoMensaje;//listaMensajes.get(indiceMensajeProximo);
            String auxCodigoMensajeProximo = mensajeProcesandoRespuesta.getCodigoMensajeDeComandoTXT(textoComando);
            if (auxCodigoMensajeProximo != null) {
                    this.codigoMensajeProximo = auxCodigoMensajeProximo;
                    comandoValido = true;
            }
            return comandoValido;
    }

    public Map<String, MensajeLogica> getListaMensajes() {
        return listaMensajes;
    }

    
    
    public void setListaMensajes(Map<String, MensajeLogica> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public String getCodigoMensajeProximo() {
        return codigoMensajeProximo;
    }

    public void setCodigoMensajeProximo(String codigoMensajeProximo) {
        this.codigoMensajeProximo = codigoMensajeProximo;
    }
    
    
    
	
}