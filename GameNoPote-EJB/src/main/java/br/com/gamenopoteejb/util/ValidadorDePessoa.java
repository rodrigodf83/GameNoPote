package br.com.gamenopoteejb.util;

import br.com.gamenopoteejb.modelo.Pessoa;

public class ValidadorDePessoa {
	
	/**
	 * Método responsável por validar os dados informados pelo usuário para a pessoa.
	 * @param pessoa
	 * @return
	 */
	public static Boolean isPessoaValida(Pessoa pessoa) {

		if (pessoa.getNome() == null || pessoa.getNome().equals("")) {
			return false;
		} else if (pessoa.getEmail() == null || pessoa.getEmail().equals("")) {
			return false;
		} else if (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().equals("")) {
			return false;
		} else if (!ValidadorDeEmail.isEmailValido(pessoa.getEmail())) {
			Messages.addErrorMessages(":msg", "O email é inválido!");
			return false;
		} else if (pessoa.getEndereco() == null) {
			return false;
		}

		return true;
	}

}
