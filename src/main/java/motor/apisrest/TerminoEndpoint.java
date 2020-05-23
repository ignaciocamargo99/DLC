package motor.apisrest;


import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import motor.entities.Termino;

@Path("/term")
public class TerminoEndpoint {
    
    @GET
    public Response prueba(){
        ArrayList a = new ArrayList();
        
        a.add("hola");
        a.add("esto es una prueba de");
        a.add("vista de arreglos");
        a.add("sin usar clases de mapeo");
        
        return Response.ok(a).build();
    }
    
    @GET
    @Path("/{cod}")
    public Response buscarTermino(@PathParam("cod") int cod){
        Termino resp = null;
        
        if (cod == 1) resp = new Termino(1, "hola");
        else if (cod == 2) resp = new Termino(2, "prueba");
        
        if (resp != null) return Response.ok(resp).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
