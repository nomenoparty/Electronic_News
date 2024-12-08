package service.admin;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import model.RoleModel;

public class CheckRoleService {
    private RoleDAO roleDAO = new RoleDAO();
    public boolean checkRoleUser(int userID, String permission){
        RoleModel role = roleDAO.selectWithUserId(userID);

        return role.getPermission().equals(permission);
    }
}
