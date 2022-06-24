package com.uni.javacrud.dao;

import com.uni.javacrud.beans.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDao {

    private Connection conn;

    public SubscriptionDao(Connection connection) {
        conn = connection;
    }

    public void insertSubscription(Subscription subscription) throws SQLException {
        String sql = "insert into subscriptions (name, price, date_start, date_end, edition_id, user_id) values (?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, subscription.getName());
        pstm.setFloat(2, subscription.getPrice());
        pstm.setString(3, subscription.getDate_start());
        pstm.setString(4, subscription.getDate_end());
        pstm.setInt(5, subscription.getEdition_id());
        pstm.setInt(6, subscription.getUser_id());
        pstm.executeUpdate();
    }

    public List<Subscription> querySubscriptionsByUserId(int userId) throws SQLException {
        String sql = "select * from subscriptions where user_id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, userId);
        ResultSet rs = pstm.executeQuery();
        List<Subscription> subscriptions = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            float price = rs.getFloat("price");
            String date_start = rs.getString("date_start");
            String date_end = rs.getString("date_end");
            int editionId = rs.getInt("edition_id");
            Subscription subscription = new Subscription(id, name, price, date_start, date_end, editionId, userId);
            subscriptions.add(subscription);
        }
        return subscriptions;
    }
}
