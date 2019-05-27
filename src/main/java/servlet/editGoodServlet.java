package servlet;

import dao.GoodDao;
import dao.GoodDoaHibImpl;
import dao.RoleDao;
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

@WebServlet(value = "/editGoodServlet")
public class editGoodServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idGood"));
        Good good = GoodDoaHibImpl.findById(id);
        String name = request.getParameter("nameGood");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));
        good.setDescription(description);
        good.setName(name);
        good.setPrice(price);
        GoodDoaHibImpl.update(good);
        List<User> users = UserDaoHibImpl.getAllUsers();
        List<Good> goods = GoodDoaHibImpl.getAllGoods();
        request.setAttribute("users", users);
        request.setAttribute("goods", goods);
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idGood"));
        Good good = GoodDoaHibImpl.findById(id);
        request.setAttribute("name", good.getName());
        request.setAttribute("idGood", good.getId());
        request.setAttribute("description", good.getDescription());
        request.setAttribute("price", good.getPrice());
        request.getRequestDispatcher("/addGood.jsp").forward(request, response);
    }
}
