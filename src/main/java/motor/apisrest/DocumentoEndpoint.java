package motor.apisrest;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;

@Path("/doc")
public class DocumentoEndpoint 
{
    @Inject private Documento doc;
    @Inject private DocumentoDAO documentoDAO;
    @PersistenceContext(unitName="JPA_PU")
    protected EntityManager entityManager;
    
    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/mensaje")
    public Response hola() {
        return Response.ok("*******WORKING-P1*******").build();
    }
    
    @GET
    @Path("/todos")
    @Produces("application/json")
    public Response listarTodos()
    {
        List<Documento> documentos = documentoDAO.findAll();
        if(documentos.isEmpty())
        {
            
            doc = new Documento(-1, "esteNoEsUnDoc.txt");
            doc.setTitulo("no se encontraron documentos en la base de datos");
            return Response.ok(doc).build();
            
        }
        else
        {
            return Response.ok(documentos).build();
        }           
    }
    
    @GET
    @Path("/{clave}")
    @Produces("application/json")
    public Response buscarPorID(@PathParam("clave") String clave)
    {
        String jpql = "SELECT d FROM Documento d WHERE d.nombreDoc = :nombre";
        Query query = entityManager.createQuery(jpql); 
        query.setParameter("nombre", clave);
        List<Documento> documentos = query.getResultList();
        
        if(documentos.isEmpty())
        {
            doc = new Documento(-1, "esteNoEsUnDoc.txt");
            doc.setTitulo("no se encontraron documentos en la base de datos");
            return Response.ok(doc).build();
            
        }
        else
        {
            return Response.ok(documentos).build();
        }           
    }
    
    @GET
    @Path("/id/{clave}")
    @Produces("application/json")
    public Response buscarPorID(@PathParam("clave") int clave)
    {
        String jpql = "SELECT d FROM Documento d WHERE d.id_documento = :id";
        Query query = entityManager.createQuery(jpql); 
        query.setParameter("id", clave);
        List<Documento> documentos = query.getResultList(); 
        
        if(documentos.isEmpty())
        {
            
            doc = new Documento(-1, "esteNoEsUnDoc.txt");
            doc.setTitulo("no se encontraron documentos en la base de datos");
            return Response.ok(doc).build();
            
        }
        else
        {
            return Response.ok(documentos).build();
        }           
    } 
}
