package br.com.gamenopoteejb.util;

import br.com.gamenopoteejb.modelo.Endereco;
import br.com.gamenopoteejb.modelo.Pessoa;

public class ConversorDeCaracteres {
	
	/**
	 * Método responsável por retirar caracteres especiais de atributos
	 * 
	 * @param pessoa
	 * @param endereco
	 */
	public static void converter(Pessoa pessoa, Endereco endereco) {
		pessoa.setCpf(pessoa.getCpf().replaceAll("\\.", "").replaceAll("\\-", ""));
		pessoa.setCelular(pessoa.getCelular().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "")
				.replaceAll(" ", ""));
		pessoa.setTelefone(pessoa.getTelefone().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "")
				.replaceAll(" ", ""));
		endereco.setCep(endereco.getCep().replaceAll("\\-", "").replaceAll("\\.", ""));
	}
	
}
