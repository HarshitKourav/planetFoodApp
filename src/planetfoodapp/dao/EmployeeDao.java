
package planetfoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Employee;
import planetfoodapp.pojo.Product;

public class EmployeeDao {
    public static String getNewId()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select count(*) from employees");
       int id=101;
       if(rs.next())
       {
       id=id+rs.getInt(1);
       }
       return "e"+id;
    }
    public static boolean addEmployee(Employee e)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
      ps.setString(1,e.getEmpId());
      ps.setString(2,e.getEmpName());
      ps.setString(3,e.getJob());
      ps.setDouble(4,e.getSal());
      int x=ps.executeUpdate();
      return(x>0);
    }
    public static ResultSet SearchEmployeeDetail(String empId)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("select * from employees where empid=?");
      ps.setString(1,empId);
      ResultSet rs=ps.executeQuery();
      return rs;
    }
    public static boolean UpdateEmployee(Employee e)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("update employees set ename=?,job=?,sal=? where empid=?");
     ps.setString(1, e.getEmpName());
     ps.setString(2, e.getJob());
     ps.setDouble(3, e.getSal());
     ps.setString(4, e.getEmpId());
     int x=ps.executeUpdate();
     return (x>0);
    }
   
    public static HashMap<String,Employee> getAllEmployee()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from employees");
        HashMap<String,Employee>employee=new HashMap<>();
        while(rs.next())
        {
            Employee emp=new Employee();
            emp.setEmpId(rs.getString(1));
            emp.setEmpName(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSal(rs.getDouble(4));
            employee.put(emp.getEmpId(),emp);
        }
        return employee;
    }
     public static ArrayList<Employee> getAllData() throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      Statement st=conn.createStatement();
      ResultSet rs=st.executeQuery("Select * from employees");  
      ArrayList<Employee> employeeList=new ArrayList<>();
      while(rs.next())
      {
        Employee e=new Employee();
        e.setEmpId(rs.getString("empid"));
        e.setEmpName(rs.getString("ename"));
        e.setJob(rs.getString("job"));
        e.setSal(rs.getDouble("sal"));
        employeeList.add(e);
      }
      return employeeList;
    }
    
     public static boolean removeEmployee(Employee e)throws SQLException
     {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?" ); 
     ps.setString(1, e.getEmpId());
     int x=ps.executeUpdate();
     return (x>0);
    }
    
}
