package servlet;

import dao.GoodDao;
import dao.RoleDao;
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

@WebServlet(value = "/editUserServlet")
public class editUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idUser");
        User user = UserDao.selectOne(id,"id");
        String login = request.getParameter("login");
        String password =  request.getParameter("password");
        String name =  request.getParameter("nameUser");
        Long role = Long.parseLong(request.getParameter("idRoleUser"));
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setRoleId(role);
        UserDao.update(user);
        List<User> users = UserDao.selectAll();
        List<Good> goods = GoodDao.selectAll();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        request.setAttribute("role",RoleDao.selectOne(user.getRoleId(),"id"));
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idUser");
        User user = UserDao.selectOne(id, "id");
        request.setAttribute("login",user.getLogin());
        request.setAttribute("name",user.getName());
        request.setAttribute("id",user.getId());
        request.setAttribute("role", RoleDao.selectOne(user.getRoleId(),"id"));
        request.setAttribute("email",user.getEmail());
        request.getRequestDispatcher("/editUser.jsp").forward(request, response);
    }
}
