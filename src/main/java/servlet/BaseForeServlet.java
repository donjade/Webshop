package servlet;

import DAO.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseForeServlet extends HttpServlet {

    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected CustomerDAO customerDAO = new CustomerDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderitemDAO orderitemsDAO = new OrderitemDAO();
    protected ProductDAO productDAO = new ProductDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected ShipmethodDAO shipmethodDAO = new ShipmethodDAO();
    protected SubCategoryDAO subCategoryDAO = new SubCategoryDAO();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {

            String method = (String) req.getAttribute("method");
            Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String redirect = m.invoke(this, req, res).toString();

            if (redirect.startsWith("@")) {
                res.sendRedirect(redirect.substring(1));
            } else {
                req.getRequestDispatcher(redirect).forward(req, res);
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
