package service.admin;

import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import helper.GenerateToken;
import model.ArticleModel;
import model.RoleModel;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public ArrayList<UserModel> search(String keyword, String permission, int userID){
        ArrayList<UserModel> userList = new ArrayList<>();
        if(permission.equals("quan_ly_he_thong")){
            userList = userDAO.selectAllExceptUserID(userID);
        }else if(permission.equals("quan_ly_nguoi_dung")){
            userList = userDAO.selectAllWithRoleIDExceptUserID(1, userID);
        }

        ArrayList<UserModel> searchAccountList = new ArrayList<>();

        Pattern pattern = Pattern.compile(keyword.trim(), Pattern.CASE_INSENSITIVE);

        for(UserModel user: userList){
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(removeAccent(user.getUsername()).toLowerCase().trim());
            tmp.add(removeAccent(user.getFullName()).toLowerCase().trim());
            tmp.add(removeAccent(user.getPermission()).toLowerCase().trim());
            tmp.add(removeAccent(user.getStatus().equals("active") ? "hoat dong" : "dung hoat dong").toLowerCase().trim());

            for(String s: tmp){
                Matcher matcher = pattern.matcher(s);
                if(matcher.find()){
                    searchAccountList.add(user);
                    break;
                }
            }
        }

        return searchAccountList;
    }
    public String removeAccent(String text) {
        return text
                .replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a")
                .replaceAll("[èéẹẻẽêềếệểễ]", "e")
                .replaceAll("[ìíịỉĩ]", "i")
                .replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o")
                .replaceAll("[ùúụủũưừứựửữ]", "u")
                .replaceAll("[ỳýỵỷỹ]", "y")
                .replaceAll("[đ]", "d");
    }
}
