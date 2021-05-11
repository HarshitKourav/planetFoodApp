
package planetfoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Product;

public class ProductDao {
    public static String getNewId()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select count(*) from products");
       int id=101;
       if(rs.next())
       {
       id=id+rs.getInt(1);
       }
       return "P"+id;
    }
    public static boolean addProduct(Product p)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,?)");
      ps.setString(1,p.getProdId());
      ps.setString(2,p.getCatId());
      ps.setString(3,p.getProdName());
      ps.setDouble(4,p.getProdPrice());
      ps.setString(5,p.getIsActive());
      int x=ps.executeUpdate();
      return(x>0);
    }  
    public static HashMap<String,Product> getProductsByCategory(String catId)throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("select * from products where cat_id=?");
      HashMap<String,Product> productList=new HashMap<>();
      ps.setString(1,catId);
      ResultSet rs=ps.executeQuery();
      while(rs.next())
      {
       Product product=new Product();
       product.setCatId(catId);
       product.setIsActive(rs.getString("active"));
       product.setProdId(rs.getString("prod_id"));
       product.setProdPrice(rs.getDouble("prod_price"));
       product.setProdName(rs.getString("prod_name"));
       productList.put(product.getProdId(),product);
       
      }
      return productList;
    }
    
    public static ArrayList<Product> getAllData() throws SQLException
    {
      Connection conn=DBConnection.getConnection();
      Statement st=conn.createStatement();
      ResultSet rs=st.executeQuery("Select * from products");  
      ArrayList<Product> productList=new ArrayList<>();
      while(rs.next())
      {
        Product p=new Product();
        p.setCatId(rs.getString("cat_id"));
        p.setProdId(rs.getString("prod_id"));
        p.setProdName(rs.getString("prod_name"));
        p.setProdPrice(rs.getDouble("prod_price"));
        p.setIsActive(rs.getString("active"));
        productList.add(p);
      }
      return productList;
    }
    
    public static boolean updateProduct(Product p)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("update products set cat_id=?,prod_name=?,prod_price=?,active=? where prod_id=?");
       
     ps.setString(1, p.getCatId());
     ps.setString(2, p.getProdName());
     ps.setDouble(3, p.getProdPrice());
     ps.setString(4, p.getIsActive());
     ps.setString(5, p.getProdId());
     int x=ps.executeUpdate();
     return (x>0);
    }
    
    public static boolean removeProduct(String prodId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("update products set active='N' where prod_id=?" ); 
     ps.setString(1, prodId);
     int x=ps.executeUpdate();
     return (x>0);
    }
    public static HashMap<String,String> getActiveProductByCategory(String catId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select prod_name,prod_id from products where cat_id=? and active='Y' " );   
     ps.setString(1, catId);
     ResultSet rs=ps.executeQuery();
     HashMap<String,String> productList=new HashMap<>();
     while(rs.next())
     {
         String prodName=rs.getString("prod_name");
         String prodId=rs.getString("prod_id");
         productList.put(prodName,prodId);
     }
     return productList;
    }
}
