package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;
import org.apache.log4j.Logger;

public class OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class);
    private static Order order= null;

    public static Order selectOne(long id) {

        Connection connection = DbConnector.connect();
        try {
            String sql = "SELECT * FROM orders WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                int idDb = resultSet.getInt(1);
                int goodId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                int code = resultSet.getInt(4);
                order = new Order(idDb, goodId, userId, code);
            }
        } catch (SQLException e) {
            logger.error("Can't return user; ", e);
        }
        return order;
    }

    public static int insert(Order order) {
        Connection connection = DbConnector.connect();
        String sql = "INSERT INTO orders (goodId, userId, code) Values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getGoodId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getCode());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't insert good;", e);
        }
        return 0;
    }
}
