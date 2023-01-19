package service;

import dao.UserDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import util.ModelException;

/**
 *
 * @author User
 */
public class UserService {
    private UserService() {}
    
    public static User login(String email, String password) throws ClassNotFoundException, SQLException, ModelException {
        ResultSet data = UserDAO.getUser(email, password);
        System.out.println(data);
        User found = null;
        if(data != null) {
            data.next();
            String f_fullName = data.getString("Full Name");
            String f_email = data.getString("Email Address");
            String f_studentId = data.getString("Student ID");
            String f_password = data.getString("Password");
            String f_role = data.getString("Role");
            
            found = new User(f_email, f_fullName, f_studentId, f_password, f_role);
        }
        data.close();
        return found;
    }
    
    public static User signup_user(String fullName, String email, String studentId, String password) throws ModelException, SQLException, ClassNotFoundException {
        User current = new User(email, fullName, studentId, password);
        return UserDAO.addUser(current) ? current : null;
    }
}
