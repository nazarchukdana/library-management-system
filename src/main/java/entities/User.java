package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",
            nullable = false,
            length = 20)
    private String name;

    @Column(name="email",
            nullable = false,
            unique = true,
            length = 50)
    private String email;

    @Column(
            nullable = false,
            length = 15)
    private String phoneNumber;
    @Column(name = "address",
            nullable = false,
            length = 200)
    private String address;
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    private Librarian librarian;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;

    public User() {
    }

    public User(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        borrowings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
    public List<Borrowing> getActiveBorrowings() {
        return borrowings.stream()
                .filter(Borrowing::isActive)
                .toList();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
