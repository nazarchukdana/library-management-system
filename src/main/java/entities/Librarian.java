package entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "librarians")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Date employmentDate;

    @Column(nullable = false, length = 100)
    private String position;

    public Librarian() {
    }

    public Librarian(User user, Date employmentDate, String position) {
        this.user = user;
        this.employmentDate = employmentDate;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", user=" + (user != null ? user.getName() : "No User Assigned") +
                ", employmentDate=" + employmentDate +
                ", position='" + position + '\'' +
                '}';
    }
}
