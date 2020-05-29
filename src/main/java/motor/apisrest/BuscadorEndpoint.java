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
import motor.dal.PosteoDAO;
import motor.entities.Posteo;

/**
 *
 * @author mateo
 */

@Path("/search")
public class BuscadorEndpoint {
    
    private Resultado res;
    @Inject PosteoDAO posteoDAO;
    
    @PersistenceContext(unitName="JPA_PU")
    protected EntityManager entityManager;
    
    @GET
    @Path("/{clave}")
    @Produces("application/json")
    public Response buscarPorID(@PathParam("clave") String clave)
    {
        String jpql = "SELECT d FROM Documento d WHERE d.nombreDoc = :nombre";
        Query query = entityManager.createQuery(jpql); 
        query.setParameter("nombre", clave);
        List<Resultado> resultados = query.getResultList();
        
        if(resultados.isEmpty())
        {
            res = new Resultado("sin resultados", "null", -1);
            return Response.ok(res).build();
            
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
            return Response.ok("No hay nada por aca").build();
            
        }
        else
        {
            return Response.ok(posts).build();
        }           
    }
    
    
    
}
