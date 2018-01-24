/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author alumnossur
 */
@Singleton
public class PersonaBean implements PersonaBeanLocal {
    
    private List<Persona> list=new ArrayList<>();

    public PersonaBean() {
        list.add(new Persona("111-1","Ana","persona","ana@gmail.com","111",true));
        list.add(new Persona("222-2","Martin","persona","martin@gmail.com","222",true));
        list.add(new Persona("333-3","Jimena","administrador","jimena@gmail.com","333",true));
    }
    
    

    @Override
    public Persona buscar(String rut) {
        for(Persona p:list){
            if(p.getRut().equals(rut)){
                return p;
            }
        }
        return null;
    }

    @Override
    public Persona logear(String rut, String clave) {
        for(Persona p:list){
            if(p.getRut().equals(rut) && p.getClave().equals(clave) && p.isActivo()){
                return p;
            }
        }
        return null;
    }

    @Override
    public void editar(String rut, boolean activo) {
        Persona p=buscar(rut);
        p.setActivo(activo);
    }

    @Override
    public List<Persona> getPersonaList() {
        return list;
    }

    @Override
    public String add(Persona persona) {
        String mensaje;
        Persona p=buscar(persona.getRut());
        if(p==null) {
            list.add(persona);
            mensaje="persona agregada";
        }
        else mensaje="El RUT ya esta registrado";
        return mensaje;
    }

    
}
