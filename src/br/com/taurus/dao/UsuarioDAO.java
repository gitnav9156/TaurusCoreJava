/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.taurus.dao;

import br.com.taurus.jdbc.ConnectionFactory;
import br.com.taurus.model.Usuario;
import br.com.taurus.view.Frmmenu;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author victoraugustonunes
 */
public class UsuarioDAO {
    
    private Connection con;
    
    //construtor
    public UsuarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //salvar
    public void salvar(Usuario obj)
    {
        String senha = obj.getSenha();
        
        try {
            
            //Criptografia MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder();
            
            for(byte b: messageDigest)
            {
                sb.append(String.format("%02X", b & 0xFF));
            }
            
            String senhaHex = sb.toString();
            
            //1 - Criar comando SQL
            String sql = "INSERT INTO usuario(nome,email,senha,dt_cadastro,ativo) VALUES (?,?,?,now(),?)";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            //stmt.setString(3, obj.getSenha());
            stmt.setString(3, senhaHex);
            //stmt.setDate(4, (Date) obj.getDt_cadastro());
            //stmt.setDate(4, (Date) obj.getDt_cadastro());
            stmt.setBoolean(4, obj.getAtivo());
            
            //3 - Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro efetuado.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar " + e);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Editar
    public void editarUsuario(Usuario obj)
    {
        
        try {
            
            //1 - Criar comando SQL
            String sql = "UPDATE usuario SET nome=?,email=?,ativo=? WHERE id_usuario=?";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setBoolean(3, obj.getAtivo());
            stmt.setInt(4, obj.getId_usuario());
            
            //Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro efetuado.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar " + e);
            
        }
        
    }
    
    //Excluir
    public void excluirUsuario(Usuario obj)
    {
        
        try {
            
            //1 - Criar comando SQL
            String sql = "DELETE FROM usuario WHERE id_usuario=?";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId_usuario());
            
            //Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar " + e);
            
        }
        
    }
    
    public void efetuarLogin(String email, String senha)
    {
        
        try {
            
            // 1 - comando SQL
            String sql = "SELECT * FROM usuario WHERE email=? AND senha=?";
            
            // 2 - Preparar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            // 3 - executa o comando SQL
            //stmt.execute();
            //stmt.close();
            
            // 3 - ResultSet é sempre utilizado em comandos de SELECT
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next())
            {
                
                //Usuario encontrado
                JOptionPane.showMessageDialog(null, "Usuário logado.");
                
                //chamar a tela menu
                Frmmenu tela = new Frmmenu();
                tela.setVisible(true);
                
            }else{
                //Usuario não encontrado
                JOptionPane.showMessageDialog(null, "Erro ao tentar logar");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operação não efetuada: " + e);
        }
        
    }
    
}
