/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import javax.persistence.Table;

/**
 *
 * @author mateo
 */
@Table(name = "posteos")
public class Posteo {
    private int id_termino;
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
    
    
}
