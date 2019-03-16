/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;

import com.google.gson.Gson;
import edu.proyecto2.crud_escenarios.bean.CorreoBean;
import edu.proyecto2.crud_escenarios.data.Correo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tania
 */

@Path("enviarCorreoRest")
public class EnviarCorreoRest {
    private CorreoBean correoBean=new CorreoBean();
    
    /*
        * Servicio que recibe un String en formato Gson el cual contiene una lista de correos destinatarios, el asunto y cuerpo de un email.
        * Esta función envía correos a los destinatarios especificados en la lista de destinatarios.
        * Retorna true o false de acuerdo a si el envío fue exitoso o no.
    */
    @POST
    @Path("/enviarCorreo")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean enviarCorreo(String correoJson){
        final Gson gson = new Gson();
        final Correo correoObj = gson.fromJson(correoJson, Correo.class);
        
        if(correoObj.getDestinatarios().isEmpty() || correoObj.getAsunto().equals("") || correoObj.getCuerpo().equals("")){
            return false;
        }
        else{
            return this.correoBean.enviarCorreo(correoObj); 
        }
    }
}
