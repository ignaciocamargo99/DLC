
package motor.dal;

import motor.commons.dal.DaoEclipseLink;
import motor.entities.Documento;

public class DocumentoDAO extends DaoEclipseLink<Documento, Long>
{
    public DocumentoDAO()
    {
        super(Documento.class);
    }
}
