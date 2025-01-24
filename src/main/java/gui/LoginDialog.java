package gui;

import entities.Book;
import entities.Publisher;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import services.UserService;

import javax.swing.*;
import java.awt.*;


public class LoginDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginDialog::showLoginDialog);
        EntityManager entityManager = Persistence.createEntityManagerFactory("LibraryManagement").createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE size(u.borrowings) >= 1 ", User.class);

        System.out.println(query.getResultList());

    }
    private static void showLoginDialog() {
        JTextField emailField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Library Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText().trim();
            if (!email.isEmpty()) {
                authenticateUser(email);
            } else {
                JOptionPane.showMessageDialog(null, "Email cannot be empty.");
                showLoginDialog();
            }
        }
    }

    private static void authenticateUser(String email) {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);
        String userType = null;
        int userId = 0;
        if(user != null){
            if(userService.isLibrarian(user))
                userType =  "Librarian";
            else{
                userType = "User";
                userId = user.getId();
            }
        }
        if (userType != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Login successful! You are logged in as a " + userType + ".",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if ("Librarian".equals(userType)) {
                openLibrarianInterface();
            } else {
                openUserInterface(userId);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email. User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            showLoginDialog();
        }
    }

    private static void openLibrarianInterface() {
        SwingUtilities.invokeLater(LibrarianFrame::new);
    }

    private static void openUserInterface(int id) {
        SwingUtilities.invokeLater(()-> new UserFrame(id));
    }
}
