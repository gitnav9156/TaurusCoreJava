/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.taurus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author victoraugustonunes
 */
public class ConnectionFactory {
    
    public Connection getConnection()
    {
        
        try {
            
            //return DriverManager.getConnection("jdbc:mysql://31.220.20.51/tauruscoredb", "usuporte4", "bj7OQEQ3B6582Xewoj1R2qK");
            return DriverManager.getConnection("jdbc:mysql://31.220.20.51/tauruscoredb?autoReconnect=true&useSSL=false", "usuporte4", "bj7OQEQ3B6582Xewoj1R2qK");
            
        } catch (Exception e) {
            
            throw new RuntimeException(e);
            
        }
        
    }
    
}
