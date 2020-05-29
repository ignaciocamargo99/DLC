/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import motor.commons.dal.DalEntity;

@Entity
@Table(name = "terminos")
@NamedQueries(
    {
        @NamedQuery(name = "Termino.findAll", query = "SELECT t FROM Termino t"),
        @NamedQuery(name = "Termino.findById", query = "SELECT t FROM Termino t WHERE t.id_termino = :id_termino"),
       
    })
public class Termino implements Serializable, DalEntity 
{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_termino")
    private Long id_termino;
    
    @Column(name = "nombre", length = 64)
    private String nombre;
    
    @Column (name ="idf")
    private int idf;
    
    @Column(name = "max_tf")
    private int max_tf;
    
    
    //private HashSet<Posteo> posteoSet;
    
    //Constructores...
    
    public Termino() {
    }
    
    public Termino(long idPalabra, String palabra, int nr, int maxtf, HashSet<Posteo> posteoSet){
        this.id_termino = idPalabra;
        this.nombre= palabra;
        this.idf = nr;
        this.max_tf = maxtf;
        //this.posteoSet = posteoSet;
    }
    
    public Termino(long idPalabra, String palabra, int nr, int maxtf){
        this.id_termino = idPalabra;
        this.nombre= palabra;
        this.idf = nr;
        this.max_tf = maxtf;
        //this.posteoSet = new HashSet<>();
    }
    
    public Termino(long idPalabra, String palabra){
        this.id_termino = idPalabra;
        this.nombre = palabra;
        this.idf= 1;
        this.max_tf = 0;
        //this.posteoSet= new HashSet<>();
    }
    public Termino(String palabra){
        this.id_termino = (long)-1;
        this.nombre = palabra;
        this.idf = 0;
        this.max_tf = 0;
    } 

    public long getId_termino() {
        return id_termino;
    }

    public void setId_termino(long id_termino) {
        this.id_termino = id_termino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public int getMax_tf() {
        return max_tf;
    }

    public void setMax_tf(int max_tf) {
        this.max_tf = max_tf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id_termino ^ (this.id_termino >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Termino other = (Termino) obj;
        if (this.id_termino != other.id_termino) {
            return false;
        }
        if (this.idf != other.idf) {
            return false;
        }
        if (this.max_tf != other.max_tf) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    public void actMax_tf(int n){
        if (n> max_tf)
        {
            max_tf =n;
        }
    }
    
    
}
