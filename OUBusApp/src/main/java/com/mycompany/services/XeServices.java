/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
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
public class XeServices {
    public List<Xe> getCars(String kw) throws SQLException {
        List<Xe> results = new ArrayList<>();
        
        try (Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM xe";
            if (kw != null && !kw.isEmpty())
                sql += "WHERE tenXe like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Xe x = new Xe(rs.getInt("maXe"), rs.getString("tenXe"), rs.getString("bienSo"), 
                        rs.getInt("soGhe"));
                results.add(x);
            }
        }
        
        return results;
    }
    
    public List<Xe> getCarsByName() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM xe");

            List<Xe> xe = new ArrayList<>();

            while (rs.next()) {
                int maXe = rs.getInt("maXe");
                String tenXe = rs.getString("tenXe");
                String bienSo = rs.getString("bienSo");
                int soGhe = rs.getInt("soGhe");
                xe.add(new Xe(maXe, tenXe, bienSo, soGhe));
            }

            return xe;
        }
    }
    
    public static Xe getCarByID(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM xe WHERE maXe=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Xe x = null;
            while(rs.next()) {
                int maXe = rs.getInt("maXe");
                String tenXe = rs.getString("tenXe");
                String bienSo = rs.getString("bienSo");
                int soGhe = rs.getInt("soGhe");
                x = new Xe(maXe, tenXe, bienSo, soGhe);
            }
            return x;
        }
    }
    
    
    public void addXe(Xe xe) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            PreparedStatement stm1 = conn.prepareStatement("INSERT INTO xe(maXe, tenXe, bienSo, soGhe) VALUES(?, ?, ?, ?)");
            stm1.setInt(1, xe.getMaXe());
            stm1.setString(2, xe.getTenXe());
            stm1.setString(3, xe.getBienSo());
            stm1.setInt(4, xe.getSoGhe());
            
            stm1.executeUpdate();
            
            conn.commit();
        }
    }
    
    public Boolean deleteXe(int idXe) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            
            String sql = "DELETE FROM xe WHERE maXe = ?";
            PreparedStatement stm1 = conn.prepareStatement(sql);
            stm1.setInt(1, idXe);
            if(stm1.executeUpdate()==1){
                conn.commit();
                return true;
            }
            return false;
        }
    }
    
    public void updateXe(Xe x) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            
            String sql = "UPDATE xe SET  tenXe=?, bienSo=?, soGhe=? WHERE maXe=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(4, x.getMaXe());
            stm.setString(1, x.getTenXe());
            stm.setString(2, x.getBienSo());
            stm.setInt(3, x.getSoGhe());
            
            stm.executeUpdate();
            
            conn.commit();
        }
    }
    
}
