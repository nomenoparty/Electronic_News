package middleware.admin;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import model.RoleModel;
import model.UserModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/admin/dashboard", "/admin/category", "/admin/article", "/admin/account", "/admin/account/update", "/admin/account/delete", "/admin/category/load", "/admin/category/update", "/admin/category/delete", "/admin/article/category/load", "/admin/article/update", "/admin/article/delete" })
public class AuthMiddleware implements Filter {
    private UserDAO adminDao = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpRequest.getCookies();

        String token = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token.equals("")) {
            httpResponse.sendRedirect("/admin/login");
            return;
        }

        System.out.println("Filter authen Admin");

        UserModel adminModel = adminDao.getUserByTokenUser(token);

        if(adminModel == null){
            httpResponse.sendRedirect("/admin/login");
            return;
        }

        RoleModel role = roleDAO.selectById(adminModel.getRoleID());

        httpRequest.setAttribute("user", adminModel);
        httpRequest.setAttribute("role", role);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
