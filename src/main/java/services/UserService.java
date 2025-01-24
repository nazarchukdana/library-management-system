package services;

import entities.Borrowing;
import entities.User;
import repositories.UserRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class UserService implements EntityService<User>{
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public void create(User user) {
        if(!existsUserWithEmail(user.getEmail()) && hasCorrectPhoneNumber(user.getPhoneNumber()) && isValidEmail(user.getEmail())) userRepository.create(user);
    }
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
    private boolean existsUserWithEmail(String email){
        if (getUserByEmail(email) != null) throw new IllegalArgumentException("A user with this email already exists!");
        return false;
    }
    private boolean hasCorrectPhoneNumber(String phoneNumber){
        String phoneNumberPattern = "\\+48 \\d{3}-\\d{3}-\\d{3}";
        if (!phoneNumber.matches(phoneNumberPattern)) {
            throw new IllegalArgumentException("Invalid phone number format. The correct format is: +48 XXX-XXX-XXX");
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        return true;
    }

    @Override
    public void update(User user) {
        if(exists(user) && hasCorrectPhoneNumber(user.getPhoneNumber())) userRepository.update(user);
    }
    @Override
    public void delete(int id) {
        User user = getById(id);
        if (exists(user)) {
            if (isLibrarian(user)) {
                throw new IllegalArgumentException("You cannot delete the user because he is a librarian. Delete the librarian first");
            }
            if (hasActiveBorrowings(user)) {
                throw new IllegalArgumentException("You cannot delete the user because he has active borrowings. Delete borrowings first");
            }
            userRepository.delete(user);
        }
    }

    public List<User> getAll() {
        List<User> users = userRepository.getAll();
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public <U> U getReference(Field field, int id) {
        return null;
    }
    @Override
    public User getById(int id) {
        return userRepository.findById(id);
    }
    public boolean isLibrarian(User user) {
        return user.getLibrarian() != null;
    }
    public boolean exists(User user){
        if (user != null) return true;
        else throw new IllegalArgumentException("User does not exist.");
    }

    private boolean hasActiveBorrowings(User user) {
        List<Borrowing> borrowings = user.getBorrowings();
        if(borrowings != null)
            return borrowings.stream().anyMatch(Borrowing::isActive);
        return false;

    }

}

