/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.apisrest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;


/**
 *
 * @author mateo
 */
@Path("/index")
public class IndexDAO {
    @Inject private Documento doc;
    @Inject private DocumentoDAO documentoDAO;
    
    @GET
    public Response indexar() throws FileNotFoundException{
        Set<Documento> docs = getDocEnCarpeta();
        return Response.ok(docs).build();
    }
    
    
    private File[] getArchivosDesde(String dirName) {
        File dir = new File(dirName);
        
        return dir.listFiles((dir1, filename) -> filename.endsWith(".txt"));
        //Lista los archivos que terminan en txt que estan dentroo de la carpeta representada por el objeto DIR de la clase FILE
    }
    private Set<Documento> getDocEnCarpeta () throws FileNotFoundException{
        File[] files = getArchivosDesde("/home/mateo/Documentos/Facultad/DLC/TP/DocumentosTP1");
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
            
            //Agregar a la BD el titulo ...
            
            Documento newInstance = documentoDAO.create(aux);
            
            documentos.add(newInstance);
            
            System.out.println(newInstance);
            
                    
            System.out.println("titulo: " + titulo);
            break;
        }
        
        return documentos;
    }
    
}