package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class DeletaProduto {
	private Connection connection;

	Scanner entrada = new Scanner(System.in);
	ListaProduto listaProdutos = new ListaProduto();

	public DeletaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void removerProduto() {
		PreparedStatement preparedStatement;
		if (listaProdutos.listarProdutos() == null) {
			return;
		}

		try {
			System.out.println("Insira o ID do produto que voc� deseja excluir");
			int idProdutoExcluir = entrada.nextInt();
			String sql2 = "select * from produto where codigoDoProduto = " + idProdutoExcluir;
			preparedStatement = connection.prepareStatement(sql2);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Nao existe com esse codigo");
				return;
			}
			resultSet.previous();

			String sql = "delete from produto where codigoDoProduto = " + idProdutoExcluir;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println("Produto excluido");

		} catch (Exception e) {
			System.out.println("Erro");
			return;
		}

	}
}
