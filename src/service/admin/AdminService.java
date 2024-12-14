package service.admin;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.RoleModel;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

public class AdminService {
    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();
    public UserModel loginAdmin(UserModel adminModel) {
        UserModel adminModel1 = userDAO.selectByUsername(adminModel.getUsername());
        if (adminModel1 == null) {
            System.out.println("Username khong ton tai");

            return adminModel1;
        }

        if (!adminModel.getPassword().equals(adminModel1.getPassword())) {
            System.out.println("Sai mat khau");

            return null;
        }

        if(adminModel1.getRoleID() == 1) return null;

        return adminModel1;
    }
    public int insertAdmin(UserModel admin, String permission){
        RoleModel role = roleDAO.selectWithPermission(permission);

        if(role.getRoleID() == 0) return 0;

        admin.setRoleID(role.getRoleID());
        admin.setTokenUser(GenerateToken.generateToken());

        return userDAO.insert(admin);
    }
    public int getAdminActiveSize(){
        return userDAO.countAdminByStatus("active");
    }
    public int getAdminInactiveSize(){
        return userDAO.countAdminByStatus("inactive");
    }
}
