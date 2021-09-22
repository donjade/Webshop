package servlet;

import bean.Category;
import bean.SubCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");

        Category category = new Category();
        category.setName(name);
        categoryDAO.add(category);

        return "@admin_category_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));

        categoryDAO.delete(cid);

        return "@admin_category_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));

        Category category = categoryDAO.get(cid);
        category.setSubCategories(subCategoryDAO.listByCategory(cid));

        request.setAttribute("category", category);

        return "admin_editCategory.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDAO.update(category);

        return "@admin_category_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryDAO.list();

        for (Category category: categories) {
            category.setSubCategories(subCategoryDAO.listByCategory(category.getId()));
        }

        request.setAttribute("categories", categories);

        return "admin_listCategories.jsp";
    }
}
