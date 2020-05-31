/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.apisrest;

import java.util.ArrayList;
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
    
    /**
     * 
     * @param clave palabras/términos que se desean buscar en los documentos
     * @return  lista de objetos Resultado con los documentos que se encontraron que correspondan con la clave especificada
     */
    @GET
    @Path("/param/{clave}")
    @Produces("application/json")
    public Response buscarPorClave(@PathParam("clave") String clave)
    {
        Buscador search = new Buscador();
        System.out.println("clave: " + clave);
        List<Resultado> resultados = search.buscar(clave, documentoDAO, posteoDAO);
        System.out.println("Resultados: " +resultados);
        
        if(resultados.isEmpty())
        {
            
            res = new Resultado("nada.txt", "sin resultados", "no se encontro el término", -1);
            
            List<Resultado> notFound = new ArrayList<Resultado>();
            notFound.add(res);
            
            return Response.ok(notFound).build();
            
        }
        else
        {
            return Response.ok(resultados).build();
        }           
    }    
}
