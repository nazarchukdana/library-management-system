# Library Management System

## Project Objective
This project is a practical exercise involving the development of a **Library Management System**, utilizing **JPA, Hibernate, Java Swing**. The system is designed to facilitate library operations, enabling librarians to manage books, users, and borrowings efficiently.

## Project Features
The **Library Management System** provides the following functionalities:

### 1. **Graphical User Interface (GUI) - Java Swing**
- Librarians can **manage the whole system**.
- Interactive tables to display lists of:
  - Users
  - Librarians
  - Books
  - Borrowing records
  - Publishers
  - Copies of books
- Regular users (non-librarians) can:
  - View the complete list of the library’s titles.
  - View the list of currently available books.
  - Check their own borrowing history.

### 2. **Database Integration - JPA & Hibernate**
- Implements a database schema to store information about library entities.
- Uses **JPA annotations** to define entity relationships.
- Supports **CRUD (Create, Read, Update, Delete) operations** for all entities.

## Database Schema Design
The system includes the following six tables:

### **1. Users**
- `id` (Primary Key, Auto-Increment, Integer)
- `name` (String)
- `email` (String, Unique)
- `phoneNumber` (String)
- `address` (String)

### **2. Books**
- `id` (Primary Key, Auto-Increment, Integer)
- `title` (String)
- `author` (String)
- `publisher` (String)
- `publicationYear` (Integer)
- `isbn` (String, Unique)

### **3. Borrowings**
- `id` (Primary Key, Auto-Increment, Integer)
- `userId` (Foreign Key, References Users(id))
- `copyId` (Foreign Key, References Copies(id))
- `borrowDate` (Date)
- `returnDate` (Date, Nullable)

### **4. Librarians**
- `id` (Primary Key, Auto-Increment, Integer)
- `userId` (Foreign Key, References Users(id))
- `employmentDate` (Date)
- `position` (String)

### **5. Copies**
- `id` (Primary Key, Auto-Increment, Integer)
- `bookId` (Foreign Key, References Books(id))
- `copyNumber` (Integer)
- `status` (String, e.g., "Available", "Borrowed", "Withdrawn")

### **6. Publishers**
- `id` (Primary Key, Auto-Increment, Integer)
- `name` (String)
- `address` (String)
- `phoneNumber` (String)

## Entity Relationships
The following relationships are implemented using JPA annotations:
- **One-to-Many**: A **User** can borrow multiple books (via Borrowings table).
- **One-to-Many**: A **Book** can have multiple copies.
- **One-to-One**: A **Librarian** is a specific type of **User**.
- **Many-to-One**: A **Book** is associated with one **Publisher**.

## CRUD Operations
The system supports full CRUD functionality:
- **Create**: Add new users, books, copies, and borrowing records.
- **Read**: View lists of books, users, and borrowing histories.
- **Update**: Modify existing records (e.g., update book details or user info).
- **Delete**: Remove records (only if not involved in existing relationships).

## Technologies Used
- **Java Swing** (GUI Design)
- **JPA & Hibernate** (ORM and Database Integration)
- **Maven/Gradle** (Build Management)

## Future Enhancements
- Integrate search and filtering features for book listings.
- Intergrate utit tests for the whole system.

## License
This project is **proprietary and closed-source**.  
Unauthorized use, distribution, or modification is **strictly prohibited**. 

## Contributors
- **Dana Nazarchuk** – Developer

**The project is designed as a school project for Polish Japanese Academy of Information Technologies.**

