package br.com.gamenopoteejb.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.gamenopoteejb.modelo.Pessoa;

public class PessoaRepository {
	
	@Inject
	private EntityManager manager;
	
	/**
	 * método responsável por criar um novo objeto no banco de dados
	 * @param pessoa
	 */
	public void adiciona(Pessoa  pessoa) {
		getManager().persist(pessoa);
	}
	
	/**
	 * Métod responsável por atualizar o objeto no banco com novos dados
	 * @param pessoa
	 */
	public void atualiza(Pessoa pessoa) {
		getManager().merge(pessoa);
	}
	
	/**
	 * Método responsável por exluir um objeto no banco de dados (Utilização não recomendada)
	 * @param pessoa
	 */
	public void remove(Pessoa pessoa) {
		getManager().remove(pessoa);
	}
	
	/**
	 * Método responsável por buscar uma pessoa no banco através do ID
	 * @param id
	 * @return
	 */
	public Pessoa buscaPorId(Long id) {
		return getManager().find(Pessoa.class, id);
	}
	
	/**
	 * Método responsável por listar todas as pessoas sem restrições
	 * @return
	 */
	public List<Pessoa> lista() {
		return getManager().createQuery("select p from Pessoa p", Pessoa.class).getResultList();
	}
	
	/**
	 * Método responsável por listar todas as pessoas em ordem alfabética
	 * @return
	 */
	public List<Pessoa> listaEmOrdemAlfabetica() {
		return getManager().createQuery("select p from Pessoa p order by p.nome asc", Pessoa.class).getResultList();
	}
		
	/*
	 * Métodos Getters e Setters
	 */
	public EntityManager getManager() {
		return manager;
	}

}
