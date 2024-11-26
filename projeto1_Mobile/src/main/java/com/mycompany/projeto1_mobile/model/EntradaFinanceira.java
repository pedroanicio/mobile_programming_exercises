package com.mycompany.projeto1_mobile.model;

import java.time.LocalDate;

public class EntradaFinanceira {

    private int id;  
    private String nome;  
    private String classificacao;  
    private double valor;  
    private LocalDate dataEntrada;  
    private LocalDate dataCadastro;  
    
  
    public EntradaFinanceira() {
    }

    
    public EntradaFinanceira(String nome, String classificacao, double valor, LocalDate dataEntrada, LocalDate dataCadastro) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "EntradaFinanceira{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", valor=" + valor +
                ", dataEntrada=" + dataEntrada +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
