/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import motor.commons.dal.DalEntity;

@Entity
@Table(name = "posteos")
@IdClass(PosteoPK.class)

//@NamedQueries(
//    {
//        @NamedQuery(name = "Posteo.findAll", query = "SELECT t.id_termino, t.nombre, d.id_documento, d.nombre, p.tf \n" +
//                            "FROM Posteo p AS p \n" +
//                            "JOIN Termino AS t ON t.id_termino = p.id_termino \n" +
//                            "JOIN Documento AS d ON d.id_documento = p.id_documento"),
//        @NamedQuery(name = "Posteo.findById", query = "SELECT p.* FROM Posteo p WHERE m.id_termino = :id_termino AND m.id_documento = :id_documento"),
//    })
public class Posteo implements Serializable, DalEntity 
{
    private static final long serialVersionUID = 1L;

//    @Id
//    //@Column(name = "id_termino")
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_termino")
//    private Termino termino;
//    
//    @Id
//    //@Column(name = "id_documento")
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_documento")
//    private Documento documento;
    
    
    @Id
    @Column(name = "id_documento")
    private Long id_documento;
    
    @Id
    @Column(name = "id_termino")
    private Long id_termino;

    @Column(name = "tf")
    private int tf;

    public Posteo() 
    {
        
    }

    public Posteo(Long id_documento, Long id_termino, int tf) {
        this.id_documento = id_documento;
        this.id_termino = id_termino;
        this.tf = tf;
    }

    public Long getId_documento() {
        return id_documento;
    }

    public Long getId_termino() {
        return id_termino;
    }

    public int getTf() {
        return tf;
    }

    public void setId_documento(Long id_documento) {
        this.id_documento = id_documento;
    }

    public void setId_termino(Long id_termino) {
        this.id_termino = id_termino;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id_documento);
        hash = 23 * hash + Objects.hashCode(this.id_termino);
        hash = 23 * hash + this.tf;
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
        if (!Objects.equals(this.id_documento, other.id_documento)) {
            return false;
        }
        if (!Objects.equals(this.id_termino, other.id_termino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Posteo{" + "id_documento=" + id_documento + ", id_termino=" + id_termino + ", tf=" + tf + '}';
    }
}