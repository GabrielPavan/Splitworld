package com.example.splitworld.api.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class Travel {

    private long id, usuario, idConta;
    private int totalViajantes, duracaoViagem;
    private double custoTotalViagem, custoPorPessoa;
    private String local;
    private TravelGasCost gasolina;
    private TravelAirTicketCost aereo;
    private TravelMealCost refeicao;
    private TravelHostingCost hospedagem;
    private List<TravelOtherCost> listaEntretenimento;

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
    public TravelGasCost getGasolina() {
        return gasolina;
    }
    public void setGasolina(TravelGasCost gasolina) {
        this.gasolina = gasolina;
    }
    public TravelAirTicketCost getAereo() {
        return aereo;
    }
    public void setAereo(TravelAirTicketCost aereo) {
        this.aereo = aereo;
    }
    public TravelMealCost getRefeicao() {
        return refeicao;
    }
    public void setRefeicao(TravelMealCost refeicao) {
        this.refeicao = refeicao;
    }
    public TravelHostingCost getHospedagem() {
        return hospedagem;
    }
    public void setHospedagem(TravelHostingCost hospedagem) {
        this.hospedagem = hospedagem;
    }
    public List<TravelOtherCost> getListaEntretenimento() {
        return listaEntretenimento;
    }
    public void setListaEntretenimento(List<TravelOtherCost> listaEntretenimento) {
        this.listaEntretenimento = listaEntretenimento;
    }

    @NonNull
    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
