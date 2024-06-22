package com.example.splitworld.api.model;

import java.io.Serializable;

public class TravelHostingCost implements Serializable {

    private long id, viagemId;
    private double custoMedioNoite;
    private int totalNoite, totalQuartos;

    public TravelHostingCost() {}

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
    public double getCustoMedioNoite() {
        return custoMedioNoite;
    }
    public void setCustoMedioNoite(double custoMedioNoite) {
        this.custoMedioNoite = custoMedioNoite;
    }
    public int getTotalNoite() {
        return totalNoite;
    }
    public void setTotalNoite(int totalNoite) {
        this.totalNoite = totalNoite;
    }
    public int getTotalQuartos() {
        return totalQuartos;
    }
    public void setTotalQuartos(int totalQuartos) {
        this.totalQuartos = totalQuartos;
    }
}
