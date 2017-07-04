package br.com.gamenopoteejb.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	
	/**
	 * Método responsável por criar mensagens de Informações
	 * @param idComponent
	 * @param message
	 */
	public static void addInfoMessages(String idComponent, String message) {
		FacesContext.getCurrentInstance().addMessage(idComponent, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	/**
	 * Método responsável por criar mensagens de erro 
	 * @param idComponent
	 * @param message
	 */
	public static void addErrorMessages(String idComponent, String message) {
		FacesContext.getCurrentInstance().addMessage(idComponent, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

}
