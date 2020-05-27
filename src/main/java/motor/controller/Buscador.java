/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.controller;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Hashtable;
import java.util.Scanner;
import motor.entities.Posteo;
import motor.entities.Termino;
import motor.controller.TSBHeap;
import motor.controller.Resultado;

/**
 *
 * @author nacho
 */
public class Buscador {

    public Buscador() {
    }
    
    static void buscar(String cadena){ 
        //separa por espacios y crea array
        //String palabras[] = cadena.split("\\s+");
        String palabras[] = cadena.split("'*[^\\p{IsAlphabetic}']+'*");
        
        TSBHeap<Resultado> resultados = new TSBHeap();
       
        for (String f : palabras) {
            f = f.toLowerCase();
            
            //Imprime las palabras de la busqueda
            //System.out.println(f);
            
            //Realizar consula de posteos por Termino
            //llamar query...
            Array[] posteos = new Array[30];
            
            for (Array p : posteos) {
                //comprobar si existe resultado para el documento del posteo i
                //si existe calcular peso y usar metodo agregarTermino(termino, peso);
                //sino calcular peso, crear nuevo resultado y agregar al heap
            }
            
            
        }
        

    }
    
    public static void main(String [] sax){
        buscar("a, hoáTsFd");
    }
}
