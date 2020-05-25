
package motor.apisrest;

import java.io.FileNotFoundException;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import motor.controller.Indexador;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;

@Path("/index")
public class IndexEndpoint 
{
    @Inject private DocumentoDAO documentoDAO;
    
    @GET
    public Response indexar() throws FileNotFoundException
    {
        Indexador indexador = new Indexador();
        Set<Documento> docs = indexador.getDocEnCarpeta(documentoDAO);
        return Response.ok(docs).build();
    } 
}