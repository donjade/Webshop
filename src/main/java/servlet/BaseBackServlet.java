package servlet;

import DAO.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseBackServlet extends HttpServlet {

    public abstract String add(HttpServletRequest request, HttpServletResponse response);
    public abstract String delete(HttpServletRequest request, HttpServletResponse response);
    public abstract String edit(HttpServletRequest request, HttpServletResponse response);
    public abstract String update(HttpServletRequest request, HttpServletResponse response);
    public abstract String list(HttpServletRequest request, HttpServletResponse response);

    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected CustomerDAO customerDAO = new CustomerDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderitemDAO orderitemsDAO = new OrderitemDAO();
    protected ProductDAO productDAO = new ProductDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected ShipmethodDAO shipmethodDAO = new ShipmethodDAO();
    protected SubCategoryDAO subCategoryDAO = new SubCategoryDAO();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String redirect = m.invoke(this, request, response).toString();

            if (redirect.startsWith("@")) {
                response.sendRedirect(redirect.substring(1));
            } else {
                request.getRequestDispatcher(redirect).forward(request, response);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
