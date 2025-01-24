package services;


import entities.Librarian;
import entities.User;
import repositories.LibrarianRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LibrarianService implements EntityService<Librarian> {
    private LibrarianRepository librarianRepository;
    private final UserService userService;

    public LibrarianService() {
        this.librarianRepository = new LibrarianRepository();
        this.userService = new UserService();
    }
    public boolean exists(Librarian librarian){
        if (librarian != null) return true;
        else throw new IllegalArgumentException("Librarian does not exist.");
    }

    public User getUserByLibrarian(Librarian librarian){
        return librarian.getUser();
    }

    @Override
    public void create(Librarian librarian) {
        User user = userService.getById(librarian.getUser().getId());
        if(userService.exists(user) && !assignedToAnotherLibrarian(user.getId())) librarianRepository.create(librarian);
    }
    public boolean assignedToAnotherLibrarian(int userId){
        if(getAll().stream().anyMatch(l -> l.getUser().getId() == userId))
            throw new IllegalArgumentException("User is already assigned to librarian");
        return false;
    }

    @Override
    public void delete(int id) {
        Librarian librarian = getById(id);
        if (exists(librarian)) librarianRepository.delete(librarian);
    }

    @Override
    public void update(Librarian librarian) {
        if(exists(librarian)) librarianRepository.update(librarian);
    }

    @Override
    public List<Librarian> getAll() {
        List<Librarian> librarians = librarianRepository.getAll();
        if (librarians == null) return new ArrayList<>();
        else return librarians;
    }

    @Override
    public User getReference(Field field, int id) {
        if (field.getType() == User.class){
            User user = userService.getById(id);
            return user;
        }
        return null;
    }

    public Librarian getById(int id){
        return librarianRepository.findById(id);
    }
}
