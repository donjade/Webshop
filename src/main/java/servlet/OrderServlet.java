package servlet;

import bean.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderServlet extends BaseBackServlet {

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
        String num = request.getParameter("num");
        Order order = orderDAO.get(num);
        order.setCustomer(customerDAO.get(order.getCustid()));

        request.setAttribute("order", order);

        return "admin_orderDetails.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        String num = request.getParameter("num");
        String status = request.getParameter("status");
        Order order = orderDAO.get(num);
        order.setStatus(status);

        orderDAO.udpateStatus(order);
        return "@admin_order_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = orderDAO.list();

        for (Order order: orders) {
            order.setCustomer(customerDAO.get(order.getCustid()));
        }

        request.setAttribute("orders", orders);

        return "admin_listOrders.jsp";
    }
}
