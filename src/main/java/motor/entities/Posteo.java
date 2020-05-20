/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import motor.commons.dal.DalEntity;

/**
 *
 * @author mateo
 */
@Entity
@Table(name = "posteos")
@NamedQueries(
    {
        @NamedQuery(name = "posteos.findAll", query = "SELECT t.id_termino, t.nombre, d.id_documento, d.nombre, p.tf\n" +
                            "FROM posteos AS p\n" +
                            "JOIN terminos AS t ON t.id_termino = p.id_termino\n" +
                            "JOIN documentos AS d ON d.id_documento = p.id_documento"),
        @NamedQuery(name = "posteos.findById", query = "SELECT m FROM posteos m WHERE m.id_termino = :id_termino AND m.id_documento = :id_documento"),
       
    })
public class Posteo implements Serializable, DalEntity{
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_termino")
    private Termino termino;
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_documento")
    private Documento documento;
    
    private int tf;

    public Posteo(){
    }

    public Posteo(Termino termino, Documento documento) {
        this(termino, documento, 0);
    }

    public Posteo(Termino termino, Documento documento, int tf) {
        this.termino = termino;
        this.documento = documento;
        this.tf = tf;
    }
    
    
    
    public Termino getTermino() {
        return termino;
    }

    public void setTermino(Termino termino) {
        this.termino = termino;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.termino);
        hash = 67 * hash + Objects.hashCode(this.documento);
        hash = 67 * hash + this.tf;
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
        final Posteo other = (Posteo) obj;
        if (this.tf != other.tf) {
            return false;
        }
        if (!Objects.equals(this.termino, other.termino)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Posteo{" + "termino=" + termino + ", documento=" + documento + ", tf=" + tf + '}';
    }
}
    
    /*
    @Id
    private int id_termino;
    @Id
    private int id_documento;
    private int tf;
        
    public Posteo(){
        this(0, 0, 0);
    }
    
    public Posteo(int idTermino, int idDocumento){
        this(idTermino, idDocumento, 0);
    }
    
    public Posteo(int idTermino, int idDocumento, int tf){
        id_termino = idTermino;
        id_documento = idDocumento;
        this.tf = tf;
    }

    public int getId_termino() {
        return id_termino;
    }

    public void setId_termino(int id_termino) {
        this.id_termino = id_termino;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    
    
    @Override
    public String toString() {
        return "Posteo{" + "id_termino=" + id_termino + ", id_documento=" + id_documento + ", tf=" + tf + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_termino;
        hash = 59 * hash + this.id_documento;
        hash = 59 * hash + this.tf;
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
        final Posteo other = (Posteo) obj;
        if (this.id_termino != other.id_termino) {
            return false;
        }
        if (this.id_documento != other.id_documento) {
            return false;
        }
        if (this.tf != other.tf) {
            return false;
        }
        return true;
    }
    */
