package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name="copy_number",
            nullable = false)
    private int copyNumber;

    @Column(name = "status",
            nullable = false)
    private String status;
    @OneToMany(mappedBy = "copy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrowing> borrowings;

    public Copy() {
    }

    public Copy(Book book, Integer copyNumber, String status) {
        this.book = book;
        this.copyNumber = copyNumber;
        this.status = status;
        this.borrowings = new ArrayList<>();
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
    public boolean isAvailable(){
        return status.equalsIgnoreCase("available");
    }
}
