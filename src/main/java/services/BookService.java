package services;

import entities.Book;
import entities.Publisher;
import repositories.BookRepository;

import java.lang.reflect.Field;
import java.util.List;

public class BookService implements EntityService<Book> {
    private final BookRepository bookRepository;
    private final PublisherService publisherService;

    public BookService() {
        this.bookRepository = new BookRepository();
        publisherService = new PublisherService();
    }

    @Override
    public void create(Book book) {
        Publisher publisher = publisherService.getById(book.getPublisher().getId());
        if(publisherService.exists(publisher) && !existsBookWithISBN(book.getIsbn())) bookRepository.create(book);

    }
    public boolean existsBookWithISBN(String isbn){
        if (bookRepository.getBookByISBN(isbn) != null)
            throw new IllegalArgumentException("A book with this ISBN already exists");
        return false;
    }

    @Override
    public void delete(int id) {
        Book book = getById(id);
        if (exists(book) && !hasCopies(book)) bookRepository.delete(book);
    }
    private boolean hasCopies(Book book){
        if(!book.getCopies().isEmpty()) throw new IllegalArgumentException("You cannot delete this book. Delete copies first");
        return false;
    }
    public boolean exists(Book book){
        if (book == null) throw new IllegalArgumentException("Book does not exist.");
        return true;
    }

    @Override
    public void update(Book book) {
        if (exists(book)) {
            bookRepository.update(book);
        }
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book getById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Publisher getReference(Field field, int id) {
        if (field.getType() == Publisher.class){
            return publisherService.getById(id);
        }
        else return null;
    }
}
