/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Posteo;

/**
 *
 * @author mateo
 */
public class PosteoDAO extends DaoEclipseLink<Posteo, Integer> {
    public PosteoDAO(){
        super(Posteo.class);
    }    
}
