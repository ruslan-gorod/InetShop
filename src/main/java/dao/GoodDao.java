package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Good;
import org.apache.log4j.Logger;

public class GoodDao {

    private static final Logger logger = Logger.getLogger(GoodDao.class);

    public static ArrayList<Good> selectAll() {
        ArrayList<Good> goods = new ArrayList<>();
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Double price = resultSet.getDouble(4);
                Good good = new Good(id, name, description, price);
                goods.add(good);
            }
        } catch (SQLException e) {
            logger.error("Can't return goods; ", e);
        }
        return goods;
    }

    public static Good selectOne(long id, String param) {
        Good good = null;
        Connection connection = DbConnector.connect();
        try {
            String sql = "SELECT * FROM goods WHERE " + param + "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                long idDb = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Double price = resultSet.getDouble(4);
                good = new Good(idDb, name, description, price);
            }
        } catch (SQLException e) {
            logger.error("Can't return user; ", e);
        }
        return good;
    }

    public static int insert(Good good) {
        Connection connection = DbConnector.connect();
        String sql = "INSERT INTO goods (name, description, price) Values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setDouble(3, good.getPrice());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't insert good;", e);
        }
        return 0;
    }

    public static int update(Good good) {
        Connection connection = DbConnector.connect();
        String sql = "UPDATE goods SET name = ?, description = ?, id = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setLong(3, good.getId());
            preparedStatement.setDouble(4, good.getPrice());
            preparedStatement.setLong(5, good.getId());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user;", e);
        }
        return 0;
    }

    public static boolean delete(long id) {
        Connection connection = DbConnector.connect();
        String sql = "DELETE FROM goods WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.debug(sql);
            return true;
        } catch (SQLException e) {
            logger.error("Can't delete good;", e);
        }
        return false;
    }
}
