package dao.impl;

import dao.DAOInterface;
import model.UserModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements DAOInterface<UserModel> {
    @Override
    public int insert(UserModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO user(username, password, fullName, status, tokenUser, roleID) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getUsername());
            pstm.setString(2, t.getPassword());
            pstm.setString(3, t.getFullName());
            pstm.setString(4, t.getStatus());
            pstm.setString(5, t.getTokenUser());
            pstm.setInt(6, t.getRoleID());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Thêm thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int update(UserModel userModel) {
        return 0;
    }

    @Override
    public int delete(UserModel userModel) {
        return 0;
    }

    @Override
    public ArrayList<UserModel> selectAll() {
        ArrayList<UserModel> userList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user JOIN role ON user.roleID = role.roleID";

            PreparedStatement pstm = con.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                System.out.println(id);
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleID = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");
                String permission = rs.getString("title");

                UserModel userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(username);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleID);
                userModel.setTokenUser(tokenUser);
                userModel.setPermission(permission);

                userList.add(userModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public ArrayList<UserModel> selectAllExceptUserID(int userID) {
        ArrayList<UserModel> userList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user JOIN role ON user.roleID = role.roleID WHERE user.userID != ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, userID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                System.out.println(id);
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleID = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");
                String permission = rs.getString("title");

                UserModel userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(username);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleID);
                userModel.setTokenUser(tokenUser);
                userModel.setPermission(permission);

                userList.add(userModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public ArrayList<UserModel> selectAllWithRoleID(int roleID) {
        ArrayList<UserModel> userList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user JOIN role ON user.roleID = role.roleID WHERE role.roleID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, roleID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleId = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");
                String permission = rs.getString("title");

                UserModel userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(username);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleId);
                userModel.setTokenUser(tokenUser);
                userModel.setPermission(permission);

                userList.add(userModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public ArrayList<UserModel> selectAllWithRoleIDExceptUserID(int roleID, int userID) {
        ArrayList<UserModel> userList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user JOIN role ON user.roleID = role.roleID WHERE role.roleID = ? AND user.userID != ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, roleID);
            pstm.setInt(2, userID);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleId = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");
                String permission = rs.getString("title");

                UserModel userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(username);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleId);
                userModel.setTokenUser(tokenUser);
                userModel.setPermission(permission);

                userList.add(userModel);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public UserModel selectById(int id) {
        UserModel userModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user WHERE userID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleId = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");

                userModel = new UserModel();

                userModel.setUserID(userID);
                userModel.setUsername(userName);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleId);
                userModel.setTokenUser(tokenUser);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public UserModel selectByUsername(String username){
        UserModel userModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user WHERE username = ? AND status = 'active'";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, username);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleId = rs.getInt("roleID");
                String tokenUser = rs.getString("tokenUser");

                userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(userName);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleId);
                userModel.setTokenUser(tokenUser);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public UserModel getUserByTokenUser(String tokenUser) {
        UserModel userModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM user WHERE tokenUser = ? AND status = 'active'";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, tokenUser);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String status = rs.getString("status");
                int roleId = rs.getInt("roleID");
                String token = rs.getString("tokenUser");

                userModel = new UserModel();

                userModel.setUserID(id);
                userModel.setUsername(userName);
                userModel.setPassword(password);
                userModel.setFullName(fullName);
                userModel.setStatus(status);
                userModel.setRoleID(roleId);
                userModel.setTokenUser(token);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }
    public int updatePasswordWithTokenUser(UserModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE user SET password = ? WHERE tokenUser = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getPassword());
            pstm.setString(2, t.getTokenUser());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Cập nhật thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    public int updateInforUserWithTokenUser(UserModel t) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE user SET password = ?, fullName = ? WHERE tokenUser = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, t.getPassword());
            pstm.setString(2, t.getFullName());
            pstm.setString(3, t.getTokenUser());

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Cập nhật thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    public int updateStatusUserWithId(int userID, String status) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE user SET status = ? WHERE userID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, status);
            pstm.setInt(2, userID);

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Cập nhật thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    public int updateRoleUserWithId(int userID, int roleID) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE user SET roleID = ? WHERE userID = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, roleID);
            pstm.setInt(2, userID);

            row = pstm.executeUpdate();

            if (row != 0) {
                System.out.println("Cập nhật thành công: " + row);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
