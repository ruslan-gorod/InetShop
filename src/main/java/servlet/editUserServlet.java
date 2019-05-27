package servlet;

import dao.GoodDao;
import dao.GoodDoaHibImpl;
import dao.RoleDao;
import dao.RoleDaoHibImpl;
import dao.UserDao;
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

@WebServlet(value = "/editUserServlet")
public class editUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUser"));
        User user = UserDaoHibImpl.findById(id);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("nameUser");
        int role = Integer.parseInt(request.getParameter("idRoleUser"));
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setRoleId(role);
        UserDaoHibImpl.update(user);
        List<User> users = UserDaoHibImpl.getAllUsers();
        List<Good> goods = GoodDoaHibImpl.getAllGoods();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        request.setAttribute("role", RoleDaoHibImpl.findById((int)user.getRoleId()));
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUser"));
        User user = UserDaoHibImpl.findById(id);
        request.setAttribute("login", user.getLogin());
        request.setAttribute("name", user.getName());
        request.setAttribute("id", user.getId());
        request.setAttribute("role", RoleDaoHibImpl.findById((int)user.getRoleId()));
        request.setAttribute("email", user.getEmail());
        request.getRequestDispatcher("/editUser.jsp").forward(request, response);
    }
}
