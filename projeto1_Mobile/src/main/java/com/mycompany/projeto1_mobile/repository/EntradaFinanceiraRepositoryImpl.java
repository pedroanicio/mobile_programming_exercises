package com.mycompany.projeto1_mobile.repository;

import com.mycompany.projeto1_mobile.model.EntradaFinanceira;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EntradaFinanceiraRepositoryImpl implements EntradaFinanceiraRepository {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

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

    private Connection getConnection() throws SQLException {
        carregarConfiguracoes();
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void salvar(EntradaFinanceira entrada) {
        String sql = "INSERT INTO entrada (nome, classificacao, valor, data_entrada, data_cadastro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, entrada.getNome());
            stmt.setString(2, entrada.getClassificacao());
            stmt.setDouble(3, entrada.getValor());
            stmt.setDate(4, Date.valueOf(entrada.getDataEntrada()));
            stmt.setDate(5, Date.valueOf(entrada.getDataCadastro()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EntradaFinanceira buscarPorId(int id) {
        String sql = "SELECT * FROM entrada WHERE id = ?";
        EntradaFinanceira entrada = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                entrada = new EntradaFinanceira();
                entrada.setId(rs.getInt("id"));
                entrada.setNome(rs.getString("nome"));
                entrada.setClassificacao(rs.getString("classificacao"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
                entrada.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entrada;
    }

    @Override
    public List<EntradaFinanceira> buscarTodas() {
        String sql = "SELECT * FROM entrada";
        List<EntradaFinanceira> entradas = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                EntradaFinanceira entrada = new EntradaFinanceira();
                entrada.setId(rs.getInt("id"));
                entrada.setNome(rs.getString("nome"));
                entrada.setClassificacao(rs.getString("classificacao"));
                entrada.setValor(rs.getDouble("valor"));
                entrada.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
                entrada.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());

                entradas.add(entrada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entradas;
    }

    @Override
    public void atualizar(EntradaFinanceira entrada) {
        String sql = "UPDATE entrada SET nome = ?, classificacao = ?, valor = ?, data_entrada = ?, data_cadastro = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, entrada.getNome());
            stmt.setString(2, entrada.getClassificacao());
            stmt.setDouble(3, entrada.getValor());
            stmt.setDate(4, Date.valueOf(entrada.getDataEntrada()));
            stmt.setDate(5, Date.valueOf(entrada.getDataCadastro()));
            stmt.setInt(6, entrada.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM entrada WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
