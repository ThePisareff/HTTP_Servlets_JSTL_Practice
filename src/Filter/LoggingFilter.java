package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var parameterMap = servletRequest.getParameterMap();
        if (!parameterMap.isEmpty()) {
            parameterMap.forEach((k, v) -> System.out.println(k + " : " + Arrays.toString(v)));
        } else {
            System.out.println("parameterMap is empty");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
