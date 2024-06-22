package com.example.splitworld.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Travel implements Serializable {

    private long id, usuario, idConta;
    private int totalViajantes, duracaoViagem;
    private double custoTotalViagem, custoPorPessoa;
    private String local;
    private TravelGasCost travelGasCost;
    private TravelAirTicketCost travelAirTicketCost;
    private TravelMealCost travelMealCost;
    private TravelHostingCost travelHostingCost;
    private List<TravelOtherCost> travelOtherCosts;

    public Travel(){}

    public long getUsuario() {
        return usuario;
    }
    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }
    public long getIdConta() {
        return idConta;
    }
    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getTotalViajantes() {
        return totalViajantes;
    }
    public void setTotalViajantes(int totalViajantes) {
        this.totalViajantes = totalViajantes;
    }
    public int getDuracaoViagem() {
        return duracaoViagem;
    }
    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }
    public double getCustoTotalViagem() {
        return custoTotalViagem;
    }
    public void setCustoTotalViagem(double custoTotalViagem) {
        this.custoTotalViagem = custoTotalViagem;
    }
    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }
    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public TravelGasCost getTravelGasCost() {
        return travelGasCost;
    }
    public void setTravelGasCost(TravelGasCost travelGasCost) {
        this.travelGasCost = travelGasCost;
    }
    public TravelAirTicketCost getTravelAirTicketCost() {
        return travelAirTicketCost;
    }
    public void setTravelAirTicketCost(TravelAirTicketCost travelAirTicketCost) {
        this.travelAirTicketCost = travelAirTicketCost;
    }
    public TravelMealCost getTravelMealCost() {
        return travelMealCost;
    }
    public void setTravelMealCost(TravelMealCost travelMealCost) {
        this.travelMealCost = travelMealCost;
    }
    public TravelHostingCost getTravelHostingCost() {
        return travelHostingCost;
    }
    public void setTravelHostingCost(TravelHostingCost travelHostingCost) {
        this.travelHostingCost = travelHostingCost;
    }
    public List<TravelOtherCost> getTravelOtherCosts() {
        return travelOtherCosts;
    }
    public void setTravelOtherCosts(List<TravelOtherCost> travelOtherCosts) {
        this.travelOtherCosts = travelOtherCosts;
    }
}
