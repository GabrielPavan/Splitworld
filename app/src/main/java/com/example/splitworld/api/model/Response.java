package com.example.splitworld.api.model;

import java.io.Serializable;

public class Response implements Serializable {

    private boolean sucesso;
    private String mensagem;
    private int chavePrimaria;

    public Response(){}

    public boolean isSucesso() {
        return sucesso;
    }
    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public int getChavePrimaria() {
        return chavePrimaria;
    }
    public void setChavePrimaria(int chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }
}
