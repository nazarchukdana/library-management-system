package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class EntityRepository<T> {
    private final Class<T> entityClass;
    protected EntityManager entityManager;

    public EntityRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = Persistence.createEntityManagerFactory("LibraryManagement").createEntityManager();
    }

    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> getAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    public void create(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

