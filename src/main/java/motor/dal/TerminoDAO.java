
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Termino;

public class TerminoDAO extends DaoEclipseLink<Termino,Long> {
    
    public TerminoDAO() {
        super(Termino.class);
    }
}
