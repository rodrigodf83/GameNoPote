package br.com.gamenopoteejb.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository<Entidade> implements Serializable {

	private static final long serialVersionUID = -6240949091492253868L;

	private Class<Entidade> classe;

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.classe = (Class<Entidade>) ((ParameterizedType)getClass().getGenericSuperclass()) //ParameterizedType importado do reflect
				.getActualTypeArguments()[0];
	}

	
	/**
	 * Método responsável por adicionar um objeto no banco de dados
	 * @param entidade
	 * @throws Exception
	 */
	public void adiciona(Entidade entidade) throws Exception {

		getManager().persist(entidade);

	}

	/**
	 * Método responsável por atualizar as informações de um objeto no banco de dados
	 * @param entidade
	 * @throws Exception
	 */
	public void atualiza(Entidade entidade) throws Exception {

		getManager().merge(entidade);

	}
	
	/**
	 * Método responsável por remocer um objeto do banco de dados.
	 * @param entidade
	 * @throws Exception
	 */
	public void remove(Entidade entidade) throws Exception {

		getManager().remove(entidade);

	}
	
	/**
	 * Método responsável por buscar um objeto no banco através do id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object buscaPorId(Long id) throws Exception {

		return getManager().find(getClasse(), id);

	}

	/**
	 * Método padrão responsável por listar objetos sem restrições
	 * @return
	 * @throws Exception
	 */
	public List<Entidade> lista() throws Exception {

		return getManager().createQuery("select o from Object o", getClasse()).getResultList();

	}

	/*
	 * Métodos Getters e Setters
	 */
	public EntityManager getManager() {
		return manager;
	}

	public Class<Entidade> getClasse() {
		return classe;
	}

}
