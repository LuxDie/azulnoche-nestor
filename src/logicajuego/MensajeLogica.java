package logicajuego;

import java.util.ArrayList;

public class MensajeLogica {
    
    private Boolean mensajeMostrado;
    private Boolean obligatorio;
    private Integer numeroTarea; 
    private String detalleTarea;

    private boolean mostrarMensaje;//permite definir si el texto se debe mostrar, no se debe mostrar el texto si el mensaje anterior activo era del tipo pop up
    private boolean mensajePopUp;



    private boolean procesarRespuesta;
    //private int indiceMensajeAnterior;///talves no se utilice...
    private String codigoMensajeSiguiente;

    private String codigoMensaje;

    //String texto;
    private ArrayList<String> listaMensajeTexto;
    private Boolean textoAleatorio;
    private int indiceTexto;

    private ArrayList<ComandoLogica> comandos;

    private ArrayList<ObjetoX> listaObjetoX;

    public MensajeLogica() {
        this.listaMensajeTexto = new ArrayList<>();
        this.textoAleatorio = false;
	this.indiceTexto = -1;
        this.obligatorio = false;
	this.procesarRespuesta = false;
	this.codigoMensajeSiguiente = null;
        this.mensajePopUp = false;
        this.comandos = new ArrayList<ComandoLogica>();
        
        this.listaObjetoX = new ArrayList<>();
    }
	
        

    public MensajeLogica(String codigoMensaje, String texto, boolean mensajePopUp) {
        listaMensajeTexto = new ArrayList<>();
        this.codigoMensaje = codigoMensaje;
        listaMensajeTexto.add(texto);
        textoAleatorio = false;
        indiceTexto = -1;
        //this.texto = texto;

        this.obligatorio = false;
        this.procesarRespuesta = false;
        this.codigoMensajeSiguiente = null;

        this.mensajePopUp = mensajePopUp;
        this.mostrarMensaje = true;

        this.comandos = new ArrayList<ComandoLogica>();
        
        this.listaObjetoX = new ArrayList<>();
    }

    public Boolean setCodigoMensajeSiguienteDeComando(String codigoComando, String codigoMensajeSiguiente) {
        Boolean resultado = false;
        int cantidadComandos = comandos.size();
        for(int i = 0; i < cantidadComandos; i++) {
            ComandoLogica comando = comandos.get(i);
            String codigoComandoaux = comando.getCodigoComando();
            if (codigoComandoaux.equalsIgnoreCase(codigoComando)) {
                comando.setCodigoMensaje(codigoMensajeSiguiente);
                comandos.set(i, comando);
                resultado = true;
                break;
            }
            

        }
        return resultado;
    }
    
    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public ArrayList<String> getListaMensajeTexto() {
        return listaMensajeTexto;
    }

    public ArrayList<ComandoLogica> getComandos() {
        return comandos;
    }
    
    public void agregarObjetoX(ObjetoX objetoX) {
        listaObjetoX.add(objetoX);
    }

    public ArrayList<ObjetoX> getListaObjetoX() {
        return listaObjetoX;
    }
    
    
    
    public void agregarMensajeTexto(String mensajeTexto) {
        listaMensajeTexto.add(mensajeTexto);
    }

    public void setProcesarRespuesta(boolean procesarRespuesta) {
        this.procesarRespuesta = procesarRespuesta;
    }

    public void setTextoAleatorio(Boolean textoAleatorio) {
        this.textoAleatorio = textoAleatorio;
    }

    public void setIndiceTexto(int indiceTexto) {
        this.indiceTexto = indiceTexto;
    }

    
        
        
	public MensajeLogica(String codigoMensaje, String texto, String codigoMensajeSiguiente) {
		listaMensajeTexto = new ArrayList<>();
		this.codigoMensaje = codigoMensaje;
		listaMensajeTexto.add(texto);
		textoAleatorio = false;
		indiceTexto = -1;
		
		this.obligatorio = true;
		this.procesarRespuesta = false;
		this.codigoMensajeSiguiente = codigoMensajeSiguiente;

		this.mensajePopUp = false;
        this.mostrarMensaje = true;
                
        this.comandos = new ArrayList<ComandoLogica>();
        this.listaObjetoX = new ArrayList<>();
	}

	public MensajeLogica(String codigoMensaje, String texto) {
		listaMensajeTexto = new ArrayList<>();
		this.codigoMensaje = codigoMensaje;
		listaMensajeTexto.add(texto);
		textoAleatorio = false;
		indiceTexto = -1;
		
		this.obligatorio = false;
		this.procesarRespuesta = false;
		this.codigoMensajeSiguiente = null;

		this.mensajePopUp = false;
        this.mostrarMensaje = true;
                
        this.comandos = new ArrayList<ComandoLogica>();
        
        this.listaObjetoX = new ArrayList<>();
	}

	public void agregarComando(ComandoLogica comando) {
		this.procesarRespuesta = true;
		this.comandos.add(comando);
		

	}

    public Boolean getMensajeMostrado() {
        return mensajeMostrado;
    }

    public Boolean getObligatorio() {
        return obligatorio;
    }

    public Integer getNumeroTarea() {
        return numeroTarea;
    }

    public String getDetalleTarea() {
        return detalleTarea;
    }
    
    

    public void setMensajeMostrado(Boolean mensajeMostrado) {
        this.mensajeMostrado = mensajeMostrado;
    }

    public void setObligatorio(Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public void setNumeroTarea(Integer numeroTarea) {
        this.numeroTarea = numeroTarea;
    }

    public void setDetalleTarea(String detalleTarea) {
        this.detalleTarea = detalleTarea;
    }
        
        

	public String getMensaje() {
		String texto;
		if (textoAleatorio) {
			texto = listaMensajeTexto.get(0);//REEMPLAZAR PR CODIGO QUE DEVUELVE TEXTO ALEATORIO
		} else {
			indiceTexto = indiceTexto + 1;
			texto = listaMensajeTexto.get(indiceTexto);
			if ((indiceTexto + 1) >= listaMensajeTexto.size()) {
				indiceTexto = -1;
			}
		}
		return texto;
	}

	public String getCodigoMensaje() {
		return this.codigoMensaje;
	}

	public void setCodigoMensajeSiguiente(String codigoMensajeSiguiente) {
		this.codigoMensajeSiguiente = codigoMensajeSiguiente;
	}

	public void setMensajePopUp(boolean mensajePopUp) {
		this.mensajePopUp = mensajePopUp;
	}

	public void setMostrarMensaje(boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public String getCodigoMensajeSiguiente() {
		return this.codigoMensajeSiguiente;
	}

	public boolean getMensajePopUp() {
		return this.mensajePopUp;
	}

        /**
         * Se utiliza para saber si un mensaje tiene comandos que puede procesar
         * @return Boolean Devuelve verdadero si el mensaje procesa comandos o falso si no procesa comandos
         */
	public boolean getProcesarRespuesta() {
		return this.procesarRespuesta;
	}

	public boolean getMostrarMensaje() {
		return this.mostrarMensaje;
	}

        /**
         * Devuelve el codigo del mensaje que se tiene que mostrar cuando se ingresar un comando o null si el textocomando no es valido
         * @param textoComando Es el texto del comando que se deber verificar si es valido
         * @return String Devuelve el codigoMensaje que corresponde al parametro que se ingreso o null si no se encontro el textoComando
         */
	public String getCodigoMensajeDeComandoTXT(String textoComando) {
		String codigoMensaje = null;
		int cantidadComandos = comandos.size();
		for(int i = 0; i < cantidadComandos; i++) {
			ComandoLogica comando = comandos.get(i);
			if (comando.comandoValido(textoComando)) {
				codigoMensaje = comando.getCodigoMensaje();
				break;
			}
			/*
			String auxTextoComando = comando.getComando();
			if (auxTextoComando.equals(textoComando)) {
				indiceMensaje = comando.getIndiceMensaje();
				break;
			}
			*/

		}
		return codigoMensaje;

	}
	
}