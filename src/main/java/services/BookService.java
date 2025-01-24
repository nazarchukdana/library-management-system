package services;

import entities.Book;
import entities.Publisher;
import repositories.BookRepository;

import java.lang.reflect.Field;
import java.time.Year;
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
        if(ISBNHasCorrectFormat(book.getIsbn()) &&
                hasCorrectYear(book.getPublicationYear()) &&
                publisherService.exists(publisher) &&
                !existsBookWithISBN(book.getIsbn())) bookRepository.create(book);

    }
    private boolean ISBNHasCorrectFormat(String isbn){
        if (isbn == null || !isbn.matches("\\d{13}")) {
            throw new IllegalArgumentException("ISBN must be exactly 13 digits and contain only numbers.");
        }
        return true;
    }
    private boolean hasCorrectYear(int publicationYear){
        int currentYear = Year.now().getValue();
        if (publicationYear > currentYear) {
            throw new IllegalArgumentException("Publication year cannot exceed the current year.");
        }
        return true;
    }
    private boolean existsBookWithISBN(String isbn){
        if (bookRepository.getBookByISBN(isbn) != null)
            throw new IllegalArgumentException("A book with this ISBN already exists");
        return false;
    }
    public boolean exists(Book book){
        if (book == null) throw new IllegalArgumentException("Book does not exist.");
        return true;
    }
    @Override
    public void update(Book book) {
        if (exists(book) && hasCorrectYear(book.getPublicationYear())) {
            bookRepository.update(book);
        }
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
        if (field.getType() == Publisher.class) return publisherService.getById(id);
        else return null;
    }
}
