package gui;

import entities.*;
import jakarta.persistence.*;
import services.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class LibrarianFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private EntityService currentEntityService;
    private String activeEntity;
    private JButton addEntityButton;
    private JButton updateEntityButton;
    private JButton deleteEntityButton;

    public LibrarianFrame() {
        setTitle("Librarian");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createUI();
        setVisible(true);
    }
    private void createUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JButton usersButton = new JButton("Users");
        JButton librariansButton = new JButton("Librarians");
        JButton booksButton = new JButton("Books");
        JButton publishersButton = new JButton("Publishers");
        JButton copiesButton = new JButton("Copies");
        JButton borrowingsButton = new JButton("Borrowings");

        Dimension buttonSize = new Dimension(120, 30);
        usersButton.setPreferredSize(buttonSize);
        librariansButton.setPreferredSize(buttonSize);
        booksButton.setPreferredSize(buttonSize);
        publishersButton.setPreferredSize(buttonSize);
        copiesButton.setPreferredSize(buttonSize);
        borrowingsButton.setPreferredSize(buttonSize);

        usersButton.setFocusPainted(false);
        usersButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usersButton.setBackground(Color.LIGHT_GRAY);
        usersButton.setForeground(Color.BLACK);

        librariansButton.setFocusPainted(false);
        librariansButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        librariansButton.setBackground(Color.LIGHT_GRAY);
        librariansButton.setForeground(Color.BLACK);

        booksButton.setFocusPainted(false);
        booksButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        booksButton.setBackground(Color.LIGHT_GRAY);
        booksButton.setForeground(Color.BLACK);

        publishersButton.setFocusPainted(false);
        publishersButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        publishersButton.setBackground(Color.LIGHT_GRAY);
        publishersButton.setForeground(Color.BLACK);

        copiesButton.setFocusPainted(false);
        copiesButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        copiesButton.setBackground(Color.LIGHT_GRAY);
        copiesButton.setForeground(Color.BLACK);

        borrowingsButton.setFocusPainted(false);
        borrowingsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        borrowingsButton.setBackground(Color.LIGHT_GRAY);
        borrowingsButton.setForeground(Color.BLACK);


        topPanel.add(usersButton);
        topPanel.add(librariansButton);
        topPanel.add(booksButton);
        topPanel.add(publishersButton);
        topPanel.add(copiesButton);
        topPanel.add(borrowingsButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        add(tableScrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        addEntityButton = new JButton("Add");
        updateEntityButton = new JButton("Update");
        deleteEntityButton = new JButton("Delete");

        addEntityButton.setPreferredSize(buttonSize);
        updateEntityButton.setPreferredSize(buttonSize);
        deleteEntityButton.setPreferredSize(buttonSize);

        addEntityButton.setFocusPainted(false);
        addEntityButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addEntityButton.setBackground(new Color(175, 225, 175));
        addEntityButton.setForeground(Color.BLACK);

        updateEntityButton.setFocusPainted(false);
        updateEntityButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        updateEntityButton.setBackground(new Color (255,69,0));
        updateEntityButton.setForeground(Color.BLACK);

        deleteEntityButton.setFocusPainted(false);
        deleteEntityButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        deleteEntityButton.setBackground(new Color(220,20,60));
        deleteEntityButton.setForeground(Color.BLACK);

        bottomPanel.add(addEntityButton);
        bottomPanel.add(updateEntityButton);
        bottomPanel.add(deleteEntityButton);

        add(bottomPanel, BorderLayout.SOUTH);
        usersButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "User";
                currentEntityService = new UserService();
                showUsers();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        librariansButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "Librarian";
                currentEntityService = new LibrarianService();
                System.out.println(currentEntityService);
                showLibrarians();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        booksButton.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "Book";
                currentEntityService = new BookService();
                showBooks();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        publishersButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "Publisher";
                currentEntityService = new PublisherService();
                showPublishers();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        copiesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "Copy";
                currentEntityService = new CopyService();
                showCopies();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        borrowingsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeEntity = "Borrowing";
                currentEntityService = new BorrowingService();
                showBorrowings();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        addEntityButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    showAddDialog();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(LibrarianFrame.this, "Class not found");
                }
                refreshActiveEntity();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        updateEntityButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    showUpdateDialog();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(LibrarianFrame.this, "Class not found");
                }
                refreshActiveEntity();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        deleteEntityButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteEntity();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void refreshActiveEntity() {
        if ("User".equals(activeEntity)) showUsers();
        else if ("Librarian".equals(activeEntity)) showLibrarians();
        else if ("Book".equals(activeEntity)) showBooks();
        else if ("Publisher".equals(activeEntity)) showPublishers();
        else if ("Copy".equals(activeEntity)) showCopies();
        else if ("Borrowing".equals(activeEntity)) showBorrowings();
    }

    private void showUsers() {
        String[] columns = {"ID", "Name", "Phone", "Email", "Librarian"};
        List<User> users = currentEntityService.getAll();
        if(users != null && !users.isEmpty()) {
            Object[][] data = new Object[users.size()][columns.length];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                data[i][0] = user.getId();
                data[i][1] = user.getName();
                data[i][2] = user.getPhoneNumber();
                data[i][3] = user.getEmail();
                data[i][4] = user.getLibrarian() != null;
            }
            updateTable(columns, data);
        }
        else {
            updateTable(columns, new Object[0][columns.length]);
        }
    }
    private void showLibrarians(){
        String[] columns = {"ID", "Name", "Phone", "Email", "Employment Date", "Position"};
        List<Librarian> librarians = currentEntityService.getAll();
        if(librarians != null && !librarians.isEmpty()){
            Object[][] data = new Object[librarians.size()][columns.length];
            for (int i = 0; i < librarians.size(); i++) {
                Librarian librarian = librarians.get(i);
                User user = librarian.getUser();
                data[i][0] = librarian.getId();
                data[i][1] = user.getName();
                data[i][2] = user.getPhoneNumber();
                data[i][3] = user.getEmail();
                data[i][4] = librarian.getEmploymentDate().toString();
                data[i][5] = librarian.getPosition();
            }
            updateTable(columns, data);
        }
        else updateTable(columns, new Object[0][columns.length]);
    }

    private void showBooks() {
        String[] columns = {"ID", "Title", "Author", "Publication Year", "Publisher Name", "ISBN"};
        List<Book> books = currentEntityService.getAll();
        if(books != null && !books.isEmpty()){
            Object[][] data = new Object[books.size()][columns.length];
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                Publisher publisher = book.getPublisher();
                data[i][0] = book.getId();
                data[i][1] = book.getTitle();
                data[i][2] = book.getAuthor();
                data[i][3] = String.valueOf(book.getPublicationYear());
                data[i][4] = publisher.getName();
                data[i][5] = book.getIsbn();
            }
            updateTable(columns, data);
        }
        else updateTable(columns, new Object[0][columns.length]);
    }
    private void showPublishers(){
        String[] columns = {"ID", "Name", "Phone"};
        List<Publisher> publishers = currentEntityService.getAll();
        if(publishers != null && !publishers.isEmpty()) {
            Object[][] data = new Object[publishers.size()][columns.length];
            for (int i = 0; i < publishers.size(); i++) {
                Publisher publisher = publishers.get(i);
                data[i][0] = publisher.getId();
                data[i][1] = publisher.getName();
                data[i][2] = publisher.getPhoneNumber();
            }
            updateTable(columns, data);
        }
        else {
            updateTable(columns, new Object[0][columns.length]);
        }
    }
    private void showCopies() {
        String[] columns = {"ID", "Title", "Author", "ISBN", "Copy Number", "Status"};
        List<Copy> copies = currentEntityService.getAll();
        if(copies != null && !copies.isEmpty()){
            Object[][] data = new Object[copies.size()][columns.length];
            for (int i = 0; i < copies.size(); i++) {
                Copy copy = copies.get(i);
                Book book = copy.getBook();
                data[i][0] = copy.getId();
                data[i][1] = book.getTitle();
                data[i][2] = book.getAuthor();
                data[i][3] = book.getIsbn();
                data[i][4] = copy.getCopyNumber();
                data[i][5] = copy.getStatus();
            }
            updateTable(columns, data);
        }
        else updateTable(columns, new Object[0][columns.length]);
    }
    private void showBorrowings() {
        String[] columns = {"ID", "Book Title", "ISBN", "Copy Number","User ID", "Borrow Date", "Return Date"};
        List<Borrowing> borrowings = currentEntityService.getAll();
        if(borrowings != null && !borrowings.isEmpty()){
            Object[][] data = new Object[borrowings.size()][columns.length];
            for (int i = 0; i < borrowings.size(); i++) {
                Borrowing borrowing = borrowings.get(i);
                Copy copy = borrowing.getCopy();
                Book book = copy.getBook();
                User user = borrowing.getUser();
                data[i][0] = borrowing.getId();
                data[i][1] = book.getTitle();
                data[i][2] = book.getIsbn();
                data[i][3] = copy.getCopyNumber();
                data[i][4] = user.getId();
                data[i][5] = borrowing.getBorrowDate().toString();
                data[i][6] = borrowing.getReturnDate() == null ? " - " : borrowing.getReturnDate().toString();
            }
            updateTable(columns, data);
        }
        else updateTable(columns, new Object[0][columns.length]);
    }


    private void showAddDialog() throws ClassNotFoundException {
        Class<?> entityClass = Class.forName("entities."+activeEntity);
        JDialog dialog = new JDialog(this, "Add New " + entityClass.getSimpleName(), true);
        dialog.setLayout(new BorderLayout());

        Field[] fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(JoinColumn.class))
                .toArray(Field[]::new);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField[] textFields = new JTextField[fields.length];
        //init textfields and labels
        for (int i = 0; i < fields.length; i++) {
            formPanel.add(new JLabel(fields[i].getName()));
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(200, 30));
            textFields[i] = textField;
            formPanel.add(textField);
        }

        dialog.add(formPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        dialog.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            //retrieve and set
            try {
                Object newEntity = entityClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    String value = textFields[i].getText().trim();
                    //if field is refid
                    if (field.isAnnotationPresent(JoinColumn.class)) {
                        //get ref and set
                        int referenceId = 0;
                        try {
                            referenceId = Integer.parseInt(value);
                        } catch (NumberFormatException e1){
                            throw new IllegalArgumentException("The input should be integer");
                        }
                        Object referencedEntity = currentEntityService.getReference(field, referenceId);
                        if (referencedEntity != null ) {
                            field.set(newEntity, referencedEntity);
                        }
                        else throw new IllegalArgumentException(field.getType().getSimpleName()+" does not exist");
                    } else {
                        //field - date
                        if (field.getType() == Date.class) {
                            //returnDate - null
                            if(field.getName().equals("returnDate") && value.isEmpty()){
                                field.set(newEntity, null);
                            }
                            else{
                                //other dates
                                if (!value.isEmpty()) {
                                    try {
                                        Date sqlDate = Date.valueOf(value);
                                        field.set(newEntity, sqlDate);
                                    }
                                    catch (Exception e1){
                                        throw new IllegalArgumentException("Not appropriate format. Format: YYYY-MM-DD");
                                    }
                                }
                                else throw new IllegalArgumentException("Date is required");
                            }
                        }
                        else if(field.getType() == int.class){
                            if(!value.isEmpty()) {
                                try {
                                    int number = Integer.parseInt(value);
                                    field.set(newEntity, number);
                                } catch (NumberFormatException e1){
                                    throw new IllegalArgumentException("The input should be integer");
                                }
                            }
                            else throw new IllegalArgumentException("Fields cannot be empty");
                        }
                        //string
                        else{
                            if(!value.isEmpty()) {
                                field.set(newEntity, value);
                            }
                            else throw new IllegalArgumentException("Fields cannot be empty");
                        }
                    }
                }
                currentEntityService.create(newEntity);
                dialog.dispose();
                refreshActiveEntity();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        dialog.setSize(400, 100 * fields.length);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private void showUpdateDialog() throws ClassNotFoundException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an entity to update.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Object existingEntity = currentEntityService.getById(id);

        if (existingEntity == null) {
            JOptionPane.showMessageDialog(this, "Selected entity no longer exists.");
            return;
        }

        Class<?> entityClass = Class.forName("entities." + activeEntity);
        JDialog dialog = new JDialog(this, "Update " + entityClass.getSimpleName(), true);
        dialog.setLayout(new BorderLayout());

        Field[] fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(JoinColumn.class))
                .toArray(Field[]::new);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField[] textFields = new JTextField[fields.length];
        //get data in fields
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            formPanel.add(new JLabel(field.getName()));
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(200, 30));
            //set unable
            //unique ot status
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.unique() || field.getName().equals("status")) {
                    textField.setEnabled(false);
                }
            }
            //id
            else if (field.isAnnotationPresent(JoinColumn.class)){
                textField.setEnabled(false);
            }
            try {
                Object value = field.get(existingEntity);
                //get data
                if(!field.isAnnotationPresent(JoinColumn.class)) textField.setText(value != null ? value.toString() : "");
                //for fk just id
                else{
                    field.setAccessible(true);
                    Object reference = value;
                    String referenceId = "";
                    if (reference != null) {
                        try {
                            Field idField = reference.getClass().getDeclaredField("id");
                            idField.setAccessible(true);
                            Object idValue = idField.get(reference);
                            referenceId = (idValue != null) ? idValue.toString() : "";
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            JOptionPane.showMessageDialog(this, "Cannot find id of the reference");
                        }
                    }

                    textField.setText(referenceId);
                }
            } catch (IllegalAccessException e) {
                textField.setText("");
            }
            textFields[i] = textField;
            formPanel.add(textField);
        }

        dialog.add(formPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        dialog.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            try {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    String value = textFields[i].getText().trim();
                    if(!field.isAnnotationPresent(JoinColumn.class)){
                        //dates
                        if (field.getType() == Date.class) {
                            //return null
                            if(field.getName().equals("returnDate") && value.isEmpty()){
                                field.set(existingEntity, null);
                            }
                            else{
                                if (!value.isEmpty()) {
                                    try {
                                        Date sqlDate = Date.valueOf(value);
                                        field.set(existingEntity, sqlDate);
                                    }
                                    catch (Exception e1){
                                        throw new IllegalArgumentException("Not appropriate format. Format: YYYY-MM-DD");
                                    }
                                }
                                else throw new IllegalArgumentException("Date is required");
                            }
                            //ints
                        } else if (field.getType() == int.class) {
                            if(!value.isEmpty()) {
                                try {
                                    int number = Integer.parseInt(value);
                                    field.set(existingEntity, number);
                                } catch (NumberFormatException e1){
                                    throw new IllegalArgumentException("The input should be integer");
                                }
                            }
                            else throw new IllegalArgumentException("Fields cannot be empty");
                        } else {
                            if(!value.isEmpty()) {
                                field.set(existingEntity, value);
                            }
                            else throw new IllegalArgumentException("Fields cannot be empty");
                        }
                    }
                }
                currentEntityService.update(existingEntity);
                dialog.dispose();
                refreshActiveEntity();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });
        dialog.setSize(400, 100 * fields.length);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void deleteEntity() {
        if (currentEntityService != null) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                try {
                    currentEntityService.delete(id);
                }
                catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(this, e.getMessage() );                }
                refreshActiveEntity();
            } else {
                JOptionPane.showMessageDialog(this, "No " + activeEntity + " selected for deletion.");
            }
        }
        else JOptionPane.showMessageDialog(this, "First choose any table");
    }
    private void updateTable(String[] columns, Object[][] data) {
        tableModel.setDataVector(data, columns);
    }

}

