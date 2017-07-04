package br.com.gamenopoteejb.repositories;

import javax.inject.Inject;

import org.junit.Test;

import br.com.gamenopoteejb.modelo.Estado;
import br.com.gamenopoteejb.service.AbstractService;

public class AbstractRepositoryTest {

	@Inject
	private AbstractService service;

	@Test
	public void salva() throws Exception {
		Estado estado = new Estado();

		estado.setNome("Distrito Federal");
		estado.setSigla("DF");

		service.adiciona(estado);
	}

}
