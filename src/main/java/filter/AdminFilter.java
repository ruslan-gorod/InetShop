package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import model.User;

@WebFilter(urlPatterns = "/admin")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null && user.getRoleId() == 2) {
            req.getRequestDispatcher("goods.jsp").forward(req,resp);
            chain.doFilter(httpServletRequest, resp);
        } else {
            req.getRequestDispatcher("users.jsp").forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
