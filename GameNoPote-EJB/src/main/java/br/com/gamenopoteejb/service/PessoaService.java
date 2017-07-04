package br.com.gamenopoteejb.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gamenopoteejb.modelo.Pessoa;
import br.com.gamenopoteejb.repository.PessoaRepository;

@Stateless
public class PessoaService implements Serializable {

	private static final long serialVersionUID = -2841936078429600831L;
	
	@Inject
	private PessoaRepository repository;
	
	/**
	 * método que acessa o método de adição do repository
	 * @param pessoa
	 */
	public void adiciona(Pessoa pessoa) {
		this.repository.adiciona(pessoa);
	}
	
	/**
	 * Método que acessa o método de atualização do objeto
	 * @param pessoa
	 */
	public void atualiza(Pessoa pessoa) {
		getRepository().atualiza(pessoa);
	}
	
	/**
	 * Método que acessa o método de exclusão do objeto no banco
	 * @param pessoa
	 */
	public void remove(Pessoa pessoa) {
		getRepository().remove(pessoa);
	}
	
	/**
	 * Método que acessa a busca da cidade pelo id
	 * @param id
	 * @return
	 */
	public Pessoa buscaPorId(Long id) {
		return getRepository().buscaPorId(id);
	}
	
	/**
	 * Método que acessa a lista de cidade sem restrições
	 * @return
	 */
	public List<Pessoa> lista() {
		return getRepository().lista();
	}
	
	/**
	 * Método que acessa a lista de cidades em ordem alfabética
	 * @return
	 */
	public List<Pessoa> listaEmOrdemAlfabetica(){
		return getRepository().listaEmOrdemAlfabetica();
	}

	
	/*
	 *Métodos Getters e Setters
	 */
	public PessoaRepository getRepository() {
		return repository;
	}
	
}
