/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;
import edu.proyecto2.crud_escenarios.jpa.ReservaEspacioJpaController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author jose
 */

/*
    Esta clase contiene todo lo relacionado con Reservas es el intermediario entre
    el servicio rest y los jpa.
    
    Se utiliza las anotaciones ManagedBean, el cual es una clase de Java Server Faces
*/
@ManagedBean
@ViewScoped
public class ReservaBean {

    
    private EntityManagerFactory emf;//Es utilizado para hacer la conexion con la base de datos    
    
    
    @PostConstruct
    public void init() {
    }
    
    public ReservaBean() {
    }    
 
    
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de guardar la Reserva de la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *Se llama al metodo create para guardar el objeto.
*/    
    public void guardarReserva(ReservaEspacio objReserva){
        System.out.println("Reserva"+objReserva.getFechafin());
        Usuario objUsuario= new Usuario(2,"josej","tricolor","Invitado","104614010913","Jose Julio","Tobar Cifuentes","Activo",new Date(2018-06-01),"Gustavo Ordoñez");
        objReserva.setIdReserva(null);
        objReserva.setIdUsuario(objUsuario);
        objReserva.setFechahorareg(new Date());
        objReserva.setFechahoramod(new Date());
        objReserva.setModificadopor("Administrador");
        objReserva.setRegistradopor("Administrador");
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl= new ReservaEspacioJpaController(emf);
       ctrl.create(objReserva);
    }
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de obtener la lista de Reservas deacuerdo a un id del Deporte asociado en la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *Se Utiliza un for para obtener la lista y comparar el id.
*/  
    public List<ReservaEspacio> getReservaEspacio(int id){
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl= new ReservaEspacioJpaController(emf);
        List<ReservaEspacio> reservas=ctrl.findReservaEspacioEntities();
        List<ReservaEspacio> reservaEspacio=new ArrayList<ReservaEspacio>();
        for(int i=0;i<reservas.size();i++){
            if(reservas.get(i).getIdEspacio().getIdEspacio()==id){
                reservaEspacio.add(reservas.get(i));
            }
           
        }
        return reservaEspacio;
    }
    
    
    public List<ReservaEspacio> getAllReservas()
    {
        emf = Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl = new ReservaEspacioJpaController(emf);
        List<ReservaEspacio> reservas = ctrl.findReservaEspacioEntities();
        List<ReservaEspacio> reservaEspacio = new ArrayList<>();
        for (int i = 0; i < reservas.size(); i++)
        {
            reservaEspacio.add(reservas.get(i));            
        }
        return reservaEspacio;
    }

//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de elimiar una Reserva en la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *Se llama al metodo destroy del objeto jpa donde se pasa el id del objeto a eliminar.
*/  
    public String deleteReserva(int id){
        System.out.println("id de Reserva a eliminar"+id);
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        ReservaEspacioJpaController ctrl= new ReservaEspacioJpaController(emf);
        try{
            ctrl.destroy(id);
            return "true";
        }catch(Exception e){
            return "false";
        }
    }
}
