package servlet;

import bean.Category;
import bean.SubCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubCategoryServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        String subCategoryname = request.getParameter("subCategoryname");

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryname);
        subCategory.setCid(cid);
        subCategoryDAO.add(subCategory);

        return "@admin_category_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int subcid = Integer.parseInt(request.getParameter("subcid"));

        subCategoryDAO.delete(subcid);

        return "@admin_category_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
