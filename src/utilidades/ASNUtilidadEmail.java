/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ASNCita;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author sebas
 */
public class ASNUtilidadEmail {
    
    public static void ASNEnviaMailHtml(ASNCita cita, String emailEnviado) {

        try {
            HtmlEmail email = new HtmlEmail();

            email.setHostName("smtp.hostinger.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("consultorio@reynaldomd.com", "2024-Consultorio"));
            email.setSSLOnConnect(true);

            email.setCharset("UTF-8");
            email.setFrom("consultorio@reynaldomd.com");
            email.setSubject("Correo enviado automáticamente.");

            email.setMsg(cita.toString());
            /*  reynaldomunoz@gmail.com  */
            email.addTo("sebastian.alex220@gmail.com");
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(ASNUtilidadEmail.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, verifique que esté todo correcto.");
        }
    }

}
