package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of("/login","/registration","/images");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicUri(requestURI) || isUserLoggedIn(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer"); // referer - header хранит предыдущую страницу (т.е. откуда пришел запрос)
            ((HttpServletResponse)servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
        }

    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null;
    }

    private boolean isPublicUri(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }
}
