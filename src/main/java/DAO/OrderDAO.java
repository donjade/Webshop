package DAO;

import bean.Order;
import util.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAO {

    public boolean add(Order order) {
        String sql = "INSERT INTO order_ VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, order.getNum());
            ps.setInt(2, order.getPcount());
            ps.setDate(3, (Date) order.getOrderdate());
            ps.setDate(4, (Date) order.getPaydate());
            ps.setDate(5, (Date) order.getShipdate());
            ps.setString(6, order.getStatus());
            ps.setInt(7, order.getShipmethod().getId());
            ps.setInt(8, order.getCustid());
            ps.setInt(9, order.getTotalprice());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean udpateStatus(Order order) {
        String sql = "UPDATE order_ SET status=? WHERE num=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, order.getStatus());
            ps.setString(2, order.getNum());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Order get(String num) {
        Order order = new Order();
        String sql = "SELECT * FROM order_ where num=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, num);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                order.setNum(rs.getString("num"));
                order.setPcount(rs.getInt("pcount"));
                order.setOrderdate(rs.getDate("orderdate"));
                order.setPaydate(rs.getDate("paydate"));
                order.setShipdate(rs.getDate("shipdate"));
                order.setStatus(rs.getString("status"));
                order.setShipmethod(new ShipmethodDAO().get(rs.getInt("shipmethodid")));
                order.setCustid(rs.getInt("custid"));
                order.setTotalprice(rs.getInt("totalprice"));

                order.setOrderitems(new OrderitemDAO().listByOrder(order.getNum()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> list() {
        List<Order> orders = new ArrayList<Order>();


        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "SELECT * FROM order_ ORDER BY shipdate nulls first, paydate";

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order();
                order.setNum(rs.getString("num"));
                order.setPcount(rs.getInt("pcount"));
                order.setOrderdate(rs.getDate("orderdate"));
                order.setPaydate(rs.getDate("paydate"));
                order.setShipdate(rs.getDate("shipdate"));
                order.setStatus(rs.getString("status"));
                order.setShipmethod(new ShipmethodDAO().get(rs.getInt("shipmethodid")));
                order.setCustid(rs.getInt("custid"));
                order.setTotalprice(rs.getInt("totalprice"));

                order.setOrderitems(new OrderitemDAO().listByOrder(order.getNum()));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> get(int custid) {
        List<Order> orders = list(custid, "%");
        return orders;
    }

    public List<Order> list(int custid, String status) {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM order_ where custid=? and status LIKE ?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, custid);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setNum(rs.getString("num"));
                order.setPcount(rs.getInt("pcount"));
                order.setOrderdate(rs.getDate("orderdate"));
                order.setPaydate(rs.getDate("paydate"));
                order.setShipdate(rs.getDate("shipdate"));
                order.setStatus(rs.getString("status"));
                order.setShipmethod(new ShipmethodDAO().get(rs.getInt("shipmethodid")));
                order.setCustid(rs.getInt("custid"));
                order.setTotalprice(rs.getInt("totalprice"));

                order.setOrderitems(new OrderitemDAO().listByOrder(order.getNum()));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


}
