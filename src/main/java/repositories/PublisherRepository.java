package repositories;

import entities.Publisher;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PublisherRepository extends EntityRepository<Publisher> {

    public PublisherRepository() {
        super(Publisher.class);
    }
    public Publisher getPublisherByName(String name){
        TypedQuery<Publisher> query = entityManager.createQuery(
                "SELECT p FROM Publisher p WHERE p.name = :name", Publisher.class)
                .setParameter("name", name);
        return query.getResultStream().findFirst().orElse(null);
    }

    public Publisher findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Publisher entity) {
        super.update(entity);
    }

    @Override
    public void create(Publisher entity) {
        super.create(entity);
    }

    @Override
    public void delete(Publisher entity) {
        super.delete(entity);
    }
}
