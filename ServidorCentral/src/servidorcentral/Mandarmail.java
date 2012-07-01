package servidorcentral;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mandarmail {

    public static void postMail(String recipient, String subject,
            String message, String from) {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "vzlatransito@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage mime = new MimeMessage(session);
            mime.setFrom(new InternetAddress(from));

            mime.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(recipient));

            mime.setSubject(subject);
            mime.setText(message);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("vzlatransito@gmail.com", "3papasalvapor");
            t.sendMessage(mime, mime.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
