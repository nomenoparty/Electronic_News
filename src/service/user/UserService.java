package service.user;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.RoleModel;
import model.UserModel;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();
    public void updateUser(UserModel userModel){
        RoleModel role = roleDAO.selectWithPermission(userModel.getPermission());

        userModel.setRoleID(role.getRoleID());

        userDAO.update(userModel);
    }
    public void deleteUser(int userID){
        UserModel userModel = new UserModel();

        userModel.setUserID(userID);

        userDAO.delete(userModel);
    }
    public void insertUser(UserModel userModel) {
        RoleModel role = roleDAO.selectWithPermission(userModel.getPermission());
        userModel.setRoleID(role.getRoleID());

        String token = GenerateToken.generateToken();
        userModel.setTokenUser(token);

        userModel.setStatus("active");

        userDAO.insert(userModel);
    }

    public UserModel login(String username, String password) {
        UserModel userModel = userDAO.selectByUsername(username);

        if (userModel == null) {
            System.out.println("Username khong ton tai");

            return userModel;
        }

        if (!userModel.getPassword().equals(password)) {
            System.out.println("Sai mat khau");

            return null;
        }

        if(userModel.getRoleID() != 1) return null;

        return userModel;
    }

    public boolean register(UserModel userModel) {
        if (userDAO.selectByUsername(userModel.getUsername()) != null) {
            return false;
        }

        insertUser(userModel);
        return true;
    }

    public int getClientActiveSize(){
        return userDAO.countClientByStatus("active");
    }
    public int getClientInactiveSize(){
        return userDAO.countClientByStatus("inactive");
    }
}
