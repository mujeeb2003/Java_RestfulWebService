/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Mrehm
 */
@Path("LoginController")
public class LoginController {
    
    @GET
    @Path("/login/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@PathParam("name") String name) throws ClassNotFoundException, SQLException{
        
        String credentials[]=name.split(":");
        String username=credentials[0];
        String password=credentials[1];
        
        TravelController TC = new TravelController();
        String query="Select * from customer where name=?";
        
        Connection con=TC.getcon();
        
        PreparedStatement pst=con.prepareStatement(query);
        pst.setString(1, username);
//        pst.setString(2, password);
        ResultSet rs=pst.executeQuery();
        
        rs.next();//****Very necessary to move the cursor to the data row.
        
        if(password.equals(rs.getString("Password"))){
            return "true";
        }
        
        return "false";
    }
}
