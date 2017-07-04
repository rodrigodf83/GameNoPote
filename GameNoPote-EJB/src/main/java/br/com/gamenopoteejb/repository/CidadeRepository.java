package br.com.gamenopoteejb.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.gamenopoteejb.modelo.Cidade;

public class CidadeRepository {

	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por buscar uma cidade no banco através do ID
	 * 
	 * @param id
	 * @return
	 */
	public Cidade buscaPorId(Long id) {
		return getManager().find(Cidade.class, id);
	}

	/**
	 * Método responsável por listar todas as cidades sem restrições
	 * 
	 * @return
	 */
	public List<Cidade> lista() {
		return getManager().createQuery("select c from Cidade c", Cidade.class).getResultList();
	}

	/**
	 * Método responsável por listar todos as cidade em ordem alfabética
	 * 
	 * @return
	 */
	public List<Cidade> listaEmOrdemAlfabetica() {
		return getManager().createQuery("select c from Cidade c order by c.nome asc", Cidade.class).getResultList();
	}
	
	/**
	 * Método que lista as cidades de acordo com o estado a qual pertencem
	 * @param estadoId
	 * @return
	 */
	public List<Cidade> listaCidadePorEstado(Long estadoId) {
		return getManager()
				.createQuery("select c from Cidade c where c.estado.id = :pEstadoId order by c.nome asc", Cidade.class)
				.setParameter("pEstadoId", estadoId).getResultList();
	}
	

	/*
	 * Métodos Getters e Setters
	 */
	public EntityManager getManager() {
		return manager;
	}
}
