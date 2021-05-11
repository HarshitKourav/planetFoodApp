package planetfoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Category;

public class CategoryDao {
    public static HashMap<String,String> getAllCategory()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String>categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName,catId);
        }
        return categories;
    }
     public static String getNewId()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select count(*) from categories");
       int id=101;
       if(rs.next())
       {
       id=id+rs.getInt(1);
       }
       return "C"+id;
    }
     
    public static boolean addCategory(Category c)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
      ps.setString(1,c.getCatId());
      ps.setString(2,c.getCatName());
      int x=ps.executeUpdate();
      return(x>0);
    }
    
     public static HashMap<String,String> getCategory()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_id,cat_name from categories");
        HashMap<String,String>categories=new HashMap<>();
        while(rs.next())
        {
            String catId=rs.getString(1);
            String catName=rs.getString(2);
            categories.put(catId,catName);
        }
        return categories;
    }
     public static boolean updatCategory(Category c)throws SQLException 
     {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
      ps.setString(1,c.getCatName());
      ps.setString(2,c.getCatId());
      int x=ps.executeUpdate();
      return(x>0);   
     }
     
      public static ArrayList<Category> getAllData() throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      Statement st=conn.createStatement();
      ResultSet rs=st.executeQuery("Select * from categories");  
      ArrayList<Category> categories=new ArrayList<>();
      while(rs.next())
      {
        Category p=new Category();
        p.setCatId(rs.getString("cat_id"));
        p.setCatName(rs.getString("cat_name"));
        categories.add(p);
      }
      return categories;
    }
    
}
