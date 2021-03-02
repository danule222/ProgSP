package controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Controlador de los envíos de correos electrónicos.
 * @author Daniel Ramírez Morilla
 */
public class Mail {
	
	private static String mailer = Config.getMailer();
	private static String contrasenna = Config.getContrasennaMailer();
	private static String mailEmergencia = Config.getEmailEmergencia();

	/**
	 * Envía un correo.
	 * @param para Destinatario.
	 * @param asunto Asunto del correo.
	 * @param mensaje Cuerpo/contendio del correo.
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void enviarCorreo(String para, String asunto, 
			String mensaje)
			throws AddressException, MessagingException {
		
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
				return new PasswordAuthentication(mailer, contrasenna);
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
		message.setSubject(asunto);
		message.setText(mensaje);
		
		Transport.send(message);
		System.out.println("Correo enviado.");
	}

	/**
	 * Envía un correo a la dirección especificada en el archivo de configuración
	 * avisando de la falta de stock del producto con el ID indicado.
	 * @param ID_Producto ID del producto sin stock.
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void faltaStock(String Nombre_Producto, Double Precio_Producto)
			throws AddressException, MessagingException {
		enviarCorreo(mailEmergencia, "AVISO | Sin stock de producto",
				"Falta stock de " + Nombre_Producto + ". Precio: "
				+ Precio_Producto + "€.");
	}

}
