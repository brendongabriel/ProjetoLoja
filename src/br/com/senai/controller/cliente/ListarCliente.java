package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListarCliente {
	private Connection connection;

	public ListarCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarClientes() {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from usuarios ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %15s | %15s |\n", "Nome", "Senha");

			if (!resultSet.next()) {
				System.out.println("Não possui dados escolhido");
				return null;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| %15s | %15s |\n", resultSet.getString("nome"), resultSet.getString("senha"));
			}
			return resultSet;

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return null;
		}

	}
}
