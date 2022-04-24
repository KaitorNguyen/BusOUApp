/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oubusapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.ChuyenXe;
import com.mycompany.pojo.Xe;
import com.mycompany.services.ChuyenXeServices;
import com.mycompany.services.XeServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class FXMLQuanLyChuyenXeController implements Initializable {
    @FXML
    private ComboBox<Xe> choiceCars;
    @FXML
    private TextField txt_MaChuyenXe;
    @FXML
    private TextField txt_DiemXuatPhat;
    @FXML
    private TextField txt_DiemDen;
    @FXML
    private TextField txt_ThoiGianXuatPhat;
    @FXML
    private TextField txt_GiaVe;
    @FXML
    private TableView<ChuyenXe> tbChuyenXe;
    @FXML
    private Button btnUpdate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XeServices x = new XeServices();
        
        this.loadTableView();
        
        try {
            this.choiceCars.setItems(FXCollections.observableList(x.getCarsByName()));
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.btnUpdate.setVisible(false);
             // Select row on Tableview
        this.tbChuyenXe.setRowFactory(et -> {
                TableRow row = new TableRow();
                row.setOnMouseClicked(r -> {
                    this.btnUpdate. setVisible(true);
                    ChuyenXe cx = (ChuyenXe) this.tbChuyenXe.getSelectionModel().getSelectedItem();
                    this.txt_MaChuyenXe.setText(String.valueOf(cx.getMaChuyenXe()));
                    this.txt_DiemXuatPhat.setText(String.valueOf(cx.getDiemXuatPhat()));
                    this.txt_DiemDen.setText(String.valueOf(cx.getDiemDen()));
                    this.txt_ThoiGianXuatPhat.setText(String.valueOf(cx.getTimeXuatPhat()));
                    this.txt_GiaVe.setText(String.valueOf(cx.getGiaVe()));
                });
            return row;
        });
    }
    
    static boolean isStringEmpty(String string){
        return string==null || string.isBlank();
    }
    public void addChuyenXeHandler(ActionEvent event){
        String maCX = this.txt_MaChuyenXe.getText();
        int maXe = this.choiceCars.getSelectionModel().getSelectedItem().getMaXe();
        String diemXP = this.txt_DiemXuatPhat.getText();
        String diemDen = this.txt_DiemDen.getText();
        String timeXP =  this.txt_ThoiGianXuatPhat.getText();
        String giaVe = this.txt_GiaVe.getText();
        
//        XeServices x = new XeServices();
//        try {
//            this.choiceCars.setItems(FXCollections.observableList(x.getCarsByName()));
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLQuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if(isStringEmpty(maCX) || isStringEmpty(diemXP) || isStringEmpty(diemDen) || isStringEmpty(timeXP) || isStringEmpty(giaVe)){
            Utils.getBox("Vui lòng điền các thông tin còn trống", Alert.AlertType.WARNING).show();
        } else {
            try {
                ChuyenXe c = new ChuyenXe(maCX, maXe, diemXP, diemDen, timeXP, Double.parseDouble(giaVe));
                ChuyenXeServices cx = new ChuyenXeServices();
                cx.addChuyenXe(c);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                this.loadTableData(null);
            } catch (SQLException ex) {
                Utils.getBox("Thêm thất bại", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void deleteChuyenXeHandler(ActionEvent event){
        ChuyenXe selected = (ChuyenXe) tbChuyenXe.getSelectionModel().getSelectedItem();
        String id = selected.getMaChuyenXe();        
        ChuyenXeServices cx = new ChuyenXeServices();
        Utils.getBox("Bạn chắc chắn muốn xóa chuyến xe: " + id + "?", Alert.AlertType.INFORMATION)
                .showAndWait().ifPresent(res -> {
                    try {
                        if(cx.deleteChuyenXe(id) == true){
                            Utils.getBox("Đã xóa thành công", Alert.AlertType.INFORMATION).show();
                            loadTableData(null);
                        }
                        else
                            Utils.getBox("Đã xóa thất bại", Alert.AlertType.WARNING).show();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLQuanLyChuyenXeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
    }
    
    public void updateChuyenXeHandler(ActionEvent event) throws SQLException{
        ChuyenXe selected = (ChuyenXe) tbChuyenXe.getSelectionModel().getSelectedItem();
        String id = selected.getMaChuyenXe();
        
//        String maCX = this.txt_MaChuyenXe.getText();
        int maXe = this.choiceCars.getSelectionModel().getSelectedItem().getMaXe();
        String diemXP = this.txt_DiemXuatPhat.getText();
        String diemDen = this.txt_DiemDen.getText();
        String timeXP =  this.txt_ThoiGianXuatPhat.getText();
        String giaVe = this.txt_GiaVe.getText();
        
        ChuyenXe c = new ChuyenXe(id, maXe, diemXP, diemDen, timeXP, Double.parseDouble(giaVe));
        ChuyenXeServices cx = new ChuyenXeServices();
        try {            
            cx.updateChuyenXe(c);
            Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Utils.getBox("Cập nhật thất bại!", Alert.AlertType.WARNING).show();
        }    
    }
    
    private void loadTableView() {
        TableColumn colMaCX = new TableColumn("Mã chuyến xe");
        colMaCX.setCellValueFactory(new PropertyValueFactory("maChuyenXe"));
        colMaCX.setPrefWidth(106);
        
        TableColumn colTenXe = new TableColumn("Tên xe");
        colTenXe.setCellValueFactory(new PropertyValueFactory("xe.tenXe"));
        colTenXe.setPrefWidth(100);
        
        TableColumn colBienSoXe = new TableColumn("Biển số xe");
        colBienSoXe.setCellValueFactory(new PropertyValueFactory("xe.bienSo"));
        colBienSoXe.setPrefWidth(104);
        
        TableColumn colDiemXuatPhat = new TableColumn("Điểm xuất phát");
        colDiemXuatPhat.setCellValueFactory(new PropertyValueFactory("diemXuatPhat"));
        colDiemXuatPhat.setPrefWidth(142);
        
        TableColumn colDiemDen = new TableColumn("Điểm đến");
        colDiemDen.setCellValueFactory(new PropertyValueFactory("diemDen"));
        colDiemDen.setPrefWidth(142);
        
        TableColumn colThoiGianXP = new TableColumn("Thời gian xuất phát");
        colThoiGianXP.setCellValueFactory(new PropertyValueFactory("timeXuatPhat"));
        colThoiGianXP.setPrefWidth(154);
        
        TableColumn colGiaVe = new TableColumn("Giá vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        colGiaVe.setPrefWidth(119);
        
        this.tbChuyenXe.getColumns().addAll(colMaCX, colTenXe, colBienSoXe, colDiemXuatPhat, colDiemDen, colThoiGianXP, colGiaVe);
    }
    private void loadTableData(String kw) throws SQLException {
        ChuyenXeServices cx = new ChuyenXeServices();
        this.tbChuyenXe.setItems(FXCollections.observableArrayList(cx.getListChuyenXe()));
    }
}
