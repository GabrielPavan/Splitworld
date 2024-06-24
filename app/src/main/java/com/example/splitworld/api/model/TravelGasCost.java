package com.example.splitworld.api.model;

import java.io.Serializable;

public class TravelGasCost {

    private long id, viagemId;
    private int totalEstimadoKM, totalVeiculos;
    private double mediaKMLitro, custoMedioLitro;

    public TravelGasCost(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getViagemId() {
        return viagemId;
    }
    public void setViagemId(long viagemId) {
        this.viagemId = viagemId;
    }
    public int getTotalEstimadoKM() {
        return totalEstimadoKM;
    }
    public void setTotalEstimadoKM(int totalEstimadoKM) {
        this.totalEstimadoKM = totalEstimadoKM;
    }
    public int getTotalVeiculos() {
        return totalVeiculos;
    }
    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }
    public double getMediaKMLitro() {
        return mediaKMLitro;
    }
    public void setMediaKMLitro(double mediaKMLitro) {
        this.mediaKMLitro = mediaKMLitro;
    }
    public double getCustoMedioLitro() {
        return custoMedioLitro;
    }
    public void setCustoMedioLitro(double custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }
}
