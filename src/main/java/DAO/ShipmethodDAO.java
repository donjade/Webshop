package DAO;

import bean.Shipmethod;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmethodDAO {

    public boolean add(Shipmethod shipmethod) {
        String sql = "INSERT INTO shipmethod (name, cost) VALUES (?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, shipmethod.getName());
            ps.setInt(2, shipmethod.getCost());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Shipmethod shipmethod) {
        String sql = "UPDATE shipmethod SET name=?, cost=? WHERE id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, shipmethod.getName());
            ps.setInt(2, shipmethod.getCost());
            ps.setInt(3, shipmethod.getId());

            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM shipmethod WHERE id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Shipmethod get(int id) {
        Shipmethod shipmethod = new Shipmethod();
        String sql = "SELECT * FROM shipmethod WHERE id=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                shipmethod.setId(rs.getInt("id"));
                shipmethod.setName(rs.getString("name"));
                shipmethod.setCost(rs.getInt("cost"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipmethod;
    }


    public List<Shipmethod> list() {
        List<Shipmethod> shipmethods = new ArrayList<>();

        try (Connection c = DatabaseUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "SELECT * FROM shipmethod";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Shipmethod shipmethod = new Shipmethod();

                shipmethod.setId(rs.getInt("id"));
                shipmethod.setName(rs.getString("name"));
                shipmethod.setCost(rs.getInt("cost"));

                shipmethods.add(shipmethod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipmethods;
    }
}
