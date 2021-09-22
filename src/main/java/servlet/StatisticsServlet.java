package servlet;

import DAO.Statistics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class StatisticsServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        return null;
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
        Statistics statistics = new Statistics();
        Map<String, Integer> map1 = statistics.method1();
        Map<String, Integer> map2 = statistics.method2();
        Map<String, Integer> map3 = statistics.method3();
        Map<String, Integer> map4 = statistics.method4();
        Map<String, Integer> map5 = statistics.method5();

        request.setAttribute("map1", map1);
        request.setAttribute("map2", map2);
        request.setAttribute("map3", map3);
        request.setAttribute("map4", map4);
        request.setAttribute("map5", map5);

        return "admin_statistics.jsp";
    }
}
