package across.application;

import across.enumerations.*;
import across.admin.*;
import across.project.*;
import across.user.*;
import across.notification.*;

import es.uam.eps.sadp.grants.*;

import java.util.*;
import java.io.*;
import java.time.*;
import java.lang.Integer;


/**
 * Clase Application
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/
public class Application implements Serializable{

    private static final long serialVersionUID = 1L;

    private static Application application;
    private User currentUser = null;
    private boolean currentAdmin = false;

    private int daysExpiration = 30;
    private int minVotes = 1000; 
    private ArrayList<String> districts;

    private Admin admin;
    private ArrayList<Project> projects;
    private ArrayList<Project> nonValidatedProjects;
    private ArrayList<Project> rejectedProjects;
    private ArrayList<Collective> collectives;
    private ArrayList<User> users;
    private ArrayList<User> nonValidatedUsers;

    private boolean logOut;

    private HashMap<Project,String> pendingFinance = new HashMap<Project,String>();


    /**
     * Constructor de un objeto Application
     * 
     */
    private Application(){
        admin = new Admin();
        projects = new ArrayList<Project>();
        nonValidatedProjects = new ArrayList<Project>();
        rejectedProjects = new ArrayList<Project>();
        collectives = new ArrayList<Collective>();
        users = new ArrayList<User>();
        nonValidatedUsers = new ArrayList<User>();
        districts = readDistricts();
    }

    /**
     * Crea un nuevo objeto Application ni no ha sido creado con anterioridad o,
     * sino, devuelve la aplicacion ya creada
     * 
     * @return objeto aplicacion unico
     */
    public static Application getApplication(){
        if (application == null){
            application = new Application();
        }
        return application;
    }

    /**
     * Actualiza el objeto aplicacion al parametro de entrada
     * 
     * @param app aplicacion
     */
    public static void setApplication(Application app){
        Application.application = app;
    }

    /**
     * Devuelve el usuario logueado en la aplicacion en el momento en el que se llama a esta funcion
     * 
     * @return usuario conectado actualmente
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Actualiza el usuario logueado en la aplicacion
     * 
     * @param current usuario conectado
     */
    public void setCurrentUser(User current){
        currentUser = current;
    }

    /**
     * Devuelve si el usuario logueado es el administrador o no
     * 
     * @return true, si el administrador esta conectado
     * @return false, en otro caso 
     */
    public boolean getCurrentAdmin(){
        return currentAdmin;
    }

    /**
     * Modifica si el administrador esta logueado o no
     * 
     * @param is_admin administrador conectado o no
     */
    public void setCurrentAdmin(boolean is_admin){
        currentAdmin = is_admin;
    }

    /**
     * Devuelve el numero de dias sin votos nuevos para que un proyecto caduque
     * 
     * @return daysExpiration
     */
    public int getDaysExpiration(){ 
        return daysExpiration; 
    }

    /**
     * Actualiza el numero de dias sin votos nuevos para que un proyecto caduque
     * 
     * @param daysExp numero de dias
     */
    public void setDaysExpiration(int daysExp){
        daysExpiration = daysExp;
    }

    /**
     * Devuelve el minimo numero de votos en un proyecto para poder financiar
     * 
     * @return minVotes
     */
    public int getMinVotes(){ 
        return minVotes; 
    }

    /**
     * Actualiza el minimo numero de votos en un proyecto para poder financiar
     * 
     * @param minV numero de votos
     */
    public void setMinVotes(int minV){ 
        minVotes = minV; 
        for (Project p: projects) p.updateState();
    }

    /**
     * Devuelve el admistrador de la app
     * 
     * @return administrador de la app
     */
    public Admin getAdmin(){ 
        return admin; 
    }

    /**
     * Devuelve un array con todos los usualrios validados que contiene la aplicacion
     * 
     * @return array de usuarios validados
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }
    

    public boolean getLogOut(){
        return this.logOut;
    }


    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public void addUsers(User u) {
        this.users.add(u);
    }


    public ArrayList<User> getNonValidatedUsers() {
        return this.nonValidatedUsers;
    }


    public void setNonValidatedUsers(ArrayList<User> nonValidatedUsers) {
        this.nonValidatedUsers = nonValidatedUsers;
    }


    public void addNewUsers(User u) {
        this.nonValidatedUsers.add(u);
    }

    public ArrayList<Collective> getCollectives() {
        return this.collectives;
    }

    public void setCollectives(ArrayList<Collective> collectives) {
        this.collectives = collectives;
    }

    public void addCollectives(Collective c) {
        this.collectives.add(c);
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project p) {
        this.projects.add(p);
    }

    public ArrayList<Project> getNonValidatedProjects() {
        return this.nonValidatedProjects;
    }

    public void setNonValidatedProjects(ArrayList<Project> nonValidatedProjects) {
        this.nonValidatedProjects = nonValidatedProjects;
    }


    public ArrayList<Project> getRejectedProjects() {
        return this.rejectedProjects;
    }

    public void setRejectedProjects(ArrayList<Project> rejectedProjects) {
        this.rejectedProjects = rejectedProjects;
    }


    public void addNewProject(Project p) {
        this.nonValidatedProjects.add(p);
    }

    public void setLogOut(boolean lo){
        this.logOut = lo;
    }

    public void addPendingFinance(Project project, String id){
        pendingFinance.put(project, id);
    }

    /**
     * Metodo auxiliar para los tests, para poder avanzar la fecha de la pasarela hasta la fecha date y asi poder
     * simular facilmente.
     */
    public void setCCGGDate(LocalDate date) {
    	CCGG.getGateway().setDate(date);
    }
    
    /**
     * Lee del archivo de texto "Distritos.txt" los distritos de Madrid y los guarda en un array
     * 
     * @return
     */
    public ArrayList<String> readDistricts(){
        districts = new ArrayList<String>();
        try {
            File file = new File("Distritos.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                districts.add(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return districts;
    }

    /**
     * Permite a un usuario registrarse en la aplicacion
     * 
     * @param username nombre de usuario
     * @param NIF NIF del usuario a registrar
     * @param password contrasena del usuario
     * @return true, si se lleva a cabo el registro con exito
     * @return false, si no se puede registrar porque ya existe el nombre de
     * usuario introducido o el NIF ya ha sido registrado
     */
    public boolean register(String username, String NIF, String password){

        //comprobar que el username y el NIF no existen
        for (User u: users) {
            if (username.equals(u.getUsername()) || NIF.equals(u.getNIF())) return false;
        }
        for (User u: nonValidatedUsers) {
            if (username.equals(u.getUsername()) || NIF.equals(u.getNIF())) return false;
        }

        User u = new User(username, NIF, password, false);
        nonValidatedUsers.add(u);

        // mandar a validar al admin
        new NotificationAdminUser(u);
        return true;
    }

    /**
     * Permite a un usuario loguearse en la aplicacion
     * 
     * @param username nombre de usuario
     * @param password contrasena del usuario
     * @return true, si la contrasena coincide con la del nombre de usuario
     * @return false, en otro caso
     */
    public boolean login(String username, String password){
        for (User u: users){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())){
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    /**
     * Permite a cualquier usuario cerrar sesion
     */
    public void logout(){
        currentUser = null;
        currentAdmin = false;
        logOut = true;
    }

    /**
     * Filtra un proyecto social por su estado
     * 
     * @param type tipo de proyecto social
     * @param group Grupo social al que va dirigido
     * @return ArrayList de proyectos qye cumplen las condiciones
     */
    public ArrayList<Project> filterProject(projectState state){
        ArrayList<Project> output = new ArrayList<Project>();

        // encontrar proyectos con un cierto estado
        for (Project p: projects){
            if (p.getProjectState().equals(state))
                output.add(p);
        }

        return output;
    }

    /**
     * Filtra un proyecto social por el tipo
     * 
     * @param type tipo de proyecto social
     * @return ArrayList de proyectos que cumplen las condiciones
     */
    public ArrayList<Project> filterSocialProject(typeSocial type){
        ArrayList<Project> output = new ArrayList<Project>();

        // encontrar proyectos sociales nacionales o internacionales
        for (Project p: projects){
            if (p.getClass().equals(SocialProject.class))
                output.add(p);
        }

        return output;
    }


    /**
     * Filtra un proyecto social por el grupo social al que va dirigido
     * 
     * @param group Grupo social al que va dirigido
     * @return ArrayList de proyectos que cumplen las condiciones
     */
    public ArrayList<Project> filterSocialProject(String group){
        ArrayList<Project> output = new ArrayList<Project>();

        // encontrar proyectos sociales dirigidos a cierto grupo
        for (Project p: projects){
            if (p.getClass().equals(SocialProject.class)){
                if (((SocialProject)p).getGroup().contains(group))
                    output.add(p);
            }
        }

        return output;
    }

    /**
     * Filtra un proyecto de infraestructura por distrito
     * 
     * @param d distrito 
     * @return ArrayList de proyectos que cumplen las condiciones
     */
    public ArrayList<Project> filterInfrProject(String disc){
        ArrayList<Project> output = new ArrayList<Project>();

        // encotrar proyectos de infraestructura en cierto distrito
        for (Project p: projects){
            if (p instanceof InfraestructureProject){ 
                if (((InfraestructureProject)p).getDistrict().contains(disc)) 
                    output.add(p);
            }
        }

        return output;

    }

    /**
     * Busca un proyecto segun si el input esta contenido en el nombre
     * o en la descripcion del proyecto
     * 
     * @param subj string a buscar
     * @return ArrayList de proyectos que cumplen las condiciones
     */
    public ArrayList<Project> searchProject(String subj){
        ArrayList<Project> output = new ArrayList<Project>();

        for (Project p: projects){
            if (p.getName().contains(subj) || p.getDescription().contains(subj)) output.add(p);
        }
        
        return output;
    }

    /**
     * Busca un colectivo segun si el input esta contenido en el nombre
     * o en la descripcion del proyecto
     * 
     * @param subj string a buscar
     * @return ArrayList de colectivos que cumplen las condiciones
     */
    public ArrayList<Collective> searchCollective(String subj){
        ArrayList<Collective> output = new ArrayList<Collective>();

        for (Collective c: collectives){
            if (c.getName().contains(subj) || c.getDescription().contains(subj)) output.add(c);
        }
        
        return output;
    }

    /**
     * Ordena los proyectos en base a su numero de votos
     * 
     * @return Informe de popularidad de los proyectos creados
     */
    public String popularityReport(){
    	String report = "";

        ArrayList<Project> output = new ArrayList<Project>();
        output.addAll(projects);
        Collections.sort(output);

        int count = 1;
        for (Project p: output){
            report += count + ". " + p.getName() + "\n";
            count++;
        }

        return report;
    }

    /**
     * Devuelve el informe de afinidad de un colectivo
     * 
     * @param c colectivo sobre el que se hace el informe
     * @return HashMap de los colectivos ordenados segun afinidad
     */
    public String affinityReport(Collective c){
        if (currentUser.getMemberCollectives().contains(c) == false){
            return null;
        }

        HashMap<Collective, Integer> notSorted= new HashMap<Collective, Integer>();

        for (Collective col: collectives){
            if (c.equals(col)) {
            } else {
                Set<Project> p1 = new HashSet<Project>();
                Set<Project> p2 = new HashSet<Project>();
                Set<Project> p3 = new HashSet<Project>(); 

                p1.addAll(c.getCreatedProjects());
                p1.addAll(col.getVotedProjects());

                p2.addAll(col.getCreatedProjects());
                p2.addAll(c.getVotedProjects());

                p3.addAll(c.getVotedProjects());
                p3.addAll(col.getVotedProjects());

                int tasa = 0;
                if (p3.size() == 0) tasa = 0;
                else tasa = (p1.size() + p2.size())/p3.size();
                notSorted.put(col, tasa);

            }
        }

        LinkedHashMap<Collective, Integer> output = sortCollectives(notSorted);

        // Pasar a string

        String result = "";
        int count = 1;

        for (Collective colec : output.keySet()) {
            result = count + ". " + colec.getName() + ", " + output.get(colec) + "\n" + result;
            count ++;
          }
        return result;

    }

    /**
     * Permite ordenar un HashMap en orden decreciente segun el valor de las entradas
     * 
     * @param input HashMap a ordenar
     * @return HashMap con mismo contenido que input pero ordenado
     */
    private LinkedHashMap<Collective,Integer> sortCollectives (HashMap<Collective, Integer> input){
        List<Collective> mapKeys = new ArrayList<>(input.keySet());
        List<Integer> mapValues = new ArrayList<>(input.values());
        //Collections.sort(mapValues);
        //Collections.sort(mapKeys);

        LinkedHashMap<Collective, Integer> sortedMap = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<Collective> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Collective key = keyIt.next();
                Integer comp1 = input.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }

        return sortedMap;
    }


    /**
     * Comprueba si hay algún proyecto nuevo financiado
     */

    public void checkFinance(){
        HashMap<Project,String> output = new HashMap<Project,String>();

        for (Project p: pendingFinance.keySet()){

            // Si todavia no hay respuesta
            if (p.financed(pendingFinance.get(p))==null){
                //Anadir al nuevo array de financiacion
                output.put(p, pendingFinance.get(p));
            }
            // Si no ha sido financiado
            else if (p.financed(pendingFinance.get(p)).equals(0)){
                
                // Cambiar el estado del proyecto y enviar Notificacion
                p.setProjectState(projectState.NOFINANCIADO);

                // Anadir al array de rechazados
                rejectedProjects.add(p);

           
            // Si ha sido financiado
            }  else {
                // Cambiar el coste del proyecto
                p.setCost(p.financed(pendingFinance.get(p)));

                // Cambiar el estado del proyecto y enviar Notificacion
                p.setProjectState(projectState.FINANCIADO);

            }

            pendingFinance = output;
        }

    }

    public void checkExpired(){
        ArrayList<Project> newArray = new ArrayList<Project>();
    
        for (Project p: projects){
            if (!p.isExpired()){
                newArray.add(p);
            }
            else rejectedProjects.add(p);
        }

        projects = newArray;
    }





    /************************************************************************/
    /************ FUNCIONES AUXILIARES PARA PROBAR FUNCIONAMIENTO ***********/
    /************************************************************************/


    /**
     * Pantalla de inicio de la aplicacion.
     * Da opcion de registrarse como nuevo usario o iniciar sesion como usuario o administrador
     * 
     * @return true, si la accion se ejecuto con exito
     * @return false, si no se ha podido realizar la accion
     */
    public void pantallaLogin(){
        System.out.println("\n ------------------- ACROSS MADRID --------------------");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1. Registro\n2. Login\n3. Login como administrador");
            String opc = reader.readLine();
            if (opc.equals("1")) {
                System.out.println("Nombre de usuario:");
                String username = reader.readLine();
                System.out.println("NIF:");
                String NIF = reader.readLine();
                System.out.println("Contrasena:");
                String password = reader.readLine();
                //reader.close();
                if(!register(username, NIF, password)){
                    System.out.println("El nombre de usuario o NIF ya han sido registrados");
                }
            }
            else if(opc.equals("2")) {
                System.out.println("Nombre de usuario:");
                String username = reader.readLine();
                System.out.println("Contrasena:");
                String password = reader.readLine();
                if (!login(username, password)){
                    System.out.println("Nombre de usuario o contrasena incorrectos");
                }
            }
            else if (opc.equals("3")){
                System.out.println("Contrasena de administrador: ");
                String password = reader.readLine();
                if (!admin.login(password)){
                    System.out.println("Contrasena erronea");
                }else{
                    currentAdmin = true;
                }
            }
            else if(opc.equals("q")){
                logOut = true;
            }
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }

    /**
     * Redirige la aplicacion a la pantalla principal de usuario o de administrador,
     * segun cual de los dos este logueado
     */
    public void pantallaPrincipal(){
        while (currentUser != null)
            currentUser.principalUser();
        while (currentAdmin){
            admin.principalAdmin();
        }
        return;

    }
    

}

