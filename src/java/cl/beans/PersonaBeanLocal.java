
package cl.beans;

import cl.model.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alumnossur
 */
@Local
public interface PersonaBeanLocal {
    Persona buscar(String rut);
    Persona logear(String rut, String clave);
    void editar(String rut, boolean activo);
    List<Persona> getPersonaList();
    String add(Persona persona);
    
}
