package repositories;

import entities.Librarian;

import java.util.List;

public class LibrarianRepository extends EntityRepository<Librarian> {
    public LibrarianRepository() {
        super(Librarian.class);
    }

    public Librarian findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<Librarian> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Librarian entity) {
        super.update(entity);
    }

    @Override
    public void create(Librarian entity) {
        super.create(entity);
    }

    @Override
    public void delete(Librarian entity) {
        super.delete(entity);
    }
}
