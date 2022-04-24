/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Ghe;
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
public class GheServices {
    public List<Ghe> getListGhe() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ghe");

            List<Ghe> g = new ArrayList<>();

            while (rs.next()) {
                int maGhe = rs.getInt("maGhe");
                String tenGhe = rs.getString("tenGhe");
                g.add(new Ghe(maGhe, tenGhe));
            }

            return g;
        }
    }
    
    public Ghe getGheById(int maGhe) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM ghe where id = ?");
            stm.setInt(1, maGhe);
            ResultSet rs = stm.executeQuery();
            
            Ghe g = null;
            if(rs.next()) {  
                g = new Ghe(rs.getInt("maGhe"), rs.getString("tenGhe"));
            }
            return g;
        }
    }
}
