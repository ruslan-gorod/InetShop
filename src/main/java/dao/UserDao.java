package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    public static ArrayList<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int roleId = resultSet.getInt("roleId");
                String salt = resultSet.getString("salt");
                User user = new User(id, login, password, name, email, roleId, salt);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can't return users; ", e);
        }
        return users;
    }

    public static User selectOne(String login, String param) {
        User user = null;
        Connection connection = DbConnector.connect();
        try {
            String sql = "SELECT * FROM users WHERE " + param + "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String loginDb = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int roleId = resultSet.getInt("roleId");
                String salt = resultSet.getString("salt");
                user = new User(id, loginDb, password, name, email, roleId, salt);
            }
        } catch (SQLException e) {
            logger.error("Can't return user; ", e);
        }
        return user;
    }

    public static int insert(User user) {
        Connection connection = DbConnector.connect();
        String sql = "INSERT INTO users (login, password, name, email, roleId, salt) Values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getRoleId());
            preparedStatement.setString(6, user.getSalt());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't insert user;", e);
        }
        return 0;
    }

    public static int update(User user) {
        Connection connection = DbConnector.connect();
        String sql = "UPDATE users SET  login = ?, password = ?, name = ?, email = ?, roleId = ?, salt = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getRoleId());
            preparedStatement.setString(6, user.getSalt());
            preparedStatement.setInt(7, user.getId());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user;", e);
        }
        return 0;
    }

    public static boolean delete(String id) {
        Connection connection = DbConnector.connect();
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            logger.debug(sql);
            return true;
        } catch (SQLException e) {
            logger.error("Can't delete user;", e);
        }
        return false;
    }
}

