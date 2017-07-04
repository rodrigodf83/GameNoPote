package br.com.gamenopoteejb.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gamenopoteejb.modelo.Estado;
import br.com.gamenopoteejb.repository.EstadoRepository;

@Stateless
public class EstadoService {
	
	@Inject
	private EstadoRepository respository;
	
	/**
	 * Método que acessa o método de busca por Id no repository
	 * @param id
	 * @return
	 */
	public Estado buscaPorId(Long id) {
		return getRespository().buscaPorId(id);
	}
	
	/**
	 * Método que acessa a lista de estados sem restrições
	 * @return
	 */
	public List<Estado> lista() {
		return getRespository().lista();
	}
	
	/**
	 * Método que acessa a lista de estados ordenados alfabeicamente
	 * @return
	 */
	public List<Estado> listaEmOrdemAlfabetica() {
		return getRespository().listaEmOrdemAlfabetica();
	}
	
	/*
	 * Métodos Getters e Setters
	 */
	public EstadoRepository getRespository() {
		return respository;
	}
}
