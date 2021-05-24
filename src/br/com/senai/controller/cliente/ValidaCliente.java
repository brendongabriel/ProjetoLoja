package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class ValidaCliente {

	Scanner entrada = new Scanner(System.in);
	private Connection connection;

	public ValidaCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public String validadarCliente() {
		System.out.println("------- MENU LOGIN -------");
		System.out.print("NOME: ");
		String nome = entrada.next();
		System.out.print("SENHA: ");
		String senha = entrada.next();
		
		try {
			String sql = "select * from usuarios where nome = ? and senha = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, senha);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Nome ou senha está errado, tente novamente");
				return null;
			}

			System.out.println("Login realizado com sucesso!");
			return nome;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}
}
