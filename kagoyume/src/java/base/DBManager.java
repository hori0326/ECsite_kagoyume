package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hori
 */

public class DBManager {
    
    public static Connection getConnection(){
        
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db","hori","geekjob@hori");
            
            System.out.println("DBConnected!!");
            return conn;
            
        } catch(ClassNotFoundException e) {
            throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            throw new IllegalMonitorStateException();
        }
    }
}
