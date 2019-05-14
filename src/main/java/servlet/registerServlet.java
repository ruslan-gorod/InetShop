package servlet;

import dao.GoodDao;
import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import utils.HashUtil;

@WebServlet(value = "/regist")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String name = request.getParameter("nameUser");
        String email = request.getParameter("emailUser");
        User tmpUser = UserDao.selectOne(login, "login");
        if (tmpUser == null) {
            User user = new User(login, pass, name, email, 2);
            UserDao.insert(user);
            request.getSession().setAttribute("user", UserDao.selectOne(login, "login"));
            getServletContext().getRequestDispatcher("/admin").forward(request, response);
        } else {
            if (!tmpUser.getPassword().equals(pass)) {
                getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
