package com.example.sns;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.sql.*;
public class DbManager {
    private static final String URL = "jdbc:postgresql://192.168.1.77:5432/post";  //192.168.1.77
    private static final String USERNAME = "jojo";
    private static final String PASSWORD = "bean";
    public User select(String login) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String querySel = "SELECT * FROM users JOIN emails ON users.login = emails.login WHERE users.login = '" + login + "'";
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySel);
            if (resultSet.next()) {
                user = new User(resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("dat"),
                        resultSet.getString("email")
                );
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    public int insert(User user) {
        int rowsUpdated = 0;
        String insertQuery = "INSERT INTO users (login, password, dat) VALUES (?, ?, ?); " +
                "INSERT INTO emails (login, email) VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, java.sql.Date.valueOf(user.getDat()));
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }
}
