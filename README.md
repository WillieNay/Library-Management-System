# Library Management System

## Overview

A modern, user-friendly Java-based Library Management System that provides an intuitive interface for managing book collections, tracking book borrowing status, and performing library operations.

![Java](https://img.shields.io/badge/Java-11+-blue?style=flat-square&logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-green?style=flat-square)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square)

## Features

- **Book Management**
  - Add new books to the library
  - Track book borrowing status
  - View comprehensive book list
  - Borrow and return books with ease

- **User-Friendly Interface**
  - Modern, gradient-styled design
  - Color-coded action buttons
  - Responsive table-based book display

## Prerequisites

- Java Runtime Environment (JRE) 8 or higher
- Minimum screen resolution: 800x600

## Installation

### Method 1: Direct Compilation

1. Ensure Java Development Kit (JDK) is installed
2. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   ```

3. Navigate to the project directory:
   ```bash
   cd library-management-system
   ```

4. Compile the Java files:
   ```bash
   javac LibraryGUI.java
   ```

5. Run the application:
   ```bash
   java LibraryGUI
   ```

### Method 2: Using an IDE

- Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
- Ensure JDK is configured
- Locate the `LibraryGUI.java` file
- Right-click and select "Run"

## Usage

1. **Adding a Book**
   - Enter book title and author in the input fields
   - Click "Add Book"

2. **Borrowing a Book**
   - Select a book from the table
   - Click "Borrow Book"

3. **Returning a Book**
   - Select a borrowed book from the table
   - Click "Return Book"


## Customization

You can easily customize the application by modifying:
- Initial book list in `initializeBooks()` method
- Color schemes in `createStyledButton()` method
- Layout in the constructor

## Known Issues

- Currently supports single-user mode
- No persistent storage (books reset on application close)

## Contact

Nay Linn Htin/Willie Nay
williamgarrix14@gmail.com


## Acknowledgements

- Java Swing
- GridBagLayout
- Modern UI Design Principles
