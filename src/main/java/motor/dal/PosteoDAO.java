
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Posteo;

public class PosteoDAO extends DaoEclipseLink<Posteo, Integer> {
    public PosteoDAO(){
        super(Posteo.class);
    }    
}
