/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import motor.entities.Documento;

/**
 *
 * @author nacho
 */
public class Indexador {
     private static String directorio = "documentos/DocumentosTP1/00ws110";
     private EntityManager manager;
    
    private File[] getArchivosDesde(String dirName) {
        File dir = new File(dirName);
        
        return dir.listFiles((dir1, filename) -> filename.endsWith(".txt"));
        //Lista los archivos que terminan en txt que estan dentroo de la carpeta representada por el objeto DIR de la clase FILE
    }
    private Set<Documento> getDocEnCarpeta () throws FileNotFoundException{
        File[] files = getArchivosDesde("documentos/DocumentosTP1/");
        HashSet<Documento> documentos = new HashSet<>();
        for (File f : files) {
            String arch = f.getName();
            Scanner sc = new Scanner(f);
            String titulo = "";
            if (sc.hasNextLine())
                do {
                    titulo = sc.nextLine();
                }while (titulo.length() < 2 && sc.hasNextLine());
            sc.close();
            Documento aux = new Documento(titulo, arch);
            documentos.add(aux);
            //Agregar a la BD el titulo ...
            manager.getTransaction().begin();
            manager.persist(aux);
            manager.getTransaction().commit();
            
                    
            System.out.println("titulo: " + titulo);
            break;
        }
        
        return documentos;
    }
    
    
/*    public static void main(String[] args) throws FileNotFoundException {
            Set<Documento> doc = getDocEnCarpeta();
    }
  */  
    

}
