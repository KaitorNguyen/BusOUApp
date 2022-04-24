/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.conf.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author GIGABYTE
 */
public class DangNhapServices {
    public Integer loginUser(String us, String pw) throws SQLException {
         try (Connection conn = JdbcUtils.getConn()) { // kết nối đến csdl
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM employee");// tạo 1 câu lệnh và sử dụng đối tượng kết nối
            ResultSet rs = stm.executeQuery();
            while (rs.next()) { //
               if(us.equals(rs.getString("username")) && rs.getString("password").equals(pw))
               {
                   Utils.getBox("successful!", Alert.AlertType.INFORMATION).show(); // đúng thì show ra box t.báo thành công
                   return 1;
               }
            }   
        }
        catch(Exception e) { // ngoại lệ
            Utils.getBox("ngoại lệ", Alert.AlertType.ERROR).show(); // xuất hiện hộp box ngoại lệ
        }
        return -1;// do k vô dc dòng if, trường hợp đăng nhập k thành công thì sẽ chạy ra dòng này
    }
}
