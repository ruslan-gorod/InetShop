package servlet;

import dao.GoodDao;
import dao.UserDao;
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
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idGood");
        GoodDao.delete(id);
        List<Good> goods = GoodDao.selectAll();
        List<User> users = UserDao.selectAll();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
