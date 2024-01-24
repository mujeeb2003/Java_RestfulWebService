/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Mrehm
 */
@Path("/testclass")
public class TestClass {
   
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String getdata(){
        return "Get Data tested";
    }
    
    
    
//    paramter example
    @GET
    @Path("/pathparam/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String pathparam(@PathParam("id") int id){
        return "Id:"+id;
    }
    
     @GET
    @Path("/queryparam")
    @Produces(MediaType.APPLICATION_JSON)
    public String queryparam(@QueryParam("id") int id,@QueryParam("name") String name,@QueryParam("email") String email){
        return "Id:"+id+"\nName:"+name+"\nEmail:"+email;
    }
}
