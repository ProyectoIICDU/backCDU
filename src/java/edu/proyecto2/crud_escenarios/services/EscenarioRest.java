/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.proyecto2.crud_escenarios.services;
//--------------------------------------------------------------------------------------------------------------------
/*
 Importacion gson para convertir json to objetos java   
*/
import com.google.gson.Gson;
//-----------------------------------------------------------------------------
/*
    Importaciones de entidades de objetos del modelo.
*/
import edu.proyecto2.crud_escenarios.bean.DeporteBean;
import edu.proyecto2.crud_escenarios.bean.EscenarioBean;
import edu.proyecto2.crud_escenarios.bean.ReservaBean;
import edu.proyecto2.crud_escenarios.bean.UsuarioBean;
import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.util.ConverterJson;

//-----------------------------------------------------------------------------------------------------
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
//-----------------------------------------------------------------------------------------------------------------
/*
    Importaciones de metodos get, post, put, delete para manejar las peticiones
*/

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

//--------------------------------------------------------------------------------------------------------------------

/**
 *
 * @author jose
 */

//--------------------------------------------------------------------------------------------------------------------

/*
    Especificacion de el servicio rest que tendra como path Escenario
*/
@Path("Escenario")

public class EscenarioRest {
//----------------------------------------------------------------------------------------------------------------------------
    
    private EscenarioBean escenariobean=new EscenarioBean();//Objeto que permite gestionar los escenarios
    private DeporteBean deportebean=new DeporteBean();//Objeto que permite gestionar los deportes
    private ReservaBean reservabean=new ReservaBean();//Objeto que permite gestionar las reservas
    private UsuarioBean usuariobean= new UsuarioBean();//Objeto que permite gestionar los usuarios
    private ConverterJson converteJson=new ConverterJson();//Objeto que permite convertir un objeto a json
    
//----------------------------------------------------------------------------------------------------------------------------------
/*
    *Funcion que se encarga de recibir una peticion get y obtiene todos los espacios deportivos
    
    *Esta funcion invoca a un metodo llamado getList de escenariobean donde obtiene la lista de escenarios,
    de la base de datos.
    
    *Convierte el array de escenario en un jsonArray para retornar todos los espacios deportivos
    
*/   
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllEspaciosdeportivos(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         JSONObject enviar=new JSONObject();
        JSONArray espaciosJson = new JSONArray();
        for(EspacioDeportivo obj:escenariobean.getList()){
            JSONObject objson=new JSONObject();
            objson.put("idEspacio",obj.getIdEspacio());
            objson.put("nombre", obj.getNombre());
            objson.put("ubicacion",obj.getUbicacion());
            objson.put("estado",obj.getEstado());
            objson.put("descripcion",obj.getDescripcion());
            objson.put("foto",obj.getFoto());
            objson.put("tipofoto",obj.getTipofoto());
            JSONArray deportesJson = new JSONArray();
            for(Deporte objDeporte:obj.getDeporteList()){
                JSONObject objDepJson=new JSONObject();
                objDepJson.put("idDeporte",objDeporte.getIdDeporte());
                objDepJson.put("nombre", objDeporte.getNombre());
                deportesJson.put(objDepJson);
            }
            objson.put("deporteList", deportesJson);
            espaciosJson.put(objson);
        }
       
        //enviar.put("espacios", espaciosJson);
        //System.out.println("Resturn"+escenariobean.getList().get(5).getDeporteList().get(0).getNombre());
        return espaciosJson.toString();
        //return escenariobean.getList(); 
    }
//-----------------------------------------------------------------------------------------------------------------------------    
 /*
    *Función encargada de recibir una peticion get y obtener todos los deportes 
    
    *Esta función invoca al metodo getDeportes de el objeto deportebean 
    
    
 */
    @GET
    @Path("deportes")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Deporte> findAllDeportes(){
        return deportebean.getDeportes();
       
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    //------------------------------Pruebas----------------------------------------
    @GET
    @Path("reservas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReservaEspacio> findAllReservas(){
        return reservabean.getAllReservas();//deportebean.getDeportes();
       
    }
    
    
//-------------------------------------------------------------------------------------------------------------------------
/*
    *Función encargada de recibir una peticion get, ádemas recibe un parametro en el path que es id 
    
    *Esta función recupera el parametro y obtiene los espacios deportivos que estan asociados a este id, osea
    un deporte especifico.
    
    *Convierte el array de escenarios en un jsonArray para retornar todos los espacios deportivos asociados al deporte
*/    
    @GET
    @Path("EspacioDeporte/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAllEscenariosDeportes(@PathParam("id") int id){
        
        JSONArray espaciosJson = new JSONArray();
        for(EspacioDeportivo obj:this.escenariobean.getEspaciosDeportes(id)){  
            JSONObject objson=new JSONObject();
            objson.put("idEspacio",obj.getIdEspacio());
            objson.put("nombre", obj.getNombre());
            objson.put("ubicacion",obj.getUbicacion());
            objson.put("estado",obj.getEstado());
            objson.put("descripcion",obj.getDescripcion());
            objson.put("foto",obj.getFoto());
            objson.put("tipofoto",obj.getTipofoto());
            JSONArray deportesJson = new JSONArray();
            for(Deporte objDeporte:obj.getDeporteList()){
                JSONObject objDepJson=new JSONObject();
                objDepJson.put("idDeporte",objDeporte.getIdDeporte());
                objDepJson.put("nombre", objDeporte.getNombre());
                deportesJson.put(objDepJson);
            }
            objson.put("deporteList",deportesJson);
            espaciosJson.put(objson);
        }   
        return espaciosJson.toString();   
    }
    
//----------------------------------------------------------------------------------------------------------------------------------
/*
    *Esta funcion se encarga de crear un espacio deportivo
    
    *Función encargada de recibir una peticion post, recibe un espacio deportivo.
    
    *Esta funcion recupera el espacio deportivo que es json, y atraves de la libreria gson
    llamando al metodo fromJson y lo convierte a un objeto Espacio Deportivo
    
    *Se llama a la funcion save del objeto escenariobean para guardar el objeto en la base de datos
*/    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String createEspacioDeportivo(String espacioJson) {
        System.out.println("-- " + espacioJson);
        final Gson gson = new Gson();
        final EspacioDeportivo espacioObj = gson.fromJson(espacioJson, EspacioDeportivo.class);
        System.out.println(": " + espacioObj.getNombre());
        System.out.println(": " + espacioObj.getDeporteList());
        this.escenariobean.save(espacioObj);
        return espacioJson;
    }
 //------------------------------------------------------------------------------------------------------------------------------
 /*
    *Esta funcion se encarga de crear una reserva
    
    *Función encargada de recibir una peticion post, recibe una reserva.
    
    *Esta funcion recupera la reserva que es json, y atraves de la libreria gson
    llamando al metodo fromJson y lo convierte a un objeto ReservaEspacio
    
    *Se llama a la funcion guardarReserva del objeto reservabean para guardar el objeto en la base de datos
 */
    @POST
    @Path("AgregarReserva")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String guardarReservaEspacio(String reservaJson) {
        System.out.println("-- " + reservaJson);
        final Gson gson = new Gson();
        final ReservaEspacio reservaObj = gson.fromJson(reservaJson, ReservaEspacio.class);
        Usuario usuario= this.usuariobean.getUsuario(reservaObj.getNombre());
        this.reservabean.guardarReserva(reservaObj, usuario);
        return reservaJson;
    }
//---------------------------------------------------------------------------------------------------------------------------
/*
    *Esta funcion se encarga de editar un espaacio deportivo
    
    *Función encargada de recibir una peticion put, recibe un espaciodeportivo.
    
    *Esta funcion recupera el espaciodeportivo que es json, y atraves de la libreria gson
    llamando al metodo fromJson y lo convierte a un objeto EspacioDeportivo
    
    *Se llama a la funcion edit del objeto escenariobean para editar el objeto en la base de datos
*/
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String updateEspacioDeportivo(String espacioJson) {
        final Gson gson = new Gson();
        final EspacioDeportivo espacioObj = gson.fromJson(espacioJson, EspacioDeportivo.class);
        this.escenariobean.edit(espacioObj);
        return "true";
    }
//---------------------------------------------------------------------------------------------------------------------------
/*
    *Esta funcion se encarga de Eliminar un espaacio deportivo
    
    *Función encargada de recibir una peticion delete, recibe un parametro id
    
    *Esta funcion recupera el parametro id  que viene junto a la peticion
    
    *Se llama a la funcion delete del objeto escenariobean que recibe el id del escenarioDeportivo a eliminar.
*/    

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteEspacioDeportivo(@PathParam("id") int id) {
        escenariobean.delete(id);
        return "true";
    }
//---------------------------------------------------------------------------------------------------------------------------    
/*
    *Esta funcion se encarga de Eliminar una reserva
    
    *Función encargada de recibir una peticion delete, recibe un parametro id
    
    *Esta funcion recupera el parametro id que viene junto a la peticion.
    
    *Se llama a la funcion delete del objeto reservabean que recibe el id de la reserva a eliminar.
*/    
    @DELETE
    @Path("ReservaDelete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteReserva(@PathParam("id") int id){
        System.out.println("id de Reserva a eliminar"+id);
        return reservabean.deleteReserva(id);
    }

//---------------------------------------------------------------------------------------------------------------------------    
/*
    *Esta funcion se encarga de Obtener la lista de reservas por un id de deporte
    
    *Función encargada de recibir una peticion get, recibe un parametro id
    
    *Esta funcion recupera el parametro id que viene junto a la peticion.
    
    *Se llama a la funcion getReservaEspacio del objeto reservabean, que recibe el id del deporte asociado a la reserva.
    
    *Se convierte la lista de reservas en un array json para luego retornar
*/    
    
    @GET
    @Path("Reserva/{id},{usu}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getReservaEspacio(@PathParam("id") int id,@PathParam("usu") String usu){
        
        JSONArray reservasJson = new JSONArray();
        Date fechaActual = new Date();
        if(this.usuariobean.identificarUsuario(usu)){
            for(ReservaEspacio obj:this.reservabean.getReservaEspacio(id)){ 
                if(obj.getFechafin().compareTo(fechaActual)>0){
                
                    JSONObject objson=new JSONObject();
                    objson.put("idReserva",obj.getIdReserva());
                    objson.put("nombre", obj.getNombre());
                    objson.put("fechaini",obj.getFechaini().getTime());
                    objson.put("fechafin",obj.getFechafin().getTime());
                    objson.put("tipo",obj.getTipo());
                    objson.put("esfija",obj.getEsfija());
                    objson.put("descripcion",obj.getDescripcion());
                    objson.put("idEspacio",this.converteJson.convertirEspacio(obj.getIdEspacio()) );
                    reservasJson.put(objson);
                }
                
            }
        }else{
            for(ReservaEspacio obj:this.reservabean.getReservaEspacio(id)){  
                JSONObject objson=new JSONObject();
                if(obj.getFechafin().compareTo(fechaActual)>0){
                    if(obj.getNombre().equals(usu)){
                        objson.put("idReserva",obj.getIdReserva());
                        objson.put("nombre", obj.getNombre());
                        objson.put("fechaini",obj.getFechaini().getTime());
                        objson.put("fechafin",obj.getFechafin().getTime());
                        objson.put("tipo",obj.getTipo());
                        objson.put("esfija",obj.getEsfija());
                        objson.put("descripcion",obj.getDescripcion());
                        objson.put("idEspacio",this.converteJson.convertirEspacio(obj.getIdEspacio()) );
                    }else{
                        objson.put("idReserva",obj.getIdReserva());
                        objson.put("nombre", "Reservado");
                        objson.put("fechaini",obj.getFechaini().getTime());
                        objson.put("fechafin",obj.getFechafin().getTime());
                        objson.put("tipo",obj.getTipo());
                        objson.put("esfija",obj.getEsfija());
                        objson.put("descripcion","No Disponible para el Usuario");
                        objson.put("idEspacio",this.converteJson.convertirEspacio(obj.getIdEspacio()) );
                    }
                    reservasJson.put(objson);

                }
                
            }
        }
           
        return reservasJson.toString();
    }
    
   
   

 //-------------------------------------------------Espacios reservados----------------------------------------------   
    /**
     * Este método pretende obtener los espacios reservados hasta la fecha, más específico se mostrarán 
     * los datos del espacio deportivo reservado, el usuario quien ha hecho la reserva, su fecha de reserva, 
     * y por último la hora de inicio y fin de la reserva. 
     * 
     * @return devuelve el json con los datos anteriormente mendionados
     */
    @GET
    @Path("EspaciosReservados/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEspaciosReservados(@PathParam("id") String id){
        //MIRAR SI TOCA CAMBIAR EL CAMPO
        Date fechaActual = new Date();
        System.out.println("Fecha Actual "+ fechaActual);
        JSONArray reservasJson = new JSONArray();
        if(!this.usuariobean.identificarUsuario(id)){
            for(ReservaEspacio obj:this.reservabean.getAllReservas())
            {  
                
                if(obj.getFechafin().compareTo(fechaActual)>0){
                    if(obj.getNombre().equals(id)){
                        JSONObject objson=new JSONObject();
                        objson.put("usuario", obj.getIdUsuario().getNombres());
                        objson.put("espacioDeportivo", obj.getIdEspacio().getNombre());
                        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");            
                        objson.put("fecha", fecha.format(obj.getFechaini()));
                        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");  
                        objson.put("horaInicio",hora.format(obj.getFechaini()));
                        objson.put("horaFin",hora.format(obj.getFechafin()));

                        reservasJson.put(objson);

                    }
                }
                
            }
        }else
        {
            for(ReservaEspacio obj:this.reservabean.getAllReservas())
            {  
                System.out.println("Fecha Actual "+ obj.getFechafin());
                if(obj.getFechafin().compareTo(fechaActual)>0){
                        JSONObject objson=new JSONObject();
                    objson.put("usuario", obj.getIdUsuario().getNombres());
                    objson.put("espacioDeportivo", obj.getIdEspacio().getNombre());
                    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");            
                    objson.put("fecha", fecha.format(obj.getFechaini()));
                    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");  
                    objson.put("horaInicio",hora.format(obj.getFechaini()));
                    objson.put("horaFin",hora.format(obj.getFechafin()));
                    reservasJson.put(objson);
                }
                


            }
        }
            
         return reservasJson.toString();
    }

//---------------------------------------------------------------------------------------------------------------------------    
/*
    *Esta funcion se encarga de Obtener la lista de usuarios Registrados que no han sido activados
    
    *Función encargada de recibir una peticion get
    
    *Se llama a la funcion getUsuariosNoValidos del objeto usuariobean, que recupera de la base de datos
    los usuarios registrados
    
    *Se Utiliza el metodo toJson de la libreria Gson para convertir la lista a Json
*/    

    @GET
    @Path("Usuarios")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUsuariosNoValidos(){
          final Gson gson = new Gson();
          String nuevo=gson.toJson(this.usuariobean.getUsuariosNoValidos());
          System.out.println("este es el nuevo");
          return nuevo;
    }

//---------------------------------------------------------------------------------------------------------------------------    
/*
    *Esta funcion se encarga de cambiar el estado de un Usuario en la base de datos
    
    *Función encargada de recibir una peticion put, donde recibe el usuario a modificar
    
    *Se Utiliza el metodo fromJson para convertir el json recibido en objeto de tipo Usuario
    
    *Se llama a la funcion edit del objeto usuariobean, que guarda el estado en la base de datos
    
*/    
    
    @PUT
    @Path("CambiarUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String CambiarEstadoUsuario(String usuarioJson)  {
        final Gson gson = new Gson();
        final Usuario usuarioObj = gson.fromJson(usuarioJson, Usuario.class);
        this.usuariobean.edit(usuarioObj);
        return "true";
    }

//---------------------------------------------------------------------------------------------------------------------------    
    
    
}
