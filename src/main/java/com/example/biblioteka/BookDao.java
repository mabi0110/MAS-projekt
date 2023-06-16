package com.example.biblioteka;

import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Person;


import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


public class BookDao {
    private final DataSource dataSource;
    public BookDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Book> findBookWithTitle(String bookTitle){
        String sql = "SELECT id, title, isbn, publisher, publicationYear from book " +
                "where title='" + bookTitle + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Integer isbn = resultSet.getInt("isbn");
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
        final String sql = String.format("INSERT INTO book (title, isbn, publisher, publicationYear) VALUES('%s', %d,'%s', %d)",
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
}
