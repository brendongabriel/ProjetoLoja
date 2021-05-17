package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	private Scanner entrada = new Scanner(System.in);
	private ListaProduto listaProduto;
	private ProdutoModel produto;
	private Connection connection;

	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		PreparedStatement preparedStatement;

		produto = new ProdutoModel();
		listaProduto = new ListaProduto();

		int id, index;

		if (listaProduto.listarProdutos() == (null)) {
			return null;
		}

		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		id = entrada.nextInt();

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return null;
			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDeProduto"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		System.out.println("--- CAMPOS ---");
		System.out.println("Informe o campo que deseja editar: ");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		index = entrada.nextInt();

		switch (index) {
		case 1:
			editarNomeDoProduto(id);
			break;
		case 2:
			editarPrecoDoProduto(id);
			break;
		case 3:
			editarQuatidadeDoProduto(produtos, id);
			break;
		default:
			System.out.println("Opção inválida!!");
			break;
		}
		return produto;
	}

	private ProdutoModel editarQuatidadeDoProduto(List<ProdutoModel> produtos, int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.println("Informe a nova quantidade para o produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		try {
			String sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? "
					+ " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private ProdutoModel editarPrecoDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.println("Informe o novo preço para o produto: ");
		produto.setPrecoDoProduto(entrada.nextDouble());
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());

		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? " + " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(entrada.next());
		try {
			String sql = "UPDATE produto SET nomeDoProduto = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
