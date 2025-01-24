package repositories;

import entities.Book;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookRepository extends EntityRepository<Book> {

    public BookRepository() {
        super(Book.class);
    }

    @Override
    public void create(Book entity) {
        super.create(entity);
    }

    @Override
    public void delete(Book entity) {
        super.delete(entity);
    }

    @Override
    public void update(Book entity) {
        super.update(entity);
    }

    @Override
    public Book findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return super.getAll();
    }
    public Book getBookByISBN(String isbn){
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class).
                setParameter("isbn", isbn);
        return query.getResultStream().findFirst().orElse(null);
    }
}
