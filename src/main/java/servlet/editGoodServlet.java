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

@WebServlet(value = "/editGoodServlet")
public class editGoodServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idGood");
        Good good = GoodDao.selectOne(id, "id");
        String name = request.getParameter("nameGood");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));
        good.setDescription(description);
        good.setName(name);
        good.setPrice(price);
        GoodDao.update(good);
        List<User> users = UserDao.selectAll();
        List<Good> goods = GoodDao.selectAll();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idGood");
        Good good = GoodDao.selectOne(id, "id");
        request.setAttribute("name", good.getName());
        request.setAttribute("idGood", good.getId());
        request.setAttribute("description", good.getDescription());
        request.setAttribute("price", good.getPrice());
        request.getRequestDispatcher("/addGood.jsp").forward(request, response);
    }
}
