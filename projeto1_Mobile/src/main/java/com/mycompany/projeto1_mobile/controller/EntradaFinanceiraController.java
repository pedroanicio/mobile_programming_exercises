package com.mycompany.projeto1_mobile.controller;

import com.mycompany.projeto1_mobile.model.EntradaFinanceira;
import com.mycompany.projeto1_mobile.repository.EntradaFinanceiraRepository;
import com.mycompany.projeto1_mobile.repository.EntradaFinanceiraRepositoryImpl;
import java.time.LocalDate;
import java.util.List;

public class EntradaFinanceiraController {

    private EntradaFinanceiraRepository entradaRepository;

    public EntradaFinanceiraController() {
        // Inicializa o repositório que fará as operações no banco de dados
        this.entradaRepository = new EntradaFinanceiraRepositoryImpl();
    }

    //  adicionar uma nova entrada financeira
    public void adicionarEntrada(String nome, String classificacao, double valor, LocalDate dataEntrada) {
        EntradaFinanceira entrada = new EntradaFinanceira();
        entrada.setNome(nome);
        entrada.setClassificacao(classificacao);
        entrada.setValor(valor);
        entrada.setDataEntrada(dataEntrada);
        entrada.setDataCadastro(LocalDate.now()); // Define a data de cadastro como a data atual

        entradaRepository.salvar(entrada);
    }

    //buscar todas as entradas financeiras
    public List<EntradaFinanceira> listarTodasEntradas() {
        return entradaRepository.buscarTodas();
    }

    //  buscar uma entrada por ID
    public EntradaFinanceira buscarEntradaPorId(int id) {
        return entradaRepository.buscarPorId(id);
    }

    // atualizar uma entrada financeira
    public void atualizarEntrada(int id, String nome, String classificacao, double valor, LocalDate dataEntrada) {
        EntradaFinanceira entrada = entradaRepository.buscarPorId(id);

        if (entrada != null) {
            entrada.setNome(nome);
            entrada.setClassificacao(classificacao);
            entrada.setValor(valor);
            entrada.setDataEntrada(dataEntrada);
            entrada.setDataCadastro(LocalDate.now());

            entradaRepository.atualizar(entrada);
        } else {
            System.out.println("Entrada financeira não encontrada para o ID: " + id);
        }
    }

    // deletar uma entrada financeira por ID
    public void deletarEntrada(int id) {
        entradaRepository.deletar(id);
    }
}
