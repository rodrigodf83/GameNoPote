package br.com.gamenopoteejb.modelo;

public enum TipoDeUsuario {
	
	ADMINISTRADOR("Admnistrador"), AUTOR("autor");
	
	private String tipo;
	
	private TipoDeUsuario(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
}
