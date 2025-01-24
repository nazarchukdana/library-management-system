package repositories;

import entities.Copy;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CopyRepository extends EntityRepository<Copy> {
    public CopyRepository() {
        super(Copy.class);
    }

    public Copy findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<Copy> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Copy entity) {
        super.update(entity);
    }

    @Override
    public void create(Copy entity) {
        super.create(entity);
    }

    @Override
    public void delete(Copy entity) {
        super.delete(entity);
    }
    public Copy getCopyByNumber(int copyNumber){
        TypedQuery<Copy> query = entityManager.createQuery(
                "SELECT c FROM Copy c WHERE c.copyNumber = :copyNumber", Copy.class);
        query.setParameter("copyNumber", copyNumber);
        return query.getResultStream().findFirst().orElse(null);
    }
}
