/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.web;

import cl.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author roman
 */
@WebListener
public class ContextoPersona implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Persona> list= new ArrayList();
        list.add(new Persona("1-1", "Juan Perez", "Administrador", "jp@gmail.com", "123", true));
        list.add(new Persona("1-2", "Maria Reyes", "Persona", "mr@gmail.com", "123", false));
        list.add(new Persona("1-3", "Tomas Torres", "Administrador", "tt@gmail.com", "123", true));
        list.add(new Persona("1-4", "Hector Roa", "Persona", "hr@gmail.com", "123", true));
        list.add(new Persona("1-5", "Anibal Suazo", "Persona", "as@gmail.com", "123", true));
        sce.getServletContext().setAttribute("data", list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
