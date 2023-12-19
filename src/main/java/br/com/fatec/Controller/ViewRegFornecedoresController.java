/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.model.Fornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sorve
 */
public class ViewRegFornecedoresController implements Initializable {

    @FXML
    private TextField txtIdFornecedor;
    @FXML
    private TextField txtNomeF;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEndereco;
    @FXML
    private Button btnLimparF;
    @FXML
    private Button btnAtualizarF;
    @FXML
    private Button btnIncluirF;
    @FXML
    private Button btnExcluirF;
    @FXML
    private Button btnExibirF;
    
    //auxiliares
    private Fornecedor fornecedor;
    private FornecedorDAO forDAO = new FornecedorDAO();
    @FXML
    private Button btnPesquisar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtIdFornecedor.setDisable(false);
    }    

    @FXML
    private void btnLimparF_Click(ActionEvent event) {
    }

    @FXML
    private void btnAtualizarF_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        //recebe todos os dados da tela
        fornecedor = moveDadosTelaModel();
        
        //vamos alterar
        try {
            if(forDAO.altera(fornecedor)) {
                mensagem("Dados Alterados com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                btnIncluirF.setDisable(false);
                btnPesquisar.setDisable(false);
                btnAtualizarF.setDisable(true);
                btnExcluirF.setDisable(true);
                txtIdFornecedor.setDisable(true);
                
                txtNomeF.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnIncluirF_Click(ActionEvent event) {
        
        
        
        if(!validarCampos()){
            mensagem("Preencha todos os campos!", Alert.AlertType.NONE);
            
            return;
        }
        
        fornecedor = moveDadosTelaModel();
       
        
        //vamos inserir
        try {
            if(forDAO.insere(fornecedor)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txtIdFornecedor.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void btnExcluirF_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Fornecedor?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        
        //recebe todos os dados da tela
        fornecedor = moveDadosTelaModel();
        fornecedor.setCodFornecedor(Integer.parseInt( txtIdFornecedor.getText()));
        //vamos Excluir
        try {
            if(forDAO.remove(fornecedor)) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                btnIncluirF.setDisable(true);
                btnAtualizarF.setDisable(false);
                btnExcluirF.setDisable(false);
                txtNomeF.requestFocus();
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
    private void btnExibirF_Click(ActionEvent event) {
        fornecedor = new Fornecedor();
        //quem será pesquisado
        fornecedor.setCodFornecedor(Integer.parseInt(txtIdFornecedor.getText()));
        try {
            //busca o fornecedor
            fornecedor = forDAO.buscaID(fornecedor);
            //se não achou
            if(fornecedor == null) {
                mensagem("Fornecedor não existe!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(fornecedor);
                btnAtualizarF.setDisable(false);
                btnExcluirF.setDisable(false);
                btnIncluirF.setDisable(true);
                btnExibirF.setDisable(true);
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
        
        
    }
    
    //tela para model
    private Fornecedor moveDadosTelaModel() {
        fornecedor = new Fornecedor();
        fornecedor.setCodFornecedor(Integer.parseInt( txtIdFornecedor.getText()));
        fornecedor.setNome(txtNomeF.getText());
        fornecedor.setCNPJ(txtCNPJ.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setEndereco(txtEndereco.getText());
        
        
        return fornecedor;
    }
    
    //model para tela  
    private void  moveDadosModelTela ( Fornecedor f ){
        txtIdFornecedor.setText(Integer.toString(f.getCodFornecedor()));
        txtNomeF.setText((f.getNome()));
        txtCNPJ.setText(f.getCNPJ());
        txtTelefone.setText(f.getTelefone());
        txtEndereco.setText(f.getEndereco());
        
    }
    
    private void limparCampos() {
        txtIdFornecedor.setText(""); 
        txtNomeF.setText("");
        txtEndereco.setText("");     
        txtTelefone.setText("");
        txtCNPJ.setText("");
    }
    
    private boolean validarCampos() {
        if(
           txtNomeF.getText().length() == 0 ||
           txtTelefone.getText().length() == 0 ||
           txtTelefone.getText().length() > 11 ||
           txtCNPJ.getText().length() == 0 ||
           txtCNPJ.getText().length() > 14 ){
            return false;
        } else {
            return true;
        }
    }
    
    
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        btnIncluirF.setDisable(true);
        btnExibirF.setDisable(false);
        btnPesquisar.setDisable(true);
        txtIdFornecedor.setDisable(false);
        
    }
}
