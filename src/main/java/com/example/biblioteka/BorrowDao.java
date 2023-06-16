package com.example.biblioteka;

import com.example.biblioteka.model.Borrow;
import com.example.biblioteka.model.Person;

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

public class BorrowDao {
    private final DataSource dataSource;
    public BorrowDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Borrow> findBorrowsWithUserId(int selectedUserId){
        List<Borrow> listOfBorrowedBooks = new ArrayList<>();
        String sql = String.format("SELECT id, userId, bookId, borrowDate from borrow where userId=%d", selectedUserId);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
                Integer bookId = resultSet.getInt("bookId");
                Date borrowDate = resultSet.getDate("borrowDate");
                listOfBorrowedBooks.add(new Borrow(id, userId, bookId, borrowDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfBorrowedBooks;
    }


    public void save(Borrow borrow) {
        final String sql = String.format("INSERT INTO borrow (userId, bookId, borrowDate) VALUES(%d, %d,'%s')",
                borrow.getUserId(), borrow.getBookId(), borrow.getBorrowDate().toString());
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                borrow.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}