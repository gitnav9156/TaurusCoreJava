/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.taurus.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author victoraugustonunes
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        
        try {
            
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conexão estabelecida.");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Sem conexão. " + e);
            
        }
        
    }
    
}
