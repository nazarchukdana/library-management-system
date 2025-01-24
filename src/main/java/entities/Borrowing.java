package entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (optional = false)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private User user;

    @ManyToOne (optional = false)
    @JoinColumn(name = "copy_id",
            referencedColumnName = "id",
            nullable = false)
    private Copy copy;

    @Column(name = "borrow_date",
            nullable = false)
    private Date borrowDate;

    @Column(name = "return_date")
    private Date returnDate;

    public Borrowing() {
    }

    public Borrowing(Integer id, User user, Copy copy, Date borrowDate, Date returnDate) {
        this.id = id;
        this.user = user;
        this.copy = copy;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public boolean isActive(){
        return returnDate == null;
    }
    public int getUserId(){
        return user.getId();
    }
}
