package br.com.gamenopoteejb.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *Classe responsável por criar a tabela/entidade Estado no banco de dados.
 * @author rodrigo.pereira
 */
@Entity
public class Estado implements Serializable {

	private static final long serialVersionUID = 7740707977290689327L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 2, nullable = false)
	private String sigla;
	
//	@OneToMany(mappedBy = "Estado", targetEntity = Cidade.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Cidade> cidades;
	
	/**
	 * Métodos getters e setters
	 * @return
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
