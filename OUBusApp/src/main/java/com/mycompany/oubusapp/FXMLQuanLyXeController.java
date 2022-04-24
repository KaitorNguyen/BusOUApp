/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oubusapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Xe;
import com.mycompany.services.XeServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class FXMLQuanLyXeController implements Initializable {
    @FXML 
    private TextField txt_TenXe;
    @FXML 
    private TextField txt_BienSo;
    @FXML 
    private TextField txt_SoGhe;
    @FXML
    private TableView<Xe> tbXe;
    @FXML
    private Button btnUpdate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnUpdate.setVisible(false);
             // Select row on Tableview
        this.tbXe.setRowFactory(et -> {
                TableRow row = new TableRow();
                row.setOnMouseClicked(r -> {
                    this.btnUpdate. setVisible(true);
                    Xe x = (Xe) this.tbXe.getSelectionModel().getSelectedItem();
                    this.txt_TenXe.setText(String.valueOf(x.getTenXe()));
                    this.txt_BienSo.setText(String.valueOf(x.getBienSo()));
                    this.txt_SoGhe.setText(String.valueOf(x.getSoGhe()));
                });
            return row;
        });
    }
    
    static boolean isStringEmpty(String string){
        return string==null || string.isBlank();
    }
        
    public void addXeHandler(ActionEvent event){
        String tenXe = this.txt_TenXe.getText();
        String bienSo = this.txt_BienSo.getText();
        String soGhe = this.txt_SoGhe.getText();
        
        if(isStringEmpty(tenXe) || isStringEmpty(bienSo) || isStringEmpty(soGhe)){
            Utils.getBox("Vui lòng điền các thông tin còn trống", Alert.AlertType.WARNING).show();
        } else{
            try {
                Xe x = new Xe(tenXe, bienSo, Integer.parseInt(soGhe));
                XeServices xe = new XeServices();
                xe.addXe(x);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                this.loadTableData(null);
            } catch (SQLException ex) {
                Utils.getBox("Thêm thất bại", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    private void loadTableView() {
        TableColumn colMaXe = new TableColumn("Mã xe");
        colMaXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        colMaXe.setPrefWidth(94);
        
        TableColumn colTenXe = new TableColumn("Tên xe");
        colTenXe.setCellValueFactory(new PropertyValueFactory("tenXe"));
        colTenXe.setPrefWidth(141);
        
        TableColumn colBienSo = new TableColumn("Biển số xe");
        colBienSo.setCellValueFactory(new PropertyValueFactory("bienSo"));
        colMaXe.setPrefWidth(145);
        
        TableColumn colSoGhe = new TableColumn("Số ghế");
        colSoGhe.setCellValueFactory(new PropertyValueFactory("soGhe"));
        colMaXe.setPrefWidth(126);
        
        this.tbXe.getColumns().addAll(colMaXe, colTenXe, colBienSo, colSoGhe);
    }
    
    private void loadTableData(String kw) throws SQLException {
        XeServices x = new XeServices();
        this.tbXe.setItems(FXCollections.observableArrayList(x.getCars(kw)));
    }
    
    public void deleteXeHandler() {
        Xe selected = (Xe) tbXe.getSelectionModel().getSelectedItem();
        int id = selected.getMaXe();        
        XeServices x = new XeServices();
        Utils.getBox("Bạn chắc chắn muốn xóa chuyến xe: " + id + "?", Alert.AlertType.INFORMATION)
                .showAndWait().ifPresent(res -> {
                    try {
                        if(x.deleteXe(id) == true){
                            Utils.getBox("Đã xóa thành công", Alert.AlertType.INFORMATION).show();
                            loadTableData(null);
                        }
                        else
                            Utils.getBox("Đã xóa thất bại", Alert.AlertType.WARNING).show();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLQuanLyXeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
    }
    
    public void updateXeHandler(ActionEvent event) throws SQLException{
        Xe selected = (Xe) tbXe.getSelectionModel().getSelectedItem();
        int id = selected.getMaXe();
        
        String tenXe = this.txt_TenXe.getText();
        String bienSo = this.txt_BienSo.getText();
        String soGhe = this.txt_SoGhe.getText();
        
        Xe x = new Xe(id, tenXe, bienSo, Integer.parseInt(soGhe));
        XeServices xe = new XeServices();
        try {            
            xe.updateXe(x);
            Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Utils.getBox("Cập nhật thất bại!", Alert.AlertType.WARNING).show();
        }    
    }
    
    
}
