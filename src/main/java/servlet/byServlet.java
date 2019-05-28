package servlet;

import dao.OrderDao;
import dao.OrderDaoHibImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.User;
import service.SendEmail;

@WebServlet(value = "/byServlet")
public class byServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goodId = Integer.parseInt(request.getParameter("idGood"));
        int code = (int) ((Math.random() * (9999 - 1000)) + 1000);
        User user = (User) request.getSession().getAttribute("user");
        OrderDaoHibImpl.save(new Order(goodId, user.getId(), code));
        SendEmail.sendMessage(user.getEmail(), code);
        request.getRequestDispatcher("code.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
