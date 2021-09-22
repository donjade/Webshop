package DAO;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    // usage of shipmethod
    public Map<String, Integer> method1() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "SELECT name, count(name) as num FROM shipmethod, order_ WHERE shipmethod.id = order_.shipmethodid group by name";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    // how many orders contains of each product
    public Map<String, Integer> method2() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select name, count(name) as num from product, orderitem where product.id=orderitem.pid group by name order by num desc";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    // number of product of each subcategory
    public Map<String, Integer> method3() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select subcategory.name, count(subcategory.name) as num from subcategory, product where subcategory.id=product.subcid group by subcategory.name";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    // each customer maximum of ordered total price
    public Map<String, Integer> method4() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select username, max(totalprice) from customer, order_ where customer.id=order_.custid group by username";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    // each product maximum of rate on review
    public Map<String, Integer> method5() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select product.name, max(review.rate) as maxrate from product, review where product.id=review.pid group by product.name";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
}
