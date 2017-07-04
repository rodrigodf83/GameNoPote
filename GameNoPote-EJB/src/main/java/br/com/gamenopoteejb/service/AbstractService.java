package br.com.gamenopoteejb.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.gamenopoteejb.repository.AbstractRepository;

@Stateless
public class AbstractService<Entidade> implements Serializable{
	
	
	private static final long serialVersionUID = 2220185979397245113L;

	
	@Inject
	private AbstractRepository repository;
	
	/**
	 * Método que acessa o método de adição no repositório
	 * @param object
	 * @throws Exception
	 */
	public void adiciona(Object object) throws Exception {
		
		getRepository().adiciona(object);
		
	}
	
	/**
	 * Método que acessa o método de edição no repositório
	 * @param object
	 * @throws Exception
	 */
	public void atualiza(Object object) throws Exception {
		
		getRepository().atualiza(object);
		
	}
	
	/**
	 * Método que acessa o método de exclusão no repositório
	 * @param object
	 * @throws Exception
	 */
	public void remove(Object object) throws Exception {
		
		getRepository().remove(object);
		
	}
	
	/**
	 * Método que acessa o método de busca por ID no repositório
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object buscarPorId(Long id) throws Exception {
		
		return getRepository().buscaPorId(id);
		
	}
	
	/**
	 * Método que acessa o método que lista objetos no repositório  sem restrição
	 * @return
	 * @throws Exception
	 */
	public List<Entidade> lista() throws Exception {
		
		return getRepository().lista();
		
	}
	
	/*
	 * Métodos getters e setters
	 */
	public AbstractRepository getRepository() {
		return repository;
	}

	public void setRepository(AbstractRepository repository) {
		this.repository = repository;
	}

}
