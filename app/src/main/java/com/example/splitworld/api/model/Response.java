package com.example.splitworld.api.model;

import java.io.Serializable;

public class Response implements Serializable {

    private boolean Sucesso;
    private String Mensagem;
    private int ChavePrimaria;

    public Response(){}

    public boolean isSucesso() {
        return Sucesso;
    }
    public void setSucesso(boolean sucesso) {
        Sucesso = sucesso;
    }
    public String getMensagem() {
        return Mensagem;
    }
    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
    public int getChavePrimaria() {
        return ChavePrimaria;
    }
    public void setChavePrimaria(int chavePrimaria) {
        ChavePrimaria = chavePrimaria;
    }
}
