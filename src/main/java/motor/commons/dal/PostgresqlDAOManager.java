/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.commons.dal;


import java.sql.*;

/**
 * Conexión con la base de datos
 * @author nacho
 */ 
public class PostgresqlDAOManager implements IConnection
{
    // Cadena de conexion
    public static String cadena = "jdbc:postgresql://localhost:5432/dlc_tp_db";
    
    @Override
    public Connection getConnection()
    {
        try 
        {
            // Cambiar los parametros a sus usuarios y passwords según corresponda
            Connection conn = DriverManager.getConnection(cadena, "postgres", "talleres123");
            conn.setAutoCommit(true);
            Statement statement = conn.createStatement();
            
            statement.close();
            return conn;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
}
