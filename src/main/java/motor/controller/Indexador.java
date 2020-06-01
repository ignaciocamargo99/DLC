
package motor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import motor.dal.*;
import motor.entities.*;
import motor.entities.Termino;

public class Indexador {
    private final String directorio = "C:\\Users\\nacho\\Desktop\\DLC\\DocumentosIndexados\\";
    private static Vocabulario voc = new Vocabulario();
    private static boolean a = false;
    
    public Indexador() 
    {
        
    }
    
     /**
     * obtiene los documentos desde un directorio local
     * @param dirName
     * @return documentos .txt
     */
    public static File[] getArchivosDesde(String dirName) 
    {
        //Lista los archivos que terminan en txt que estan dentroo de la carpeta representada por el objeto DIR de la clase FILE
        File dir = new File(dirName);
        return dir.listFiles((dir1,filename) -> filename.endsWith(".txt"));
    }
    
     /**
     * indexa cada uno de los documentos 
     * @param documentoDAO
     * @param terminoDAO
     * @param posteoDAO
     * @return documentos indexados
     */
    public Set<Documento> getDocEnCarpeta (DocumentoDAO documentoDAO, TerminoDAO terminoDAO, PosteoDAO posteoDAO) throws FileNotFoundException
    {
        File[] files = getArchivosDesde(directorio);
        HashSet<Documento> documentos = new HashSet<>();
        voc.recuperarVocabulario(terminoDAO);

        for (File f : files) {
            String arch = f.getName();
            Scanner sc = new Scanner(f);
            String titulo = "";
            if (sc.hasNextLine())
            {
                do 
                {
                    titulo = sc.nextLine();
                }
                while (titulo.length() < 2 && sc.hasNextLine());
            }
            sc.close();           
            //Agregar a la BD el titulo
            Documento aux = new Documento(titulo, arch);
            List<Documento> a = documentoDAO.findAll();
            if (!a.contains(aux))
            {
                Documento newInstance = documentoDAO.create(aux);

                documentos.add(newInstance);
                postearArchivo(newInstance, terminoDAO, posteoDAO);
            } 
            else 
            {
                System.out.println("...---...El archivo ya existe en la base de datos...---...");
            }
        } 
        return documentos;
    }
    
     /**
     * realiza el posteo de un documento y lo persiste en la base de datos 
     * junto con los posteos y sus términos respectivos
     * @param doc
     * @param terminoDAO
     * @param posteoDAO
     */
    public void postearArchivo(Documento doc,TerminoDAO terminoDAO,PosteoDAO posteoDAO){
        
        File arch = new File(directorio + doc.getNombreDoc());     
        try (Scanner sc = new Scanner(arch, "ISO-8859-1")) {
            sc.useDelimiter("'*[^\\p{IsAlphabetic}']+'*");
            // Definimos una lista de terminos con posteos x documento
            Hashtable<String, Termino> terminosDocumento = new Hashtable<String, Termino>();
            Hashtable<String,Posteo > posteosDocumento = new Hashtable<>();
            //Bucle por palabra de un documento
            while (sc.hasNext()) {
                String pal = sc.next().toLowerCase(); 
                Termino termino = null;
                if (!pal.equals("")) {
                    //Controlo si ya lo agregué
                    if (!voc.getTerminos().containsKey(pal)) {
                        //Aparece por primera vez en el documento                  
                       termino = new Termino(pal);
                        //Se agrega solo la palabra;
                        Termino newInstance = terminoDAO.create(termino);
                        voc.getTerminos().put(pal, newInstance);
                        terminosDocumento.put(pal, newInstance);
                   }
                    //Si ya esta agregado, aumenta la cantidad de veces que aparece en el documento                                                            
                    //Controlo si ya esta posteado
                    if (posteosDocumento.containsKey(pal)) {
                        //Aumento la cant de veces que aparece en el documento
                        posteosDocumento.get(pal).setTf(posteosDocumento.get(pal).getTf() + 1);
                    } else {
                        //Lo agrego a la lista                                       
                        posteosDocumento.put(pal, new Posteo(doc.getId_documento(),voc.getTerminos().get(pal).getId_termino(),1));
                    }               
                }
            }
            for (String n : posteosDocumento.keySet()) 
            {
                voc.addIdf(n);
                voc.getTerminos().put(n, voc.getTerminos().get(n));
                voc.actualizarMaxTf(n, posteosDocumento.get(n).getTf());
                terminoDAO.update(voc.getTerminos().get(n));
            }
            //Persistencia
            for (Posteo posteo : posteosDocumento.values() ) 
            {
                
                Posteo newIns = posteoDAO.create(posteo);
            }           
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("Archivo inexistente.. " + ex.getMessage());
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
