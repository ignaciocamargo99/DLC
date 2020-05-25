/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Termino;

/**
 *
 * @author mateo
 */
public class TerminoDAO extends DaoEclipseLink<Termino,Long> {
    
    public TerminoDAO() {
        super(Termino.class);
    }
    
}
