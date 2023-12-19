/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
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
public class ProdutoDAO implements DAO <Produto> {
    
    //variaveis auxiliares
    private Produto produto;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Produto model) throws SQLException {
       boolean inseriu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem gravados
        String sql = "INSERT INTO produtos (Nome_produto, Preco_produto, "
                   + "Receita_produto, Descricao_produto, ID_fornecedor ) values (?, ?, ?, ?, ?)";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getNome()); //1º interrogação
        pst.setDouble(2, model.getPreco()); //2º interrogacao
        pst.setBoolean(3, model.isReceita()); //3º interrogacao
        pst.setString(4, model.getDescricao());
        pst.setInt(5, model.getFornecedor().getCodFornecedor());
        
        //executar o comando
        if(pst.executeUpdate() > 0)
            inseriu = true; //tudo certo com a inserção
        else
            inseriu = false; //ocorreu um erro na inserção
        
        //fecha a conexao
        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Produto model) throws SQLException {
        boolean removeu;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem removidos
        String sql = "DELETE FROM produtos WHERE ID_produto = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setInt(1, model.getID_Produto()); //1º interrogação
        
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
    public boolean altera(Produto model) throws SQLException {
        boolean alterou;
        
        //conectar com o banco
        Banco.conectar();
        
        //cria o comando SQL
        //as ? representam os dados para serem alterados
        String sql = "UPDATE produtos SET Nome_produto = ?, Preco_produto = ?, "
                   + "Receita_produto = ?, Descricao_produto = ?, ID_fornecedor = ? WHERE ID_produto = ?";
        
        //cria o preparedStatement
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //colocar os dados no PST
        pst.setString(1, model.getNome()); //
        pst.setDouble(2, model.getPreco()); 
        pst.setBoolean(3, model.isReceita()); 
        pst.setString(4, model.getDescricao());
        pst.setInt(5, model.getFornecedor().getCodFornecedor());
        pst.setInt(6, model.getID_Produto());
        
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
    public Produto buscaID(Produto model) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();
        
        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM produtos WHERE ID_produto = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, model.getID_Produto());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto produto
            produto = new Produto(new Fornecedor());
            //move os dados do resultSet para o objeto proprietario
            produto.setID_Produto(rs.getInt("ID_produto"));
            produto.setNome(rs.getString("Nome_produto"));
            produto.setPreco(rs.getDouble("Preco_produto"));
            produto.setReceita(rs.getBoolean("Receita_produto"));
            produto.setDescricao(rs.getString("Descricao_produto"));
            //precisa buscar os dados do fornecedor em fornecedorDAO
            Fornecedor fornecedor = new Fornecedor();
            //informa quem deve ser pego de propri.
            fornecedor.setCodFornecedor(rs.getInt("ID_fornecedor"));
            //faz a busca
            fornecedor = dao.buscaID(fornecedor);
            System.out.println(fornecedor.getCodFornecedor());
            produto.setFornecedor(fornecedor);
        }
        
        Banco.desconectar();
        
        return produto;
    }

    @Override
    public Collection<Produto> lista(String criterio) throws SQLException {
        Collection<Produto> listagem = new ArrayList<>();
        
        FornecedorDAO dao = new FornecedorDAO();
        
        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM produtos ";
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
            //cria o objeto produto
            produto = new Produto(new Fornecedor());
            //move os dados do resultSet para o objeto produto
            produto.setID_Produto(rs.getInt("ID_produto"));
            produto.setNome(rs.getString("Nome_produto"));
            produto.setPreco(rs.getDouble("Preco_produto"));
            produto.setReceita(rs.getBoolean("Receita_produto"));
            produto.setDescricao(rs.getString("Descricao_produto"));
            
            Fornecedor fornecedor = new Fornecedor();
            //informa quem deve ser pego de propri.
            fornecedor.setCodFornecedor(rs.getInt("ID_fornecedor"));
            //faz a busca
            fornecedor = dao.buscaID(fornecedor);
            produto.setFornecedor(fornecedor);
            
            //adicionar na coleção
            listagem.add(produto);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}
