/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oubusapp;

import com.mycompany.conf.Utils;
import com.mycompany.services.DangNhapServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class FXMLDangNhapController implements Initializable {
    @FXML 
    private TextField txt_username;
    @FXML 
    private TextField txt_password;
    @FXML
    public Button btn_DangNhap;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void dangNhapHandler (ActionEvent event) throws SQLException, IOException {
        String us = txt_username.getText(); // lấy giá trị trong text field
        String pw = txt_password.getText();

        DangNhapServices l = new DangNhapServices();
        int kq = l.loginUser(us, pw); // truyền 2 tham số
        if (txt_username.getText().isEmpty()) {
            Utils.getBox("Chưa nhập username!", Alert.AlertType.INFORMATION).show();
            return;
        }
        if (txt_password.getText().isEmpty()) {
            Utils.getBox("Chưa nhập password!", Alert.AlertType.INFORMATION).show();
            return;
        }
        if(kq!=1){
            Utils.getBox("Đăng nhập không thành công!", Alert.AlertType.ERROR).show();
        } else {
            dangNhapAdminHandler();
        }
        
    }
    
    public void exit (ActionEvent event) throws SQLException {
        Platform.exit();
    }
    
    public void dangNhapAdminHandler() throws IOException{
        Stage primaryStage = (Stage) btn_DangNhap.getScene().getWindow();
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLTrangChuAdmin.fxml"));
        fxmlLoader.load();
        
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    } 
}
