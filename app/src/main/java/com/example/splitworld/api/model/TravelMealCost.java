package com.example.splitworld.api.model;

import java.io.Serializable;

public class TravelMealCost implements Serializable{

    private long id, viagemId;
    private double custoRefeicao;
    private int refeicoesDia;

    public TravelMealCost() {}

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
    public double getCustoRefeicao(double sum) {
        return custoRefeicao;
    }
    public void setCustoRefeicao(double custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }
    public int getRefeicoesDia() {
        return refeicoesDia;
    }
    public void setRefeicoesDia(int refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }
}
