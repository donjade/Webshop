package servlet;

import bean.Shipmethod;
import oracle.sql.INTERVALDS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShipmethodServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int cost = Integer.parseInt(request.getParameter("cost"));

        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setName(name);
        shipmethod.setCost(cost);

        shipmethodDAO.add(shipmethod);

        return "@admin_shipmethod_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        shipmethodDAO.delete(id);

        return "@admin_shipmethod_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        Shipmethod shipmethod = shipmethodDAO.get(id);

        request.setAttribute("shipmethod", shipmethod);

        return "admin_editShipmethod.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int cost = Integer.parseInt(request.getParameter("cost"));

        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setId(id);
        shipmethod.setName(name);
        shipmethod.setCost(cost);

        shipmethodDAO.update(shipmethod);

        return "@admin_shipmethod_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Shipmethod> shipmethods = shipmethodDAO.list();

        request.setAttribute("shipmethods", shipmethods);

        return "admin_listShipmethods.jsp";
    }
}
