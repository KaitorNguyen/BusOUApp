/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author GIGABYTE
 */
public class Xe {
    private int maXe;
    private String tenXe;
    private String bienSo;
    private int soGhe;

    public Xe() {
    }
    
    public Xe(String tenXe, String bienSo, int soGhe) {
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.soGhe = soGhe;
    }
    
    public Xe(int maXe, String tenXe, String bienSo, int soGhe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.soGhe = soGhe;
    }

//    @Override
//    public String toString() {
//        String s1 = this.tenXe;
//        int s2 = this.soGhe;
//        String s3 = s1 + " - Số ghế: " + s2;
//        return s3; //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public String toString() {
        return this.tenXe; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * @return the maXe
     */
    public int getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the tenXe
     */
    public String getTenXe() {
        return tenXe;
    }

    /**
     * @param tenXe the tenXe to set
     */
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    /**
     * @return the bienSo
     */
    public String getBienSo() {
        return bienSo;
    }

    /**
     * @param bienSo the bienSo to set
     */
    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
    
    
}
