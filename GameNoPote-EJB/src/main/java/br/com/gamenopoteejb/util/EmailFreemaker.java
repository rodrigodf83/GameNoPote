package br.com.gamenopoteejb.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailFreemaker {
	
	public void enviaEmail(String remetente, String destinatario, String assunto) throws EmailException {
		
		Email email = new SimpleEmail();
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthentication("rodrigo.gringodf@gmail.com", "ayrtonsenna94");
		email.setFrom(remetente);
		email.setSubject(assunto);
		email.setMsg("Teste de email");
		email.addTo(destinatario);
		email.send();
		
	}
	
	
}
