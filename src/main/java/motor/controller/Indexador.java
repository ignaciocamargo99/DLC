
package motor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import motor.dal.DocumentoDAO;
import motor.entities.Documento;

public class Indexador {
     private final String directorio = "C:\\Users\\nacho\\Desktop\\DLC\\DocumentosIndexados";

    public Indexador() 
    {
        
    }
     
    public static File[] getArchivosDesde(String dirName) 
    {
        File dir = new File(dirName);
        //Lista los archivos que terminan en txt que estan dentroo de la carpeta representada por el objeto DIR de la clase FILE
        return dir.listFiles((dir1, filename) -> filename.endsWith(".txt"));
    }

    public Set<Documento> getDocEnCarpeta (DocumentoDAO documentoDAO) throws FileNotFoundException
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

                System.out.println(newInstance);
            } 
            else 
            {
                System.out.println("...---...El archivo ya existe en la base de datos...---...");
            }
        } 
        return documentos;
    }
}
