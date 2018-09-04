/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author jose
 */
/*
    Clase utilizada para que al servidor le lleguen las peticiones get, post, put, delete
*/
@Provider
public class CORSFilter implements ContainerResponseFilter {


//-----------------------------------------------------------------------------------------------------------------------------------
    /*
        Filter es un metodo que deja que todas las peticiones entrantes sean recibidas por el servidor,
        si no se implementa el servidor da como acceso denegado cualquier peticion
    */
    @Override
    public void filter(ContainerRequestContext creq, ContainerResponseContext cresp){
        cresp.getHeaders().add( "Access-Control-Allow-Origin", "*");
        cresp.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cresp.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        cresp.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    
    }
//-----------------------------------------------------------------------------------------------------------------------------------

}
