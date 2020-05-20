/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import java.util.Objects;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Table(name = "documentos")
public class Documento 
{
    private int id_documento;
    private String nombre;

    public Documento() {
    }

    public int getId_documento() 
    {
        return id_documento;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setId_documento(int id_documento) 
    {
        this.id_documento = id_documento;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public Documento(int id_documento, String nombre) 
    {
        this.id_documento = id_documento;
        this.nombre = nombre;
    }

    @Override
    public String toString() 
    {
        return "Documento{" + "id_documento=" + id_documento + ", nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 59 * hash + this.id_documento;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Documento other = (Documento) obj;
        if (this.id_documento != other.id_documento) 
        {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) 
        {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
}
