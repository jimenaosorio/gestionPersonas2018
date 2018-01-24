/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.util.List;

/**
 *
 * @author roman
 */
public interface IUtilidad {
    Persona buscar(String rut, List<Persona> list);
    Persona loguear(String rut, String clave, List<Persona> list);
}
