/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.entities;

import java.util.*;

/**
 *
 * @author Lenovo
 */
public class Vocabulario {
    private Hashtable <String,Termino> terminos ;

    public Hashtable<String, Termino> getTerminos() {
        return terminos;
    }

    public void setTerminos(Hashtable<String, Termino> terminos) {
        this.terminos = terminos;
    }

    public Vocabulario(Hashtable<String, Termino> terminos) {
        this.terminos = terminos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.terminos);
        return hash;
    }

    public synchronized boolean contains(Object value) {
        return terminos.contains(value);
    }

    public boolean containsValue(Object value) {
        return terminos.containsValue(value);
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
        final Vocabulario other = (Vocabulario) obj;
        if (!Objects.equals(this.terminos, other.terminos)) {
            return false;
        }
        return true;
    }
    
    
}
