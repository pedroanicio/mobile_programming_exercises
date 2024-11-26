# Para rodar a aplicação:

1: Crie o Banco de dados, Mysql:

````
CREATE DATABASE financas_seu_jose;
USE financas_seu_jose;

CREATE TABLE entrada (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
classificacao ENUM('CASA', 'COMPRAS', 'SAUDE', 'AUTOMOVEL', 'ALIMENTACAO', 'BEM_ESTAR', 'INVESTIMENTO', 'SALARIO', 'OUTROS'),
valor DECIMAL(10, 2),
data_entrada DATE,
data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
````

2: Configurar o config.properties de acordo com suas credenciais

3: Iniciar o Menu e utilizar a aplicação.
