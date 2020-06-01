
package motor.commons.dal;
import java.util.List;
/**
 *
 * Interfaz que define los métodos mínimos a ser implementados por un dao para una entidad mapeada a la base de datos
 *
 * @param <E> Entidad para la cual se implementa el presente dao
 * @param <K> Clave de la entidad representada por el dao
 */
public interface IDao<E extends DalEntity, K> 
{
    // Modificar datos
    void update(E pData);
    
    // Eliminar datos
    void delete(K pKey);
    
    // Crear datos
    E create(E pData);

    // Recupara un dato existente con la key K
    E retrieve(K pKey);
    
    // Lista para obtener una lista de datos de la bd
    List<E> findAll();
    
}

