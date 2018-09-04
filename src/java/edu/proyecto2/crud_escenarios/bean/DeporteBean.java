/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.jpa.DeporteJpaController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jose
 */
/*
    Esta clase contiene todo lo relacionado con los deportes es el intermediario entre
    el servicio rest y los jpa.
    
    Se utiliza las anotaciones ManagedBean, el cual es una clase de Java Server Faces
*/
@ManagedBean
@ViewScoped
public class DeporteBean implements Serializable {
    List<Deporte> deportes= new ArrayList<Deporte>();//lista de deportes
    private EntityManagerFactory emf;//Es utilizado para hacer la conexion con la base de datos
    private Deporte selectDe; 
    private String cambio;
    
    @PostConstruct
    public void init() {
        this.cambio="valor";
        
    }
    
//--------------------------------------------------------------------------------------------------------------------------------
/*
    *Este metodo se encarga de obtener los deportes de la base de datos
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de Deporte para obtener las listar de deportes
*/    
    public List<Deporte> getDeportes() {
         emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
         DeporteJpaController ctrl=new DeporteJpaController(emf);
         deportes=ctrl.findDeporteEntities();
         return deportes;
         
    }

//--------------------------------------------------------------------------------------------------------------------------------
/*
    *Este metodo se encarga de settear la lista de deportes
    
   
*/    
    public void setDeportes(List<Deporte> deportes) {
        this.deportes = deportes;
    }
    

//--------------------------------------------------------------------------------------------------------------------------------
/*
    *Este metodo se encarga de settear la lista de deportes
    
   
*/    
     public void changeDeporte(){
        cambio="valor1";
        System.out.println("Deporte");
    }

    public Deporte getSelectDe() {
        return selectDe;
    }

    public void setSelectDe(Deporte selectDe) {
        this.selectDe = selectDe;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    public Deporte getDeport(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Deporte beer : this.getDeportes()){
            if (id.equals(beer.getIdDeporte())){
                return beer;
            }
        }
        return null;
    }
    
}
