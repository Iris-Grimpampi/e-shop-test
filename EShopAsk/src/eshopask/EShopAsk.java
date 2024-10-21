/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eshopask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;
import java.util.logging.*;

/**
 *
 * @author User
 */
public class EShopAsk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hi!!");     
        //Iris here
        String url = "jdbc:mysql://localhost:3306/eshop";
        String username = "eshop-app";
        String password = "eshop-app";
        
        Logger logger = Logger.getLogger(EShopAsk.class.getName());  
        try { 
            logger.addHandler(new FileHandler(System.getProperty("user.dir") + "\\log.txt"));
        } catch (IOException ex) {
            Logger.getLogger(EShopAsk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EShopAsk.class.getName()).log(Level.SEVERE, null, ex);
        }
  

        System.out.println("Connecting database ...");
        logger.info("Connecting database ...");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use com.mysql.jdbc.Driver if you're not on MySQL 8+ yet.
            System.out.println("Driver loaded!");
            logger.info("Driver loaded!");
        } catch (ClassNotFoundException e) {
            logger.severe("Cannot find the driver in the classpath!\n" + e);
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            logger.info("Database connected!");
            var stat1 = connection.createStatement();
            stat1.execute("show databases;");
            var res = stat1.getResultSet();
            System.out.println("DATABASES FOR ESHOP-APP");
            for(res.next(); !res.isAfterLast(); res.next()){
                System.out.println(stat1.getResultSet().getNString(1));
                logger.info(stat1.getResultSet().getNString(1));
            }
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        // TODO code application logic here
    }
    
}
