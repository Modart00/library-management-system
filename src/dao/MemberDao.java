package dao;

import model.*;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public void saveMember(Member member){

        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO members(name) VALUES(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, member.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> GetAllMembers() {

        List<Member> memberList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM members";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Member member = new Member(rs.getInt("id"),
                        rs.getString("name"));
                memberList.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public void deleteById(int id){
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "DELETE FROM members WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Member member){
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "UPDATE members SET name = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, member.getName());
            ps.setInt(2,member.getMemberId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Member findById(int id){

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM members WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Member member = new Member(rs.getInt("id"),
                        rs.getString("name"));
                return member;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
}
