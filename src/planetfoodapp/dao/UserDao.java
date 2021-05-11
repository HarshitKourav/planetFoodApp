
package planetfoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Employee;
import planetfoodapp.pojo.User;

public class UserDao {
    public static String validateUser(User user)throws SQLException //since only method is there in the class so it is declared as ststic
    {
       Connection conn=DBConnection.getConnection();
       String qry="select username from users where userid=? and password=? and usertype=?";
       PreparedStatement ps=conn.prepareStatement(qry);
       ps.setString(1,user.getUserId());
       ps.setString(2,user.getPassword());
       ps.setString(3,user.getUserType());
       ResultSet rs=ps.executeQuery();
       
       String username=null;
       if(rs.next())
               {
                
                username=rs.getString("username");
               }
       return username;
   }
    
    public static boolean registerCashier(User user,Employee emp)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      String qry="insert into users values(?,?,?,?,?)";
      PreparedStatement ps=conn.prepareStatement(qry); 
      ps.setString(1,user.getUserId());
      ps.setString(2,emp.getEmpName());
      ps.setString(3,emp.getEmpId());
      ps.setString(4,user.getPassword());
      ps.setString(5,user.getUserType());
      int result=ps.executeUpdate();
      return result>0;
    }
    public static ResultSet getUserDetail(String userId)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      String qry="select * from users where userid=? and usertype=?";
      PreparedStatement ps=conn.prepareStatement(qry); 
      ps.setString(1,userId);
      ps.setString(2,"Cashier");
      ResultSet rs=ps.executeQuery();
      return rs;
    }
    public static boolean removeCashier(String userId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     String qry="delete from users where userid=?";
     PreparedStatement ps=conn.prepareStatement(qry);
     ps.setString(1,userId);
     int result=ps.executeUpdate();
     return result>0;
    }
}
