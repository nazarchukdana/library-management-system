package services;


import entities.Librarian;
import entities.User;
import repositories.LibrarianRepository;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LibrarianService implements EntityService<Librarian> {
    private LibrarianRepository librarianRepository;
    private final UserService userService;

    public LibrarianService() {
        this.librarianRepository = new LibrarianRepository();
        this.userService = new UserService();
    }
    @Override
    public void create(Librarian librarian) {
        User user = userService.getById(librarian.getUser().getId());
        if(hasCorrectDate(librarian.getEmploymentDate()) && userService.exists(user) && !assignedToAnotherLibrarian(user.getId())) librarianRepository.create(librarian);
    }
    private boolean exists(Librarian librarian){
        if (librarian != null) return true;
        else throw new IllegalArgumentException("Librarian does not exist.");
    }
    private boolean hasCorrectDate(Date employmentDate){
        Date currentDate = new Date(System.currentTimeMillis());
        if (employmentDate.after(currentDate)) {
            throw new IllegalArgumentException("Employment date cannot be in the future.");
        }
        return true;
    }
    private boolean assignedToAnotherLibrarian(int userId){
        if(getAll().stream().anyMatch(l -> l.getUser().getId() == userId))
            throw new IllegalArgumentException("User is already assigned to librarian");
        return false;
    }
    @Override
    public void update(Librarian librarian) {
        if(exists(librarian) && hasCorrectDate(librarian.getEmploymentDate())) librarianRepository.update(librarian);
    }
    @Override
    public void delete(int id) {
        Librarian librarian = getById(id);
        if (exists(librarian)) {
            if (getAll().size() <= 1) {
                throw new IllegalArgumentException("Cannot delete the only librarian in the system.");
            }
            librarianRepository.delete(librarian);
        }
    }
    @Override
    public List<Librarian> getAll() {
        List<Librarian> librarians = librarianRepository.getAll();
        if (librarians == null) return new ArrayList<>();
        else return librarians;
    }

    @Override
    public User getReference(Field field, int id) {
        if (field.getType() == User.class)
            return userService.getById(id);
        return null;
    }

    public Librarian getById(int id){
        return librarianRepository.findById(id);
    }
}
