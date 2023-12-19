/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sorve
 */
public class ViewRegProdutosController implements Initializable {

    @FXML
    private ComboBox<Fornecedor> cbFornecedor;
    @FXML
    private TextField txtIdProduto;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPreco;
    @FXML
    private CheckBox checkReceitaMedica;
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextArea txtDescricao;
    
    private Produto produto;
    private Fornecedor fornecedor;
    private ProdutoDAO proDAO = new ProdutoDAO();
    private FornecedorDAO forDAO = new FornecedorDAO();
    //auxiliar para a comboBox
    private ObservableList<Fornecedor> fornecedores =  
            FXCollections.observableArrayList();
    @FXML
    private Button btnExibir;
    @FXML
    private Button btnAtualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencheCombo();
        //colocar a coleção na comboBox
        cbFornecedor.setItems(fornecedores);
    }    

    @FXML
    private void btnIncluir_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        produto = moveDadosTelaModel();

        //vamos inserir
        try {
            if(proDAO.insere(produto)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txtNome.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
        
    }
    
    private boolean validarCampos() {
        if(txtNome.getText().length() == 0 ||
           txtPreco.getText().length() == 0 ||          
           txtDescricao.getText().length() == 0 ||          
           cbFornecedor.getSelectionModel().getSelectedIndex() == -1) {
            return false;
        } else {
            return true;
        }
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    // tela para o model
    private Produto moveDadosTelaModel() {
        
        produto = new Produto(new Fornecedor());
        produto.setNome(txtNome.getText());
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        produto.setReceita(checkReceitaMedica.isSelected());
        produto.setDescricao( txtDescricao.getText());
        produto.setFornecedor(cbFornecedor.getValue());
        
        return produto;
    }
    
    private void moveDadosModelTela(Produto p) {
        
        txtNome.setText(p.getNome());
        txtPreco.setText(Double.toString(p.getPreco()));
        checkReceitaMedica.setSelected(p.isReceita());
        cbFornecedor.setValue(p.getFornecedor());
        txtDescricao.setText(p.getDescricao());
        
    }
    
    private void limparCampos() {
        txtIdProduto.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtDescricao.setText("");
        cbFornecedor.getSelectionModel().clearSelection();
        checkReceitaMedica.setSelected(false);
    }
    
    private void preencheCombo() {
        try {
            fornecedores.addAll(forDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
//        cbProprietario.getSelectionModel().
    }

    @FXML
    private void btnExibir_Click(ActionEvent event) {
        produto = new Produto(new Fornecedor());
        //quem será pesquisado
        produto.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
        try {
            //busca o veiculo
            produto = proDAO.buscaID(produto);
            //se não achou
            if(produto == null) {
                mensagem("Produto Não Existe!!!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(produto);
//                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Produto?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        
        //recebe todos os dados da tela
        produto = moveDadosTelaModel();
        produto.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
        System.out.println(produto.getFornecedor().getNome());
        //vamos Excluir
        try {
            if(proDAO.remove(produto)) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                        
                txtNome.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Exclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnAtualizar_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        //recebe todos os dados da tela
        produto = moveDadosTelaModel();
        produto.setID_Produto(Integer.parseInt(txtIdProduto.getText()));
        
        //vamos alterar
        try {
            if(proDAO.altera(produto)) {
                mensagem("Dados Alterados com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                
                txtNome.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
}
