package br.com.gamenopoteejb.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gamenopoteejb.modelo.Cidade;
import br.com.gamenopoteejb.repository.CidadeRepository;

@Stateless
public class CidadeService {
	
	@Inject
	private CidadeRepository repository;
	
	/**
	 * Método que acessa a busca da cidade pelo id
	 * @param id
	 * @return
	 */
	public Cidade buscaPorId(Long id) {
		return getRepository().buscaPorId(id);
	}
	
	/**
	 * Método que acessa a lista de cidade sem restrições
	 * @return
	 */
	public List<Cidade> lista() {
		return getRepository().lista();
	}
	
	/**
	 * Método que acessa a lista de cidades em ordem alfabética
	 * @return
	 */
	public List<Cidade> listaEmOrdemAlfabetica(){
		return getRepository().listaEmOrdemAlfabetica();
	}
	
	/**
	 * Método acessa lista das cidades de acordo com os seus estados
	 * @param estadoId
	 * @return
	 */
	public List<Cidade> listaCidadePorEstado(Long estadoId) {
		return getRepository().listaCidadePorEstado(estadoId);
	}
	
	/*
	 * Métodos Getters e Setters
	 */
	public CidadeRepository getRepository() {
		return repository;
	}
}
