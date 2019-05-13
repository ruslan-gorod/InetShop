package servlet;

import dao.OrderDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/codeServlet")
public class codeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        if (code == OrderDao.selectOne(code, "code").getCode()){
            response.getWriter().print("Congratulations");
        } else {
            response.getWriter().print("Wrong code!");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
