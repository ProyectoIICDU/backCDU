/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;
import edu.proyecto2.crud_escenarios.jpa.exceptions.IllegalOrphanException;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jose
 */


enum estados{ CDU,Diamante}//enum que contiene la ubicacion de los escenarios

/*
    Esta clase contiene todo lo relacionado con Escenarios, es el intermediario entre
    el servicio rest y los jpa.
    
    Se utiliza las anotaciones ManagedBean, el cual es una clase de Java Server Faces
*/
@ManagedBean
@ViewScoped
public class EscenarioBean  implements Serializable{

    public static final String servicio = "En Servicio";//atributo para identificar el estado del espacio deportivo
    public static final String mantenimiento = "Mantenimiento";//atributo para identificar el estado del espacio deportivo
    
    private List<EspacioDeportivo> list=new ArrayList();//Lista de espacio deportivos
    private Deporte selectDe;
    private EntityManagerFactory emf;//Es utilizado para hacer la conexion con la base de datos
    private List<String> estado= new ArrayList();//lista que contiene los estados de los espacios deportivos
    private List<String> ubicacion= new ArrayList();//lista que contiene las ubicaciones de los espacios deportivos
    private List<Deporte> deportes=new ArrayList();//lista que contiene los deporte
    private String cambio;
    private EspacioDeportivo create=new EspacioDeportivo();
    @PostConstruct
    public void init() {
        cambio="valor";
    }

    public List<String> getUbicacion() {
        ubicacion.add(servicio);
        ubicacion.add(mantenimiento);
        return ubicacion;
    }

//-------------------------------------------------------------------------------------------------------------------------------
/*  
    Set para el atributo ubicación 
*/    
    public void setUbicacion(List<String> ubicacion) {
        this.ubicacion = ubicacion;
    }
    
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de obtener la lista de espacio deportivos de la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
*/    
    
    public List<EspacioDeportivo> getList() {
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        list = ctrl.findEspacioDeportivoEntities();
        return list;
    }
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de obtener la lista de espacio deportivos que estan asociados a un deporte
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *Se uiliza dos metodos for porque un escenario puede tener mas de un deporte
*/    
    public List<EspacioDeportivo> getEspaciosDeportes(Integer idDeporte){
        List<EspacioDeportivo> espacioDeportes=new ArrayList<EspacioDeportivo>();
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        list= ctrl.findEspacioDeportivoEntities();
        
        for(int i=0;i<list.size();i++){
            List<Deporte> deportes_1= list.get(i).getDeporteList();
            for(int j=0;j<deportes_1.size();j++){
                if(deportes_1.get(j).getIdDeporte().equals(idDeporte)){
                    espacioDeportes.add(list.get(i));
                 
                }
            }
        }
        return espacioDeportes;
    }
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de guardar un espacio deportivo en la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *En el objeto jpa se utilza un metodo save que recibe el objeto a guardar
*/
    public void save(EspacioDeportivo espacio){
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl= new EspacioDeportivoJpaController(emf);
        espacio.setIdEspacio(null);
        System.out.println("nombre:"+espacio.getNombre());
        System.out.println("Estado:"+espacio.getUbicacion());
        System.out.println("Descripcion:"+espacio.getDescripcion());
        System.out.println("Estado:"+espacio.getEstado());
        System.out.println(espacio.getDeporteList().size());
        if(espacio.getDeporteList()==null){
            System.out.println("Lista Nula");
        }else{
            System.out.println("Lista bien");
        }
        
        
        byte[] f1= "dasdsaopdiosap".getBytes();  
        espacio.setTipofoto("image/jpeg");
        espacio.setFoto(f1); 
       ctrl.create(espacio);
    }
     public void edit(EspacioDeportivo espacio) {
          byte[] f1= "dasdsaopdiosap".getBytes(); 
        espacio.setFoto(f1);
        espacio.setTipofoto("image/jpeg");
        try {
            emf = Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
            EspacioDeportivoJpaController ctrl = new EspacioDeportivoJpaController(emf);
//        espacio.setIdEspacio(null);
            ctrl.edit(espacio);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EscenarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EscenarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------
/*  
    *Este metodo se encarga de eliminar un espacio deportivo
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de EspacioDeportiv para obtener las listar de escenarios
    
    *En el objeto jpa se utilza un metodo destroy que elimina el objeto
*/

     
    public void delete(Integer idEspacio) {
        emf = Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        EspacioDeportivoJpaController ctrl = new EspacioDeportivoJpaController(emf);
        try {
            ctrl.destroy(idEspacio);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(EscenarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EscenarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------    
    public void setList(List<EspacioDeportivo> list) {
        this.list = list;
    }
    public String create(){
        return "create";
    }
    public void agregarDeporte(){
        this.deportes.add(selectDe);
        
    }

    public String confirmar(){
        return "listardeportes";
    }
    public String gestionEscenarios(){
        return "listardeportes";
    }
    
    public EspacioDeportivo getCreate() {
        return create;
    }

    public void setCreate(EspacioDeportivo create) {
        System.out.println("Escenario"+create.getIdEspacio());
        this.create = create;
    }
    
    
    public String getCambio() {
       
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    
    public Deporte getSelectDe() {
        return selectDe;
    }

    public void setSelectDe(Deporte selectDe) {
        
        this.selectDe = selectDe;
    }
    public void changeDeporte(){
        cambio="valor1";
        
    }

    public List<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(List<Deporte> deportes) {
        this.deportes = deportes;
    }
    
  
    
   
    
    public List<String>  getEstado() {
       estados aux[]=estados.values();
       for(int i=0;i<aux.length;i++){
           estado.add(aux[i].name());
       }
       return estado;
    }
    
    
}
