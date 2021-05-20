package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListaProduto;

public class RemoverCliente {
	private Connection connection;

	Scanner entrada = new Scanner(System.in);
	ListarCliente listaCliente = new ListarCliente();

	public RemoverCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void deletaCliente() {
		PreparedStatement preparedStatement;
		if (listaCliente.listarClientes() == null) {
			return;
		}

		try {
			System.out.println("Insira o nome do cliente que você deseja excluir");
			String nomeExcluir = entrada.next();
			String sql2 = "select * from usuarios where nome = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, nomeExcluir);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Nao existe com esse nome");
				return;
			}
			resultSet.previous();

			String sql = "delete from usuarios where nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nomeExcluir);
			preparedStatement.execute();
			System.out.println("Cliente excluido");

		} catch (Exception e) {
			e.printStackTrace();			
			return;
		}

	}
}
