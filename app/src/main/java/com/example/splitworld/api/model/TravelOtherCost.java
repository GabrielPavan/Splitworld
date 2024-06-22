package com.example.splitworld.api.model;

import java.io.Serializable;

public class TravelOtherCost implements Serializable {

    private long id, viagemId;
    private String entretenimento;
    private double valor;
    
    public TravelOtherCost() {}

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
    public String getEntretenimento() {
        return entretenimento;
    }
    public void setEntretenimento(String entretenimento) {
        this.entretenimento = entretenimento;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
