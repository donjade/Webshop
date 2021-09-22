package servlet;

import bean.Product;
import bean.SubCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imagename = request.getParameter("imagename");
        int subcid = Integer.parseInt(request.getParameter("subcid"));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImagename(imagename);
        product.setSubcid(subcid);

        productDAO.add(product);

        return "@admin_product_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));

        productDAO.delete(pid);

        return "@admin_product_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));

        Product product = productDAO.get(pid);
        List<SubCategory> subCategories = subCategoryDAO.list();

        request.setAttribute("subCategories", subCategories);
        request.setAttribute("product", product);

        return "admin_editProduct.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imagename = request.getParameter("imagename");
        int subcid = Integer.parseInt(request.getParameter("subcid"));

        Product product = productDAO.get(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImagename(imagename);
        product.setSubcid(subcid);

        productDAO.update(product);

        return "@admin_product_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productDAO.list();

        List<SubCategory> subCategories = subCategoryDAO.list();

        request.setAttribute("subCategories", subCategories);
        request.setAttribute("products", products);
        return "admin_listProducts.jsp";
    }
}
