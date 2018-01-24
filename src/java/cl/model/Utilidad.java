/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author roman
 */
@ApplicationScoped
public class Utilidad implements IUtilidad{

    @Override
    public Persona buscar(String rut, List<Persona> list) {
        for (Persona persona : list) {
            if (persona.getRut().equals(rut)) {
                return persona;
            }
        }
        return null;
    }

    @Override
    public Persona loguear(String rut, String clave, List<Persona> list) {
        for (Persona persona : list) {
            if (persona.getRut().equals(rut) && persona.getClave().equals(clave) && persona.isActivo()) {
                return persona;
            }
        }
        return null;
    }
    
}
