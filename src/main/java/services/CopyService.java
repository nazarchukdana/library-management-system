package services;

import entities.Book;
import entities.Borrowing;
import entities.Copy;
import repositories.CopyRepository;

import java.lang.reflect.Field;
import java.util.List;

public class CopyService implements EntityService<Copy> {
    private final CopyRepository copyRepository;
    private final BookService bookService;

    public CopyService() {
        this.copyRepository = new CopyRepository();
        this.bookService = new BookService();
    }

    @Override
    public void create(Copy copy) {
        Book book = bookService.getById(copy.getBook().getId());
        if(bookService.exists(book) && !existsCopyWithNumber(copy.getCopyNumber())) copyRepository.create(copy);
    }
    private boolean existsCopyWithNumber(int copyNumber){
        if(copyRepository.getCopyByNumber(copyNumber) != null){
            throw new IllegalArgumentException("Copy with such copyNumber already exists");
        }
        return false;
    }
    @Override
    public void update(Copy copy) {
        if (exists(copy)) copyRepository.update(copy);
    }
    @Override
    public void delete(int id) {
        Copy copy = getById(id);
        if (exists(copy) && !hasActiveBorrowings(copy)) copyRepository.delete(copy);
    }
    private boolean hasActiveBorrowings(Copy copy) {
        if(copy.getStatus().equals("Borrowed")) throw new IllegalArgumentException("There are active borrowings");
        return false;

    }
    public void updateCopyStatus(Copy copy, String status){
        copy.setStatus(status);
        update(copy);
    }
    public boolean exists(Copy copy){
        if (copy != null) return true;
        else throw new IllegalArgumentException("Copy does not exist.");
    }


    @Override
    public List<Copy> getAll() {
        return copyRepository.getAll();
    }

    @Override
    public Copy getById(int id) {
        return copyRepository.findById(id);
    }
    public boolean copyIsAvailable(Copy copy){
        if (copy.isAvailable())
            return true;
        throw new IllegalArgumentException("Copy is not available");
    }
    @Override
    public Book getReference(Field field, int id) {
        if (field.getType() == Book.class) return bookService.getById(id);
        return null;
    }
}
