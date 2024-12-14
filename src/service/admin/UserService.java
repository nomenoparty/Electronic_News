package service.admin;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
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
}
