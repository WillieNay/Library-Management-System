import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Object[] toTableRow() {
        return new Object[]{title, author, borrowed ? "Borrowed" : "Available"};
    }
}

public class LibraryGUI extends JFrame {

    private List<Book> books;
    private DefaultTableModel tableModel;
    private JTable bookTable;
    private JTextField titleField;
    private JTextField authorField;
    private JButton addButton;
    private JButton borrowButton;
    private JButton returnButton;

    public LibraryGUI() {
        books = new ArrayList<>();
        initializeBooks();

        // Set up the frame
        setTitle("📚 Modern Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(230, 240, 255), 
                    getWidth(), getHeight(), new Color(200, 220, 255)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title input
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        titleField = new JTextField(20);
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(titleField, gbc);

        // Author input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(new JLabel("Author:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        authorField = new JTextField(20);
        authorField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(authorField, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        
        // Styling buttons
        addButton = createStyledButton("Add Book", new Color(76, 175, 80));
        borrowButton = createStyledButton("Borrow Book", new Color(33, 150, 243));
        returnButton = createStyledButton("Return Book", new Color(255, 152, 0));

        buttonPanel.add(addButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);

        // Table for displaying books
        String[] columnNames = {"Title", "Author", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        bookTable.setRowHeight(25);
        bookTable.setFont(new Font("Arial", Font.PLAIN, 12));
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addBook());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());

        // Populate initial book list
        updateBookList();

        // Set up the frame
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Create styled buttons with custom colors
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private void initializeBooks() {
        books.add(new Book("To Kill A Mockingbird", "Harper Lee"));
        books.add(new Book("Pride and Prejudice", "Jane Austen"));
        books.add(new Book("1984", "George Orwell"));
    }

    private void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        if (!title.isEmpty() && !author.isEmpty()) {
            Book newBook = new Book(title, author);
            books.add(newBook);
            updateBookList();
            titleField.setText("");
            authorField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please enter both title and author.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrowBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a book to borrow.", 
                "Selection Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            updateBookList();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Book is already borrowed.", 
                "Borrow Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a book to return.", 
                "Selection Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Book book = books.get(selectedRow);
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            updateBookList();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Book is not currently borrowed.", 
                "Return Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBookList() {
        // Clear existing rows
        tableModel.setRowCount(0);
        
        // Add books to table
        for (Book book : books) {
            tableModel.addRow(book.toTableRow());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}