package DAO;

import bean.Order;
import bean.Orderitem;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderitemDAO {

    public boolean add(Orderitem orderitem) {
        String sql = "INSERT INTO orderitem (ordernum, pid, pquantity, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, orderitem.getOrdernum());
            ps.setInt(2, orderitem.getProduct().getId());
            ps.setInt(3, orderitem.getPquantity());
            ps.setInt(4, orderitem.getSubtotal());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Orderitem> listByOrder(String ordernum) {
        List<Orderitem> orderitems = new ArrayList<Orderitem>();
        String sql = "SELECT * FROM orderitem WHERE ordernum=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ordernum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orderitem orderitem = new Orderitem();
                orderitem.setId(rs.getInt("id"));
                orderitem.setProduct(new ProductDAO().get(rs.getInt("pid")));
                orderitem.setPquantity(rs.getInt("pquantity"));
                orderitem.setSubtotal(rs.getInt("subtotal"));

                orderitems.add(orderitem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderitems;
    }
}
