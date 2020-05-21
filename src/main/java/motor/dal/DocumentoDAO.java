/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Documento;

/**
 *
 * @author nacho
 */
public class DocumentoDAO extends DaoEclipseLink<Documento, Long>
{
    public DocumentoDAO()
    {
        super(Documento.class);
    }
    
    
}
