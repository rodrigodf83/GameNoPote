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
import br.com.gamenopoteejb.util.Messages;
import br.com.gamenopoteejb.util.ValidadorDeEmail;

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
			converter(getPessoa(), getEndereco());
			setaEndereco(getEndereco());
			if (isPessoaValida(getPessoa())) {
				
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
	public void setaEndereco(Endereco endereco) {
		if (isEnderecoValido(endereco)) {
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
	public void novo() {
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

	/**
	 * Método responsável por retirar caracteres especiais de atributos
	 * 
	 * @param pessoa
	 * @param endereco
	 */
	public void converter(Pessoa pessoa, Endereco endereco) {
		getPessoa().setCpf(pessoa.getCpf().replaceAll("\\.", "").replaceAll("\\-", ""));
		getPessoa().setCelular(pessoa.getCelular().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "")
				.replaceAll(" ", ""));
		getPessoa().setTelefone(pessoa.getTelefone().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "")
				.replaceAll(" ", ""));
		getEndereco().setCep(endereco.getCep().replaceAll("\\-", "").replaceAll("\\.", ""));
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
	
	/**
	 * Método responsável por validar os dados informados pelo usuário para a pessoa.
	 * @param pessoa
	 * @return
	 */
	public Boolean isPessoaValida(Pessoa pessoa) {

		if (pessoa.getNome() == null || pessoa.getNome().equals("")) {
			return false;
		} else if (pessoa.getEmail() == null || pessoa.getEmail().equals("")) {
			return false;
		} else if (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().equals("")) {
			return false;
		} else if (!ValidadorDeEmail.isEmailValido(pessoa.getEmail())) {
			Messages.addErrorMessages(":msg", "O email é inválido!");
			return false;
		} else if (pessoa.getEndereco() == null) {
			return false;
		}

		return true;
	}
	
	/**
	 * Método resoponsável por validar os dados informados pelo usuário para o endereço da pessoa.
	 * @param endereco
	 * @return
	 */
	public Boolean isEnderecoValido(Endereco endereco) {

		if (endereco.getLogradouro() == null || endereco.getLogradouro().equals("")) {
			return false;
		} else if (endereco.getNumero() == null || endereco.getNumero().equals("")) {
			return false;
		} else if (endereco.getBairro() == null || endereco.getBairro().equals("")) {
			return false;
		} else if (endereco.getCidade() == null || endereco.getCidade().equals("")) {
			return false;
		} else if (endereco.getCep() == null || endereco.getCep().equals("")) {
			return false;
		}
		return true;
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
