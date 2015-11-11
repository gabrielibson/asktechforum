package asktechforum.negocio;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	private static final String USR = "asktechforum@gmail.com";
	private static final String PWD = "webasktech";

	public void sendMail(String senhaNova, String nome,
			String destinatario) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USR, PWD);
			}
		});

		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("Prezado(a), "+nome+",\n\n");
			buffer.append("Informamos abaixo, os dados necessários para que você acesse o nosso sistema.\n\n");
			buffer.append("Login: ");
			buffer.append(destinatario + "\n");
			buffer.append("Senha: " + senhaNova + "\n\n\n");
			buffer.append("Caso não tenha solicitado seus dados, favor desconsiderar.\n\n\n");
			buffer.append("Atenciosamente,\n\n");
			buffer.append("Equipe Ask TechForum.\n");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject("Email para Recuperação De Senha -- Ask TechForum");
			message.setText(buffer.toString());

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void sendEmailAutor(String nome,String destinatario, String tituloPergunta){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USR, PWD);
			}
		});
		
		try {
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("Prezado(a), "+nome+",\n\n");
			buffer.append("Informamos que a pergunta '"+tituloPergunta+ "' a qual você está relacionado recebeu uma nova resposta. Acesse nosso fórum e cheque as novas mensagens.\n\n");
			buffer.append("Atenciosamente,\n\n");
			buffer.append("Equipe Ask TechForum.\n");

			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject("Email de Aviso: Você recebeu uma nova resposta");
			message.setText(buffer.toString());

			Transport.send(message);
			
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}