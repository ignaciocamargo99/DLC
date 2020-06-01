
package motor.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

public class PosteoPK implements Serializable
{
    private Long id_documento;
    private Long id_termino;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id_documento);
        hash = 23 * hash + Objects.hashCode(this.id_termino);
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
        final PosteoPK other = (PosteoPK) obj;
        if (!Objects.equals(this.id_documento, other.id_documento)) {
            return false;
        }
        if (!Objects.equals(this.id_termino, other.id_termino)) {
            return false;
        }
        return true;
    }
    
    
    
}
