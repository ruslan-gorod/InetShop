package servlet;

import dao.GoodDao;
import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Roles;
import model.User;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", UserDao.selectAll());
        request.setAttribute("goods", GoodDao.selectAll());
        User user = (User)(request.getSession().getAttribute("user"));
        request.setAttribute("name", user.getName());
        if (user.getRoleId() == Roles.ADMIN.getRole()){
            request.getRequestDispatcher("users.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("goods.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
