package across.control;

import across.model.application.Application;
import across.control.admin.*;
import across.control.menu.*;
import across.control.notif.*;
import across.control.start.*;
import across.control.user.collective.*;
import across.control.user.filtro.*;
import across.control.user.informes.*;
import across.control.user.project.*;
import across.gui.MainFrame;

/**
 * Clase Controlador
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class Controlador {

    /* inicio de la app */
    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin login;
    private ControladorRegistro reg;
    private ControladorAtras atras;

    /* menu user */
    private ControladorLogout logout;
    private ControladorToInicio toInicio;
    private ControladorToPerfil toPerfil;
    private ControladorToNotif toNotif;

    /* notificaciones */
    private ControladorVistoNotif vistoNotif;
    
    /* inicio de User */
    private ControladorUserCrearProyecto userCrearProyecto;
    private ControladorUserDisplayProject userDisplayProject;
    private ControladorUserCrearColectivo userCrearColectivo;
    private ControladorUserDisplayCollective userDisplayCollective;
    private ControladorToInformes informes;
    private ControladorUpdateColectivos updateCol;
    private ControladorFiltrar filtrar;
    private ControladorLimpiarFiltro limpiar;
    
    /* control nuevo colectivo */
    private ControladorNewCollective nuevoColectivo;
    
    /* control nuevo proyecto */
    private ControladorNewProject nuevoProyecto;
    private ControladorLoadImage cargarImagen;

    /* botones de DisplayProject */
    private ControladorVotar votar;
    private ControladorSeguir seguir;
    private ControladorDejarSeguir dejarSeguir;
    private ControladorFinanciar financiar;
    
    /* botones de DisplayCollective*/
    private ControladorJoin join;

    /* inicio de admin */
    private ControladorAdminUsuarios adminUsuarios;
    private ControladorAdminConfig adminConfig;
    private ControladorAdminProyectos adminProyectos;
    private ControladorAdminConfigVotes adminConfigVotes;
    private ControladorAdminConfigCaducidad adminConfigCaducidad;
    private ControladorAdminProyectosGuardar adminProyectosGuardar;
    private ControladorAdminUsuariosBloq adminUsuariosBloq;
    private ControladorValidar validar;
    private ControladorRechazar rechazar;

    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase Controlador
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public Controlador(MainFrame frame, Application model){
        this.frame = frame;
        this.model = model;
        setupControladores();
    }

    /**
     * Crea todos los controladores de la aplicacion
     * 
     */
    private void setupControladores(){
        /* inicio de la app */
        inicioRegistro = new ControladorInicioRegistro(frame, model);
        inicioLogin = new ControladorInicioLogin(frame, model);
        login = new ControladorLogin(frame, model);
        reg = new ControladorRegistro(frame, model);
        atras = new ControladorAtras(frame, model);

        /* menu user */
        logout = new ControladorLogout(frame, model);
        toInicio = new ControladorToInicio(frame, model);
        toPerfil = new ControladorToPerfil(frame, model);
        toNotif = new ControladorToNotif(frame, model);
        informes = new ControladorToInformes(frame, model);
        updateCol = new ControladorUpdateColectivos(frame, model);
        filtrar = new ControladorFiltrar(frame, model);
        limpiar = new ControladorLimpiarFiltro(frame, model);

        /* notificaciones */
        vistoNotif = new ControladorVistoNotif(frame, model);

        /* inicio de user */
        userCrearProyecto = new ControladorUserCrearProyecto(frame, model);
        userDisplayProject = new ControladorUserDisplayProject(frame, model);
        userCrearColectivo = new ControladorUserCrearColectivo(frame,model);
        userDisplayCollective = new ControladorUserDisplayCollective(frame, model);

        /* display proyecto */
        votar = new ControladorVotar(frame, model);
        seguir = new ControladorSeguir(frame, model);
        dejarSeguir = new ControladorDejarSeguir(frame, model);
        financiar = new ControladorFinanciar(frame, model);
        
        /* display colectivo */
        join = new ControladorJoin(frame, model);
         
        /* inicio de admin */
        adminUsuarios = new ControladorAdminUsuarios(frame, model);
        adminConfig = new ControladorAdminConfig(frame, model);
        adminProyectos = new ControladorAdminProyectos(frame, model);
        adminConfigVotes = new ControladorAdminConfigVotes(frame, model);
        adminConfigCaducidad = new ControladorAdminConfigCaducidad(frame, model);
        adminProyectosGuardar = new ControladorAdminProyectosGuardar(frame, model);
        adminUsuariosBloq = new ControladorAdminUsuariosBloq(frame, model);
        validar = new ControladorValidar(frame, model);
        rechazar = new ControladorRechazar(frame, model);
        
        /* nuevo proyecto */
        nuevoProyecto = new ControladorNewProject(frame, model);
        cargarImagen = new ControladorLoadImage(frame, model);
        
        /* nuevo colectivo */
        nuevoColectivo = new ControladorNewCollective(frame, model);
    }

    /**
     * Devuelve el controlador que permite el cierre de sesion
     * 
     * @return controlador de logout
     */
    public ControladorLogout getLogout(){
        return this.logout;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial al de registro
     * 
     * @return controlador de incio a registro
     */
    public ControladorInicioRegistro getInicioRegistro(){
        return this.inicioRegistro;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial al de login
     * 
     * @return controlador de inicio a login
     */
    public ControladorInicioLogin getInicioLogin(){
        return this.inicioLogin;
    }

    /**
     * Devuelve el controlador que maneja el inicio de sesion
     * 
     * @return controlador de login
     */
    public ControladorLogin getLogin(){
        return this.login;
    }
    
    /**
     * Devuelve el controlador que maneja el registro de un usuario
     * 
     * @return controlador de registro
     */
    public ControladorRegistro getRegistro(){
        return this.reg;
    }

    /**
     * Devuelve el controlador que lleva de registro o de login al inicio
     * 
     * @return controlador de registro
     */
    public ControladorAtras getAtras(){
        return this.atras;
    }

    /**
     * Devuelve el controlador que lleva a la pantalla inicial del usuario
     * 
     * @return controlador de inicio
     */
    public ControladorToInicio getToInicio(){
        return this.toInicio;
    }

    /**
     * Devuelve el controlador que lleva al perfil del usuario
     * 
     * @return controlador de perfil
     */
    public ControladorToPerfil getToPerfil(){
        return this.toPerfil;
    }
    
    /**
     * Devuelve el controlador que lleva al panel de notificaciones 
     * 
     * @return controlador de notificaciones
     */
    public ControladorToNotif getNotif(){
        return toNotif;
    }

    /**
     * Devuelve el panel de control de notificacion vista 
     * 
     * @return panel de notificacion vista
     */
    public ControladorVistoNotif getVistoNotif(){
        return vistoNotif;
    }


    /**
     * Devuelve el controlador que maneja la creacion de un proyecto nuevo
     * 
     * @return controlador para crear proyectos
     */
    public ControladorNewProject getNuevoProyecto(){
        return nuevoProyecto;
    }

    /**
     * Devuelve el controlador que permite cargar una imagen a la aplicacion
     * 
     * @return controlador de cargar imagenes
     */
    public ControladorLoadImage getCargarImagen(){
        return cargarImagen;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de crear proyecto
     * 
     * @return controlador de inicio a crear proyecto
     */
    public ControladorUserCrearProyecto getUserCrearProyecto(){
        return userCrearProyecto;
    }
    
    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de visualizar proyecto
     * 
     * @return controlador de inicio a visualizar proyecto
     */
    public ControladorUserDisplayProject getUserDisplayProject(){
        return userDisplayProject;
    }
    
    /**
     * Devuelve el controlador que maneja la creacion de un proyecto nuevo
     * 
     * @return controlador para crear proyectos
     */
    public ControladorNewCollective getNuevoColectivo(){
        return nuevoColectivo;
    }
    
    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de crear colectivo
     * 
     * @return controlador de inicio a crear colectivo
     */
    public ControladorUserCrearColectivo getUserCrearColectivo(){
        return userCrearColectivo;
    }
    
    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de visualizar colectivo
     * 
     * @return controlador de inicio a visualizar colectivo
     */
    public ControladorUserDisplayCollective getUserDisplayCollective(){
        return userDisplayCollective;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de solicitar informes
     * 
     * @return controlador de inicio a solicitar informes
     */
    public ControladorToInformes getInformes(){
        return informes;
    }

    /**
     * Devuelve el controlador que actualiza la tabla de informe de afinidad
     * 
     * @return controlador de actualizar afinidad
     */
    public ControladorUpdateColectivos getUpdateColectivos(){
        return updateCol;
    }

    /**
     * Devuelve el controlador que filtra los proyectos del panel inicial del usuario
     * 
     * @return controlador de filtro
     */
    public ControladorFiltrar getFiltrar(){
        return filtrar;
    }

    /**
     * Devuelve el controlador que limpia los filtros del panel inicial del usuario
     * 
     * @return controlador de limpiar filtro
     */
    public ControladorLimpiarFiltro getLimpiarFiltro(){
        return limpiar;
    }
    
    /**
     * Devuelve el controlador que permite unirse a un colectivo
     * 
     * @return controlador de unirse a un colectivo
     */
    public ControladorJoin getJoin(){
        return join;
    }

    /**
     * Devuelve el controlador que permite votar un proyecto
     * 
     * @return controlador de votar proyecto
     */
    public ControladorVotar getVotar(){
        return votar;
    }

    /**
     * Devuelve el controlador que permite seguir un proyecto
     * 
     * @return controlador de votar proyecto
     */
    public ControladorSeguir getSeguir(){
        return seguir;
    }

    /**
     * Devuelve el controlador que permite votar un proyecto
     * 
     * @return controlador de votar proyecto
     */
    public ControladorDejarSeguir getDejarSeguir(){
        return dejarSeguir;
    }

    /**
     * Devuelve el controlador que permite enviar a financiar un proyecto
     * 
     * @return controlador de financiar proyecto
     */
    public ControladorFinanciar getFinanciar(){
        return financiar;
    }
 
    /**
     * Devuelve el controladro que lleva de la pantalla inicial del admin a la pantalla de gestion de usuarios
     * 
     * @return controlador de inicioadmin a adminUsuarios
     */
    public ControladorAdminUsuarios getAdminUsuarios() {
    	return adminUsuarios;
    }
    
    /**
     * Devuelve el controladro que lleva de la pantalla inicial del admin a la pantalla de configuracion
     * 
     * @return controlador de inicioadmin a adminConfig
     */
    public ControladorAdminConfig getAdminConfig() {
    	return adminConfig;
    }
    
    /**
     * Devuelve el controlador  que lleva de la pantalla inicial del admin a la pantalla de proyectos
     * 
     * @return controlador de inicioadmin a adminProyectos
     */
    public ControladorAdminProyectos getAdminProyectos() {
    	return adminProyectos;
    }
    
    /**
     * Devuelve el controlador que permite configurar los votos minimos
     * 
     * @return controlador configuracion votos minimos
     */
    public ControladorAdminConfigVotes getAdminConfigVotes() {
    	return adminConfigVotes;
    }
    
    /**
     * Devuelve el controlador que permite configurar el limite de caducidad
     * 
     * @return controlador configuracion caducidad
     */
    public ControladorAdminConfigCaducidad getAdminConfigCaducidad() {
    	return adminConfigCaducidad;
    }
    
    /**
     * Devuelve el controlador que permite guardar los cambios efectuados en los proyectos por el admin
     * 
     * @return controlador guardar cambios de proyectos
     */
    public ControladorAdminProyectosGuardar getAdminProyectosGuardar() {
    	return adminProyectosGuardar;
    }
    
    /**
     * Devuelve el controlador que permite bloquear un usuario
     * 
     * @return controlador bloqueo
     */
    public ControladorAdminUsuariosBloq getAdminUsuariosBloq() {
    	return adminUsuariosBloq;
    }

    /**
     * Devuelve el controlador que permite validar un projecto
     * 
     * @return controlador validacion
     */
    public ControladorValidar getValidar() {
    	return validar;
    }

    /**
     * Devuelve el controlador que permite rechazar un projecto
     * 
     * @return controlador rechazo
     */
    public ControladorRechazar getRechazar() {
    	return rechazar;
    }
    


}