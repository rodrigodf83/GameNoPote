package br.com.gamenopoteejb.repository;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.gamenopoteejb.modelo.Estado;

public class EstadoRepository {
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por buscar um Estado no banco de dados através do id;
	 * @param id
	 * @return
	 */
	public Estado buscaPorId(Long id) {
		return getManager().find(Estado.class, id);
	}
	
	/**
	 * Método responsável por listar todos os estados sem restrições
	 * @return
	 */
	public List<Estado> lista() {
		return getManager().createQuery("select e from Estado e", Estado.class).getResultList();
	}
	
	/**
	 * Método responsável por retornar uma lista de todos os estados em ordem alfabética
	 * @return
	 */
	public List<Estado> listaEmOrdemAlfabetica() {
		return getManager().createQuery("select e from Estado e order by e.nome asc", Estado.class).getResultList();
	}
	
	/*
	 * Métodos Getters e Setters
	 */
	public EntityManager getManager() {
		return manager;
	}
	
}
