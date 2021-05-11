
package planetfoodapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnection {
       private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-IFQCPLS:1521/XE","planetfood","student");
            System.out.println("Connection opened!");
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(null,"Connection not done!","Error",JOptionPane.ERROR_MESSAGE); 
           e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
        conn.close();
        System.out.println("connection closed");
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(null," DBerror :Connection not established!","Error",JOptionPane.ERROR_MESSAGE); 
           e.printStackTrace();
        }
        
    }
    
}
