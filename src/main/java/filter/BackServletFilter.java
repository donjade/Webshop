package filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BackServletFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("contextPath", contextPath);

        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        // eg. admin_category_list
        if (uri.startsWith("/admin_")) {
            // category
            String servletPath = StringUtils.substringBetween(uri, "_", "_") + "Servlet";
            // list
            String method = StringUtils.substringAfterLast(uri, "_");
            request.setAttribute("method", method);
            servletRequest.getRequestDispatcher("/" + servletPath).forward(request, response);
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
