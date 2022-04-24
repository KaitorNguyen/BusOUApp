/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author GIGABYTE
 */
public class ChuyenXe {
    private String maChuyenXe;
    private int maXe;
    private String diemXuatPhat;
    private String diemDen;
    private String timeXuatPhat;
    private double giaVe;
    
    private String tenXe;
    private String bienSo;
    
    public ChuyenXe() {
    }

    public ChuyenXe(String maChuyenXe, String tenXe, String bienSo, String diemXuatPhat, String diemDen, String timeXuatPhat, double giaVe) {
        this.maChuyenXe = maChuyenXe;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.diemXuatPhat = diemXuatPhat;
        this.diemDen = diemDen;
        this.timeXuatPhat = timeXuatPhat;
        this.giaVe = giaVe;
    }

    public ChuyenXe(String maChuyenXe, String diemXuatPhat, String diemDen, String timeXuatPhat, double giaVe) {
        this.maChuyenXe = maChuyenXe;
        this.diemXuatPhat = diemXuatPhat;
        this.diemDen = diemDen;
        this.timeXuatPhat = timeXuatPhat;
        this.giaVe = giaVe;
    }
    
    public ChuyenXe(String maChuyenXe, int maXe, String diemXuatPhat, String diemDen, String timeXuatPhat, double giaVe) {
        this.maChuyenXe = maChuyenXe;
        this.maXe = maXe;
        this.diemXuatPhat = diemXuatPhat;
        this.diemDen = diemDen;
        this.timeXuatPhat = timeXuatPhat;
        this.giaVe = giaVe;
    }

    /**
     * @return the maChuyenXe
     */
    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    /**
     * @param maChuyenXe the maChuyenXe to set
     */
    public void setMaChuyenXe(String maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
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
     * @return the diemXuatPhat
     */
    public String getDiemXuatPhat() {
        return diemXuatPhat;
    }

    /**
     * @param diemXuatPhat the diemXuatPhat to set
     */
    public void setDiemXuatPhat(String diemXuatPhat) {
        this.diemXuatPhat = diemXuatPhat;
    }

    /**
     * @return the diemDen
     */
    public String getDiemDen() {
        return diemDen;
    }

    /**
     * @param diemDen the diemDen to set
     */
    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    /**
     * @return the timeXuatPhat
     */
    public String getTimeXuatPhat() {
        return timeXuatPhat;
    }

    /**
     * @param timeXuatPhat the timeXuatPhat to set
     */
    public void setTimeXuatPhat(String timeXuatPhat) {
        this.timeXuatPhat = timeXuatPhat;
    }

    /**
     * @return the giaVe
     */
    public double getGiaVe() {
        return giaVe;
    }

    /**
     * @param giaVe the giaVe to set
     */
    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
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
}
