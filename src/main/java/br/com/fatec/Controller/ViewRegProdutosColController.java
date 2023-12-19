/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sorve
 */
public class ViewRegProdutosColController implements Initializable {

    @FXML
    private Label lblIdProduto;
    @FXML
    private TextField txtIdProduto;
    @FXML
    private Label lblNome;
    @FXML
    private TextField txtNome;
    @FXML
    private Label lblPreco;
    @FXML
    private TextField txtPreco;
    @FXML
    private Label lblReceita;
    @FXML
    private CheckBox checkReceitaMedica;
    @FXML
    private Label lblFornecedor;
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnExcluir;
    @FXML
    private ComboBox<Fornecedor> cbFornecedor;
    @FXML
    private TextArea txtDescricao;
    
    //variavel auxiliar representante do model
    private Fornecedor fornecedor = new Fornecedor();
    private Produto produto = new Produto(fornecedor); 
    
    //Coleção que exibirá a lista de fornecedores
    private ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();
    
    private ObservableList<Produto> produtos = FXCollections.observableArrayList();
    @FXML
    private Button btnExibir;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnAtualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedores.add(new Fornecedor(1,"Colgate"));
        fornecedores.add(new Fornecedor(2,"Minuano"));
        fornecedores.add(new Fornecedor(3,"Sazon"));
        cbFornecedor.setItems(fornecedores);
        
    }    
    
    private void moveModelParaView(){
        txtIdProduto.setText(String.valueOf(produto.getID_Produto()));
        txtNome.setText(produto.getNome());
        txtPreco.setText(String.valueOf(produto.getPreco()));
        txtDescricao.setText(produto.getDescricao());
        checkReceitaMedica.setSelected(produto.isReceita());
        cbFornecedor.setValue(produto.getFornecedor());
    }
    
    private Produto moveViewParaModel(){
        //fornecedor = new Fornecedor();
        produto = new Produto(new Fornecedor());
        
        produto.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
        produto.setNome(txtNome.getText());
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        produto.setReceita(checkReceitaMedica.isSelected());
        produto.setDescricao(txtDescricao.getText());
        produto.setFornecedor(cbFornecedor.getValue());
        
        return produto;
    }
    
    private void limpar(){
        txtDescricao.clear();
        txtIdProduto.clear();
        txtNome.clear();
        txtPreco.clear();
        checkReceitaMedica.setSelected(false);
        cbFornecedor.setValue(null);
    }

    @FXML
    private void btnIncluir_Click(ActionEvent event) {
        //verifica se os campos estão em branco
        if(txtNome.getText().trim().isEmpty() || txtDescricao.getText().trim().isEmpty() || 
           txtPreco.getText().trim().isEmpty() || txtPreco.getText().trim().isEmpty() || 
           cbFornecedor.getValue() == null ){
            mensagem("Preencha todos os campos de texto e selecione um fornecedor para o produto!");
            txtIdProduto.requestFocus();
        }else{
        // Verifica se o novo produto esta usando um ID já existente 
                Produto p = new Produto(new Fornecedor());
                p.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
                if ( produtos.contains(p) == true) {
                mensagem("ID de produto já esta em uso. Por favor, digite outro ID. ");
                txtIdProduto.requestFocus();
                limpar();
                }else{

                    produto = moveViewParaModel();
                    produtos.add(produto);
                    mensagem("Produto Inserido!!!");
                    limpar();
                    txtIdProduto.requestFocus();
                }
        }
//        for(Produto a : produtos){
//            System.out.println(a.getID_Produto() + " - " + a.getNome() + " - " + a.getPreco() + " - " + 
//                                           a.getDescricao() + " - " + a.getFornecedor().getNome() + " - " + 
//                                         a.isReceita());
//        }
    }
    
    
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); 
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        Fornecedor f = new Fornecedor ();
        Produto p = new Produto(f);
        
//          p.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
//          txtIdProduto.setText(String.valueOf(p.getID_Produto()));
        if( txtIdProduto.getText().trim().isEmpty() ){
            mensagem("Digite o código do produto a ser excluido.");
            txtIdProduto.requestFocus();
         }else{ 
            
            p.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
            if ( produtos.contains(p) == false) {
            mensagem("Produto não encontrado! ");
            txtIdProduto.requestFocus();
            limpar();
            }else{
                for(Produto aux : produtos){
                    if(aux.getID_Produto() == p.getID_Produto()){
                        
                        int result = JOptionPane.showConfirmDialog(null, 
                                "Realmente deseja deletar o item " + aux.getNome() +"?", "Confirme",
                                JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                        produtos.remove(aux);
                        limpar();
                        mensagem("Produto excluido com sucesso!");
//                        produto = aux;
//                        moveModelParaView();
                        break;
                        }else{
                            mensagem("Produto mantido");
                            
                        }
                    }        
                }
            }
         }
    }


    @FXML
    private void btnExibir_Click(ActionEvent event) {
        Fornecedor f = new Fornecedor ();
        Produto p = new Produto(f);
        
//          p.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
//          txtIdProduto.setText(String.valueOf(p.getID_Produto()));
        if( txtIdProduto.getText().trim().isEmpty() ){
            mensagem("Digite o código do produto a ser exibido.");
            txtIdProduto.requestFocus();
         }else{ 
            
            p.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
            if ( produtos.contains(p) == false) {
            mensagem("Produto não encontrado! ");
            txtIdProduto.requestFocus();
            limpar();
            }else{
                for(Produto aux : produtos){
                    if(aux.getID_Produto() == p.getID_Produto()){
                        produto = aux;
                        moveModelParaView();
                        btnAtualizar.setDisable(false);
                        btnLimpar.setDisable(false);
                        btnExibir.setDisable(true);
                        btnExcluir.setDisable(true);
                        btnIncluir.setDisable(true);
                        txtIdProduto.setEditable(false);
                        break;
                    }        
                }
            }
         }
        
    }

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
        limpar();
        btnAtualizar.setDisable(true);
        btnLimpar.setDisable(true);
        btnExibir.setDisable(false);
        btnExcluir.setDisable(false);
        btnIncluir.setDisable(false);
        txtIdProduto.setEditable(true);
        txtIdProduto.requestFocus();
    }

    @FXML
    private void btnAtualizar_Click(ActionEvent event) {
        if(txtNome.getText().trim().isEmpty() || txtDescricao.getText().trim().isEmpty() || 
           txtPreco.getText().trim().isEmpty() || txtPreco.getText().trim().isEmpty()  ){
            mensagem("Preencha todos os campos de texto!");
        }else{
            moveViewParaModel();
            produtos.remove(produto);
            produtos.add(produto);
            mensagem("Informações do produto " + produto.getNome() + " foram atualizadas!");
        }
    }
}
    
    
    

