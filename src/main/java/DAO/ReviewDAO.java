package DAO;


import bean.Review;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public boolean add(Review review) {
        String sql = "INSERT INTO review (content, rate, custuname, pid) VALUES (?, ?, ?, ?)";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, review.getContent());
            ps.setInt(2, review.getRate());
            ps.setString(3, review.getCustuname());
            ps.setInt(4, review.getPid());

            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Review> listByProduct(int pid) {
        List<Review> reviews = new ArrayList<Review>();
        String sql = "select * from review where pid=?";

        try (Connection c = DatabaseUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setContent(rs.getString("content"));
                review.setCreateddate(rs.getDate("createddate"));
                review.setRate(rs.getInt("rate"));
                review.setCustuname(rs.getString("custuname"));
                review.setPid(rs.getInt("pid"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
