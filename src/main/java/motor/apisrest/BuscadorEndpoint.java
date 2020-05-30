/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import motor.controller.Resultado;
import motor.controller.Buscador;
import motor.dal.DocumentoDAO;
import motor.dal.PosteoDAO;
import motor.entities.Posteo;

/**
 *
 * @author mateo
 */

@Path("/search")
public class BuscadorEndpoint {
    
    private Resultado res;
    @Inject DocumentoDAO documentoDAO;
    @Inject PosteoDAO posteoDAO;
    
    @PersistenceContext(unitName="JPA_PU")
    protected EntityManager entityManager;
    
    @GET
    @Path("/param/{clave}")
    @Produces("application/json")
    public Response buscarPorID(@PathParam("clave") String clave)
    {
        Buscador search = new Buscador();
        System.out.println("clave: " + clave);
        List<Resultado> resultados = search.buscar(clave, documentoDAO, posteoDAO);
        System.out.println("Resultados: " +resultados);
        
        if(resultados.isEmpty())
        {
            
            res = new Resultado("nada.txt", "sin resultados", "null", -1);
            return Response.ok(res).build();
            //return Response.status(Response.Status.NOT_FOUND).build();
            
        }
        else
        {
            return Response.ok(resultados).build();
        }           
    }
    
    
    @GET
    @Path("/prueba")
    @Produces("application/json")
    public Response buscarPorID()
    {
        // Recordar respetar el nombre que se pone en el namedQuery despues de los dos puntos
        List<Posteo> posts = posteoDAO.findByFilter("nombre", "project");
        
        if(posts.isEmpty())
        {
            
            res = new Resultado("nada.txt", "sin resultados", "null", -1);
            return Response.ok(res).build();
            //return Response.status(Response.Status.NOT_FOUND).build();
            
        }
        else
        {
            return Response.ok(posts).build();
        }           
    }
    
    
    
}
