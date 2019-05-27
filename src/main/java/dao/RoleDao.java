package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Role;
import org.apache.log4j.Logger;

public class RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDao.class);

    public static ArrayList<Role> selectAll() {
        ArrayList<Role> roles = new ArrayList<>();
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM roles");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Role user = new Role(id, name, description);
                roles.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can't return users; ", e);
        }
        return roles;
    }

    public static Role selectOne(long id, String param) {
        Role role = null;
        Connection connection = DbConnector.connect();
        try {
            String sql = "SELECT * FROM roles WHERE " + param + "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                int idDb = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                role = new Role(idDb, name, description);
            }
        } catch (SQLException e) {
            logger.error("Can't return user; ", e);
        }
        return role;
    }

    public static int insert(Role role) {
        Connection connection = DbConnector.connect();
        String sql = "INSERT INTO roles (name, description) Values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't insert user;", e);
        }
        return 0;
    }

    public static int update(Role role) {
        Connection connection = DbConnector.connect();
        String sql = "UPDATE roles SET name = ?, description = ?, id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setLong(3, role.getId());
            preparedStatement.setLong(4, role.getId());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update role;", e);
        }
        return 0;
    }

    public static boolean delete(long id) {
        Connection connection = DbConnector.connect();
        String sql = "DELETE FROM roles WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.debug(sql);
            return true;
        } catch (SQLException e) {
            logger.error("Can't delete role;", e);
        }
        return false;
    }
}
