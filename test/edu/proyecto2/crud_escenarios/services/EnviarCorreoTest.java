/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Tania
 */
public class EnviarCorreoTest {
    @Test
    public void testEnvioExitoso() {
        String objCorreo = "{\"destinatarios\":[\"appReservasCDU@gmail.com\"],\"asunto\":\"Correo de prueba\",\"cuerpo\":\"Esta es una prueba enviando correo desde java\"}";
        EnviarCorreoRest instance = new EnviarCorreoRest();
        //Deebería retornar true porque los datos son correctos
        boolean expResult = true;
        boolean result = instance.enviarCorreo(objCorreo);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEnviarErroneo1() {
        String objCorreo = "{\"destinatarios\":[],\"asunto\":\"Correo de prueba\",\"cuerpo\":\"Esta es una prueba enviando correo desde java\"}";
        EnviarCorreoRest instance = new EnviarCorreoRest();
        //Debería retornar false porque la lista de destinatarios está vacía
        boolean expResult = false;
        boolean result = instance.enviarCorreo(objCorreo);
        assertEquals(expResult, result);
    }
    @Test
    
    public void testEnviarErroneo2() {
        String objCorreo = "{\"destinatarios\":[\"appReservasCDU@gmail.com\"],\"asunto\":\"\",\"cuerpo\":\"Esta es una prueba enviando correo desde java\"}";
        EnviarCorreoRest instance = new EnviarCorreoRest();
        //Debería retornar false porque el asunto no puede estar vacío
        boolean expResult = false;
        boolean result = instance.enviarCorreo(objCorreo);
        assertEquals(expResult, result);
    }
    @Test
    public void testEnvioErroneo3() {
        String objCorreo = "{\"destinatarios\":[\"appReservasCDU@gmail.com\"],\"asunto\":\"Correo de prueba\",\"cuerpo\":\"\"}";
        EnviarCorreoRest instance = new EnviarCorreoRest();
        //Deebería retornar false porque el cuerpo del correo no puede ir vacío
        boolean expResult = false;
        boolean result = instance.enviarCorreo(objCorreo);
        assertEquals(expResult, result);
    }
}
