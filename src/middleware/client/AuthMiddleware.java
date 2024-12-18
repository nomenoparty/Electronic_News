package middleware.client;

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

@WebFilter(urlPatterns = { "/", "/article/*", "/category/*", "/home", "/comment", "/profile" })
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
                if ("userToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        System.out.println("Filter authen Client");

        UserModel adminModel = adminDao.getUserByTokenUser(token);

        System.out.println(adminModel);

        httpRequest.setAttribute("userModel", adminModel);

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