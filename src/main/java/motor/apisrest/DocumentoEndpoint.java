package motor.apisrest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mateo
 */
@Path("/doc")
public class DocumentoEndpoint {
    
    @GET
    public Response hola(){
        return Response.ok("*******WORKING-P1*******").build();
    }
}
