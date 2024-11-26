package com.mycompany.projeto1_mobile.repository;

import com.mycompany.projeto1_mobile.model.EntradaFinanceira;
import java.util.List;

public interface EntradaFinanceiraRepository {


    void salvar(EntradaFinanceira entrada);

    EntradaFinanceira buscarPorId(int id);

    List<EntradaFinanceira> buscarTodas();


    void atualizar(EntradaFinanceira entrada);


    void deletar(int id);
}
