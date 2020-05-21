/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplomain;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nacho
 */
public class pruebaJDBC 
{
    private Connection conexion = null;
    public pruebaJDBC() throws SQLException
    {
        try
        {
            conectar();
            consulta("Harry Potter");
        }
        finally
        {
            cerrar();
        }
    }
    
    public void conectar() throws SQLException
    {
        String jdbc = "jdbc:postgresql://localhost:5432/dlc_tp_db";
        conexion = DriverManager.getConnection(jdbc, "postgres", "talleres123");
        System.out.println("Se conecta");
    }
    
    private void consulta(String id_Documento) throws SQLException
    {
        String query = "SELECT * FROM documentos d WHERE d.nombre = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, id_Documento);
        ResultSet set = statement.executeQuery();
        while(set.next())
        {
            int idDoc = set.getInt("id_documento");
            String nombreDoc = set.getString("nombre");
            System.out.println("Id Doc:" + idDoc + ", Nombre:" + nombreDoc);
        }
        set.close();
        statement.close();
    }
    
    public void cerrar() throws SQLException
    {
        if(conexion != null)
        {
            conexion.close();
        }
    }
        
    public static void main(String[] args) 
    {
        try 
        {
            new pruebaJDBC();
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error: " + ex.getStackTrace().toString());;
        }  
    }
}
