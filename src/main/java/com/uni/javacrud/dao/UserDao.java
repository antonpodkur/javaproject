package com.uni.javacrud.dao;

import com.uni.javacrud.beans.Edition;
import com.uni.javacrud.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public List<User> queryUsers () throws SQLException {
        String sql = "select * from users;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = "";
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            users.add(user);
        }
        return users;
    }

    public List<User> queryNonAdminUsers () throws SQLException {
        String sql = "select * from users where role <> 'ADMIN';";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = "";
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            users.add(user);
        }
        return users;
    }


    public int insertUser(User user) throws SQLException {
        String sql = "insert into users (username, email, password, role, status) values (?,?,?,?,?);";
        int result = 0;

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getEmail());
        pstm.setString(3, user.getPassword());
        pstm.setString(4, user.getRole());
        pstm.setString(5, user.getStatus());
        result = pstm.executeUpdate();
        return result;
    }

    public User findUserByEmail(String email, String password) throws SQLException {

        String sql = "Select * from users u  where u.email = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            return user;
        }
        return null;
    }

    public User findUserByUsername(String username, String password) throws SQLException {

        String sql = "Select * from users u where u.username = ? and u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            return user;
        }
        return null;
    }

    public User findUserByUsername(String username) throws SQLException {

        String sql = "Select * from users u where u.username = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            return user;
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "update users set username = ?, role = ?, status =? where id = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getRole());
        pstm.setString(3, user.getStatus());
        pstm.setInt(4, user.getId());
        pstm.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "delete from users where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

    public void addMoney(int id, float userMoney, float addedMoney) throws SQLException {
        float money = userMoney + addedMoney;

        String sql = "update users set money = ? where id = ?;";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setFloat(1, money);
        pstm.setInt(2, id);
        pstm.executeUpdate();
        conn.commit();
    }

    public User findUserById(int id) throws SQLException {
        String sql = "Select * from users u where u.id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            String status = rs.getString("status");
            float money = rs.getFloat("money");
            User user = new User(id, username, email, password, role, status, money);
            return user;
        }
        return null;
    }

    public void blockUserById(int id) throws SQLException {
        String sql = "update users set status = 'blocked' where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    public void unblockUserById(int id) throws SQLException {
        String sql = "update users set status = 'unblocked' where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }
}
