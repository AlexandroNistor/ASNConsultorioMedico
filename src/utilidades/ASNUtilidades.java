/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import bbdd.ASNConexion;
import static bbdd.ASNConexion.conn;
import java.awt.Component;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.ASNCita;

/**
 *
 * @author sebas
 */
public class ASNUtilidades {

    /*VARIABLES*/
    /*/////////*/
    
    static String patronTelefonoFijo = "^[98]{1}[0-8]{1}[0-9]{7}$";
    static String patronTelefonoMovil = "^[5-6-7]{1}[0-9]{8}$";

    /* COMPROBACIÓN DE DNI EXISTENTE EN LA BASE DE DATOS*/
    /*//////////////////////////////////////////////////*/
    
    public static boolean ASNComprobarDni(String campo) {
        // Comprueba si el DNI existe en la base de datos
        try {
            String consultaSQL = "SELECT dniCliente FROM cliente "
                    + "WHERE dni=?";
            PreparedStatement pst = conn.prepareStatement(consultaSQL);
            ResultSet rs;
            pst.setString(1, campo);
            rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /* TODAS LAS UTILIDADES CON DNI*/
    /*/////////////////////////////*/
    
    public static boolean ASNFormatoDniCorrecto(JTextField campo) {
        // Verifica si el formato del DNI es correcto
        String modelo = "^[0-9]{8}[A-HJ-NP-Z]{1}$";
        return campo.getText().toUpperCase().matches(modelo);
    }

    public static void ASNLanzaAlertaFormatoDni(Component c, JTextField campo) {
        // Muestra una alerta si el formato del DNI no es correcto
        JOptionPane.showMessageDialog(c, "El formato del DNI no es el correcto, compruebelo y vuelvalo a intentar.");
    }

    public static boolean ASNLetraDniCorrecta(JTextField campo) {
        // Verifica si la letra del DNI es correcta
        int dni = Integer.parseInt(campo.getText().substring(0, 8));
        int resto = dni % 23;
        char letra = campo.getText().toUpperCase().charAt(8);
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letra == letras.charAt(resto);
    }

    public static void ASNLanzaAlertaLetraDni(Component c, JTextField campo) {
        // Muestra una alerta si la letra del DNI no es correcta
        JOptionPane.showMessageDialog(c, "La letra no corresponde a un formato de DNI correcto, verifíquelo.");
    }

    /* TODAS LAS UTILIDADES CON CAMPOS*/
    /*////////////////////////////////*/
    
    public static boolean ASNCompruebaCampoVacio(JTextField campo) {
        // Comprueba si el campo de texto está vacío
        return campo.getText().trim().isEmpty();
    }

    public static void ASNLanzaAlertaCampoVacio(JTextField campo) {
        // Muestra una alerta si el campo de texto está vacío
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es obligatorio, compruebelo y vuelvalo a intentar.");
    }

    public static boolean ASNCampoEnteroCorrecto(JTextField campo) {
        // Comprueba si el campo contiene un entero válido
        try {
            Integer.valueOf(campo.getText());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void ASNLanzaAlertaCampoEntero(JTextField campo) {
        // Muestra una alerta si el campo no contiene un entero válido
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es numérico, verifíquelo.");
    }

    public static boolean ASNCampoDoubleCorrecto(JTextField campo) {
        // Comprueba si el campo contiene un valor double válido
        try {
            String texto = campo.getText();
            double numero = Double.parseDouble(texto);
            if (numero > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public static void ASNLanzaAlertaCampoDouble(JTextField campo) {
        // Muestra una alerta si el campo no contiene un valor double válido
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es numérico, verifíquelo.");
    }

    public static boolean ASNComboNoSeleccionado(JComboBox combo) {
        // Comprueba si no se ha seleccionado una opción en el JComboBox
        return combo.getSelectedIndex() == 0;
    }

    public static void ASNLanzaAlertaComboNoSeleccioando(Component inicial, JComboBox combo) {
        // Muestra una alerta si no se ha seleccionado una opción en el JComboBox
        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción: " + combo.getName());
    }

    public static boolean ASNCompruebaTextAreaVacio(JTextArea campo) {
        // Comprueba si el área de texto está vacía
        return campo.getText().trim().isEmpty();
    }

    public static void ASNLanzaAlertaTextAreaVacio(JTextArea campo) {
        // Muestra una alerta si el área de texto está vacía
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es obligatorio, compruebelo y vuelvalo a intentar.");
    }

    public static boolean ASNFormatoCampoTelefono(JTextField campo) {
        // Comprueba si el formato del teléfono es correcto
        return !campo.getText().matches(patronTelefonoFijo) && !campo.getText().matches(patronTelefonoMovil);
    }

    /* TODAS LAS UTILIDADES CON FORMATOS*/
    /*//////////////////////////////////*/
    
    public static void ASNLanzaAlertaFormatoCampoTelefono(Component c, JTextField campo) {
        // Muestra una alerta si el formato del teléfono no es correcto
        JOptionPane.showMessageDialog(c, "El teléfono no se corresponde con el formato establecido, verifíquelo.");
    }

    public static void ASNLanzaAlertaFormatoCampoRangoTelefono(Component c, JTextField campo) {
        // Muestra una alerta si el teléfono excede el rango establecido
        JOptionPane.showMessageDialog(c, "El teléfono excede el rango de caracteres establecido, verifíquelo.");
    }

    public static boolean ASNFormatoEmailCorrecto(JTextField campo) {
        // Comprueba si el formato del email es correcto
        String modeloEmail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.com";
        return campo.getText().matches(modeloEmail);
    }

    public static void ASNLanzaAlertaEmail(Component c, JTextField campo) {
        // Muestra una alerta si el formato del email no es correcto
        JOptionPane.showMessageDialog(c, "El correo no esta escrito de la manera correcta, compruebelo y vuelvalo a intentar.");
    }

    public static boolean ASNFormatoNumeroColegiadoValido(String numeroColegiado) {
        // Comprueba si el número de colegiado es válido
        if (numeroColegiado.length() != 9) {
            return false;
        }
        if (numeroColegiado.matches("^[1-9][0-9]{8}$")) {
            return true;
        }
        return false;
    }
}
