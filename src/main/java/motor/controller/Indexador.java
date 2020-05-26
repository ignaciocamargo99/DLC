
package motor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.inject.Inject;
import motor.dal.*;
import motor.entities.*;
import motor.entities.Termino;

public class Indexador {
     private final String directorio = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MotorBusqueda5\\DLC\\documentos\\prueba\\";
     private static Vocabulario voc = new Vocabulario();
     
    public Indexador() 
    {
        
    }
     
    public static File[] getArchivosDesde(String dirName) 
    {
        File dir = new File(dirName);
        //Lista los archivos que terminan en txt que estan dentroo de la carpeta representada por el objeto DIR de la clase FILE
        return dir.listFiles((dir1, filename) -> filename.endsWith(".txt"));
    }

    public Set<Documento> getDocEnCarpeta (DocumentoDAO documentoDAO, TerminoDAO terminoDAO, PosteoDAO posteoDAO) throws FileNotFoundException
    {
        File[] files = getArchivosDesde(directorio);
        HashSet<Documento> documentos = new HashSet<>();
        for (File f : files) {
            String arch = f.getName();
            Scanner sc = new Scanner(f);
            String titulo = "";
            if (sc.hasNextLine())
            {
                do 
                {
                    titulo = sc.nextLine();
                    System.out.println("titulo: " + titulo);
                }
                while (titulo.length() < 2 && sc.hasNextLine());
            }
            sc.close();
            
            //Agregar a la BD el titulo ...
            Documento aux = new Documento(titulo, arch);
            List<Documento> a = documentoDAO.findAll();

            if (!a.contains(aux))
            {
                Documento newInstance = documentoDAO.create(aux);

                documentos.add(newInstance);
                postearArchivo(newInstance, terminoDAO, posteoDAO);
                //System.out.println(newInstance);
            } 
            else 
            {
                System.out.println("...---...El archivo ya existe en la base de datos...---...");
                //postearArchivo();
            }
            
            //break;
        } 
        return documentos;
    }
    
    
    public void postearArchivo(Documento doc,TerminoDAO terminoDAO,PosteoDAO posteoDAO){
        
        File arch = new File(directorio + doc.getNombreDoc());
        //System.out.println("ruta: " + arch.getAbsolutePath());        
        try (Scanner sc = new Scanner(arch, "ISO-8859-1")) {
            // El delimitador siguiente toma palabras con apóstrofe en medio
            // (por ejemplo O'Higgings, didn't) que a nuestro criterio forman parte de la palabra.
            sc.useDelimiter("'*[^\\p{IsAlphabetic}']+'*");
            // Definimos una lista de terminos con posteos x documento
            Hashtable<String, Termino> terminosDocumento = new Hashtable<String, Termino>();
            Hashtable<String,Posteo > posteosDocumento = new Hashtable<>();
            //key Integer: palabra del termino
            //value Posteo: Posteo(con un tf y una referencia a un documento)
            //Bucle por palabra
            while (sc.hasNext()) {
                String pal = sc.next().toLowerCase();  //Convertimos a minusculas
                //System.out.println("termino: " + pal);
                Termino termino = null;
                if (!pal.equals("")) {
                    //Controlo si ya lo agregué
                    if (!voc.getTerminos().containsKey(pal)) {
                        //Aparece por primera vez en el documento,
                        
                      
                        terminosDocumento.put(pal, new Termino(pal));
                        
                        termino = new Termino(pal);
                        //Se agrego solo la palabra;
                        Termino newInstance = terminoDAO.create(termino);
                        voc.getTerminos().put(pal, newInstance);
                      //  terminosDocumento.put(pal, newInstance);
                        
                       // System.out.println(newInstance);
                   }
                    //Si ya esta agregado, debería aumentar la cant de veces que aparece en el documento...

                    
                    
                    
                    //Controlo si ya esta posteado...
                    if (posteosDocumento.containsKey(pal)) {
                        //Aumento la cant de veces que aparece en el doc
                        posteosDocumento.get(pal).setTf(posteosDocumento.get(pal).getTf() + 1);
                    } else {
                        //Lo agrego a la lista...
                  
                        
                        posteosDocumento.put(pal, new Posteo(doc.getId_documento(),voc.getTerminos().get(pal).getId_termino(),1));
                        //System.out.println("Agregado");
                    }
                
                }
            }
            
            
            //CORREGIR!!!!!!!!!!
            long cantTerminosDoc = (posteosDocumento.size());
            for (Termino termino : terminosDocumento.values()) {
                //Sumo a cada termino su IDF uno,
                termino.setIdf(termino.getIdf()+1);
                
                //terminoDAO.update(termino);
                //Actualizo su maxTF
                //Vocabulario.refreshMaxTf(pala, posteosDocumento.get(pala).getTf());
            }
            //Persistencia
            for (Posteo posteo : posteosDocumento.values()){
                Posteo newIns = posteoDAO.create(posteo);
            }
            
        }catch (FileNotFoundException ex) {
            System.out.println("Archivo inexistente.. " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
