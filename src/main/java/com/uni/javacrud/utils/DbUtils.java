package com.uni.javacrud.utils;

import com.uni.javacrud.beans.Edition;
import com.uni.javacrud.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    public static User findUserByEmail(Connection conn, //
                                String email, String password) throws SQLException {

        String sql = "Select * from users u "
                + " where u.email = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, username, email, password, role, status);
            return user;
        }
        return null;
    }

    public static User findUserByUsername(Connection conn, //
                                       String username, String password) throws SQLException {

        String sql = "Select * from users u "
                + " where u.username = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, username, email, password, role, status);
            return user;
        }
        return null;
    }

    public static User findUserByUsername(Connection conn, //
                                          String username) throws SQLException {

        String sql = "Select * from users u "
                + " where u.username = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            User user = new User(id, username, email, password, role, status);
            return user;
        }
        return null;
    }

    public static List<Edition> queryEditions(Connection conn) throws SQLException {
        String sql = "Select * from editions e ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Edition> editions = new ArrayList<Edition>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            float price = rs.getFloat("price");
            int topic_id = rs.getInt("topic_id");
            Edition edition = new Edition(id, name, price, topic_id);
            editions.add(edition);
        }
        return editions;
    }

    public static Edition findEditionById(Connection conn, int id) throws SQLException {
        String sql = "Select * from editions e where e.id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            float price = rs.getFloat("price");
            int topic_id = rs.getInt("topic_id");
            Edition edition = new Edition(id, name, price, topic_id);
            return edition;
        }
        return null;
    }

    public static Edition findEditionByName(Connection conn, String name) throws SQLException {
        String sql = "Select * from editions e where e.name=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            float price = rs.getFloat("price");
            int topic_id = rs.getInt("topic_id");
            Edition edition = new Edition(id, name, price, topic_id);
            return edition;
        }
        return null;
    }

    public static void updateEdition(Connection conn, Edition edition) throws SQLException {
        String sql = "Update editions set name =?, price=?, topic_id=? where id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, edition.getName());
        pstm.setFloat(2, edition.getPrice());
        pstm.setInt(3, edition.getTopic_id());
        pstm.setInt(4, edition.getId());
        pstm.executeUpdate();
    }

    public static void insertEdition(Connection conn, Edition edition) throws SQLException {
        String sql = "Insert into editions (name, price, topic_id) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, edition.getName());
        pstm.setFloat(2, edition.getPrice());
        pstm.setInt(3, edition.getTopic_id());

        pstm.executeUpdate();
    }

    public static void deleteEdition(Connection conn, int id) throws SQLException {
        String sql = "Delete From editions where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

}
