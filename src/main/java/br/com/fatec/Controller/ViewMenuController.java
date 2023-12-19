/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sorve
 */
public class ViewMenuController implements Initializable {
    
    public static Stage tela = new Stage( );

    public static void setTela(Stage tela) {
        ViewMenuController.tela = tela;
    }
    
    @FXML
    private Button btnRegistroProdutos;
    @FXML
    private Button btnRegistroFornecedores;
    @FXML
    private Button btnRegistroProdutosCol;
    @FXML
    private Button btnRegistroPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistroProdutos_Click(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewRegProdutos.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();
    }

    @FXML
    private void btnRegistroFornecedores_Click(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewRegFornecedores.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();
    }

    @FXML
    private void btnRegistroProdutosCol_Click(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewRegProdutosCol.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();
    }

    @FXML
    private void btnRegistroPesquisa_Click(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/viewPesquisa.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();
    }
    
}
