/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author GIGABYTE
 */
public class GheXe {
    private int maGheXe;
    private int maGhe;
    private int maXe;
    private Boolean tinhTrangGhe;

    public GheXe() {
    }

    public GheXe(int maGheXe, int maGhe, int maXe, Boolean tinhTrangGhe) {
        this.maGheXe = maGheXe;
        this.maGhe = maGhe;
        this.maXe = maXe;
        this.tinhTrangGhe = tinhTrangGhe;
    }

    
    /**
     * @return the maGheXe
     */
    public int getMaGheXe() {
        return maGheXe;
    }

    /**
     * @param maGheXe the maGheXe to set
     */
    public void setMaGheXe(int maGheXe) {
        this.maGheXe = maGheXe;
    }

    /**
     * @return the maGhe
     */
    public int getMaGhe() {
        return maGhe;
    }

    /**
     * @param maGhe the maGhe to set
     */
    public void setMaGhe(int maGhe) {
        this.maGhe = maGhe;
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
     * @return the tinhTrangGhe
     */
    public Boolean getTinhTrangGhe() {
        return tinhTrangGhe;
    }

    /**
     * @param tinhTrangGhe the tinhTrangGhe to set
     */
    public void setTinhTrangGhe(Boolean tinhTrangGhe) {
        this.tinhTrangGhe = tinhTrangGhe;
    }
    
    
}
