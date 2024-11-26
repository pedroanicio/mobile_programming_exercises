/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto1_mobile.Utils;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TabelaHelper {

    public void preencherTabela(List<String[]> dados, javax.swing.JTable tabela) {
        // Pegando o modelo da tabela
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();

        // Limpando a tabela
        modelo.setRowCount(0);

        // Adicionando os dados na tabela
        for (String[] linha : dados) {
            modelo.addRow(linha);
        }
    }
}