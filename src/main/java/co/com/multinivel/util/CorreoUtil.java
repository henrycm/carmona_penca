package co.com.multinivel.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CorreoUtil {
	public static void enviarCorreo(String subject, String mensaje) {
		enviarCorreo(CorreoEnum.CORREO_TO.getValor(), subject, mensaje);
	}

	public static void enviarCorreo(String direccionDestino, String subject, String mensaje) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", CorreoEnum.CORREO_HOST.getValor());
			props.setProperty("mail.smtp.starttls.enable", CorreoEnum.CORREO_ENABLE.getValor());
			props.setProperty("mail.smtp.port", CorreoEnum.CORREO_PORT.getValor());
			props.setProperty("mail.smtp.user", CorreoEnum.CORREO_USER.getValor());
			props.setProperty("mail.smtp.auth", CorreoEnum.CORREO_AUTH.getValor());

			Session session = Session.getDefaultInstance(props);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(CorreoEnum.CORREO_TO.getValor()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(direccionDestino));
			message.setSubject(subject);
			message.setText(mensaje);

			Transport t = session.getTransport("smtp");
			t.connect(CorreoEnum.CORREO_USER.getValor(), CorreoEnum.CORREO_CLAVE.getValor());
			t.sendMessage(message, message.getAllRecipients());

			t.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.util.CorreoUtil
 */