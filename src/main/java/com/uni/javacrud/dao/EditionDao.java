package com.uni.javacrud.dao;

import com.uni.javacrud.beans.Edition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditionDao {
    private Connection conn;

    public EditionDao(Connection conn) {
        this.conn = conn;
    }

    public List<Edition> queryEditions() throws SQLException {
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

    public Edition findEditionById(int id) throws SQLException {
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

    public Edition findEditionByName(String name) throws SQLException {
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

    public void updateEdition(Edition edition) throws SQLException {
        String sql = "Update editions set name =?, price=?, topic_id=? where id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, edition.getName());
        pstm.setFloat(2, edition.getPrice());
        pstm.setInt(3, edition.getTopic_id());
        pstm.setInt(4, edition.getId());
        pstm.executeUpdate();
    }

    public void insertEdition(Edition edition) throws SQLException {
        String sql = "Insert into editions (name, price, topic_id) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, edition.getName());
        pstm.setFloat(2, edition.getPrice());
        pstm.setInt(3, edition.getTopic_id());

        pstm.executeUpdate();
    }

    public void deleteEdition(int id) throws SQLException {
        String sql = "Delete From editions where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

}
