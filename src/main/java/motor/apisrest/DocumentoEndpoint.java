package motor.apisrest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;

@Path("/doc")
public class DocumentoEndpoint 
{
    @Inject private Documento doc;
    @Inject private DocumentoDAO documentoDAO;
    
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
            
            return Response.ok("No hay datos, cargue datos a su BD...").build();
            
        }
        else
        {
            return Response.ok(documentos + "\n Funcionando con JPA 'JPA_PU'y connection pool motorbusqueda").build();
        }           
    } 
}
