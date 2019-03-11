/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.services;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cristian_Arias
 */
public class EscenarioRestTest {
    
    public EscenarioRestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAllEspaciosdeportivos method, of class EscenarioRest.
     */
    @Test
    public void testFindAllEspaciosdeportivos() {
        System.out.println("findAllEspaciosdeportivos");
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.findAllEspaciosdeportivos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllDeportes method, of class EscenarioRest.
     */
    @Test
    public void testFindAllDeportes() {
        System.out.println("findAllDeportes");
        EscenarioRest instance = new EscenarioRest();
        List<Deporte> expResult = null;
        List<Deporte> result = instance.findAllDeportes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllReservas method, of class EscenarioRest.
     */
    @Test
    public void testFindAllReservas() {
        System.out.println("findAllReservas");
        EscenarioRest instance = new EscenarioRest();
        List<ReservaEspacio> expResult = null;
        List<ReservaEspacio> result = instance.findAllReservas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllEscenariosDeportes method, of class EscenarioRest.
     */
    @Test
    public void testFindAllEscenariosDeportes() {
        System.out.println("findAllEscenariosDeportes");
        int id = 0;
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.findAllEscenariosDeportes(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEspacioDeportivo method, of class EscenarioRest.
     */
    @Test
    public void testCreateEspacioDeportivo() {
        System.out.println("createEspacioDeportivo");
        String espacioJson = "";
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.createEspacioDeportivo(espacioJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarReservaEspacio method, of class EscenarioRest.
     */
    @Test
    public void testGuardarReservaEspacio() {
        System.out.println("guardarReservaEspacio");
        String reservaJson = "";
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.guardarReservaEspacio(reservaJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEspacioDeportivo method, of class EscenarioRest.
     */
    @Test
    public void testUpdateEspacioDeportivo() {
        System.out.println("updateEspacioDeportivo");
        String espacioJson = "";
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.updateEspacioDeportivo(espacioJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEspacioDeportivo method, of class EscenarioRest.
     */
    @Test
    public void testDeleteEspacioDeportivo() {
        System.out.println("deleteEspacioDeportivo");
        int id = 0;
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.deleteEspacioDeportivo(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteReserva method, of class EscenarioRest.
     */
    @Test
    public void testDeleteReserva() {
        System.out.println("deleteReserva");
        int id = 0;
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.deleteReserva(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservaEspacio method, of class EscenarioRest.
     */
    @Test
    public void testGetReservaEspacio() {
        System.out.println("getReservaEspacio");
        int id = 0;
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.getReservaEspacio(id,"admin");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEspaciosReservados method, of class EscenarioRest.
     */
    @Test
    public void testGetEspaciosReservados() {
        System.out.println("getEspaciosReservados");
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.getEspaciosReservados("");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuariosNoValidos method, of class EscenarioRest.
     */
    @Test
    public void testGetUsuariosNoValidos() {
        System.out.println("getUsuariosNoValidos");
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.getUsuariosNoValidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CambiarEstadoUsuario method, of class EscenarioRest.
     */
    @Test
    public void testCambiarEstadoUsuario() {
        System.out.println("CambiarEstadoUsuario");
        String usuarioJson = "";
        EscenarioRest instance = new EscenarioRest();
        String expResult = "";
        String result = instance.CambiarEstadoUsuario(usuarioJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
