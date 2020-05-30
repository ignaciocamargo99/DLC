/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.controller;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import motor.entities.Posteo;
import motor.entities.Termino;
import motor.controller.TSBHeap;
import motor.controller.Resultado;
import motor.dal.DocumentoDAO;
import motor.dal.PosteoDAO;
import motor.entities.Documento;
import org.json.*;

/**
 *
 * @author nacho
 */
public class Buscador {
    
    public Buscador() {
    }
    
    public int calcularPeso(int tf, int idf, DocumentoDAO documentoDAO){
        int n = 535;
        
        List<Documento> documentos = documentoDAO.findAll();
        n = documentos.size();
        
        return (int)(tf * Math.log(n/idf));
    }
    
    public ArrayList<Resultado> buscar(String busqueda, DocumentoDAO documentoDAO, PosteoDAO posteoDAO){ 
        //separa por espacios y crea array
        String []terminos = busqueda.split("'*[^\\p{IsAlphabetic}']+'*");
        
        ArrayList<Resultado> resultados = new ArrayList();
       
        for (String t : terminos) {
            t = t.toLowerCase();
            
            //Imprime las palabras de la busqueda
            System.out.println(t);
            
            //Realizar consula de posteos por Termino
            //llamar query...
                       //tf, t.nombre, t.max_tf, t.idf, d.nombreDoc, d.titulo
            JSONArray posteos = new JSONArray(posteoDAO.findByFilter("nombre", t));
            if (posteos.length() == 0){
                continue;
            }
            
            // Imprimir primer elemento del array
            System.out.print("con JSONArray: " + new JSONArray(posteos.get(0).toString()).get(0));
            
            
            String bool = "";
            //recorre los posteos
            for(int j = 0; j < posteos.length(); j++){
                JSONArray p = new JSONArray(posteos.get(j).toString());
                
                int tf = Integer.parseInt(p.get(0).toString());
                String nombreTerm = p.get(1).toString();
                int max_tf = Integer.parseInt(p.get(2).toString());
                int idf = Integer.parseInt(p.get(3).toString());
                String nombreDoc = p.get(4).toString();
                String titulo = p.get(5).toString();
                
                //supuestamente el max_tf sirve para descartar opciones de antemano y ahorrar procesamiento
                if ((double) tf / (double) max_tf > 0.33){
                    //comprobar si existe resultado para el documento del posteo i
                    Resultado aux;
                    aux = new Resultado(nombreDoc, titulo, nombreTerm, calcularPeso(tf, idf, documentoDAO));
                    System.out.println(nombreDoc);
                    
                    if (!resultados.isEmpty() && resultados.contains(aux)){
                        //si existe calcular peso y usar metodo agregarTermino(termino, peso);
                        resultados.get(resultados.indexOf(aux)).agregarTermino(aux.getTermino().get(0), aux.getPeso());

                        bool = "False";

                    } else {
                        //sino calcular peso, crear nuevo resultado y agregar al heap
                        resultados.add(aux);
                        bool = "True";
                    }

                    System.out.println(j + bool);
                }

                Collections.sort(resultados);
            }
        }
        
        return resultados;

    }
}
