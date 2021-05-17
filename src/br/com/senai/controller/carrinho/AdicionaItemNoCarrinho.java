package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	Scanner entrada = new Scanner(System.in);
	private Connection connection;

	CarrinhoModel carrinhoModel;
	ListaProduto listaProduto;
	EditaProduto editaProduto;

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos, String cliente) {
		PreparedStatement preparedStatement;
		carrinhoModel = new CarrinhoModel();
		int idDoProduto = 0;
		listaProduto = new ListaProduto();
		editaProduto = new EditaProduto();

		if (listaProduto.listarProdutos() == null) {
			System.out.println("Não há produtos cadastrados.");
			return null;
		}

		try {
			System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
			System.out.print("Informe o ID do produto: ");
			idDoProduto = entrada.nextInt();
			String sql = "select * from produto where codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return null;
			}
			System.out.print("Informe a quantidade desejada:");
			int quantidadeComprada = entrada.nextInt();

			if (resultSet.getInt("quantidadeDeProduto") < quantidadeComprada) {
				System.out.println("Este produto não possui toda essa quantidade.");
				return null;
			}

			String inserir = "INSERT INTO carrinho VALUES (?, ?, ?, ?, ?,?)";
			String nomeDoProduto = resultSet.getString("nomeDoProduto");
			double precoDoProduto = resultSet.getDouble("precoDoProduto");
			double saldoEmEstoque = resultSet.getDouble("precoDoProduto") * quantidadeComprada;

			preparedStatement = connection.prepareStatement(inserir);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setString(2, nomeDoProduto);
			preparedStatement.setDouble(3, precoDoProduto);
			preparedStatement.setInt(4, quantidadeComprada);
			preparedStatement.setDouble(5, saldoEmEstoque);
			preparedStatement.setString(6, cliente);
			preparedStatement.execute();

			int newQuantidade = resultSet.getInt("quantidadeDeproduto") - quantidadeComprada;
			Double precoFim = resultSet.getDouble("precoDoProduto") * newQuantidade;

			String update = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? "
					+ " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(update);
			preparedStatement.setInt(1, newQuantidade);
			preparedStatement.setDouble(2, precoFim);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("Deu ruim");
			return null;
		}

		return carrinhoModel;
	}

}
