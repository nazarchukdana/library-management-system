package services;

import entities.Borrowing;
import entities.Copy;
import entities.User;
import repositories.BorrowingRepository;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;

public class BorrowingService implements EntityService<Borrowing> {
    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final CopyService copyService;

    public BorrowingService() {
        this.borrowingRepository = new BorrowingRepository();
        this.userService = new UserService();
        this.copyService = new CopyService();
    }

    @Override
    public void create(Borrowing borrowing) {
        validateBorrowingDates(borrowing.getBorrowDate(), borrowing.getReturnDate());
        User user = userService.getById(borrowing.getUser().getId());
        Copy copy = copyService.getById(borrowing.getCopy().getId());
        if (userService.exists(user) && copyService.exists(copy) && copyService.copyIsAvailable(copy) && !userHasSameBorrowingActive(user, copy)) {
            borrowingRepository.create(borrowing);
            if(borrowing.isActive()) copyService.updateCopyStatus(borrowing.getCopy(), "Borrowed");
        }

    }
    private void validateBorrowingDates(Date borrowDate, Date returnDate) {
        Date currentDate = new Date(System.currentTimeMillis());

        if (borrowDate.after(currentDate)) {
            throw new IllegalArgumentException("Borrow date cannot be in the future.");
        }
        if (returnDate != null && returnDate.after(currentDate)) {
            throw new IllegalArgumentException("Return date cannot be in the future.");
        }
        if (returnDate != null && borrowDate.after(returnDate)) {
            throw new IllegalArgumentException("Borrow date cannot be after return date.");
        }
    }

    private boolean userHasSameBorrowingActive(User user, Copy copy){
        if(borrowingRepository.existsByUserAndBookAndActive(user.getId(), copy.getBook().getId())) throw new IllegalArgumentException("User already has an active borrowing for this book");
        return false;
    }
    private boolean exists(Borrowing borrowing) {
        if (borrowing != null) return true;
        else throw new IllegalArgumentException("Borrowing does not exist.");
    }
    @Override
    public void update(Borrowing borrowing) {
        if (exists(borrowing)) {
            validateBorrowingDates(borrowing.getBorrowDate(), borrowing.getReturnDate());
            borrowingRepository.update(borrowing);
            if(!borrowing.isActive()) {
                copyService.updateCopyStatus(borrowing.getCopy(), "Available");
            }
        }
    }
    @Override
    public void delete(int id) {
        Borrowing borrowing = getById(id);
        if (exists(borrowing)) {
            if(!borrowing.isActive()){
                copyService.updateCopyStatus(borrowing.getCopy(), "Available");
                borrowingRepository.delete(borrowing);
            }
            else throw new IllegalArgumentException("The book is not returned. Update the return date to delete");
        }
    }
    @Override
    public List<Borrowing> getAll() {
        return borrowingRepository.getAll();
    }

    @Override
    public <U> U getReference(Field field, int id) {
        if (field.getType() == User.class) return (U) userService.getById(id);
        else if (field.getType() == Copy.class) return (U) copyService.getById(id);
        else return null;
    }

    @Override
    public Borrowing getById(int id) {
        return borrowingRepository.findById(id);
    }
}
