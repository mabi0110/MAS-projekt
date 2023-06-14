package com.example.biblioteka;

import com.example.biblioteka.model.Book;


import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookDao {
    private final DataSource dataSource;
    public BookDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Book book) {
        final String sql = String.format("INSERT INTO book (title, publisher, publicationYear) VALUES('%s', '%s', %d)",
                book.getTitle(), book.getPublisher(), book.getPublicationYear());
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
