
package motor.commons.dal;

import java.sql.Connection;

/**
 *
 * Interfaz para obtener la conexion
 */
public interface IConnection 
{
    Connection getConnection();
}
