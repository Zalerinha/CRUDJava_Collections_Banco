/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sorve
 */
public class ViewPesquisaController implements Initializable {

    @FXML
    private TableView<Produto> tblProduto;
    @FXML
    private TableColumn<Produto, Integer> colIDProduto;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    @FXML
    private TableColumn<Produto, Boolean> colReceita;
    @FXML
    private TableColumn<Produto, String> colDescricao;
    @FXML
    private TableColumn<Fornecedor, String> colFornecedorId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colIDProduto.setCellValueFactory(
                new PropertyValueFactory<>("ID_produto"));
        colNome.setCellValueFactory(
                new PropertyValueFactory<>("Nome_produto"));
        colPreco.setCellValueFactory(
                new PropertyValueFactory<>("Preco_produto"));
        colReceita.setCellValueFactory(
                new PropertyValueFactory<>("Receita_produto"));
        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("Descricao_produto"));
        colFornecedorId.setCellValueFactory(
                new PropertyValueFactory<>("ID_fornecedor"));
        
        //coloca o checkbox na coluna
        colReceita.setCellFactory(
                CheckBoxTableCell.forTableColumn(colReceita));

        //preenche a tabela
        tblProduto.setItems(preencheTabela());
        // TODO
    }    
    
    private ObservableList<Produto> preencheTabela() {
        ProdutoDAO dao = new ProdutoDAO();
        ObservableList<Produto> produtos
            = FXCollections.observableArrayList();
        
        try {
            //busca somente que termina com 'a'
            //proprietarios.addAll(dao.lista("nome like '%a'"));
            //busca todo mundo
            produtos.addAll(dao.lista(""));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        
        return produtos;
    }
    
}
