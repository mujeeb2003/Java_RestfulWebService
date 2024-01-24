/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Mrehm
 */
//Controller Class
@Path("/TravelController")
public class TravelController {
    
    public Connection getcon() throws ClassNotFoundException, SQLException {
        Connection con;
//        step 1: Set class forname
        Class.forName("com.mysql.cj.jdbc.Driver");
//        step 2: get connection from driver manager
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/travel_db_api", "root", "4sight");
        return con;
    }
    
    @GET
    @Path("/getcustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Customer> getcusomters() throws ClassNotFoundException, SQLException {
        ArrayList<Customer> CML;
        CML = new ArrayList<>();
        
        String query = "Select * from customer";

//        step 1: initialise connection con 
        Connection con = getcon();
//       step 4: create statement using driver manager
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();

//       step 5: populate array list with the data
        while (rs.next()) {
            Customer cm = new Customer();
            cm.setId(rs.getInt("Id"));
            cm.setName(rs.getString("Name"));
            cm.setEmail(rs.getString("Email"));
            cm.setPassword(rs.getString("Password"));
            CML.add(cm);
        }
        
        return CML;
    }

//    Post example for inserting customers
    @POST
    @Path("/insertcustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertCustomer(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("password") String password) throws ClassNotFoundException, SQLException {
        
        
        String query = "Insert into customer (Id,Name,Email,Password) values(?,?,?,?)";
        
        Connection con = getcon();
        
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, email);
        pst.setString(4, password);
        
        int rs = pst.executeUpdate();
        if (rs == 1) {
            return "success";
        }        
        return "false";
    }
    
    @POST
    @Path("/updatecustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCustomers(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("email") String email,@QueryParam("password") String password) throws ClassNotFoundException, SQLException {
        String query = "update customer set name=?,email=?,password=? where id=?";
        
        Connection con = getcon();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, email);
        pst.setString(3,password);
        pst.setInt(3, id);
        
        int rs = pst.executeUpdate();
        if (rs == 1) {
            return "Updated Successfully";
        }
        return "Update Failed";
    }
    
    @DELETE
    @Path("/deletecustomers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletecustomers(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        String query = "delete from customer where id=?";
        
        Connection con = getcon();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        
        int rs = pst.executeUpdate();
        if(rs==1){
        return "Sucessfully deleted";
        }
        return "Failed to delete";
    }
    
}
