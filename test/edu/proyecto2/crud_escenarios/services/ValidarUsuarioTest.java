/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import edu.proyecto2.crud_escenarios.bean.UsuarioBean;

/**
 *
 * @author ASUS
 */
public class ValidarUsuarioTest {
    
    /**
     * Test of validarAdmin method, of class EscenarioRest.
     */
    @Test
    public void testValidarAdminExitoso() {
        System.out.println("Test de validacion de usuario exitoso");
        EscenarioRest instance = new EscenarioRest();
        String expResult = "true";
        String result = instance.validarAdmin("admin");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validarAdmin method, of class EscenarioRest.
     */
    @Test
    public void testValidarAdminNoExitoso() {
        System.out.println("Test de validacion de usuario no exitoso");
        EscenarioRest instance = new EscenarioRest();
        String expResult = "false";
        String result = instance.validarAdmin("cristian");
        assertEquals(expResult, result);
    }
}
