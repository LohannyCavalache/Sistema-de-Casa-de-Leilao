/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    public int cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        
        int status;
        try{
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            return status;
        }catch(SQLException ex){
            System.out.println("Erro ao cadastrar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        try{
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            List<ProdutosDTO> listagem = new ArrayList<>();
            while(resultset.next()){
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(resultset.getInt("id"));
                produtos.setNome(resultset.getString("nome"));
                produtos.setValor(resultset.getInt("valor"));
                produtos.setStatus(resultset.getString("status"));
                listagem.add(produtos);
            }
            return listagem;
        }catch(Exception e){
            return null;
        }
    }
}

