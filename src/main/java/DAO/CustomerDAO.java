package DAO;

import bean.Customer;
import bean.Order;
import oracle.jdbc.proxy.annotation.Pre;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean add(Customer customer) {
        String sql = "insert into customer (username, password, email) values (?, ?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Customer customer) {
        String sql = "update customer set username=?, password=?, email=?, firstname=?, lastname=?, mobile=?, address=? where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getFirstname());
            ps.setString(5, customer.getLastname());
            ps.setInt(6, customer.getMobile());
            ps.setString(7, customer.getAddress());
            ps.setInt(8, customer.getId());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Customer get(int id) {
        Customer customer = null;
        String sql = "select * from customer where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setMobile(rs.getInt("mobile"));
                customer.setAddress(rs.getString("address"));
                customer.setBalance(rs.getInt("balance"));

                customer.setOrders(new OrderDAO().get(customer.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> list() {
        List<Customer> customers = new ArrayList<Customer>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            ResultSet rs = s.executeQuery("select * from customer");

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setMobile(rs.getInt("mobile"));
                customer.setAddress(rs.getString("address"));
                customer.setBalance(rs.getInt("balance"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public boolean isExist(String username) {
        Customer customer = get(username);
        return customer != null;
    }

    public Customer get(String username) {
        Customer customer = null;
        String sql = "select * from customer where username=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
                customer.setMobile(rs.getInt("mobile"));
                customer.setAddress(rs.getString("address"));
                customer.setBalance(rs.getInt("balance"));

                customer.setOrders(new OrderDAO().get(customer.getId()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public Customer get(String username, String password) {
        Customer customer = get(username);
        if (customer != null && password.equals(customer.getPassword())) {
            return customer;
        } else {
            return null;
        }
    }

    public boolean updateBalance(Customer customer) {
        String sql = "update customer set balance=? where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, customer.getBalance());
            ps.setInt(2, customer.getId());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
