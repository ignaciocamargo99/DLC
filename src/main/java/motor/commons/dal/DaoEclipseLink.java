
package motor.commons.dal;

import java.util.List;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import motor.commons.exceptions.TechnicalException;
import javax.transaction.Transactional;

public abstract class DaoEclipseLink<E extends DalEntity, K> implements IDao<E, K> 
{
    //@Inject
    @PersistenceContext(unitName="JPA_PU")
    protected EntityManager entityManager;

    private final Class<E> entityClass;

    public DaoEclipseLink(Class<E> entityClass) //
    {
        this.entityClass = entityClass;
       
    }

    protected Class<E> getEntityClass()
    {
        return entityClass;
    }

    @Override
    @Transactional
    public E create(E pData)
    {
        try
        {
            entityManager.persist(pData);
            entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

        return pData;
    }

    @Override
    @Transactional
    public void update(E pData)
    {
        try
        {
            E managed = entityManager.merge(pData);
            entityManager.persist(managed);
            entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }
    }

    @Override
    @Transactional
    public void delete(K pKey)
    {
        try
        {
            entityManager.remove(retrieve(pKey));
            entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }
    }

    @Override
    public E retrieve(K pKey)
    {
        return entityManager.find(getEntityClass(), pKey);
    }

    @Override
    public List<E> findAll()
    {
        try
        {
            String className = getEntityClass().getSimpleName();
            Query query = entityManager.createNamedQuery(className + ".findAll");
            return query.getResultList();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

    }
    public List<E> findByFilter(String filter)
    {
        try
        {
            String className = getEntityClass().getSimpleName();
            Query query = entityManager.createNamedQuery(className + ".findByFilter")
                .setParameter(":filter", filter);

            return query.getResultList();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

    }   
}
