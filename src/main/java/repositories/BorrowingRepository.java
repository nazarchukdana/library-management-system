package repositories;

import entities.Borrowing;

import java.util.List;

public class BorrowingRepository extends EntityRepository<Borrowing> {
    public BorrowingRepository() {
        super(Borrowing.class);
    }

    public Borrowing findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<Borrowing> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Borrowing entity) {
        super.update(entity);
    }

    @Override
    public void create(Borrowing entity) {
        super.create(entity);
    }

    @Override
    public void delete(Borrowing entity) {
        super.delete(entity);
    }
    public boolean existsByUserAndBookAndActive(int userId, int bookId) {
        List<Borrowing> result = entityManager.createQuery("SELECT b FROM Borrowing b " +
                        "WHERE b.user.id = :userId AND b.copy.book.id = :bookId AND b.returnDate IS NULL",
                        Borrowing.class)
                .setParameter("userId", userId)
                .setParameter("bookId", bookId)
                .getResultList();
        if(result != null)
            return !result.isEmpty();
        return false;
    }
}
