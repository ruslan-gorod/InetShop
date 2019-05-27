package servlet;

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

@WebServlet(value = "/regist")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String name = request.getParameter("nameUser");
        String email = request.getParameter("emailUser");
        String salt = HashUtil.getRandomSalt();
        int roleId = Integer.parseInt(request.getParameter("idRoleUser"));
        User tmpUser = null;
        User newUser;
        List<User> listUsers = UserDaoHibImpl.getAllUsers();
        for (User user : listUsers) {
            if (user.getLogin().equals(login)) {
                tmpUser = user;
            }
        }
        if (tmpUser == null) {
            if (roleId == 1) {
                newUser = new User(login, pass, name, email, roleId, salt);
            } else {
                newUser = new User(login, pass, name, email, 2, salt);
            }
            UserDaoHibImpl.save(newUser);
            request.getSession().setAttribute("user", newUser);
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
