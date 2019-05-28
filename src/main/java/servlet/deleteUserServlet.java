package servlet;

import dao.UserDao;
import dao.UserDaoHibImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(value = "/deleteUserServlet")
public class deleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUser"));
        User user = UserDaoHibImpl.findById(id);
        UserDaoHibImpl.delete(user);
        List<User> users = UserDao.selectAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
