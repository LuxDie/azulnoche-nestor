/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicajuego;

import dato.ComandoDato;
import dato.HistoriaDato;
import dato.MensajeDato;
import dato.TextoComandoDato;
import dato.TextoMensajeDato;
import entidad.Comando;
import entidad.Historia;
import entidad.Mensaje;
import entidad.Tarea;
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
    
    public Tarea obtenerPrimerTareaPendiente() {
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
            this.narracion.agregarMensaje(mensajeLogica);
        }
        this.narracion.setProximoMensajeDeNarracion(codigoMensajeInicio);
    }
    
    public ArrayList<Historia> obtenerListaHistorias() {
        ArrayList<Historia> listaHistoria = new ArrayList<>();
        return listaHistoria;
    }
    
    public Boolean cargarHistoriaDesceScript(String archivo) {
        
        return leerScript(archivo);
    }
    
    public Boolean leerScript(String archivo) {
        NarracionLogica narracionLogica = new NarracionLogica();
        Boolean estado = false;
        if (AdministradorArchivo.archivoExiste(archivo)) {
            ArrayList<String> lineas = AdministradorArchivo.leerlineas(archivo);
            for(int i=0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                String[] parametros = linea.split("\t");
                String comando = parametros[0];
                if (comando.equals("agregarMensaje")) {
                    int cantidadParametros = parametros.length;
                    switch (cantidadParametros) {
                        case 3:
                            narracionLogica.agregarMensaje(parametros[1], parametros[2]);
                            estado = true;
                            break;
                        case 4:
                            narracionLogica.agregarMensaje(parametros[1], parametros[2], parametros[3]);
                            estado = true;
                            break;
                    }
                    
                }
            }
        }
        return estado;
    }
    ////////////
    
    public void cargarHistoriaPorDefecto() {
        this.narracion = new NarracionLogica();
        this.narracion.agregarMensaje("men1", "El interior del bar estaba un poco oscuro...");
        this.narracion.agregarMensaje("men2", "...A pesar de la oscuridad se puede observar una barra hacia el norte y una puerta con un cartel que dice Baño hacia el este. Que queres hacer? Podes caminar hacia el norte o el sur", "men1");
        this.narracion.agregarMensaje("men3", "En la barra hay una copa y dos botellas, una de agua y otra de cerveza. Que queres hacer? Podes beber algo o regresar a la entrada...");
        this.narracion.agregarMensaje("men4", "El baño es un verdadero asco..., nadie en su sano juicio lo utilizaria para hacer sus necesidades pero parece que tambien servia como deposito porque se ve en una esquina varias cajas con papeles. Que queres hacer? Podes caminar hacia el norte para revisar los papeles o regresar a la entrada...");
        this.narracion.agregarMensaje("men5", "El agua no esta fria pero te calma un poco la sed pero tambien te dan ganas de ir al baño...Te vas al Baño.");//falta setear cual es el mensaje siguiente...
        this.narracion.agregarMensaje("men6", "Bebes un poco de cerbeza pero como esta caliente te da asco y te dan ganas de vomitar...Te vas al Baño para vomitar.");//falta setear cual es el mensaje siguiente...
        this.narracion.agregarMensaje("acercarseNorteParaLeerPapeles", "Caminas hacia el norte...tu intuicion te dice que vas a encontrar alguna prueba. Que queres hacer? Podes leer los papeles o regresar a la entrada del Baño");
        this.narracion.agregarMensaje("lecturaPapeles", "Empiezas a leer los papeles...Despues de 15min encuentras una nota que dice: vecinoxxx queres robarte mis propiedades...");
        this.narracion.agregarTarea("lecturaPapeles", 1, "Encontrar nota con claves para traducir el lenguaje xxxx");
        this.narracion.agregarMensaje("conclusionLecturaPapeles", "Despues de leer esa nota llegas a la conclusion de que el asesino es xxxx. Solo te falta buscar las pruebas...", "lecturaPapeles");
        this.narracion.agregarMensaje("salirBarConSospechozo", "Encontraste un sospechoso, ahora a buscar las pruebas....", "conclusionLecturaPapeles");
        
        
        this.narracion.agregarMensajePopUp("descripcionBarra", "La barra esta muy gastada....");
        this.narracion.agregarMensajePopUp("descripcionCopa", "La copa esta llena de polvo...");


        //se agregan comandos a los mensajes para que el jugador pueda decidir que es lo que quiere hacer...
        this.narracion.agregarComandoAMensaje("men2", "caminarNorte", "caminar norte", "men3");
        this.narracion.agregarComandoAMensaje("men2", "caminarEste", "caminar este", "men4");

        this.narracion.agregarComandoAMensaje("men3", "beberAgua", "beber agua", "men5");
        this.narracion.agregarComandoAMensaje("men3", "beberCerbeza", "beber cerbeza", "men6");  
        this.narracion.agregarComandoAMensaje("men3", "CmdDescripcionBarra", "describir barra", "descripcionBarra");
        this.narracion.agregarComandoAMensaje("men3", "CmdDescripcionCopa", "describir copa", "descripcionCopa");
        
        this.narracion.setProximoMensajeDeMensajeEnLista("men5", "men4");
        this.narracion.setProximoMensajeDeMensajeEnLista("men6", "men4");      

        this.narracion.agregarComandoAMensaje("men4", "CmdAcercarseNorteParaLeerPapeles", "caminar norte", "acercarseNorteParaLeerPapeles");
        
        this.narracion.agregarComandoAMensaje("acercarseNorteParaLeerPapeles", "CmdLecturaPapeles", "leer papeles", "lecturaPapeles");
        //
        //SE CARGA EL RESTO DE LA HISTORIA JUNTO CON LA LOGICA...
        this.narracion.agregarMensaje("intro_manejasAuto", "Manejas tu viejo Renault 9 color azul noche, como siempre con la radio apagada, nada se iguala a sentir el viento pasando por la ventana. El sonido del motor, el caucho girando sobre el asfalto y manejar en camino de piedra es uufff, simplemente sublime.");
        this.narracion.agregarMensaje("intro_manejasAut2", "Mañana cumples seis años de servicio. Acaricias el volante, tiene un forro de cuero, ya gastado, mirás el torpedo", "intro_manejasAuto");
        this.narracion.agregarMensaje("intro_manejasAut3", "También cumplimos seis años juntos", "intro_manejasAut2");
        this.narracion.agregarMensaje("intro_manejasAut4", "Aunque cuando se conocieron él lucía un bordo gastado y sin brillo, el motor sonaba parejo pero muy fuerte debido al mal estado del escape. Con el tiempo y los viajes, obtuvo nuevas llantas, con estilo.", "intro_manejasAut3");
        this.narracion.agregarMensaje("intro_manejasAut5", "Pasaron por varios colores hasta encontrar el hermoso azul noche. A los dos les queda muy bien el azul.", "intro_manejasAut4");
        this.narracion.agregarMensaje("intro_manejasAut6", "Hace un buen tiempo que manejas por una ruta algo olvidada, con más pozos que carteles.", "intro_manejasAut5");
        this.narracion.agregarMensaje("intro_manejasAut7", "Pasaron muchos años desde que no visitaste estos parajes.", "intro_manejasAut6");
        this.narracion.agregarMensaje("intro_manejasAut8", "Ya no recordas bien el camino y se aproxima un cruce, el tema es...", "intro_manejasAut7");
        this.narracion.agregarMensaje("intro_manejasAut9", "¿había con salirse de la ruta en este cruce o en el próximo?", "intro_manejasAut8");
        this.narracion.agregarMensaje("intro_manejasAut10", "¿había que ir a la izquierda o a la derecha?", "intro_manejasAut9");
        this.narracion.agregarMensaje("intro_manejasAut11", "¿Qué hacemos amigo, para donde agarramos?- Le preguntas al viejo Renault 9 azul noche. Es una vieja costumbre que practicas desde que se conocieron.", "intro_manejasAut10");
        this.narracion.agregarMensaje("intro_manejasAut11b", "Podes DOBLAR IZQUIERDA, DOBLAR DERECHA o SEGUIR ADELANTE", "intro_manejasAut11");
        
        this.narracion.agregarMensaje("intro_manejasAut12", "Es un camino de tierra, sorprende el contraste entre su buen estado y lo crecidos que están los matorrales.");
        this.narracion.agregarMensaje("intro_manejasAut13", "El camino da una inmensa vuelta y te deja en el un lago....", "intro_manejasAut12");
        this.narracion.agregarMensaje("intro_manejasAut14", "...Te acuerdas de tus tiempos de adolescencia, todos los buenos momentos que pasaste en ese lago...Medidas un poco y finalmente vuelves por donde viniste llegando de nuevo al croce.", "intro_manejasAut13");
        this.narracion.agregarMensaje("intro_manejasAut15", "Que hacemos esta vez? Le vuelves a preguntar a tu viejo auto", "intro_manejasAut14");
        this.narracion.agregarMensaje("intro_manejasAut16", "Podes SEGUIR ADELANTE o DOBLAR IZQUIERDA", "intro_manejasAut15");
        
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
        
        this.narracion.agregarMensaje("casa1", "casa1 no definida");
        
        this.narracion.agregarMensaje("casa2", "casa2 no definida");
        
        this.narracion.agregarMensaje("callePrincipalSector1", "no definido...");
        
        ////////////////////////////////////////////////////////////
        //SE AGREGAN LOS COMANDOS...
        this.narracion.agregarComandoAMensaje("intro_manejasAut11b", "doblarIzquierda", "DOBLAR IZQUIERDA", "intro_manejasAut12");
        this.narracion.agregarComandoAMensaje("intro_manejasAut11b", "doblarDerecha", "DOBLAR DERECHA", "intro_manejasCaminoPueblo");
        this.narracion.agregarComandoAMensaje("intro_manejasAut11b", "seguirAdelante", "SEGUIR ADELANTE", "intro_manejasAut17");
        
        this.narracion.agregarComandoAMensaje("intro_manejasAut16", "doblarIzquierda", "DOBLAR IZQUIERDA", "intro_manejasAut17");
        this.narracion.agregarComandoAMensaje("intro_manejasAut16", "seguirAdelante", "SEGUIR ADELANTE", "intro_manejasCaminoPueblo");
        
        this.narracion.agregarComandoAMensaje("intro_manejasAut18", "volverCruce", "VOLVER AL CRUCE", "intro_manejasAut19");
        this.narracion.agregarComandoAMensaje("intro_manejasAut18", "entrarCafe", "ENTRAR AL CAFE", "intro_entrarCafe");
        
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
