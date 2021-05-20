package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class CadastrarCliente {
	Scanner entrada = new Scanner(System.in);
	ProdutoModel produtoModel;
	private Connection connection;

	public CadastrarCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void cadastrarCliente() {

		System.out.println("\n--- CADASTRAR CLIENTE ---\n");
		System.out.print("Nome: ");
		String nome = entrada.next();
		System.out.print("Senha: ");
		String senha = entrada.next();
		
		try {
			String sql = "INSERT INTO usuarios VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, senha);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}

	}

}
