package br.com.gamenopoteejb.util;

import br.com.gamenopoteejb.modelo.Endereco;

public class ValidadorDeEndereco {
	
	/**
	 * Método resoponsável por validar os dados informados pelo usuário para o endereço da pessoa.
	 * @param endereco
	 * @return
	 */
	public static Boolean isEnderecoValido(Endereco endereco) {

		if (endereco.getLogradouro() == null || endereco.getLogradouro().equals("")) {
			return false;
		} else if (endereco.getNumero() == null || endereco.getNumero().equals("")) {
			return false;
		} else if (endereco.getBairro() == null || endereco.getBairro().equals("")) {
			return false;
		} else if (endereco.getCidade() == null || endereco.getCidade().equals("")) {
			return false;
		} else if (endereco.getCep() == null || endereco.getCep().equals("")) {
			return false;
		}
		return true;
	}

}
