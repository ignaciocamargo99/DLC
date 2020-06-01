
package motor.apisrest;

import java.io.FileNotFoundException;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import motor.controller.Indexador;
import motor.dal.*;
import motor.entities.Documento;

@Path("/index")
public class IndexEndpoint 
{
    @Inject private DocumentoDAO documentoDAO;
    @Inject private TerminoDAO terminoDAO;
    @Inject private PosteoDAO posteoDAO;
    
     /**
     * la api realiza la indexación de todos los documentos, terminos y posteos
     * @return lista de objetos Documento con los documentos que se indexaron 
     */
    @GET
    public Response indexar() throws FileNotFoundException
    {
        Indexador indexador = new Indexador();
        Set<Documento> docs = indexador.getDocEnCarpeta(documentoDAO,terminoDAO,posteoDAO);
        
        return Response.ok(docs).build();
    } 
}