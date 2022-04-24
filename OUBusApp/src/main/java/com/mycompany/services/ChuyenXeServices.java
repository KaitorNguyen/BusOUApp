/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChuyenXe;
import com.mycompany.pojo.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
public class ChuyenXeServices {
//    public List<ChuyenXe> getListChuyenXe() throws SQLException{
//        List<ChuyenXe> results = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConn()){
//            PreparedStatement stm = conn.prepareStatement("SELECT * FROM chuyenxe");
//            ResultSet rs = stm.executeQuery();
//            while(rs.next()) {
//                ChuyenXe c = new ChuyenXe(rs.getString("maChuyenXe"), rs.getString("diemXuatPhat"), rs.getString("diemDen"), rs.getString("timeXuatPhat"), rs.getDouble("giaVe"));
//                results.add(c);
//            }
//        }
//        return results;
//    }
    public List<ChuyenXe> getListChuyenXe() throws SQLException{
        List<ChuyenXe> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM chuyenxe");
//            stm.setInt(1, "maXe");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                PreparedStatement stm1 = conn.prepareStatement("SELECT * FROM xe WHERE maXe=? ");
//                stm1.setInt(1, "maXe");
                stm1.setInt(1, rs.getInt(2));
                ResultSet rs1 = stm1.executeQuery();
                if(rs1.next()){
                    ChuyenXe c = new ChuyenXe(rs.getString("maChuyenXe"), rs1.getString("tenXe"), rs1.getString("bienSo")
                            , rs.getString("diemXuatPhat"), rs.getString("diemDen"), rs.getString("timeXuatPhat"), rs.getDouble("giaVe"));
                    results.add(c);
                }
            }
        }
        return results;
    }
        
    public void addChuyenXe(ChuyenXe c) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            PreparedStatement stm1 = conn.prepareStatement("INSERT INTO chuyenxe(maChuyenXe, maXe, diemXuatPhat, diemDen, timeXuatPhat, giaVe)"
                    + " VALUES(?, ?, ?, ?, ?, ?)");
            stm1.setString(1, c.getMaChuyenXe());
            stm1.setInt(2, c.getMaXe());
            stm1.setString(3, c.getDiemXuatPhat());
            stm1.setString(4, c.getDiemDen());
            stm1.setString(5, c.getTimeXuatPhat());
            stm1.setDouble(6, c.getGiaVe());
            
            stm1.executeUpdate();
            
            conn.commit();
        }
    }
    
    public Boolean deleteChuyenXe(String idChuyenXe) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            
            String sql = "DELETE FROM chuyenxe WHERE maChuyenXe = ?";
            PreparedStatement stm1 = conn.prepareStatement(sql);
            stm1.setString(1, idChuyenXe);
            if(stm1.executeUpdate() == 1)
            {
                conn.commit();
                return true;
            }
            return false;
        }
    }
    
    public void updateChuyenXe(ChuyenXe cx) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            
            String sql = "UPDATE chuyenxe SET maXe=?, diemXuatPhat=?, diemDen=? timeXuatPhat=? giaVe=? WHERE maChuyenXe=? ";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(6, cx.getMaChuyenXe());
            stm.setInt(1, cx.getMaXe());
            stm.setString(2, cx.getDiemXuatPhat());
            stm.setString(3, cx.getDiemDen());
            stm.setString(4, cx.getTimeXuatPhat());
            stm.setDouble(5, cx.getGiaVe());
            
            stm.executeUpdate();
            
            conn.commit();
        }
    }
}
