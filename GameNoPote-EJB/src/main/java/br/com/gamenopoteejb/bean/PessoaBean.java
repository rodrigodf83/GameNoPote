package br.com.gamenopoteejb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.gamenopoteejb.modelo.Cidade;
import br.com.gamenopoteejb.modelo.Endereco;
import br.com.gamenopoteejb.modelo.Estado;
import br.com.gamenopoteejb.modelo.Pessoa;
import br.com.gamenopoteejb.service.CidadeService;
import br.com.gamenopoteejb.service.EstadoService;
import br.com.gamenopoteejb.service.PessoaService;
import br.com.gamenopoteejb.util.ConversorDeCaracteres;
import br.com.gamenopoteejb.util.Messages;
import br.com.gamenopoteejb.util.ValidadorDeEmail;
import br.com.gamenopoteejb.util.ValidadorDeEndereco;
import br.com.gamenopoteejb.util.ValidadorDePessoa;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = -3608309107971482658L;

	private Pessoa pessoa;
	private Estado estado;
	private Endereco endereco;
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;

	@Inject
	private PessoaService pessoaService;
	@Inject
	private EstadoService estadoService;
	@Inject
	private CidadeService cidadeService;

	/**
	 * Método construtor
	 * 
	 * @author rodrigo.pereira
	 */
	public PessoaBean() {
		novo();
	}

	@PostConstruct
	public void iniciaListas() {
		listaPessoas();
		ListaEstados();
	}

	/**
	 * Método que lista todas as pessoas do banco sem restrições
	 * 
	 * @author rodrigo.pereira
	 */
	public void listaPessoas() {
		setPessoas(getPessoaService().lista());
	}

	/**
	 * método responsável por lista os estados que serão apresentados ao usuário
	 * em ordem alfabética
	 * 
	 * @author rodrigo.pereira
	 */
	public void ListaEstados() {
		setEstados(getEstadoService().listaEmOrdemAlfabetica());
	}

	/**
	 * método responsável por salvar uma nova pessoa no banco
	 * 
	 * @author rodrigo.pereira
	 */
	public void salva() {
		try {
			// validaCpf(getPessoa().getCpf());
			ConversorDeCaracteres.converter(getPessoa(), getEndereco());
			setaEndereco(getEndereco());
			if (ValidadorDePessoa.isPessoaValida(getPessoa())) {
				
				getPessoaService().adiciona(getPessoa());

//				EmailFreemaker email = new EmailFreemaker();
//				email.enviaEmail("rodrigo.grigodf@gmail.com", getPessoa().getEmail(), "Teste");

				listaPessoas();
				novo();
				Messages.addInfoMessages(":msg", "Cadastro realizado com sucesso!");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			Messages.addErrorMessages(":msg", "Não foi possível efetuar o cadastro!");
		}
	}

	/**
	 * Método responsável por setar as informações de endereço, na pessoa.
	 * 
	 * @author rodrigo.pereira
	 */
	private void setaEndereco(Endereco endereco) {
		if (ValidadorDeEndereco.isEnderecoValido(endereco)) {
			getPessoa().setEndereco(getEndereco());
		} else {
			Messages.addErrorMessages(":msg", "Todos os campos do Endereço com exceção do Complemento são obrigatórios!");
		}
	}

	/**
	 * método que instacia uma nova Pessoa, um novo estado
	 * 
	 * @author rodrigo.pereira
	 */
	private void novo() {
		setPessoa(new Pessoa());
		setEstado(new Estado());
		setEndereco(new Endereco());
	}

	/**
	 * Método responsável por listar as cidades de acordo com os estados
	 */
	public void listaCidadesPorEstado() {
		try {
			if (getEstado() != null) {
				setCidades(getCidadeService().listaCidadePorEstado(getEstado().getId()));
			} else {
				setCidades(new ArrayList<Cidade>());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addErrorMessages(":msg", "Ocorreu um erro ao filtrar as cidades do estado escolhido!");
		}
	}

	public void validaCpf(String cpf) {
		CPFValidator validator = new CPFValidator();
		try {
			validator.assertValid(cpf);
		} catch (InvalidStateException e) {
			e.printStackTrace();
			Messages.addErrorMessages(":msg", "O CPF informado é Iválido!");
		}
	}

	/*
	 * Métodos Getters e Setters
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public EstadoService getEstadoService() {
		return estadoService;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

}
