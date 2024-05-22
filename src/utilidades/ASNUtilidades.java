/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import bbdd.ASNConexion;
import static bbdd.ASNConexion.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import modelo.ASNCita;

/**
 *
 * @author sebas
 */
public class ASNUtilidades {
    public static boolean comprobarDni(String campo) {
        try {
            String consulta = "SELECT dniCliente from cliente where dni=?";
            PreparedStatement pst = conn.prepareStatement(consulta);
            ResultSet rs;
            pst.setString(1, campo);
            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
