package dao.impl;

import dao.DAOInterface;
import model.RoleModel;
import model.UserModel;
import util.JDBCUtil;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDAO implements DAOInterface<RoleModel> {
    @Override
    public int insert(RoleModel roleModel) {
        return 0;
    }

    @Override
    public int update(RoleModel roleModel) {
        return 0;
    }

    @Override
    public int delete(RoleModel roleModel) {
        return 0;
    }

    @Override
    public ArrayList<RoleModel> selectAll() {
        return null;
    }

    public ArrayList<RoleModel> selectAllAdminPermission() {
        ArrayList<RoleModel> roleList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM role WHERE permission = ? OR permission = ? OR permission = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, "quan_ly_he_thong");
            pstm.setString(2, "quan_ly_bai_viet");
            pstm.setString(3, "quan_ly_nguoi_dung");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String title = rs.getString("title");
                String permission = rs.getString("permission");

                RoleModel roleModel = new RoleModel();

                roleModel.setRoleID(roleID);
                roleModel.setTitle(title);
                roleModel.setPermission(permission);

                roleList.add(roleModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public RoleModel selectById(int id) {
        RoleModel roleModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM role WHERE roleID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String title = rs.getString("title");
                String permission = rs.getString("permission");

                roleModel = new RoleModel();

                roleModel.setRoleID(roleID);
                roleModel.setTitle(title);
                roleModel.setPermission(permission);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleModel;
    }
    public RoleModel selectWithUserId(int userID) {
        RoleModel roleModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM role\n" +
                    "JOIN user ON role.roleID = user.roleID\n" +
                    "WHERE user.userID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, userID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String title = rs.getString("title");
                String permission = rs.getString("permission");

                roleModel = new RoleModel();

                roleModel.setRoleID(roleID);
                roleModel.setTitle(title);
                roleModel.setPermission(permission);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleModel;
    }
    public RoleModel selectWithPermission(String permission) {
        RoleModel roleModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM role WHERE permission = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, permission);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String title = rs.getString("title");
                String permission1 = rs.getString("permission");

                roleModel = new RoleModel();

                roleModel.setRoleID(roleID);
                roleModel.setTitle(title);
                roleModel.setPermission(permission1);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleModel;
    }
}
