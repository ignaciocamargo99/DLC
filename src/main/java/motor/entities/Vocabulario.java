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
    private static Vocabulario ourInstance = new Vocabulario();
    
    public static Vocabulario getInstance() {
        return ourInstance;
    }
    
    private static Hashtable <String,Termino> terminos ;

    public static Hashtable<String, Termino> getTerminos() {
        return terminos;
    }

    public static void setTerminos(Hashtable<String, Termino> terminos) {
        terminos = terminos;
    }

    public Vocabulario(Hashtable<String, Termino> terminos) {
        terminos = terminos;
    }
    public Vocabulario(){
        
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.terminos);
        return hash;
    }

    public static boolean contains(Object value) {
        return getTerminos().containsKey(value);
    }

    public boolean containsValue(Object value) {
        return ourInstance.getTerminos().containsValue(value);
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
