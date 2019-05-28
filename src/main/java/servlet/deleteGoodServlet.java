package servlet;

import dao.GoodDoaHibImpl;
import dao.UserDaoHibImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Good;
import model.User;

@WebServlet(name = "deleteGoodServlet")
public class deleteGoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idGood"));
        Good good = GoodDoaHibImpl.findById(id);
        GoodDoaHibImpl.delete(good);
        List<Good> goods = GoodDoaHibImpl.getAllGoods();
        List<User> users = UserDaoHibImpl.getAllUsers();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
