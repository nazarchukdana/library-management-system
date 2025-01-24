package repositories;

import entities.User;
import jakarta.persistence.*;

import java.util.List;

public class UserRepository extends EntityRepository<User> {
    public UserRepository() {
        super(User.class);
    }
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public void create(User entity) {
        super.create(entity);
    }

    @Override
    public void update(User entity) {
        super.update(entity);
    }

    @Override
    public User findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }
}

