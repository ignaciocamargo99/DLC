/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.apisrest;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import motor.entities.Documento;
import motor.entities.Posteo;
import motor.entities.Termino;

/**
 *
 * @author mateo
 */
@Path("/posteos")
public class PosteoEndpoint {
    
    @GET
    public Response obtenerTodas(){
       ArrayList<Posteo> salida = new ArrayList<>();
       salida.add(new Posteo(new Termino(1, "term_palabra"), new Documento(1, "doc_prueba")));
       return Response.ok(salida).build();
    }
}
