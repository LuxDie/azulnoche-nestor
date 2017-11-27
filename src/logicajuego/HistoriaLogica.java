/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicajuego;

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
import logicajuego.TareaLogica;
import entidad.TextoComando;
import entidad.TextoMensaje;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import utils.AdministradorArchivo;

/**
 *
 * @author fabian
 */
public class HistoriaLogica {
    private NarracionLogica narracion;
    
    private Integer idHistoria;
    private Integer idIdioma;
    private String titulo;
    private String descripcion;
    private String codigoMensajeInicio;
    
    public HistoriaLogica() {
        this.narracion = new NarracionLogica();
        idIdioma = 1;
    }
    
    public MensajeLogica getMensaje() {
        return this.narracion.getMensaje();
    }
    
    public TareaLogica obtenerPrimerTareaPendiente() {
        return this.narracion.obtenerPrimerTareaPendiente();
    }
    
    public Boolean procesarTextoComando(String textoComando) {
        return this.narracion.procesarTextoComando(textoComando);
    }
    ////////////////////////////////////////////////
    public Integer insertarHistoriaCompleta() {
        Map<String, MensajeLogica> listaMensajes = this.narracion.getListaMensajes();
        
        Historia historia = new Historia();
        historia.setIdIdioma(idIdioma);
        historia.setTitulo(titulo);
        historia.setDescripcion(descripcion);
        historia.setCodigoMensajeInicio(codigoMensajeInicio);
        HistoriaDato historiaDato = new HistoriaDato();
        this.idHistoria = historiaDato.insertarHistoria(historia);
        
        for (Entry<String, MensajeLogica> mensajeLogicaCompuesto : listaMensajes.entrySet()) {
            MensajeLogica mensajeLogica = mensajeLogicaCompuesto.getValue();
            Mensaje mensaje = new Mensaje();
            mensaje.setIdHistoria(idHistoria);
            mensaje.setCodigoMensaje(mensajeLogica.getCodigoMensaje());
            mensaje.setObligatorio(mensajeLogica.getObligatorio());
            mensaje.setMostrarMensaje(mensajeLogica.getMostrarMensaje());
            mensaje.setMensajePopUp(mensajeLogica.getMensajePopUp());
            mensaje.setCodigoMensajeSiguiente(mensajeLogica.getCodigoMensajeSiguiente());
            
            MensajeDato mensajeDato = new MensajeDato();
            Integer idMensaje = mensajeDato.insertarMensaje(mensaje);
            
            ArrayList<String> listatextoMensaje = mensajeLogica.getListaMensajeTexto();
            for (String texto : listatextoMensaje) {
                TextoMensaje textoMensaje = new TextoMensaje();
                textoMensaje.setIdMensaje(idMensaje);
                textoMensaje.setIdIdioma(idIdioma);
                textoMensaje.setTextoMensaje(texto);
                TextoMensajeDato textoMensajeDato = new TextoMensajeDato();
                textoMensajeDato.insertarTextoMensaje(textoMensaje);
                
            }
            
            ArrayList<ComandoLogica> listaComandos = mensajeLogica.getComandos();
            for (ComandoLogica comandoLogica : listaComandos) {
                Comando comando = new Comando();
                comando.setIdMensaje(idMensaje);
                comando.setCodigoComando(comandoLogica.getCodigoComando());
                comando.setCodigoMensajeSiguiente(comandoLogica.getCodigoMensaje());
                ComandoDato comandoDato = new ComandoDato();
                Integer idComando = comandoDato.insertarComando(comando);
                
                ArrayList<String> listatextoComando = comandoLogica.getListaComandoTexto();
                for (String texto : listatextoComando) {
                    TextoComando textoComando = new TextoComando();
                    textoComando.setIdIdioma(idIdioma);
                    textoComando.setIdComando(idComando);
                    textoComando.setTextoComando(texto);
                    TextoComandoDato textoComandoDato = new TextoComandoDato();
                    textoComandoDato.insertarTextoComando(textoComando);
                    
                }
                
            }
            
            if (mensajeLogica.getObligatorio()) {
                TareaDato tareaDato = new TareaDato();
                Tarea tarea = new Tarea();
                tarea.setIdMensaje(idMensaje);
                tarea.setNumeroTarea(mensajeLogica.getNumeroTarea());
                tarea.setDetalleTarea(mensajeLogica.getDetalleTarea());
                tareaDato.insertarTarea(tarea);
                
            }
        }
        return this.idHistoria;
    }
    
    public void obtenerHistoriaCompletaPorId(Integer idHistoria, Integer idIdioma, String codigoMensajeInicio) {
        this.idIdioma = idIdioma;
        this.narracion = new NarracionLogica();
        
        MensajeDato mensajeDato = new MensajeDato();
        ArrayList<Mensaje> listaMensaje = mensajeDato.obtenerListaMensajesPorIdHistoria(idHistoria);
        for (Mensaje mensaje : listaMensaje) {
            MensajeLogica mensajeLogica = new MensajeLogica();
            mensajeLogica.setCodigoMensaje(mensaje.getCodigoMensaje());
            mensajeLogica.setIndiceTexto(-1);//no es necesario
            mensajeLogica.setObligatorio(mensaje.getObligatorio());
            mensajeLogica.setMostrarMensaje(mensaje.getMostrarMensaje());
            mensajeLogica.setCodigoMensajeSiguiente(mensaje.getCodigoMensajeSiguiente());
            mensajeLogica.setMensajePopUp(mensaje.getMensajePopUp());
            
            TextoMensajeDato textoMensajeDato = new TextoMensajeDato();
            ArrayList<TextoMensaje> listaTextoMensajje = textoMensajeDato.obtenerListaTextoMensajePorIdMensajeIdIdioma(mensaje.getIdMensaje(), idIdioma);
            for (TextoMensaje textoMensaje : listaTextoMensajje) {
                mensajeLogica.agregarMensajeTexto(textoMensaje.getTextoMensaje());
            }
            
            boolean contieneComando = false;
            ComandoDato comandoDato = new ComandoDato();
            ArrayList<Comando> listaComandos = comandoDato.obtenerListaComandosDeIdMensaje(mensaje.getIdMensaje());
            for (Comando comando : listaComandos) {
                contieneComando = true;
                
                ComandoLogica comandoLogica = new ComandoLogica();
                comandoLogica.setCodigoComando(comando.getCodigoComando());
                comandoLogica.setCodigoMensaje(comando.getCodigoMensajeSiguiente());
                
                TextoComandoDato textoComandoDato = new TextoComandoDato();
                ArrayList<TextoComando> listaTextoComando = textoComandoDato.obtenerListaTextoComando(comando.getIdComando(), idIdioma);
                for (TextoComando textoComando : listaTextoComando) {
                    comandoLogica.agregarTextoComando(textoComando.getTextoComando());
                }
                
                mensajeLogica.agregarComando(comandoLogica);
            }
            mensajeLogica.setProcesarRespuesta(contieneComando);
            
            //hay que modificar la estructura y logica de las tareas...
            TareaDato tareaDato = new TareaDato();
            ArrayList<Tarea> listaTareas = tareaDato.obtenerListaTareaPorIdMensaje(mensaje.getIdMensaje());
            for (Tarea tarea : listaTareas) {
                
                mensajeLogica.setObligatorio(true);
                mensajeLogica.setNumeroTarea(tarea.getNumeroTarea());
                mensajeLogica.setDetalleTarea(tarea.getDetalleTarea());
                mensajeLogica.setMensajeMostrado(false);
            }
            
            this.narracion.agregarMensaje(mensajeLogica);
        }
        this.narracion.setProximoMensajeDeNarracion(codigoMensajeInicio);
    }
    
    public ArrayList<Historia> obtenerListaHistorias() {
        ArrayList<Historia> listaHistoria = new ArrayList<>();
        return listaHistoria;
    }
    
    public EstadoScript cargarHistoriaDesceScript(String archivo, Integer idIdioma, String titulo, String descripcion) {
        EstadoScript estadoScript = new EstadoScript();
        NarracionLogica narracionLogica = new NarracionLogica();
        Boolean scriptValido = false;
        Boolean contieneInicioNarracion = false;
        if (AdministradorArchivo.archivoExiste(archivo)) {
            ArrayList<String> lineas = AdministradorArchivo.leerlineas(archivo);
            for(int i=0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                //String[] parametros = linea.split("\t");
                String[] parametros = linea.split("\", \"");
                String comando = parametros[0];
                
                switch (comando) {
                    case "agregarMensaje":
                        int cantidadParametros = parametros.length;
                        switch (cantidadParametros) {
                            case 3:
                                narracionLogica.agregarMensaje(parametros[1], parametros[2]);
                                scriptValido = true;
                                break;
                            case 4:
                                narracionLogica.agregarMensaje(parametros[1], parametros[2], parametros[3]);
                                scriptValido = true;
                                break;
                            default:
                                estadoScript.setDetalle("Cantidad de parametros no valido");
                                estadoScript.setLineaInvalida(linea);
                                scriptValido = false;
                        }
                        break;
                    case "agregarTarea":
                        if (parametros.length == 4) {
                            narracionLogica.agregarTarea(parametros[1], Integer.valueOf(parametros[2]), parametros[3]);
                            scriptValido = true;
                        } else {
                            estadoScript.setDetalle("Cantidad de parametros no valido");
                            estadoScript.setLineaInvalida(linea);
                            scriptValido = false;
                        }
                        break;
                    case "agregarMensajePopUp":
                        if (parametros.length == 3) {
                            narracionLogica.agregarMensajePopUp(parametros[1], parametros[2]);
                            scriptValido = true;
                        } else {
                            estadoScript.setDetalle("Cantidad de parametros no valido");
                            estadoScript.setLineaInvalida(linea);
                            scriptValido = false;
                        }
                        break;
                    case "agregarComandoAMensaje":
                        if (parametros.length == 5) {
                            narracionLogica.agregarComandoAMensaje(parametros[1], parametros[2], parametros[3], parametros[4]);
                            scriptValido = true;
                        } else {
                            estadoScript.setDetalle("Cantidad de parametros no valido");
                            estadoScript.setLineaInvalida(linea);
                            scriptValido = false;
                        }
                        break;
                    case "setProximoMensajeDeMensajeEnLista":
                        if (parametros.length == 3) {
                            narracionLogica.setProximoMensajeDeMensajeEnLista(parametros[1], parametros[2]);
                            scriptValido = true;
                        }
                        break;
                    case "setProximoMensajeDeNarracion":
                        if (parametros.length == 2) {
                            narracionLogica.setProximoMensajeDeNarracion(parametros[1]);
                            contieneInicioNarracion = true;
                            scriptValido = true;
                        } else {
                            estadoScript.setDetalle("Cantidad de parametros no valido");
                            estadoScript.setLineaInvalida(linea);
                            scriptValido = false;
                        }
                        break;
                    default:
                        estadoScript.setDetalle("no se reconoce el comando");
                        estadoScript.setLineaInvalida(linea);
                        scriptValido = false;
                        break;
                        
                }
                if (!scriptValido) {
                    break;
                }
                    
            }
        }
        if (scriptValido) {
            if (contieneInicioNarracion) {
                this.narracion = narracionLogica;
                this.codigoMensajeInicio = this.narracion.getCodigoMensajeProximo();
                this.idIdioma = idIdioma;
                this.titulo = titulo;
                this.descripcion = descripcion;
                estadoScript.setScriptValido(true);
            } else {
                estadoScript.setDetalle("Script incompleto. No se encontro inicio de la historia...");
                estadoScript.setLineaInvalida("-");
                estadoScript.setScriptValido(false);
            }
            
        }
        return estadoScript;
    }
    ////////////
    public void agregarMensajeTexto(String codigoMensaje, String texto) {
        this.narracion.agregarMensajeTexto(codigoMensaje, texto);
    }
    
    public void setProximoMensajeDeComandoDeMensajeEnLista(String codigoMensaje, String codigoComando, String codigoMensajeProximo) {
        this.narracion.setProximoMensajeDeComandoDeMensajeEnLista(codigoMensaje, codigoComando, codigoMensajeProximo);
    }
    ////////////
    
    private void cargarMensjesObjetos_Bar() {
        
        this.narracion.agregarMensaje("men1", "El interior del bar estaba un poco oscuro...");
        this.narracion.agregarMensaje("men2", "A pesar de la oscuridad se puede observar una barra y una puerta con un cartel que dice \"Baño\". El piso está muy pegajoso, hay varios vasos y botellas tiradas en el suelo.", "men1");
        this.narracion.agregarMensaje("men3", "En la barra hay una copa y dos botellas, una de agua y otra de cerveza. Misteriosamente la cerveza está sin abrir. Te vendría muy bien una, pero está caliente y seguramente vencida.", "men2");
        this.narracion.agregarMensaje("men4", "El baño es un verdadero asco... Nadie en su sano juicio lo utilizaría para hacer sus necesidades, pero parece que también servía como depósito porque en una esquina se ven varias cajas con papeles.", "men3");
        this.narracion.agregarMensaje("men5", "Vas hasta la pila de cajas. Están llenas de bebidas espirituosas, todas calientes. Revisás los papeles, en su mayoría son facturas de proveedores, algunas cartas de bancos, y muchas planillas contables. También hay una hoja que solo tiene el número 9 bien grande, y algunas notas, escritas a mano.", "men4");
        this.narracion.agregarMensaje("men6","\"Vendé, sabés que te conviene.\" dice una. Otra presagia: \"Si te quedás vas a terminar igual que la de las plantas\". " +
                                        "\"Vendé o andate\" dice otra. La más llamativa es una que dice: \"Vendés o morís\".", "men5");
        this.narracion.agregarMensaje("men7", "También hay una carta escrita a máquina en una hoja con el membrete de una empresa, Aceites del Sud SA. La carta es un propuesta para comprar la propiedad donde está ubicado el bar. Es mejor guardar todas estas notas, podrían servir como evidencia. " +
                                        "Revisás un poco más el lugar pero es en vano, literalmente está todo dado vuelta. " +
                                         "Salís del Bar, al mirar la calle pensás \"¿A qué otro lugar debería ir?\". Podés IR AL BOSQUE o INGRESAR CASA 3.", "men6");
             
        
        this.narracion.agregarMensaje("estado2_men1", "Ingresas al bar, miras un poco y sales rapidamente, tu instinto te dice que no encontraras nuevas pistas");
        this.narracion.agregarMensajeTexto("estado2_men1", "Entras al bar pero vuelves a salir sin buscar nada, tenes poco tiempo y preferis buscar en otro lugar...");
        this.narracion.agregarMensajeTexto("estado2_men1", "Entras al bar, miras un poco y vuelves a salir");
        
        this.narracion.agregarObjetoXAMensaje("men5", "candadoValor1", "candadoValor1", Boolean.TRUE, 9, null);
    }
    
    private void cargarMensjesObjetos_Introduccion() {
        this.narracion.agregarMensaje("intro_manejasAuto", "Manejás tu viejo Renault 9 color azul noche, como siempre con la radio apagada, nada se iguala a sentir el viento pasando por la ventana. El sonido del motor, el caucho girando sobre el asfalto y manejar en camino de piedra es ufff... simplemente sublime.");
        this.narracion.agregarMensaje("intro_manejasAut2", "Mañana cumplís seis años de servicio. Acariciás el volante, tiene un forro de cuero, ya gastado, y mirás el torpedo. \"También cumplimos seis años juntos\".", "intro_manejasAuto");
        this.narracion.agregarMensaje("intro_manejasAut3", "Aunque cuando se conocieron él lucía un bordo gastado y sin brillo, el motor sonaba parejo pero muy fuerte debido al mal estado del escape. Con el tiempo y los viajes, obtuvo nuevas llantas, con estilo. Pasaron por varios colores hasta encontrar el hermoso azul noche. A los dos les queda muy bien el azul.", "intro_manejasAut2");
        this.narracion.agregarMensaje("intro_manejasAut4", "Hace un buen tiempo que manejás por una ruta algo olvidada, con más pozos que carteles.", "intro_manejasAut3");
        this.narracion.agregarMensaje("intro_manejasAut5", "Pasaron muchos años desde que no visitaste estos parajes. Ya no recordas bien el camino y se aproxima un cruce, el tema es...", "intro_manejasAut4");
        this.narracion.agregarMensaje("intro_manejasAut6", "¿Había con salirse de la ruta en este cruce o en el próximo? ¿Había que ir a la izquierda o a la derecha?", "intro_manejasAut5");
        this.narracion.agregarMensaje("intro_manejasAut11", "\"¿Qué hacemos amigo, para donde agarramos?\", le preguntás al viejo Renault 9 azul noche. Es una vieja costumbre que practicás desde que se conocieron. " + "Podés DOBLAR DERECHA o SEGUIR ADELANTE.", "intro_manejasAut6");
         this.narracion.agregarMensaje("intro_manejasAut12", "Ahora que estas aca recordas que este camino no lleva a ningun lado, mejor DOBLAR IZQUIERDA.");

        this.narracion.agregarMensaje("intro_manejasAut17", "Después de más de una hora de viaje llegás a un pueblo, es evidente que te pasaste el cruce donde debías salir de la ruta. Igualmente parás en un café al costado de la ruta.");
        this.narracion.agregarMensaje("intro_manejasAut18", "¿Qué querés hacer? Podés VOLVER AL CRUCE, o podés ENTRAR AL CAFE. Te vendría bien estirar un poco las piernas, y tal vez consigas una buena indicación.", "intro_manejasAut17");
        
        this.narracion.agregarMensaje("intro_entrarCafe", "Entrás, es un lugar con decoración algo vieja pero agradable, y te sentás en la barra. La camarera te saluda con amabilidad, su sonrisa es pura energía.");
        this.narracion.agregarMensaje("intro_entrarCafe2", "Le pedís a la camarera un café bien cargado, te sonríe otra vez y enseguida te sirve de la jarra. Le ponés dos de azúcar, y tras un par de sorbos pensás en preguntarle a la camarera si conoce el cruce que buscás", "intro_entrarCafe");
        this.narracion.agregarMensaje("intro_entrarCafe3", "Pensás un poco, podes PEDIR INDICACION sobre el cruce o solo PAGAR e irte.", "intro_entrarCafe2");
        
        this.narracion.agregarMensaje("intro_pedirInformacion", "Tomás otro poco de café y le preguntás: \"¿Podriá darme alguna indicación sobre el cruce que busco?\"");
        this.narracion.agregarMensaje("intro_pedirInformacion2", "Se le va la sonrisa de la cara.", "intro_pedirInformacion.");
        this.narracion.agregarMensaje("intro_pedirInformacion3", "\"Ah, ese lugar buscabas, debí imaginarlo\". Frunce el ceño. \"Está más atrás, te pasaste varios kilómetros. ¿No lo viste?\"", "intro_pedirInformacion2");
        this.narracion.agregarMensaje("intro_pedirInformacion4", "\"No, la verdad que no\", le respondés intrigado por el gesto de la camarera.", "intro_pedirInformacion3");
        this.narracion.agregarMensaje("intro_pedirInformacion5", "\"Si vas para allá te aconsejo que estés más despierto y prestes más atención. No es de mi incumbencia, pero ¿a qué va?\"", "intro_pedirInformacion4");
        this.narracion.agregarMensaje("intro_pedirInformacion6", "Le podrías RESPONDER para ver qué te dice, aunque tampoco es mala idea AGRADECER e irte.", "intro_pedirInformacion5");
        
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer", "\"Solo voy de visita\" le decís mientras hacés tiempo esperando una respuesta.");
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer2", "\"No sé a quién irá a ver, hace rato que no se sabe nada de allá. Pero insisto en que sea precavido, la gente comenta sólo cosas raras de aquel lugar\", señalando hacia afuera con la vista y levantando las cejas.", "intro_pedirInformacionAgradecer");
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer3", "Pagás, le das las gracias y volvés a tu viejo Renault 9 azul noche.", "intro_pedirInformacionAgradecer2");
        
        this.narracion.agregarMensaje("intro_pedirInformacionResponder", "\"Estoy buscando a un familiar que hace mucho tiempo que no se comunica conmigo\", le decís mientras hacés tiempo esperando una respuesta.");
        this.narracion.agregarMensaje("intro_pedirInformacionResponder2", "\"Hace rato que no se sabe nada de allá. Pero insisto en que sea precavido, la gente comenta sólo cosas raras de aquel lugar\", señalando hacia afuera con la vista y levantando las cejas.", "intro_pedirInformacionResponder");
        
               
        this.narracion.agregarMensaje("intro_manejasAut19", "Volvés por el mismo camino por el que llegaste, y después de un buen viaje llegás al cruce.");
        this.narracion.agregarMensaje("intro_manejasAut20", "¿Qué querés hacer? Podés DOBLAR IZQUIERDA o DOBLAR DERECHA.", "intro_manejasAut19");
        
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_pedirInformacionResponder2", "intro_pedirInformacionAgradecer3");
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_pedirInformacionAgradecer3", "intro_manejasAut19");
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo", "Es un camino de grava. Pensás \"Es el camino ideal, es una buena señal nada malo puede venir de un camino de piedras\".");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo2", "\"¿O no?\", le preguntás en voz alta a tu compañero, el viejo Renault 9 azul noche, mirando el reloj que indica que tenés medio tanque de nafta.", "intro_manejasCaminoPueblo");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo3", "El camino de grava es largo, no da indicios de llevar a ningún lado, no hay carteles ni marcas en el camino que puedan indicar que alguien pasó por allí, al menos no recientemente.", "intro_manejasCaminoPueblo2");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo4", "Tampoco hay cercos a los lados del camino. Sólo tierra, pasto y algunas yerbas malas decoran el paisaje.", "intro_manejasCaminoPueblo3");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo5", "Con varios kilómetros de aquel camino tras la espalda llegás a un cartel, chico, de madera. Está colgando de uno de sus lados, desde el auto podés leer:", "intro_manejasCaminoPueblo4");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo6", "Bienvenido a L...", "intro_manejasCaminoPueblo5");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo7", "Falta un pedazo, pero no está a la vista. ¿Querés bajar del auto y buscar un poco más? Podés responder SI o NO.", "intro_manejasCaminoPueblo6");
        
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo8", "Buscás un poco pero no hay muchos lugares donde mirar, y terminás sin encontrar nada. Subís al auto.");
        
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo9", "Seguís camino, del lado izquierdo hay una casa, o al menos eso parece, ya que está bastante lejos.");
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_manejasCaminoPueblo8", "intro_manejasCaminoPueblo9");
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo10", "Desde el viejo Renault 9 azul noche, se puede ver que la casa está casi completamente tapada por el agua.", "intro_manejasCaminoPueblo9");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo11", "Más adelante se ven varias edificaciones, podría ser todo un pueblo.", "intro_manejasCaminoPueblo10");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo12", "Mejor dicho... ¿Podría ser el pueblo que abandonaste hace tantos años?", "intro_manejasCaminoPueblo11");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo13", "Por fortuna el camino está bastante mas alto que el nivel del agua.", "intro_manejasCaminoPueblo12");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo14", "Casi dos kilómetros después termina el camino de piedras en lo que bien podría ser la calle central del pueblo.", "intro_manejasCaminoPueblo13");
        this.narracion.agregarMensaje("intro_callePueblo", "La calle está asfaltada, muy húmeda, y hay charcos de distintos tamaños dispersos por todos lados.", "intro_manejasCaminoPueblo14");
        this.narracion.agregarMensaje("intro_callePueblo2", "Desde acá podés ver algunas de las casas y el bar. El resto parece estar inundado.", "intro_callePueblo");
        this.narracion.agregarMensaje("intro_callePueblo3", "El pueblo, tu pueblo, cambió demasiado. Cuando lo dejaste era un lugar alegre, donde todos trabajaban mucho y colaboraban con cualquiera que necesitara una mano. Siempre estaban dispuestos a hacer cualquier cosa que beneficiara al pueblo y a sus habitantes.", "intro_callePueblo2");
        this.narracion.agregarMensaje("intro_callePueblo4", "Pero eso fue hace mucho tiempo.", "intro_callePueblo3");
        this.narracion.agregarMensaje("intro_callePueblo5", "A primera vista parece no haber nadie. No escuchás ruido alguno. No sale música del bar como sucedió siempre. Ni los pájaros cantan.", "intro_callePueblo4");
        this.narracion.agregarMensaje("intro_callePueblo6", "A tu izquierda hay una casa, a la derecha hay otra casa y frente a vos la calle principal.", "intro_callePueblo5");
        this.narracion.agregarMensaje("intro_callePueblo7", "¿Qué deseas hacer? Podés INGRESAR CASA FANTASMAL, INGRESAR CASA VIEJA o IR A CALLE PRINCIPAL.", "intro_callePueblo6");
       
    }
    
    private void cargarMensjesObjetos_Casa1() {
        this.narracion.agregarMensaje("casa1a", "Entrás a la casa a tu izquierda. Se ve vieja, humeda y descuidada. Buscás por todos lados pero lo único que llama tu atención es un viejo cuaderno. Es un diario íntimo. Pertenece a Doña Aurora." );
        this.narracion.agregarMensaje("casa1b", "Cuando dejaste el pueblo ya era una señora grande, algo desequilibrada. Tenía por costumbre regalar higos pero para que fueran gratis debias pedirlo en la lengua que hablaba su abuelo.", "casa1a");
        this.narracion.agregarMensaje("casa1c", "Ya no te acordás qué idioma hablaba aquel hombre, que, claro está, usabas para pedir higos gratis todos los días. Doña Aurora te escuchaba y gustosa te regalaba dos o tres porque lo hablabas muy bien.", "casa1b");
        this.narracion.agregarMensaje("casa1d", "El diario íntimo tiene las hojas del medio arrancadas. Las primeras páginas hablan de cosas sin importancia. Y las ultimas estan llenas de garabatos. Da la impresión que Doña Aurora se terminó de volver loca... ¿O no? ¿Acaso podría ser una pista escondida?","casa1c" );
        this.narracion.agregarMensaje("casa1e", "¿La vieja se hizo pasar por loca y escribió todo en ese idioma casi muerto? " + "Mirás de nuevo el diario, y siguen pareciendo garabatos. Aunque algunos dibujos bien podrían ser letras o números.", "casa1d");        
        this.narracion.agregarMensaje("casa1f","Mirando el diario con mayor atencion ves, debajo de todos los garabatos, un garabato mucho más grande. Lo reconocés. Es el equivalente al número 3. Es otra cosa para averiguar." , "casa1e" );        
        this.narracion.agregarObjetoXAMensaje("casa1f", "candadoValor3", "candado valor 3", true, 3, null);
        this.narracion.agregarMensaje("casa1g", "Salís de la casa de Aurora. La verdad que todo se ve igual, abandonado, absolutamente todo esta húmedo, incluso con moho.", "casa1f");
        this.narracion.agregarMensaje("casa1h", "¿Donde ir ahora? Una buena pregunta. Podés INGRESAR CASA VIEJA o IR A CALLE PRINCIPAL", "casa1g");

     
        this.narracion.agregarMensaje("estado2_casa1a", "Entras a la casa revisas en algunos lugares que no revisaste antes, como no encuentras nada vuelves a salir");
        this.narracion.agregarMensajeTexto("estado2_casa1a", "Entras a la casa, vuelves a revisar en los lugares que ya revisaste antes por las dudas. Como no encuentras nada vuelves a salir");
        this.narracion.agregarMensajeTexto("estado2_casa1a", "Entras a la casa pero sales casi inmediatamente, la casa ya la revisaste y te parece mejor buscar en otro lugar.");
        
    }
    
    private void cargarMensjesObjetos_Casa2() {
        this.narracion.agregarMensaje("casa2", "Entrás a la casa. Está todo desordenado, cubierto de polvo. Hay un olor muy fuerte, es una pésima señal. Una vez que se huele ese hedor nunca más se olvida.", "casa1h");
        this.narracion.agregarMensaje("casa2a", "En el living hay un sillón de tres cuerpos que parece no estar en el sitio que le corresponde. Detrás del sillon parece haber un bulto.", "casa2");
        this.narracion.agregarMensaje("casa2b", "Te acercás un poco y enseguida te das cuenta de que es un cuerpo. " + "Te acercás más y es notorio que esa persona lleva bastante tiempo muerta. Lo reconocés fácilmente. Es Don Mateo.", "casa2a");
        this.narracion.agregarMensaje("casa2c", "En la mitad de la espalda hay una gran mancha de sangre. Fue atacado por la espalda, con un cuchillo grande o bien un machete.","casa2b" );
        this.narracion.agregarMensaje("casa2d", "El pecho se te comprime, casi de golpe. Don Mateo era un hombre con mucha historia, era dueño de un inmenso y muy particular humor serio, como él solía decir.", "casa2c");
        this.narracion.agregarMensaje("casa2e", "El arma que se llevó su vida no está. Al menos en esa casa, eso es obvio. " + "La sangre en el suelo se secó hace rato ya. Sólo queda la marca en el suelo de madera.", "casa2d");
        this.narracion.agregarMensaje("casa2f", "El cadáver tiene su mano derecha en un bolsillo. ¿Querés sacarle la mano del bolsillo? SI o NO.", "casa2e");
        this.narracion.agregarMensaje("casa2g", "Sacás la mano del cadáver del bolsillo. Aún agarra con fuerza unos papeles. Los sacás de la mano. " + "Es un boleto de compraventa, está roto en dos pedazos.", "casa2f");
        this.narracion.agregarMensaje("casa2h","Lo revisás un rato, una empresa quería comprarle la casa a Don Mateo, \"Aceites del Sud SA\". " + "El nombre de la empresa no te suena para nada. Pero te preguntás: ¿por qué dejaron el cuerpo ahí? ¿Por qué no lo enterraron?", "casa2g");
        this.narracion.agregarMensaje("casa2i", "¿Quién lo mató? ¿Tanta impunidad tiene que es capaz de dejar el cuerpo ahí tirado, cuando es notorio que se trata de un asesinato?", "casa2h"); 
        this.narracion.agregarMensaje("casa2j", "Revisás un poco más la casa pero no encontrás nada que dé algún indicio de lo que sucedió. " + "Salís de la casa con una mezcla de sentimientos, tristeza, bronca, indignación pero por sobre todas ellas, el deseo. El deseo de agarrar al culpable y hacerlo pagar.","casa2i");
        this.narracion.agregarMensaje("casa2k", "Te preguntás: \"¿Acaso voy a encontrar a todo el pueblo muerto en sus casas?\"", "casa2j");
        
        this.narracion.agregarMensaje("recuerdo1","Un escalofrío recorre todo tu cuerpo. De inmediato pensás en tus papás y en Enrique, tu hermano. La última vez que lo viste solo tenía dos años y vos 17.", "casa2k");        
        this.narracion.agregarMensaje("recuerdo2", "Tus padres se venían venir la charla. " + "\"Papá, mamá\", les dirías mientras ellos te mirarían espectantes pero sabiendo muy bien cuáles serían tus próximas palabras. " + "\"Quiero estudiar en la capital\". Harías una pausa. " + "\"Para volver como un gran policía y de esa forma ayudar al pueblo\".", "recuerdo1");
        this.narracion.agregarMensaje("recuerdo3", "Ellos llorarían, y así lo hicieron. Te abrazarían como si fuese la última vez que te verían, y así lo hicieron. "+"Una pregunta se te clava en el pecho, bien adentro.", "recuerdo2");
        this.narracion.agregarMensaje("recuerdo4", "¿Será esa, aquella, la última vez que nos viéramos? " + "¿Será aquel abrazo el último? " + "Caés de rodillas al piso. " + "\"No\", te decís casi gritando.", "recuerdo3");
        this.narracion.agregarMensaje("recuerdo5", "Esa no será la última vez, no puede, no lo vas a permitir. Vas a hacer todo lo posible para evitarlo. " + "Con mucho esfuerzo te levantás, respiras hondo y salís a la calle.", "recuerdo4");
        
    }
    
    private void cargarMensjesObjetos_Casa3() {
        this.narracion.agregarMensaje("casa3","La casa de Susana era una de las más lindas. Su madre siempre arreglaba el jardín de la entrada con muchas flores, casi siempre con gran variedad de colores.");  
        this.narracion.agregarMensaje("casa31","Susana fue ese gran amor imposible. Hasta no hace mucho te seguías preguntando \"¿Y si... le hubiera dicho... hubiera ido... tantos miles de '¿y si...?'\"","casa3");
        this.narracion.agregarMensaje("casa32","Se fue un día al norte, a estudiar el efecto de las mineras en el medio ambiente. Pero su casa ya no era su casa, ahora pertenecía a las malas yerbas y a las enamoradas del muro.","casa31");
        this.narracion.agregarMensaje("casa33", "Entrás en la casa de Susana, el interior de la casa parece más un jardin que una casa. También parece abandonada. Revisás muy por arriba, aunque la verdad no tenés ganas de encontrar más sorpresas. Revisás los dormitorios, cajoneras pero no encontrás nada. Al salir de uno de los dormitorios te chocás un espejo de pared, y cae al piso. Se rompe en varios pedazos. Por suerte no te lastimás, una buena.", "casa32");
        this.narracion.agregarMensaje("casa34", "Mientras corrés a un lado el desastre que hiciste ves en el marco del espejo unas letras. " + "Dice: \"Todo está allí, aún hay tiempo 1\". " + "¿Quién escribió eso o cuándo lo hizo? Son detalles que tal vez nunca lograrás saber. " + "Buscás con la mirada pero no ves nada que llame tu atención. " + "Salís a la calle. Desde acá podes IR AL BOSQUE, INGRESAR AL BAR o INGRESAR CASA 2.", "casa33");
        this.narracion.agregarObjetoXAMensaje("casa34", "candadoValor4", "candado valor 4", Boolean.TRUE, 1, null);

        this.narracion.agregarMensaje("estado2_casa3", "Entras a la casa revisas en algunos lugares que no revisaste antes, como no encuentras nada vuelves a salir");
        this.narracion.agregarMensajeTexto("estado2_casa3", "Entras a la casa, vuelves a revisar en los lugares que ya revisaste antes por las dudas. Como no encuentras nada vuelves a salir");
        this.narracion.agregarMensajeTexto("estado2_casa3", "Entras a la casa pero sales casi inmediatamente, la casa ya la revisaste y te parece mejor buscar en otro lugar.");
        
        
    }
    
    private void cargarMensjesObjetos_Bosque() {
        this.narracion.agregarMensaje("bosque", "Recordás muy bien este bosque. Acá jugabas con tus amigos cuando eran chicos, ya de muy chiquitos se metían al bosque a esconderse y los grandes se volvían locos porque tenían miedo de que los muerda alguna vívora o se crucen con algún animal más grade. No es un bosque asi de inmenso ni demasiado denso, pero sí lo suficientemente grande como para tardar dos días en llegar al otro lado.");
        this.narracion.agregarMensaje("bosque1", "Ahora está totalmente cambiado, tiene mucha más vegetacion, las plantas se comieron los caminos. Algo te dice que tenés que ir hasta el otro lado del bosque. Allá hay un lago al final del camino. Doña Aurora solía pasar algunos días allá.", "bosque");
        this.narracion.agregarMensaje("bosque2", "Es un viaje largo pero tal vez sea menos ya que cuando uno es chico las distancias parecen mayores. Mientras caminas pensas en todo lo que viste y encontraste, en todas las personas que vivian en el pueblo. No logras entender commo paso todo esto. Pensas, ¿por qué? ¿para qué? ¿ quién fue?","bosque1");
        this.narracion.agregarMensaje("bosque3","En principio pareciera que esta empresa, Aceites del Sud SA, desea compar los terrenos del pueblo, incluso el pueblo. Es seguro que las personas se negaron a vender. Teniendo en cuenta las notas que encontré en el bar, los amenazaron y algunos se fueron, y otros terminaron como Don Mateo. Pero ¿cómo demuestro que estos tipos acosaron, persiguieron, mataron, y mucho más?. Las notas con garabatos raros me recuerdan a Doña Aurora.", "bosque2");
        this.narracion.agregarMensaje("bosque4", "Tal vez, si busco en el lago pueda encontrar algo. Después de caminar durante algunas horas, se asoma a lo lejos el lago, el cual tapa varios arboles. Se ve mucho más grande de lo que recordás. "+"Rodeás el lago. " + "Caminás un buen rato por la orilla y ves algo a lo lejos. Parece una cabaña. " + "Te entusiasmás, \"Seguro que ahí encuentro algo\".", "bosque3" );
        this.narracion.agregarMensaje("bosque5", "Llegás a la cabaña. Las ventanas están tapiadas. Intentás abrirlas pero es imposible hacerlo sólo con las manos. La puerta está cerrada y asegurada con un gran candado de clave numérica. Hacen falta cuatro números para poder abrirlo.", "bosque4");
        this.narracion.agregarObjetoXAMensaje("bosque5","controlCandado","controlCandado",true,null,null);
        
        this.narracion.agregarMensaje("bosquecontrolCandado", "Llegás a la cabaña. Las ventanas están tapiadas. Intentás abrirlas pero es imposible hacerlo sólo con las manos. La puerta está cerrada y asegurada con un gran candado de clave numérica. Hacen falta cuatro números para poder abrirlo.");
        this.narracion.agregarMensaje("bosqueConDatosCandadoCompleto","Pensás en todo lo que viste desde que llegaste al pueblo, y deducís que todos esos números que encontraste en los distintos lugares son los que abrirán el candado. Solo te falta colocarlos en el orden correcto. Son 4 números, es cuestión de probar todas las combinaciones.");
        this.narracion.agregarObjetoXAMensaje("bosqueConDatosCandadoCompleto","bosqueConDatosCandadoCompleto","bosqueConDatosCandadoCompleto",true,null,null);
        this.narracion.agregarMensaje("bosqueConDatosCandadoInCompleto","Pensás en todo lo que viste desde que llegaste al pueblo, y deducís que en el pueblo hay pistas de los números para abrir el candado. Sólo te queda decidir dónde buscar. Podés IR A CALLE PRINCIPAL o IR AL BAR.");
        //this.narracion.agregarMensaje("bosqueSINDatosCandado","Encontraste todos los números, entrás a la cabaña.");
       
        
        this.narracion.agregarMensaje("estado2_bosque", "Caminas por el bosque, te diriges rapidamente a la cabana");
        this.narracion.agregarObjetoXAMensaje("estado2_bosque","estado2_controlCandado","estado2_controlCandado",true,null,null);

        this.narracion.agregarMensaje("estado2_bosquecontrolCandado", "Caminas por el bosque, te diriges rapidamente a la cabana");
        this.narracion.agregarMensajeTexto("estado2_bosquecontrolCandado", "Caminas un rato por el bosque y llegas a la cabana"); 
        this.narracion.agregarMensajeTexto("estado2_bosquecontrolCandado", "Caminas rapidamente por el bosque para llegar lo antes posible a la cabana. Finalmente llegas a la cabana"); 
        this.narracion.agregarMensaje("estado2_bosqueConDatosCandadoCompleto","Estas frente al candado. Piensas un poco y recuerdas todos los numeros que encotraste. Pruebas varias combinaciones...");
        this.narracion.agregarObjetoXAMensaje("estado2_bosqueConDatosCandadoCompleto","bosqueConDatosCandadoCompleto","bosqueConDatosCandadoCompleto",true,null,null);
        this.narracion.agregarMensaje("estado2_bosqueConDatosCandadoInCompleto","Estas frente al candado pero como no tenes todos los numeros para abrir el mismo decides volver a buscar mas pistas. Podes: IR A CALLE PRINCIPAL, IR AL BAR");
        
        

    }
    
    private void cargarMensjesObjetos_Cabana() {
        this.narracion.agregarMensaje("cabana", "Muy bien, pudiste abrir el candado.");
        this.narracion.agregarObjetoXAMensaje("cabana","cabana","cabana",true,null,null);
        this.narracion.agregarMensaje("cabana1","Resulta que la cabaña pertenece a Doña Aurora. Hay varias fotos de ella, todo parece estar en su lugar, con mucho polvo pero ordenado. Revisás todo pero no hay nada llamativo. " + "Corrés algunos muebles pero nada. Te sentás en la cama para descansar un momento. "+ "\"Faltó mover la cama\", pensás. Debajo de la cama hay unas maderas algo flojas. Tironeás de una de ellas, hacés palanca, fuerza y más fuerza, y conseguís romperla. " + "Encontrás una caja, adentro tiene una bolsa llena de papeles.", "cabana" );
        this.narracion.agregarMensaje("cabana2", "Entre los papeles hay notas amenazando a distintas personas, cartas de la empresa Aceites del Sud SA intimando a vender. Fotos de personas que no son del pueblo agrediendo a algunas personas del pueblo. Incluso hay una foto de una persona de traje hablando con los agresores, al dorso dice \"Aquí se puede ver a José Acuña, director de Aceites del Sud SA, hablando con sus matones\". " + "Y como si eso fuera poco, en el fondo de la caja y dentro de una bolsa hay un cuchillo con sangre. Seguramente es el arma que mató a Don Mateo.", "cabana1");
        this.narracion.agregarMensaje("cabana3", "Éstas son pruebas suficientes como para meter en la cárcel a todos los culpables de esta masacre. " + "Pensás, \"No se va a salvar ninguno\". " + "Agarrás todo y volvés corriendo para presentar todo ante la justicia.\n\nF I N", "cabana2" );
        this.narracion.agregarObjetoXAMensaje("cabana3","finhistoria","finhistoria",true,null,null);

    }
    
    private void cargarMensjesObjetos_CallePrincipal() {
        this.narracion.agregarMensaje("callePrincipal", "Es la calle principal del pueblo, hay plantas que crecen desparramadas en la calle, la vereda, hay grandes charcos, papeles y basura por todos lados.", "recuerdo5");
        this.narracion.agregarMensaje("callePrincipal1", "A unos metros hay un poste de luz con un cartel pegado. Te acercás para ver lo que dice. Está viejo con algunas letras gastadas.", "callePrincipal");
        this.narracion.agregarMensaje("callePrincipal2","Es un pedazo de plástico que dice: " + "\"Venda su propiedad a TIEMPO, no espere a que se desvalorice\". " + "Más abajo hay un logo, pero está gastado y no llegás a entenderlo."  ,"callePrincipal1");
        this.narracion.agregarMensaje("callePrincipal3", "Otra vez la sensación de que esto lleva varios años asi. Entre tanto desorden y descuido notás un grafitti, es uno de los garabatos raros del diario de Aurora. Te acercás, tras mirarlo un rato te da la impresión de que el garabato no esta bien pintado. Justo debajo hay una baldosa de la cual crece pasto a sus cuatro lados, es raro.","callePrincipal2");
        this.narracion.agregarMensaje("callePrincipal4", "Arrancás el pasto. Sorprendentemente la baldosa sale con gran facilidad. Debajo hay una bolsa con hojas, son más garabatos. Parece que alguien dejó pistas, pero ¿qué quieren decir estas pistas? ¿Qué significan los garabatos?","callePrincipal3"); 
        this.narracion.agregarMensaje("callePrincipal5", "En unas de las hojas, entre tantos garabatos sobresale uno, lo recordás bien. Es el que representa el número 5. Esto tiene que ser una pista. Desde acá podés INGRESAR a 3 casas. ¿A cuál deseas INGRESAR?","callePrincipal4");
        this.narracion.agregarObjetoXAMensaje("callePrincipal5","candadoValor2","candadoValor2",true,5,null);
        
        this.narracion.agregarMensaje("estado2_callePrincipal", "Estas en la calle principal. Que queres hacer?");
    }
    
    private void agregarContinuacionDeBifurcaciones() {
        this.narracion.setProximoMensajeDeMensajeEnLista("estado2_men1", "callePrincipal");
        this.narracion.setProximoMensajeDeMensajeEnLista("estado2_casa1a", "callePrincipal");
        this.narracion.setProximoMensajeDeMensajeEnLista("estado2_casa2", "callePrincipal");
        this.narracion.setProximoMensajeDeMensajeEnLista("estado2_casa3", "callePrincipal");
        
        this.narracion.setProximoMensajeDeMensajeEnLista("bosqueConDatosCandadoCompleto", "cabana");
        this.narracion.setProximoMensajeDeMensajeEnLista("estado2_bosqueConDatosCandadoCompleto", "cabana");
    }
    
    private void agregarComandos() {
        this.narracion.agregarComandoAMensaje("intro_manejasAut11", "doblarDerecha", "DOBLAR DERECHA", "intro_manejasCaminoPueblo");
        this.narracion.agregarComandoAMensaje("intro_manejasAut11", "seguirAdelante", "SEGUIR ADELANTE", "intro_manejasAut17");
        
        this.narracion.agregarComandoAMensaje("intro_manejasAut18", "entrarAlCafe", "ENTRAR AL CAFE","intro_entrarCafe" );
        this.narracion.agregarComandoAMensaje("intro_entrarCafe3", "pedirIndicacion", "PEDIR INDICACION", "intro_pedirInformacion");
        this.narracion.agregarComandoAMensaje("intro_entrarCafe3", "pagar", "PAGAR", "intro_pedirInformacionAgradecer3");
        this.narracion.agregarComandoAMensaje("intro_manejasAut18", "volverAlCruce", "VOLVER AL CRUCE", "intro_manejasAut12");
        this.narracion.agregarComandoAMensaje("intro_pedirInformacion6", "responder", "RESPONDER", "intro_pedirInformacionResponder");
        this.narracion.agregarComandoAMensaje("intro_pedirInformacion6", "agradecer", "AGRADECER", "intro_pedirInformacionAgradecer");
        
        this.narracion.agregarComandoAMensaje("intro_manejasAut20", "doblarIzquierda", "DOBLAR IZQUIERDA", "intro_manejasCaminoPueblo");
        this.narracion.agregarComandoAMensaje("intro_manejasAut20", "doblarDerecha", "DOBLAR DERECHA", "intro_manejasAut12");
        this.narracion.agregarComandoAMensaje("intro_manejasAut12", "doblarIzquierda", "DOBLAR IZQUIERDA", "intro_manejasCaminoPueblo");
        this.narracion.agregarComandoAMensaje("intro_manejasCaminoPueblo7", "si", "SI", "intro_manejasCaminoPueblo8");
        this.narracion.agregarComandoAMensaje("intro_manejasCaminoPueblo7", "no", "NO", "intro_manejasCaminoPueblo9");
        
        
        //
        this.narracion.agregarComandoAMensaje("casa1h", "seguirAdelante", "INGRESAR CASA VIEJA","casa2");
        this.narracion.agregarComandoAMensaje("casa1h", "irCallePrincipal", "IR A CALLE PRINCIPAL","callePrincipal");
        
        this.narracion.agregarComandoAMensaje("recuerdo6", "seguirAdelante", "INGRESAR CASA FANTASMAL","casa1a");
        this.narracion.agregarComandoAMensaje("recuerdo6", "irCallePrincipal", "IR A CALLE PRINCIPAL","callePrincipal");
        
        this.narracion.agregarComandoAMensaje("casa2f", "si", "SI", "casa2g");
        this.narracion.agregarComandoAMensaje("casa2f", "no", "no", "casa2i");
        
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "doblarDerecha", "INGRESAR CASA VIEJA", "casa2");
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "seguirAdelante", "IR A CALLE PRINCIPAL", "callePrincipal");
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "caminarIzquierda", "INGRESAR CASA FANTASMAL", "casa1a");
        
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa1", "INGRESAR CASA FANTASMAL", "casa1a");
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa2", "INGRESAR CASA VIEJA", "casa2");
        this.narracion.agregarComandoAMensaje("callePrincipal5","ingresarcasa3", "INGRESAR CASA COLONIAL", "casa3");

        this.narracion.agregarComandoAMensaje("casa34","iralbosuqe", "IR AL BOSQUE", "bosque");
        this.narracion.agregarComandoAMensaje("casa34","ingresaralbar", "INGRESAR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("casa34","ingresarcasa2", "INGRESAR CASA VIEJA", "casa2");

        this.narracion.agregarComandoAMensaje("men7", "iralbosuqe", "IR AL BOSQUE", "bosque");
        //this.narracion.agregarComandoAMensaje("men7","ingresaralbar", "INGRESAR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("men7","irCallePrincipal", "IR A CALLE PRINCIPAL", "callePrincipal");

        this.narracion.agregarComandoAMensaje("bosqueConDatosCandadoInCompleto","irBar", "IR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("bosqueConDatosCandadoInCompleto","ircalleprincipal", "IR A CALLE PRINCIPAL", "callePrincipal");
        
        
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","iralbosuqe", "IR AL BOSQUE", "bosque");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresaralbar", "INGRESAR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa1", "INGRESAR CASA FANTASMAL", "casa1a");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa2", "INGRESAR CASA VIEJA", "casa2");
        this.narracion.agregarComandoAMensaje("estado2_callePrincipal","ingresarcasa3", "INGRESAR CASA COLONIAL", "casa3");
        
        this.narracion.agregarComandoAMensaje("estado2_bosqueConDatosCandadoInCompleto","irBar", "IR AL BAR", "men1");
        this.narracion.agregarComandoAMensaje("estado2_bosqueConDatosCandadoInCompleto","ircalleprincipal", "IR A CALLE PRINCIPAL", "callePrincipal");
        

    }
    
    public void cargarHistoriaPorDefecto() {
        
        this.narracion = new NarracionLogica();
        cargarMensjesObjetos_Bar();
        cargarMensjesObjetos_Introduccion();
        cargarMensjesObjetos_Casa1();
        cargarMensjesObjetos_Casa2();
        cargarMensjesObjetos_Casa3();
        cargarMensjesObjetos_Bosque();
        cargarMensjesObjetos_Cabana();
        cargarMensjesObjetos_CallePrincipal();
        agregarContinuacionDeBifurcaciones();
        agregarComandos();

        
        //se establece cual es el principio de la historia... e informacion basica para la base de dato
        this.narracion.setProximoMensajeDeNarracion("intro_manejasAuto");//menu1 //intro_manejasAuto//intro_callePueblo6
        
        this.codigoMensajeInicio = "intro_manejasAuto";
        this.idIdioma = 1;
        this.titulo = "primer historia...";
        this.descripcion = "un hombre encuentra el pueblo donde nacio abandonado...";
        //
    }
    
    public void setProximoMensajeDeNarracion(String codigoMenaje) {
        this.narracion.setProximoMensajeDeNarracion(codigoMenaje);
    }
    
    public void setProximoMensajeDeMensajeEnLista(String codigoMensaje, String codigoMensajeSiguiente) {
        this.narracion.setProximoMensajeDeMensajeEnLista(codigoMensaje, codigoMensajeSiguiente);
    }
}
