package filter;

import DAO.CategoryDAO;
import DAO.OrderitemDAO;
import DAO.ProductDAO;
import DAO.SubCategoryDAO;
import bean.*;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeServletFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("contextPath", contextPath);

        List<Orderitem> cartitems = (List<Orderitem>) request.getSession().getAttribute("cartitems");
        int cartTotalItemNumber = 0;
        if (cartitems != null && !cartitems.isEmpty()) {
            for (Orderitem orderitem : cartitems) {
                cartTotalItemNumber += orderitem.getPquantity();
            }
        }
        request.setAttribute("cartTotalItemNumber", cartTotalItemNumber);

        List<Category> categories = (List<Category>) request.getAttribute("categories");
        if (categories == null) {
            categories = new CategoryDAO().list();
            for (Category c: categories) {
                c.setSubCategories(new SubCategoryDAO().listByCategory(c.getId()));
            }
            request.setAttribute("categories", categories);
        }
        Map<String, List<Product>> newProductsByCategory = (Map<String, List<Product>>) request.getAttribute("newProductsByCategory");
        if (newProductsByCategory == null) {
            newProductsByCategory = new HashMap<String, List<Product>>();
            ProductDAO productDAO = new ProductDAO();
            for (Category c: categories) {
                if (!(productDAO.listByCategory(c.getId())).isEmpty()) {
                    newProductsByCategory.put(c.getName(), productDAO.listNewArrivalsByCategory(c.getId(), 3));
                }
//            System.out.println(c.getName());
            }
        }
        request.setAttribute("newProductsByCategory", newProductsByCategory);


        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        if (uri.startsWith("/fore") && !uri.startsWith("foreServlet")) {
            String method = StringUtils.substringAfterLast(uri, "fore");
            request.setAttribute("method", method);
            servletRequest.getRequestDispatcher("/foreServlet").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
        return;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
