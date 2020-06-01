
package motor.entities;

import java.util.*;
import javax.enterprise.context.ApplicationScoped;
import motor.dal.TerminoDAO;

@ApplicationScoped
public class Vocabulario {
    
    private static Hashtable <String,Termino> terminos = new Hashtable <String,Termino>();

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
        terminos = new Hashtable <String,Termino>();
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
        return getTerminos().containsValue(value);
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
    
    public static void recuperarVocabulario(TerminoDAO term){
        List<Termino> termin = term.findAll();
        for (Termino t : termin)
        {
            terminos.put(t.getNombre(),t);
        }
    }
    
    public static void addIdf(String t)
    {
        getTerminos().get(t).setIdf(getTerminos().get(t).getIdf()+1);
    }
    
    public static void actualizarMaxTf(String t, int n)
    {
        getTerminos().get(t).actMax_tf(n);
    }
}
