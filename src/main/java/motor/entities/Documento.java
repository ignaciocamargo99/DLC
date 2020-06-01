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
@Table(name = "documentos")
@NamedQueries({
                @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
                @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id_documento = :id_documento"),
})
public class Documento implements Serializable, DalEntity
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_documento;
    
    @Column(name = "nombre")
    private String nombreDoc;
    @Column(name="titulo")
    private String titulo;
   

    public Documento()
    {
        
    }

    public Documento(long id_documento, String nombreDoc, HashSet<Posteo> documentoSet) 
    {
        this.id_documento = id_documento;
        this.nombreDoc = nombreDoc;
    }
    
    public Documento(long id_documento, String nombreDoc) 
    {
        this.id_documento = id_documento;
        this.nombreDoc = nombreDoc;
    }
    
    public Documento(String titulo, String nombreDoc) 
    {
        this.titulo = titulo;
        this.nombreDoc = nombreDoc;
    }

    public long getId_documento() {
        return id_documento;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }


    public void setId_documento(long id_documento) {
        this.id_documento = id_documento;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    @Override
    public String toString() {
        return "Documento{" + "id_documento=" + id_documento + ", nombreDoc=" + nombreDoc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombreDoc);
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
        final Documento other = (Documento) obj;

        if (!Objects.equals(this.nombreDoc, other.nombreDoc)) {
            return false;
        }

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }        
}
