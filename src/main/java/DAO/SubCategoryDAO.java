package DAO;

import bean.Category;
import bean.SubCategory;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubCategoryDAO {

    public boolean add(SubCategory subCategory) {
        String sql = "INSERT INTO subcategory (name, cid) VALUES (?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, subCategory.getName());
            ps.setInt(2, subCategory.getCid());

            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(SubCategory subCategory) {
        String sql = "UPDATE subcategory SET name=?, cid=? WHERE id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, subCategory.getName());
            ps.setInt(2, subCategory.getCid());
            ps.setInt(3, subCategory.getCid());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM subcategory WHERE id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<SubCategory> list() {
        List<SubCategory> subCategories = new ArrayList<SubCategory>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from subcategory";

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setId(rs.getInt("id"));
                subCategory.setName(rs.getString("name"));
                subCategory.setCid(rs.getInt("cid"));

                subCategories.add(subCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCategories;
    }

    public SubCategory get(int id) {
        SubCategory subCategory = new SubCategory();
        String sql = "select * from subcategory where id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                subCategory.setCid(id);
                subCategory.setName(rs.getString("name"));
                subCategory.setCid(rs.getInt("cid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCategory;
    }

    public List<SubCategory> listByCategory(int cid) {
        List<SubCategory> subCategories = new ArrayList<SubCategory>();
        String sql = "select * from subcategory where cid=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setId(rs.getInt("id"));
                subCategory.setName(rs.getString("name"));
                subCategory.setCid(cid);

                subCategories.add(subCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCategories;
    }

}
