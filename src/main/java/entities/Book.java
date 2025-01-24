package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    private Publisher publisher;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies = new ArrayList<>();

    // Default constructor
    public Book() {
    }

    // Constructor with fields
    public Book(String title, String author, Publisher publisher, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    // Convenience method to add a copy
    public void addCopy(Copy copy) {
        copies.add(copy);
        copy.setBook(this);
    }

    // Convenience method to remove a copy
    public void removeCopy(Copy copy) {
        copies.remove(copy);
        copy.setBook(null);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher=" + (publisher != null ? publisher.getName() : "No Publisher") +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
