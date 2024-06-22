package com.example.splitworld.api.model;

import java.io.Serializable;

public class TravelAirTicketCost implements Serializable {

    private long id, viagemId;
    private double custoPessoa, custoAluguelVeiculo;

    public TravelAirTicketCost(){}

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
    public double getCustoPessoa() {
        return custoPessoa;
    }
    public void setCustoPessoa(double custoPessoa) {
        this.custoPessoa = custoPessoa;
    }
    public double getCustoAluguelVeiculo() {
        return custoAluguelVeiculo;
    }
    public void setCustoAluguelVeiculo(double custoAluguelVeiculo) {
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }
}
