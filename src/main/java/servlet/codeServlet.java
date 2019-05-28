package servlet;

import dao.OrderDaoHibImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

@WebServlet(value = "/codeServlet")
public class codeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        List<Order> orders = OrderDaoHibImpl.getAllOrders();
        Order orderFromDb = null;
        for (Order tmpOrder: orders){
            if (tmpOrder.getCode() == code) {
                orderFromDb = tmpOrder;
            }
        }
        if (orderFromDb != null) {
            request.getRequestDispatcher("users.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("goods.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
