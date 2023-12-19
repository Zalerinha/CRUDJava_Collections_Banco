/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Fornecedor;
import br.com.fatec.persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author sorve
 */
public class FornecedorDAO implements DAO <Fornecedor> {
    
    private Fornecedor fornecedor;
    
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Fornecedor model) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
        
        String sql = "INSERT INTO fornecedor (Nome_fornecedor, "
                   + "Endereco, Telefone, CNPJ) values ( ?, ?, ?, ?)";
        
        //variavel que vai receber os dados e transportalos para o banco de dados
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //inserindo os dados do view 
        //pst.setInt(1, model.getCodFornecedor());
        pst.setString(1, model.getNome());
        pst.setString(2, model.getEndereco());
        pst.setString(3, model.getTelefone());
        pst.setString(4, model.getCNPJ());
        
        if(pst.executeUpdate() > 0)
            inseriu = true; //tudo certo com a inserção
        else
            inseriu = false; //ocorreu um erro na inserção
        
        Banco.desconectar();
        
        return inseriu;
        
    }

    @Override
    public boolean remove(Fornecedor model) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM fornecedor WHERE ID_fornecedor = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, model.getCodFornecedor()); //1º interrogação
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            removeu = true; //tudo certo com a inserção
        else
            removeu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Fornecedor model) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE fornecedor SET Nome_fornecedor = ?, Endereco = ?, "
                   + "Telefone = ?, CNPJ = ? WHERE ID_fornecedor = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getNome()); //
        pst.setString(2, model.getEndereco()); 
        pst.setString(3, model.getTelefone()); 
        pst.setString(4, model.getCNPJ());
        pst.setInt(5, model.getCodFornecedor());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            alterou = true; //tudo certo com a inserção
        else
            alterou = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return alterou;
    }

    @Override
    public Fornecedor buscaID(Fornecedor model) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();
        
        fornecedor = null;
        //Comando SELECT
        String sql = "SELECT * FROM fornecedor WHERE ID_fornecedor = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
       // pst.setString(1, model.getCNPJ());
       pst.setInt(1, model.getCodFornecedor());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto Fornecedor
            fornecedor = new Fornecedor();
            
            fornecedor.setCodFornecedor(rs.getInt("ID_fornecedor"));
            fornecedor.setNome(rs.getString("Nome_fornecedor"));
            fornecedor.setCNPJ(rs.getString("CNPJ"));
            fornecedor.setTelefone(rs.getString("Telefone"));
            fornecedor.setEndereco(rs.getString("Endereco"));
            
        }
        
        Banco.desconectar();
        
        return fornecedor;
    }

    @Override
    public Collection<Fornecedor> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Fornecedor> listagem = new ArrayList<>();
        
        fornecedor = null;
        //Comando SELECT
        String sql = "SELECT * FROM fornecedor ";
        //colocar filtro ou nao
        if(criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            fornecedor = new Fornecedor();
            //move os dados do resultSet para o objeto fornecedor
            fornecedor.setCodFornecedor(rs.getInt("ID_fornecedor"));
            fornecedor.setNome(rs.getString("Nome_fornecedor"));
            fornecedor.setEndereco(rs.getString("Endereco"));
            fornecedor.setTelefone(rs.getString("Telefone"));
            fornecedor.setCNPJ(rs.getString("CNPJ"));
            
            
            
            //adicionar na coleção
            listagem.add(fornecedor);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}
