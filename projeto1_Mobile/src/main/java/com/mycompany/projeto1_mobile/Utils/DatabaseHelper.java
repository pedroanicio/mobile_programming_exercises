package com.mycompany.projeto1_mobile.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DatabaseHelper() {
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o arquivo config.properties");
                return;
            }
            // Carregando as propriedades do arquivo
            prop.load(input);
            dbUrl = prop.getProperty("db.url");
            dbUser = prop.getProperty("db.user");
            dbPassword = prop.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws Exception {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public List<String[]> buscarDados() {
        List<String[]> dados = new ArrayList<>();
        try (Connection conn = conectar()) {

            String sql = "SELECT id, nome, classificacao, valor, data_entrada, data_cadastro FROM entrada";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Recuperando dados da consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String classificacao = rs.getString("classificacao");
                double valor = rs.getDouble("valor");
                String data = rs.getString("data_entrada");
                String dataCadastro = rs.getString("data_cadastro");

                // Adicionando os dados como uma linha (array de String)
                dados.add(new String[]{String.valueOf(id), nome, classificacao, String.valueOf(valor), data, dataCadastro});
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dados;
    }

    public double calcularTotalRecebido() {
        double totalRecebido = 0;
        try (Connection conn = conectar()) {
            String sql = "SELECT SUM(valor) FROM entrada WHERE valor > 0";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalRecebido = rs.getDouble(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRecebido;
    }

    public double calcularTotalGasto() {
        double totalGasto = 0;
        try (Connection conn = conectar()) {
            String sql = "SELECT SUM(valor) FROM entrada WHERE valor < 0";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalGasto = rs.getDouble(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalGasto;
    }
}
