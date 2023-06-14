package com.example.biblioteka;

import com.example.biblioteka.model.Person;

import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDao {

    private final DataSource dataSource;
    public PersonDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> findPersonWithLoginAndPassword(String selectedLogin, String selectedPassword){
        String sql = "SELECT id, firstName, lastName, login, pass, accountType from person where login='" + selectedLogin + "' and pass='" + selectedPassword + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String accountType = resultSet.getString("accountType");
                return Optional.of(new Person(id, firstName, lastName, login, pass, accountType));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void save(Person person) {
        final String sql = String.format("INSERT INTO person (firstName, lastName, login, pass, accountType) VALUES('%s', '%s', '%s', '%s', '%s')",
                person.getFirstName(), person.getLastName(), person.getLogin(), person.getPass(), person.getAccountType());
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
