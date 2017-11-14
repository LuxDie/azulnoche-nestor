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
    
    public void cargarHistoriaPorDefecto() {
        this.narracion = new NarracionLogica();
        this.narracion.agregarMensaje("men1", "El interior del bar estaba un poco oscuro...");
        this.narracion.agregarMensaje("men2", "...A pesar de la oscuridad se puede observar una barra y una puerta con un cartel que dice Baño. El piso esta muy pegajoso, hay varios vasos y botellas tiradas en el suelo", "men1");
        this.narracion.agregarMensaje("men3", "En la barra hay una copa y dos botellas, una de agua y otra de cerveza. Misticamente la cerveza esta sin abrir. Te vendria muy bien una, pero aquella esta caliente y seguramente vencida ", "men2");
        this.narracion.agregarMensaje("men4", "El baño es un verdadero asco..., nadie en su sano juicio lo utilizaria para hacer sus necesidades pero parece que tambien servia como deposito porque se ve en una esquina varias cajas con papeles.", "men3");
        this.narracion.agregarMensaje("men5", "Vas hasta la pila de cajas, estan llenas de vevidas espirituosas, todas calientes. Revisas los papeles, En su mayoria son facturas de proveedores, alunas cartas de bancos. Una hoja que solo tiene el numero 9 bien grande. Hay Muchas planillas contables y alugunas notas, escritas a mano.", "men4");
        this.narracion.agregarMensaje("men6","“Vendé, sabes que te conviene” dice una. Otra presagia : “ Si te quedas vas a terminar igual que la de las plantas”. \n" +
                                        "“ Vende o andate” dice otra. La que más llamativa es una que dice: “ Vendes o moris”.", "men5");
        this.narracion.agregarMensaje("men7", "También hay una carta escrita a máquina en una hoja con el membrete de una empresa, Aceites del Sud SA, la carta es un propuesta para comprar la propiedad donde está ubicado el Bar. Es mejor guardar todas estas notas, podrían servir como evidencia.\n" +
                                        "Revisas un poco más el lugar pero es en vano literalmente está todo dado vuelta.\n" +
                                         "Salis del Bar, al mirar la calle pensas ¿a qué otro lugar debería ir?", "men6");
             
        
        this.narracion.agregarObjetoXAMensaje("men6", "candadoValor1", "  ", Boolean.TRUE, null, null);
        
       

        //se agregan comandos a los mensajes para que el jugador pueda decidir que es lo que quiere hacer...
       
            

        //
        //SE CARGA EL RESTO DE LA HISTORIA JUNTO CON LA LOGICA...
        this.narracion.agregarMensaje("intro_manejasAuto", "Manejas tu viejo Renault 9 color azul noche, como siempre con la radio apagada, nada se iguala a sentir el viento pasando por la ventana. El sonido del motor, el caucho girando sobre el asfalto y manejar en camino de piedra es uufff, simplemente sublime.");
        this.narracion.agregarMensaje("intro_manejasAut2", "Mañana cumples seis años de servicio. Acaricias el volante, tiene un forro de cuero, ya gastado, mirás el torpedo. También cumplimos seis años juntos", "intro_manejasAuto");
        this.narracion.agregarMensaje("intro_manejasAut3", "Aunque cuando se conocieron él lucía un bordo gastado y sin brillo, el motor sonaba parejo pero muy fuerte debido al mal estado del escape. Con el tiempo y los viajes, obtuvo nuevas llantas, con estilo. Pasaron por varios colores hasta encontrar el hermoso azul noche. A los dos les queda muy bien el azul ", "intro_manejasAut2");
        this.narracion.agregarMensaje("intro_manejasAut4", "Hace un buen tiempo que manejas por una ruta algo olvidada, con más pozos que carteles.", "intro_manejasAut3");
        this.narracion.agregarMensaje("intro_manejasAut5", "Pasaron muchos años desde que no visitaste estos parajes. Ya no recordas bien el camino y se aproxima un cruce, el tema es...", "intro_manejasAut4");
        this.narracion.agregarMensaje("intro_manejasAut6", "¿había con salirse de la ruta en este cruce o en el próximo? ¿había que ir a la izquierda o a la derecha?", "intro_manejasAut5");
        this.narracion.agregarMensaje("intro_manejasAut11", "¿Qué hacemos amigo, para donde agarramos?- Le preguntas al viejo Renault 9 azul noche. Es una vieja costumbre que practicas desde que se conocieron. \n" + "Podes, DOBLAR DERECHA o SEGUIR ADELANTE", "intro_manejasAut6");


        this.narracion.agregarMensaje("intro_manejasAut17", "Después de más de una hora de viaje llegas a un pueblo, es evidente que te pasaste el cruce donde debías salir de la ruta. Igualmente paras en un café al costado de la ruta.");
        this.narracion.agregarMensaje("intro_manejasAut18", "Que queres hacer? Podes VOLVER AL CRUCE, o podes ENTRAR AL CAFE, te vendria bien estirar un poco las piernas y tal vez consigas una buena indicación.", "intro_manejasAut17");
        
        this.narracion.agregarMensaje("intro_entrarCafe", "Entras, es un lugar con decoración algo vieja pero agradable, te sientas en la barra. La camarera te saluda con amabilidad, su sonrisa es pura energía.");
        this.narracion.agregarMensaje("intro_entrarCafe2", "Le pides a la camarera un café bien cargado, te sonríe otra vez y enseguida te sirve de la jarra. Le ponés dos de azúcar, tras un par de sorbos te preguntas, la camarera ¿podrá darme alguna indicación sobre el cruce que busco?", "intro_entrarCafe");
        this.narracion.agregarMensaje("intro_entrarCafe3", "Piensas un poco, podes PEDIR INDICACION sobre el cruce o solo PAGAR e irte", "intro_entrarCafe2");
        
        
        this.narracion.agregarMensaje("intro_pedirInformacion", "Tomás otro poco de café y le preguntas si conoce el cruce que buscas.");
        this.narracion.agregarMensaje("intro_pedirInformacion2", "Se le va la sonrisa de la cara", "intro_pedirInformacion");
        this.narracion.agregarMensaje("intro_pedirInformacion3", "-ah, ese lugar buscabas, debí imaginarlo- Frunce el ceño- Está más atrás, te pasaste varios kilómetros¿ no lo viste?-", "intro_pedirInformacion2");
        this.narracion.agregarMensaje("intro_pedirInformacion4", "-No la verdad que no- Le respondes intrigado por el gesto de la camarera", "intro_pedirInformacion3");
        this.narracion.agregarMensaje("intro_pedirInformacion5", "-Si vas para alla te aconsejo que estés más despierto y prestes más atención. No es de mi incumbencia pero¿ a qué va?-", "intro_pedirInformacion4");
        this.narracion.agregarMensaje("intro_pedirInformacion6", "Le podrias RESPONDER para ver que te dice, aunque tampoco es mala idea AGRADECER e irte.", "intro_pedirInformacion5");
        
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer", "Solo voy de visita - Le decis mientras haces tiempo esperando una respuesta.");
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer2", "- No sé a quién irá a ver, hace rato que no se sabe nada de alla. Pero insisto en sea precavido, la gente comenta solo cosas raras de aquel lugar- Señala hacia afuera con la vista levantando las cejas.", "intro_pedirInformacionAgradecer");
        this.narracion.agregarMensaje("intro_pedirInformacionAgradecer3", "Pagas, le das las gracias y volver a tu viejo Renault 9 azul noche.", "intro_pedirInformacionAgradecer2");
        
        this.narracion.agregarMensaje("intro_pedirInformacionResponder", "- Estoy buscando a un familiar que hace mucho tiempo que no se comunica conmigo - Le decis mientras haces tiempo esperando una respuesta");
        this.narracion.agregarMensaje("intro_pedirInformacionResponder2", "- Hace rato que no se sabe nada de alla. Pero insisto en sea precavido, la gente comenta solo cosas raras de aquel lugar- Señala hacia afuera con la vista levantando las cejas.", "intro_pedirInformacionResponder");
        
        //para el comando PAGAR solo se debe de setear el proximo mensaje...
        
        this.narracion.agregarMensaje("intro_manejasAut19", "Volves por el mismo camino por el que llegaste, después de un buen viaje llegas al cruce.");
        this.narracion.agregarMensaje("intro_manejasAut20", "Que queres hacer? Podes DOBLAR IZQUIERDA o DOBLAR DERECHA", "intro_manejasAut19");
        
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_pedirInformacionResponder2", "intro_pedirInformacionAgradecer3");
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_pedirInformacionAgradecer3", "intro_manejasAut19");
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo", "Es un camino de grava. Pensas es el camino ideal, es una buena señal nada malo puede venir de un camino de piedras.");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo2", "-O no?- Le preguntas en voz alta a tu compañero, el viejo Renault 9 azul marino, mirando el reloj que indica que tenes medio tanque de nafta.", "intro_manejasCaminoPueblo");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo3", "El camino de grava es largo, no da indicios de llevar a algún lado, no hay carteles ni marcas en el camino que puedan indicar que alguien paso por alli. Al menos recientemente.", "intro_manejasCaminoPueblo2");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo4", "Tampoco hay cercos a los lados del camino, solo tierra, pasto y algunas yerbas malas decoran el paisaje.", "intro_manejasCaminoPueblo3");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo5", "Con varios kilómetros de aquel camino tras la espalda llegas a un cartel, es chico, de madera. Esta colgando de uno de sus lados, desde el auto podes leer:", "intro_manejasCaminoPueblo4");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo6", "Bienvenido a L...", "intro_manejasCaminoPueblo5");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo7", "Falta un pedazo pero no está a la vista. Deseas bajar del auto y buscar un poco más? Podes responder SI o NO", "intro_manejasCaminoPueblo6");
        
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo8", "Buscas un poco pero no hay muchos lugares donde mirar y terminas sin encontrar nada. subis al auto.");
        
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo9", "Seguis camino, del lado izquierdo hay una casa, al menos eso parece ya que está bastante lejos.");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo10", "Desde el viejo Renault 9 azul noche, se puede ver que la casa está casi tapada por el agua.", "intro_manejasCaminoPueblo9");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo11", "Más adelante se ven varias edificaciones, podría ser todo un pueblo.", "intro_manejasCaminoPueblo10");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo12", "Mejor dicho... Podría ser el pueblo que abandonaste hace tantos años?", "intro_manejasCaminoPueblo11");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo13", "Por fortuna el camino esta bastante mas alto que el nivel del agua.", "intro_manejasCaminoPueblo12");
        this.narracion.agregarMensaje("intro_manejasCaminoPueblo14", "Casi dos kilómetros después termina el camino de piedras en lo que bien podría ser la calle central del pueblo.", "intro_manejasCaminoPueblo13");
        this.narracion.agregarMensaje("intro_callePueblo", "La calle está asfaltada, muy húmeda, hay charcos de distintos tamaños dispersos por todos lados.", "intro_manejasCaminoPueblo14");
        this.narracion.agregarMensaje("intro_callePueblo2", "Desde aca podes ver algunas de las casas y el bar. El resto parece estar inundado.", "intro_callePueblo");
        this.narracion.agregarMensaje("intro_callePueblo3", "El pueblo, tu pueblo cambió demasiado. Cuando lo dejaste era un lugar alegre, donde todos trabajaban mucho y colaboraban con cualquiera que necesitara una mano. Siempre estaban dispuestos a hacer cualquier cosa que beneficiara al pueblo y a sus habitantes.", "intro_callePueblo2");
        this.narracion.agregarMensaje("intro_callePueblo4", "Pero eso fue hace mucho tiempo.", "intro_callePueblo3");
        this.narracion.agregarMensaje("intro_callePueblo5", "A primera vista parece no haber nadie. No escuchas ruido alguno. No sale música del bar como sucedió siempre. Ni los pájaros cantan.", "intro_callePueblo4");
        this.narracion.agregarMensaje("intro_callePueblo6", "A tu izquierda hay una casa, a la derecha hay otra casa y frente a vos la calle principal.", "intro_callePueblo5");
        this.narracion.agregarMensaje("intro_callePueblo7", "qué deseas hacer? Podes CAMINAR IZQUIERDA, CAMINAR DERECHA, CAMINAR ADELANTE o ENTRAR AL BAR", "intro_callePueblo6");
       
                
            
        this.narracion.agregarMensaje("casa1a", "Entreas a la casa a tu izquierda. Se ve vieja, humeda y descuidada. Buscas por todos lados pero  lo único que llama tu atención es un viejo cuaderno. Es un diario íntimo. Pertenece a Doña Aurora." );
        this.narracion.agregarMensaje("casa1b", "Cuando dejaste el pueblo ya era una señora grande, algo desequilibrada. Tenía por costumbre regalar higos pero para que fueran gratis debias pedirlo en la lengua que hablaba su abuelo.", "casa1a");
        this.narracion.agregarMensaje("casa1c", " Ya no te acordas que idioma hablaba aquel hombre, que claro está usabas para pedir higos gratis todos los días. Doña Aurora te escuchaba y gustosa te regalaba dos o tres porque lo hablabas muy bien.", "casa1b");
        this.narracion.agregarMensaje("casa1d", "El diario íntimo tiene las hojas del medio arrancadas. Las primeras páginas hablan de cosas sin importancia. Y las ultimas estan llenas de garabatos. Da la impresión que Doña Aurora se terminó de volver loca. O no? Acaso podría ser una pista escondida?.","casa1c" );
        this.narracion.agregarMensaje("casa1e", "La vieja se hizo pasar por loca y escribió todo en ese idioma casi muerto?\n" + "Miras de nuevo el diario, y siguen pareciendo garabatos. Aunque algunos dibujos podrían ser letras o Numeros.", "casa1d");        
        this.narracion.agregarMensaje("casa1f","Mirando el diario con mayor atencion vez, debajo de todos los garabatos, un garabato mucho mas grande. Lo reconoces es el equivalente al numero 3. Es otra cosa para averiguar." , "casa1e" );        
        this.narracion.agregarMensaje("casa1g", "salis de la casa de Aurora. La verdad todo se ve igual, avandonado, absolutamente todo esta humedo, icluso con moho.", "casa1f");
        this.narracion.agregarMensaje("casa1h", "Donde ir ahora? una buena pregunta. Podes CAMINAR ADELANTE y entrar a la otra casa, DOBLAR IZQUIERDA e ir a la calle princial", "casa1g");

        this.narracion.agregarMensaje("casa2", "Entras a la casa. Esta todo desordenado, cubierto de polvo. Hay un olor muy fuerte, es una pésima señal, una vez que se huele ese hedor nunca más se olvida.", "casa1h");
        this.narracion.agregarMensaje("casa2a", "\n En el living hay un sillón de tres cuerpos que parece no estar en el sitio que le corresponde. Detras del sillon parece haber un vulto.", "casa2");
        this.narracion.agregarMensaje("casa2b", "Te acercas un poco y enseguida te das cuenta de que es un cuerpo.\n" + "Te acercas más y es notorio que esa persona lleva bastante tiempo muerta. Lo reconoces fácilmente. Es Don Mateo.", "casa2a");
        this.narracion.agregarMensaje("casa2c", "En la mitad de la espalda hay una gran mancha de sangre. Fue atacado por la espalda. con un cuchillo grande o bien un machete.","casa2b" );
        this.narracion.agregarMensaje("casa2d", "El pecho se te comprime, casi de golpe. Don Mateo era un Hombre con mucha historia, era dueño de un inmenso y muy particular humor serio, como él solía decir. ", "casa2c");
        this.narracion.agregarMensaje("casa2e", "El arma que se llevó su vida no está. Al menos en esa casa, eso es obvio. \n" + "La sangre en el suelo se secó hace rato ya. Solo queda la marca en el suelo de madera. ", "casa2d");
        this.narracion.agregarMensaje("casa2f", "El cadáver tiene su mano derecha en un bolsillo. ¿Queres sacarle la mano del bolsillo? SI o NO ", "casa2e");
        this.narracion.agregarMensaje("casa2g", "Sacas la mano del cadáver del bolsillo. Aun agarra con fuerza unos papeles. Los sacas de la mano. \n" + "Es un boleto de compraventa, está roto en dos pedazos.", "casa2f");
        this.narracion.agregarMensaje("casa2h","Lo revisas un rato, una empresa quería comprarle la casa a Don Mateo, “Aceites del Sud SA”.\n" + "El nombre de la empresa no te suena para nada. Pero te preguntas ¿Porque  dejaron el cuerpo ahí?¿porque no lo enterraron? ", "casa2g");
        this.narracion.agregarMensaje("casa2i", "¿Quién lo mató?¿Tanta impunidad tiene que es capaz de dejar el cuerpo ahí tirado, cuando es notorio que se trata de un asesinato?", "casa2h"); 
        this.narracion.agregarMensaje("casa2j", "Revisas un poco más la casa pero no encontraba nada que de algún indicio de lo que sucedió.\n" + "Salís de la casa con una mezcla de sentimientos, tristeza, bronca, indignación pero por sobre todas ellas, el deseo. El deseo de agarrar al culpable y hacerlo pagar.","casa2i");
        this.narracion.agregarMensaje("casa2k", "Te preguntas ¿acaso voy a encontrar a todo el pueblo muerto en sus casas? ", "casa2j");
        
        this.narracion.agregarMensaje("recuerdo1","Un escalofrío recorre todo tu cuerpo. De inmediato pensas en tus papás y en Enrique, tu hermano. La última vez que lo viste solo tenía dos años y vos 17.", "casa2k");        
        this.narracion.agregarMensaje("recuerdo2", "Tus padres se venían venir la charla. \n " + "Papá, mamá- les dirías mientras ellos te mirarían espectantes pero sabiendo muy bien cuáles serían tus próximas palabras. \n" + " Quiero estudiar en la capital- harías una pausa. \n " + " Para volver como un gran policía y de esa forma ayudar al pueblo-", "recuerdo1");
        this.narracion.agregarMensaje("recuerdo3", "Ellos llorarían y así lo hicieron. Te abrazarían como si fuese la última vez que te verían y así lo hicieron. \n "+" Una pregunta se te clava en el pecho, bien adentro.", "recuerdo2");
        this.narracion.agregarMensaje("recuerdo4", "¿será esa, aquella, la última vez que nos  viéramos? \n" + "¿será aquel abrazo el último ? \n" + "Caes de rodillas al piso. \n" + "No - te decís casi gritando.", "recuerdo3");
        this.narracion.agregarMensaje("recuerdo5", "Esa no será la última vez, no puede, no lo vas a permitir. Vas a hacer todo lo posible  para evitarlo. \n" + " Con mucho esfuerzo te levantas, respiras hondo y salis a la calle", "recuerdo4");
              
        this.narracion.agregarMensaje("callePrincipal", " Es la calle principal del pueblo, hay plantas que crecen desparramadas en la calle, la verda, hay grandes charcos, papeles y basura por todos lados.", "recuerdo5");
        this.narracion.agregarMensaje("callePrincipal1", "A unos metros hay 1 poste de luz con un cartel pegado. Te acercas para ver lo que dice. Esta viejo con algunas letras gastadas.", "callePrincipal1");
        this.narracion.agregarMensaje("callePrincipal2","Es un pedazo de plastico que dice: \n" + " Venda su propiedad a TIEMPO, no espere a que se desvalorice \n" + "\n" + "Mas abajo hay un logo pero esta gastado y no llegas a entenderlo"  ,"callePrincipal1");
        
        
        
        
        
        
        
        
        this.narracion.agregarMensaje("casa33", "Entras en la casa de Susana, el interior de la casa parece mas un jardin que una casa. También parece abandonada.  Revisas muy por arriba, la verdad no tenes ganas de encontrar más sorpresas. Revisas los dormitorios, cajoneras pero no encontras nada. Al salir de uno de los dormitorios te chocas un espejo de pared, este cae al piso, se rompe en varios pedazos. Por suerte no te lastimas, una buena", "casa32");
        this.narracion.agregarMensaje("casa34", "Mientras corres a un lado el desastre que hiciste ves en el marco del espejo unas letras.\n" + "Dice: “ Todo está allí, aún hay tiempo 1 ”.\n" + " Quien escribió eso o cuando lo hizo? son detalles que tal vez nunca lograras saber.", "casa33");
        
        this.narracion.agregarObjetoXAMensaje("casa34", "candadoValor4", "  ", Boolean.TRUE, null, null);
       
       this.narracion.agregarMensaje("bosque", "Recordas muy bien este bosque, aca jugabas con tus amigos cuando eran chicos, ya de muy chiquitos se metian al bosque a esconderse y los grnades se volvian locos porque tenian miedo de que los muerda alguna vivora o pero se crucen con algun animal mas grade. No es un bosque asi inmenso ni demasiado denso pero si lo suficientemente grande como para tardar dos dias en llegar al otro lado.");
       this.narracion.agregarMensaje("bosque1", "Ahora esta totalmente cambiado, tiene mucha mas vegetacion, las plantas se comieron los caminos. Algo te dice que tenes que ir hasta el otro lado del bosque. Alla hay un lago al final del bosque. Doña ahora solia pasar algunos dias alla.", "bosque");
       this.narracion.agregarMensaje("bosque2", "Es un viaje largo pero tal vez sea menos ya que cuando uno es chico las distancias parecen mayores. Mientras caminas pensas en todo lo que viste y encontraste, en todas las personas que vivian en el pueblo. No logras entender commo paso todo esto. Pensas, ¿por qué? ¿para qué? ¿ quié fue? ","bosque1");
       this.narracion.agregarMensaje("bosque3","En principio pareciera que esta empresa, Aceites del Sud  SA, desea compar los terrenos del pueblo, incluso el pueblo. Es seguro que las personas se negaron a vender. Teniendo en cuenta las notas que encontre el bar, los amenazaron y algunos sefueron otros terminaron como Don Mateo. Pero como demuestro que estos tipos acosaron, persiguieron, mataron y mucho mas. Las notas con garabatos raros me recuerdan a Doña Aurora", "bosque2");
       this.narracion.agregarMensaje("bosque4", "Tal vez, si busco en el lago pueda encontrar algo. Despues de caminar durante algunas horas, se asoma a lo lejos el lago, el cual tapa varios arboles. Se ve mucho mas grande de lo que recordas. \n"+" Rodeas el lago. \n2" + " Caminar un muy buen rato por la orilla y ves algo a lo lejos. Parece una cabaña. \n" + "Te entusiasmas, seguro que ahí encuentro algo ", "bosque3" );
       this.narracion.agregarMensaje("bosque5", " Llegas a la cabaña. Las ventanas estan tapiadas. Intetas abrirlas pero es impsible hacerlo solo con las manos. La puerta esta cerrada y asegurada con un gran candado de clave numerica. Hacen falta cuatro numeros para poder abrirlo.", "bosque4");
       
       this.narracion.agregarMensaje("cabana", "Muy bien pudiste abrir el candado");
       this.narracion.agregarMensaje("cabana1"," Resulta que la cabaña pertenece a Doña Aurora. Hay varias fotos de ella, todo parece estar en su lugar, con mucho polvo pero ordenado. Reviasas todo pero no hay nada llamativo. \n" + " Corres algunos muebles pero nada. Te sentas en la cama para descansar un momento. \n"+ " Faltó mover la cama pensas. Debajo de la cama hay unas maderas algo flojas. Tironeas de una de ellas, haces palanca, fuerza, mas fuerza y se rompe la madera. \n" + "Encontras una caja, adentro tiene una bolsa llena de papeles.", "cabana" );
       this.narracion.agregarMensaje("cabana2", "Entre los papeles hay notas amenazando a distintas personas, cartas de la empresa Aceites del Sud SA intimando a vender. Fotos de personas que no son del peblo agrediendo a algunas personas del pueblo. Incluso hay una foto de una persona de traje hablando con los agresores, al dorso dice \"Aqui se puede ver a Jose Acuña, director de Aceites del Sud SA hablando con sus matones \" \n" + " Y como si eso fuera poco en el fondo de la caja y dentro de una bolsa hay un cuchillo con sangre. Seguramente es el arma que mato a Don Mateo", "cabana1");
       this.narracion.agregarMensaje("cabana3", "Estas son pruebas sufiientes como para meter a la carcel a todos los culpables de esta masacre. \n" + " Pensas: No se va a salvar ninguno. \n" + " Agarras todo y volves corriendo para presentar todo ante la justicia ", "cabana4" );


       
        this.narracion.agregarComandoAMensaje("intro_entrarCafe3", "pedirIndicacion", "PEDIR INDICACION", "intro_pedirInformacion");
        this.narracion.agregarComandoAMensaje("intro_entrarCafe3", "pagar", "PAGAR", "intro_pedirInformacionAgradecer3");
        
        this.narracion.agregarComandoAMensaje("intro_pedirInformacion6", "responder", "RESPONDER", "intro_pedirInformacionResponder");
        this.narracion.agregarComandoAMensaje("intro_pedirInformacion6", "agradecer", "AGRADECER", "intro_pedirInformacionAgradecer");
        
        this.narracion.agregarComandoAMensaje("intro_manejasAut20", "doblarIzquierda", "DOBLAR IZQUIERDA", "intro_manejasCaminoPueblo");
        this.narracion.agregarComandoAMensaje("intro_manejasAut20", "doblarDerecha", "DOBLAR DERECHA", "intro_manejasAut12");
        
        this.narracion.agregarComandoAMensaje("intro_manejasCaminoPueblo7", "si", "SI", "intro_manejasCaminoPueblo8");
        this.narracion.agregarComandoAMensaje("intro_manejasCaminoPueblo7", "no", "NO", "intro_manejasCaminoPueblo9");
        
        this.narracion.setProximoMensajeDeMensajeEnLista("intro_manejasCaminoPueblo8", "intro_manejasCaminoPueblo9");
        
        
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "casa1", "CAMINAR IZQUIERDA", "casa1");
       
         // estos 3 comandos hay que revisarlo no se si estara bien.
        this.narracion.agregarComandoAMensaje("casa1h", "seguirAdelante", "CCAMINAR ADELANTE","casa2");
        this.narracion.agregarComandoAMensaje("casa2f", "si", "SI", "casa2g");
        this.narracion.agregarComandoAMensaje("casa2f", "no", "no", "casa2i");
        
        
        
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "doblarDerecha", "CAMINAR DERECHA", "cass2");
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "seguirAdelante", "CAMINAR ADELANTE", "callePrincipalSector1");
        this.narracion.agregarComandoAMensaje("intro_callePueblo7", "caminarAlBar", "ENTRAR AL BAR", "men1");
       
                  
        //**************
        
        
        
        
        
        //...
        //se establece cual es el principio de la historia...HAY QUE ANALIZAR ESTA LOGICA
        this.narracion.setProximoMensajeDeNarracion("intro_manejasAuto");//menu1 //intro_manejasAuto
        
        this.codigoMensajeInicio = "intro_manejasAuto";
        this.idIdioma = 1;
        this.titulo = "primer historia...";
        this.descripcion = "un hombre encuentra el pueblo donde nacio abandonado...";
        //
    }
    
    
}
