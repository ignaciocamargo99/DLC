/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor.commons.dal;
import java.util.List;
/**
 *
 * @author nacho
 */
public interface Dao<E extends DalEntity, K> 
{
    void update(E pData);

    void delete(K pKey);
    
    E create(E pData);

    E retrieve(K pKey);

    List<E> findAll();
    
}

