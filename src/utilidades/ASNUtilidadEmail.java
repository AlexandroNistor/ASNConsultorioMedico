/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author sebas
 */

/*
public class ASNUtilidadEmail {
    public void actualizar() {
        DatosASNistor datosASNistor = new DatosASNistor(
                campoNombre.getText(),
                campoApellidos.getText(),
                Integer.parseInt(comboCp.getSelectedItem().toString()));

        ConexionASNistor.conectar();

        if (ConexionASNistor.actualizaDatos(datosASNistor, campoDni.getText())) {
            JOptionPane.showMessageDialog(this, "Datos Actualizados Correctamente");
            modelo.setRowCount(0);

            ConexionASNistor.cargarDatos(modelo);

            ConexionASNistor.cerrarConexion();
            enviaMailHtml(datosASNistor);

        } else {
            JOptionPane.showMessageDialog(this, "Datos No Actualizados :C");
        }
    }*/
/*
    public void enviaMailHtml(DatosASNistor datos) {

        try {
            HtmlEmail email = new HtmlEmail();

            email.setHostName("smtp.hostinger.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("consultorio@reynaldomd.com", "2024-Consultorio"));
            email.setSSLOnConnect(true);

            email.setCharset("UTF-8");
            email.setFrom("consultorio@reynaldomd.com");
            email.setSubject("Correo enviado automáticamente");

            email.setMsg(datos.toString());

            email.addTo("sebastian.alex220@gmail.com");
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(DatosActualizar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha habido un error al enviar el correo electrónico");
        }

    }
} */
