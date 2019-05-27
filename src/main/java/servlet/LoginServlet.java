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
import utils.HashUtil;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String loginFromForm = request.getParameter("login");
        String passwordFromForm = request.getParameter("password");
        User newUser = null;
        List<User> listUsers = UserDaoHibImpl.getAllUsers();
        for (User user : listUsers) {
            if (user.getLogin().equals(loginFromForm)) {
                newUser = user;
            }
        }
        if (newUser != null) {
            String hashPasswordFromForm = HashUtil.getSHA512SecurePassword(passwordFromForm, newUser.getSalt());
            if (newUser.getPassword().equals(hashPasswordFromForm)) {
                request.getSession().setAttribute("user", newUser);
                request.getRequestDispatcher("/admin").forward(request, response);
            } else {
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
