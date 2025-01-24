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
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        if(!existsUserWithEmail(user.getEmail())) userRepository.create(user);
    }
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
    private boolean existsUserWithEmail(String email){
        if (getUserByEmail(email) != null) throw new IllegalArgumentException("A user with this email already exists!");
        return false;
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
    @Override
    public void update(User user) {
        if(exists(user)) userRepository.update(user);
    }
    public boolean exists(User user){
        if (user != null) return true;
        else throw new IllegalArgumentException("User does not exist.");
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

    public boolean hasActiveBorrowings(User user) {
        List<Borrowing> borrowings = user.getBorrowings();
        if(borrowings != null)
            return borrowings.stream().anyMatch(Borrowing::isActive);
        return false;

    }
}

