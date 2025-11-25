/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.taurus.dao;

import br.com.taurus.jdbc.ConnectionFactory;
import br.com.taurus.model.Bandeira;
import br.com.taurus.interfac.Icrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author victoraugustonunes
 */
public class BandeiraDAO implements Icrud<Bandeira> {
    
    private Connection con;
    
    //Construtor
    public BandeiraDAO()
    {
        this.con = new ConnectionFactory().getConnection();
    }
    
    
    //Listar
    public List<Bandeira> listarBandeira()
    {
        
        try {
            
            //1 Criar a lista
            List<Bandeira> lista = new ArrayList<>();
            
            //2 Criar o comando SQL, organizar e executar
            String sql = "SELECT * FROM bandeira";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Bandeira bandeiraObj = new Bandeira();
                
                bandeiraObj.setId_bandeira(rs.getInt("id_bandeira"));
                bandeiraObj.setNome(rs.getString("nome"));
                
                lista.add(bandeiraObj);
            }
            
            return lista;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao carregar lista. " + e);
            return null;
            
        }
        
    }
    
    
    @Override
    public void salvar(Bandeira obj) {
        
        try {
            
            //1 - Criar comando SQL
            String sql = "INSERT INTO bandeira (nome) VALUES (?)";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            
            //Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro efetuado.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar " + e);
            
        }
        
    }

    @Override
    public void editar(Bandeira obj) {
        
        try {
            
            //1 - Criar comando SQL
            String sql = "UPDATE bandeira SET nome=? WHERE id_bandeira=?";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getId_bandeira());
            
            //Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar alterar " + e);
            
        }
        
    }

    @Override
    public void excluir(Bandeira obj) {
        
        try {
            
            //1 - Criar comando SQL
            String sql = "DELETE FROM bandeira WHERE id_bandeira = ?";
            
            //2 - Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId_bandeira());
            
            //Executar o comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir " + e);
            
        }
    }
    
    public List<Bandeira> pesquisar(String nome)
    {
        
        try {
            
            //1 - Criar a lista
            List<Bandeira> lista = new ArrayList<>();
            
            //2 - Criar SQL
            String sql = "SELECT * FROM bandeira WHERE nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Bandeira obj = new Bandeira();
                
                obj.setId_bandeira(rs.getInt("id_bandeira"));
                obj.setNome(rs.getString("nome"));
                
                lista.add(obj);
            }
            
            return lista;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar. " + e);
            return null;
            
        }
        
    }
    
}
