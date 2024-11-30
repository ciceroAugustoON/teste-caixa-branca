/**
 * Pacote que contém a classe para manipulação de login de usuários.
 */
package caixaBranca.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A classe User contém as informações do usuário e métodos para execução de login e conexão com o banco de dados.
 */
public class User {

    /**
     * Estabelece uma conexão com o banco de dados.
     * 
     * @return Um objeto {@link Connection} representando a conexão com o banco de dados, ou {@code null} se a conexão falhar.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Exceção suprimida
        }
        return conn;
    }

    /**
     * Nome do usuário autenticado. É preenchido após a verificação bem-sucedida.
     */
    public String nome = "";

    /**
     * Indica o resultado da verificação do login.
     * {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean result = false;

    /**
     * Verifica se o usuário e a senha fornecidos correspondem a um registro no banco de dados.
     * 
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        // Instrução SQL
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            // Exceção suprimida
        }
        return result;
    }
}
