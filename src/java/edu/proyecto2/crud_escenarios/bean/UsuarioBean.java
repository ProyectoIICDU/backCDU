/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.jpa.EspacioDeportivoJpaController;
import edu.proyecto2.crud_escenarios.jpa.UsuarioJpaController;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jose
 */
/*
    Esta clase contiene todo lo relacionado con los usuarios es el intermediario entre
    el servicio rest y los jpa.
    
    Se utiliza las anotaciones ManagedBean, el cual es una clase de Java Server Faces
*/
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean {

   
    private String cambio; 
    private EntityManagerFactory emf;//Es utilizado para hacer la conexion con la base de datos
    @PostConstruct
    public void init() {
        cambio="valor";
    }
    

//--------------------------------------------------------------------------------------------------------------------------------
/*
    *Este metodo se encarga de obtener los usuarios que han sido registrados
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de Deporte para obtener las lista de usuarios
    
    
*/    
    public List<Usuario> getUsuariosNoValidos(){
        List<Usuario> usuariosNoValidos = new ArrayList<Usuario>();
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        UsuarioJpaController ctrl= new UsuarioJpaController(emf);
        List<Usuario> auxUsuarios=ctrl.findUsuarioEntities();
        for(Usuario objUsuario:auxUsuarios){
                if(objUsuario.getEstado().equals("Registrado")){
                   usuariosNoValidos.add(objUsuario);
                }
        }
        return usuariosNoValidos;
        
    }
    
//--------------------------------------------------------------------------------------------------------------------------------
/*
    *Este metodo se encarga de editar un Usuario
    
    *Se utiliza el metodo createEntityManagerFactory para llamar a la unidad de persistencia, el cual recibe
    el nombre de la unidad de persistencia.
    
    *Despues se crea un jpa de Deporte para llamar al metodo de editar
    
    
*/
    public void edit(Usuario usuarioObj) {
        emf=Persistence.createEntityManagerFactory("CRUD_EscenariosPU");
        UsuarioJpaController ctrl= new UsuarioJpaController(emf);
        System.out.println(usuarioObj.getEstado());
        try {
            ctrl.edit(usuarioObj);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

    
}
