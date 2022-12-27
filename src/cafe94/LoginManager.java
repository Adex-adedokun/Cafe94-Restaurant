package cafe94;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ADESILE
 */

public class LoginManager {
    public Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    /**
     * creates a default connector.
     */
    public LoginManager() {
        connection = DBManager.DBConnection();
        if (connection == null) {
            System.exit(1);
        }
    }


    /**
     * Checks if the db is connected.
     * @return false is there is no connection.
     * @throws SQLException is the connection fails.
     */
    public boolean isDbConnected() throws SQLException {
        return !connection.isClosed();
    }

    /**
     * returns the usertype.
     * e.g. chef,customer etc..
     * @param usern Username input on the login screen.
     * @param passw Password input on the login screen.
     * @return returns the user type.
     */
    public String userType(String usern, String passw) throws SQLException {
        UserDetails.getInstance().setUserID(usern);
        String result;
        String query = "Select type FROM users WHERE userName = ? and password = ? ";
       
            pst = connection.prepareStatement(query);
            pst.setString(1, usern);
            pst.setString(2, passw);
            rs = pst.executeQuery();
            if (rs.next()) {
             result = rs.getString(1); 
             
                return result;
            } else {
                return " ";
                }
      
    }


    /**
     * Checks if the user exist in the database.
     * @param usern Username input on the login screen.
     * @param passw Password input on the login screen
     * @return true if the user exists in the database.
     */
    public boolean isUserLegit(String usern, String passw) {
        String query = "Select * FROM users WHERE userName = ? and password = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, usern);
            pst.setString(2, passw);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                return true;
            } else {
                return false;
                }
        } catch (Exception e) {
            System.out.println("Problem is in here: " + e);

        }
        return false;
    }


}
