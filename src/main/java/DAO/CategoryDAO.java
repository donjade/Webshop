package DAO;

import bean.Category;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public boolean add(Category category) {
        String sql = "insert into category (name) values (?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Category category) {
        String sql = "update category set name=? where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "delete from category where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Category get(int id) {
        Category category = null;

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                category = new Category();
                String name = rs.getString(2);
                category.setName(name);
                category.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return category;
    }

    public List<Category> list() {
        List<Category> categories = new ArrayList<Category>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from category";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Category bean = new Category();
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                categories.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
