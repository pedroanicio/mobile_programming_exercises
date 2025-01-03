/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projeto1_mobile.view;

import com.mycompany.projeto1_mobile.Utils.DatabaseHelper;
import com.mycompany.projeto1_mobile.Utils.TabelaHelper;
import com.mycompany.projeto1_mobile.controller.EntradaFinanceiraController;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class menu extends javax.swing.JFrame {

    /**
     * Creates new form menu 
     */
    public menu() {
        initComponents();
        
        
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
            "Id","Nome", "Classificação", "Valor", "Data", "Cadastro"
            }
        ));
        
        carregarDadosNaTabela();
        atualizarTotais();
    }
    
    private void carregarDadosNaTabela() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        List<String[]> dados = dbHelper.buscarDados();

        // Preenchendo a tabela
        TabelaHelper tabelaHelper = new TabelaHelper();
        tabelaHelper.preencherTabela(dados, tabela);
    }
    
    private void atualizarTotais() {
        DatabaseHelper dbHelper = new DatabaseHelper();
    
        // Chamando o método que calcula os totais
        double totalRecebido = dbHelper.calcularTotalRecebido();
        double totalGasto = dbHelper.calcularTotalGasto();
        double diferenca = totalRecebido + totalGasto;

        // Atualizando os JLabels com os valores formatados
        valorRecebido.setText(String.format("R$ %.2f", totalRecebido));
        valorGastos.setText(String.format("R$ %.2f", totalGasto));
        valorDiferenca.setText(String.format("R$ %.2f", diferenca));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botaoGanho = new javax.swing.JToggleButton();
        botaoGasto = new javax.swing.JToggleButton();
        botaoCadastro = new javax.swing.JButton();
        inputNome = new javax.swing.JTextField();
        inputData = new javax.swing.JTextField();
        inputvalor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        valorRecebido = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        valorGastos = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        valorDiferenca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        botaoDeletar = new javax.swing.JButton();
        inputClassificacao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Finanças Anual Seu José");

        jLabel2.setText("Nome:");

        jLabel3.setText("Classificação:");

        jLabel4.setText("Valor:");

        jLabel5.setText("Data Entrada:");

        botaoGanho.setBackground(new java.awt.Color(0, 255, 51));
        buttonGroup1.add(botaoGanho);
        botaoGanho.setText("Ganho ( + )");
        botaoGanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGanhoActionPerformed(evt);
            }
        });

        botaoGasto.setBackground(new java.awt.Color(255, 102, 102));
        buttonGroup1.add(botaoGasto);
        botaoGasto.setText("Gasto ( - )");
        botaoGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGastoActionPerformed(evt);
            }
        });

        botaoCadastro.setBackground(new java.awt.Color(0, 255, 204));
        botaoCadastro.setText("Cadastrar");
        botaoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastroActionPerformed(evt);
            }
        });

        inputNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNomeActionPerformed(evt);
            }
        });

        jLabel6.setText("Recebido: R$");

        jLabel8.setText("Gastos: R$");

        jLabel10.setText("Diferenca: R$");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Classificação", "Valor", "Data", "Cadastro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        botaoDeletar.setText("x");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        inputClassificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASA", "COMPRAS", "SAUDE", "AUTOMOVEL", "ALIMENTACAO", "BEM_ESTAR", "INVESTIMENTO", "SALARIO", "OUTROS" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(2, 2, 2)
                                .addComponent(inputClassificacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputvalor))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputData))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoGanho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoGasto))
                            .addComponent(botaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoDeletar)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(503, 503, 503))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(inputClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(inputvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(inputData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(139, 139, 139)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botaoGasto)
                                    .addComponent(botaoGanho))
                                .addGap(30, 30, 30)
                                .addComponent(botaoCadastro)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoDeletar)
                        .addGap(240, 240, 240)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(valorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(valorGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(valorDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoGanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGanhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoGanhoActionPerformed

    private void botaoGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGastoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoGastoActionPerformed

    private void inputNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNomeActionPerformed
        // TODO add your handling code here::
    }//GEN-LAST:event_inputNomeActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        
        int selectedRow = tabela.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma entrada para deletar!");
        return;
    }
    
    // Obtenha o valor da coluna "Nome" da linha selecionada
    int id = Integer.parseInt(tabela.getValueAt(selectedRow, 0).toString());
    
    
    
    // Confirme se o usuário realmente quer deletar a entrada
    int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar a entrada: " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
    
    if (confirmacao == JOptionPane.YES_OPTION) {
        // Instancia o controlador e deleta a entrada
        EntradaFinanceiraController controller = new EntradaFinanceiraController();
        controller.deletarEntrada(id); 
        
        // Atualiza a tabela para remover a linha deletada
        carregarDadosNaTabela();
        atualizarTotais();
        
        JOptionPane.showMessageDialog(this, "Entrada deletada com sucesso!");
    }
        
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void botaoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastroActionPerformed
        String nome = inputNome.getText();
        String classificacao = (String) inputClassificacao.getSelectedItem();
        double valor = Double.parseDouble(inputvalor.getText());
        
        
        if(botaoGasto.isSelected()){
            valor = -valor;
            
            LocalDate dataEntrada = LocalDate.parse(inputData.getText());

            EntradaFinanceiraController controller = new EntradaFinanceiraController();
            controller.adicionarEntrada(nome, classificacao, valor, dataEntrada);

            
            JOptionPane.showMessageDialog(this, "Entrada financeira adicionada com sucesso!");
            
        }else if (botaoGanho.isSelected()){

            LocalDate dataEntrada = LocalDate.parse(inputData.getText());

            EntradaFinanceiraController controller = new EntradaFinanceiraController();
            controller.adicionarEntrada(nome, classificacao, valor, dataEntrada);

            
            JOptionPane.showMessageDialog(this, "Entrada financeira adicionada com sucesso!");

        }if(!botaoGasto.isSelected() && !botaoGanho.isSelected()){
            JOptionPane.showMessageDialog(this, "Selecione se é um gasto ou ganho");
        } 
        
        carregarDadosNaTabela();
        atualizarTotais();
        
    }//GEN-LAST:event_botaoCadastroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastro;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JToggleButton botaoGanho;
    private javax.swing.JToggleButton botaoGasto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> inputClassificacao;
    private javax.swing.JTextField inputData;
    private javax.swing.JTextField inputNome;
    private javax.swing.JTextField inputvalor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel valorDiferenca;
    private javax.swing.JLabel valorGastos;
    private javax.swing.JLabel valorRecebido;
    // End of variables declaration//GEN-END:variables
}
