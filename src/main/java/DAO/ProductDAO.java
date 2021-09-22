package DAO;

import bean.Product;
import oracle.jdbc.proxy.annotation.Pre;
import util.DatabaseUtil;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean add(Product product) {
        String sql = "insert into product (name, description, price, stock, imagename, rate, subcid) values (?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImagename());
            ps.setInt(6, product.getRate());
            ps.setInt(7, product.getSubcid());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Product product) {
        String sql = "update product set name=?, description=?, price=?, stock=?, imagename=?, rate=?, subcid=? where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImagename());
            ps.setInt(6, product.getRate());
            ps.setInt(7, product.getSubcid());
            ps.setInt(8, product.getId());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "delete from product where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Product get(int pid) {
        Product product = new Product();
        String sql = "select * from product where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> list() {
        List<Product> products = new ArrayList<Product>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "SELECT * FROM product";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> listBySubcategory(int subcid) {
        List<Product> products = new ArrayList<Product>();
        String sql = "select * from product where subcid=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, subcid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> listNewArrivalsByCategory(int cid, int limits) {
        List<Product> products = new ArrayList<Product>();
        String sql = "select * from product where subcid in (select id from subcategory where cid=?) ORDER BY createddate DESC FETCH FIRST ? ROWS ONLY";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, cid);
            ps.setInt(2, limits);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // TODO
    public List<Product> listPopularsByCategory(int cid, int limits) {
        List<Product> products = new ArrayList<>();
        String sql = "";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> listByCategory(int cid) {
        List<Product> products = new ArrayList<Product>();
        String sql = "select * from product where subcid in (select id from subcategory where cid=?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> search(String keyword) {
        List<Product> products = new ArrayList<Product>();
        String sql = "select * from product where name like ? or description like ?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setImagename(rs.getString("imagename"));
                product.setRate(rs.getInt("rate"));
                product.setSubcid(rs.getInt("subcid"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
