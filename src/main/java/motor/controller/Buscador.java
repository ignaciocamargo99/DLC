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
import motor.entities.Posteo;
import motor.entities.Termino;
import motor.controller.TSBHeap;
import motor.controller.Resultado;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;

/**
 *
 * @author nacho
 */
public class Buscador {
    
    @Inject private Documento doc;
    @Inject private DocumentoDAO documentoDAO;

    public Buscador() {
    }
    
    static public int calcularPeso(int tf, int idf){
        int n = 535;
        /*
        List<Documento> documentos = documentoDAO.findAll();
        n = documentos.size();
        */
        return (int)(tf * Math.log(n/idf));
    }
    
    static public ArrayList<Resultado> buscar(String busqueda){ 
        //separa por espacios y crea array
        //String palabras[] = cadena.split("\\s+");
        String []terminos = busqueda.split("'*[^\\p{IsAlphabetic}']+'*");
        
        ArrayList<Resultado> resultados = new ArrayList();
        //TSBHeap<Resultado> heap = new TSBHeap();
       
        for (String t : terminos) {
            t = t.toLowerCase();
            
            //Imprime las palabras de la busqueda
            System.out.println(t);
            
            //Realizar consula de posteos por Termino
            //llamar query...
            ArrayList posteos = new ArrayList();
            posteos.add("hola");
            posteos.add("hola");
            posteos.add("como");
            posteos.add("estas");
            posteos.add("?");
            
            int i = 0;
            String a = "0";
            
            for (Object p : posteos) {
                //comprobar si existe resultado para el documento del posteo i
                Resultado aux = new Resultado((String) p, t, calcularPeso(1, 1));
                
                if (!resultados.isEmpty() && resultados.contains(aux)){
                    //si existe calcular peso y usar metodo agregarTermino(termino, peso);
                    resultados.get(resultados.indexOf(aux)).agregarTermino(aux.getTermino().get(0), aux.getPeso());
                    
                    a = "False";
                    
                } else {
                    //sino calcular peso, crear nuevo resultado y agregar al heap
                    resultados.add(aux);
                    a = "True";
                }
                
                System.out.println(i + a);
                i++;
            }
            
            Collections.sort(resultados);
            /*
            for (Resultado r : resultados){
                heap.add(r);
            }
            */
        }
        
        return resultados;

    }
    
    public static void main(String [] sax){
        System.out.println(buscar("a, hoáTsFd"));
    }
}
