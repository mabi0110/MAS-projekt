package com.example.biblioteka;

import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Borrow;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookDao {
    private final DataSource dataSource;
    private final BorrowDao borrowDao = new BorrowDao();
    public BookDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Book> findBookWithTitle(String bookTitle){
        String sql = "SELECT id, title, isbn, publisher, publicationYear, authorId from book " +
                "where title='" + bookTitle + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                String publisher = resultSet.getString("publisher");
                Integer publicationYear = resultSet.getInt("publicationYear");
                Integer authorId = resultSet.getInt("authorId");
                return Optional.of(new Book(id, title, isbn, publisher, publicationYear, authorId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void save(Book book) {
        final String sql = String.format("INSERT INTO book (title, isbn, publisher, publicationYear) VALUES('%s', '%s','%s', %d)",
                book.getTitle(), book.getIsbn(), book.getPublisher(), book.getPublicationYear());
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findBooksBorrowedByUser(int selectedUserId) {
        String sql;
        List<Book> borrowedBooks = new ArrayList<>();
        List<Borrow> borrows = borrowDao.findBorrowsWithUserId(selectedUserId);
        List<Integer> booksIds = new ArrayList<>();
        for (Borrow borrow : borrows) {
            booksIds.add(borrow.getBookId());
        }
        for (Integer booksId : booksIds) {
            sql = String.format("SELECT id, title, isbn, publisher, publicationYear, authorId from book where id=%d", booksId);
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String isbn = resultSet.getString("isbn");
                    String publisher = resultSet.getString("publisher");
                    Integer publicationYear = resultSet.getInt("publicationYear");
                    Integer authorId = resultSet.getInt("authorId");
                    borrowedBooks.add(new Book(id, title, isbn, publisher, publicationYear, authorId));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return borrowedBooks;
    }
}
