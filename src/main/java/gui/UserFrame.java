package gui;

import entities.Book;
import entities.Borrowing;
import entities.Copy;
import entities.Publisher;
import services.BookService;
import services.BorrowingService;
import services.EntityService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private EntityService currentEntityService;
    private final int id;

    public UserFrame(int id) {
        this.id = id;
        setTitle("User");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createUI();
        setVisible(true);
    }

    private void createUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JButton allBooksButton = new JButton("All Books");
        JButton availableBooksButton = new JButton("Available Books");
        JButton borrowingsButton = new JButton("Borrowings");
        Dimension buttonSize = new Dimension(120, 30);
        allBooksButton.setPreferredSize(buttonSize);
        availableBooksButton.setPreferredSize(buttonSize);
        borrowingsButton.setPreferredSize(buttonSize);

        allBooksButton.setFocusPainted(false);
        allBooksButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        allBooksButton.setBackground(Color.LIGHT_GRAY);
        allBooksButton.setForeground(Color.BLACK);

        availableBooksButton.setFocusPainted(false);
        availableBooksButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        availableBooksButton.setBackground(Color.LIGHT_GRAY);
        availableBooksButton.setForeground(Color.BLACK);

        borrowingsButton.setFocusPainted(false);
        borrowingsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        borrowingsButton.setBackground(Color.LIGHT_GRAY);
        borrowingsButton.setForeground(Color.BLACK);

        topPanel.add(allBooksButton);
        topPanel.add(availableBooksButton);
        topPanel.add(borrowingsButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        add(tableScrollPane, BorderLayout.CENTER);

        allBooksButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentEntityService = new BookService();
                showAllBooks();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        availableBooksButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentEntityService = new BookService();
                showAvailableBooks();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        borrowingsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentEntityService = new BorrowingService();
                showBorrowings();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void showAllBooks() {
        String[] columns = {"Title", "Author", "Publication Year", "Publisher Name", "ISBN"};
        List<Book> books = currentEntityService.getAll();
        if(books != null && !books.isEmpty()){
            Object[][] data = new Object[books.size()][columns.length];
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                Publisher publisher = book.getPublisher();
                data[i][0] = book.getTitle();
                data[i][1] = book.getAuthor();
                data[i][2] = String.valueOf(book.getPublicationYear());
                data[i][3] = publisher.getName();
                data[i][4] = book.getIsbn();
            }
            updateTable(columns, data);
        }
        else updateTable(columns, new Object[0][columns.length]);
    }

    private void showAvailableBooks() {
        String[] columns = {"Title", "Author", "Publication Year", "Publisher Name", "ISBN"};
        List<Book> books = currentEntityService.getAll();
        if (books != null && !books.isEmpty()) {
            List<Book> availableBooks = books.stream()
                    .filter(book -> book.getCopies().stream().allMatch(Copy::isAvailable))
                    .toList();
            if(availableBooks != null && !availableBooks.isEmpty()) {
                Object[][] data = new Object[availableBooks.size()][columns.length];
                for (int i = 0; i < availableBooks.size(); i++) {
                    Book book = availableBooks.get(i);
                    Publisher publisher = book.getPublisher();
                    data[i][0] = book.getTitle();
                    data[i][1] = book.getAuthor();
                    data[i][2] = String.valueOf(book.getPublicationYear());
                    data[i][3] = publisher.getName();
                    data[i][4] = book.getIsbn();
                }
                updateTable(columns, data);
            }
        }
        else updateTable(columns, new Object[0][columns.length]);
    }

    private void showBorrowings() {
        String[] columns = {"Title", "ISBN", "Borrow Date", "Return Date"};
        List<Borrowing> borrowings = currentEntityService.getAll();
        if (borrowings != null && !borrowings.isEmpty()) {
            List<Borrowing> userBorrowings = borrowings.stream()
                    .filter(borrowing -> borrowing.getUserId() == id)
                    .toList();

            if (userBorrowings != null && !userBorrowings.isEmpty()) {
                Object[][] data = new Object[userBorrowings.size()][columns.length];
                for (int i = 0; i < userBorrowings.size(); i++) {
                    Borrowing borrowing = userBorrowings.get(i);
                    Book book = borrowing.getCopy().getBook();
                    data[i][0] = book.getTitle();
                    data[i][1] = book.getIsbn();
                    data[i][2] = borrowing.getBorrowDate().toString();
                    data[i][3] = borrowing.getReturnDate() != null ? borrowing.getReturnDate().toString() : "-";
                }
                updateTable(columns, data);
            } else {
                updateTable(columns, new Object[0][columns.length]);
            }
        } else {
            updateTable(columns, new Object[0][columns.length]);
        }
    }


    private void updateTable(String[] columns, Object[][] data) {
        tableModel.setDataVector(data, columns);
    }
}


