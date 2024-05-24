/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelo.ASNCita;
import modelo.ASNConsulta;
import modelo.ASNConsultaEnfermeria;
import modelo.ASNPaciente;
import modelo.ASNPersonal;
import utilidades.ASNEncriptado;

/**
 *
 * @author sebas
 */
public class ASNConexion {

    public static Connection conn;

    public static Connection ASNconectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://145.14.151.1/u812167471_consultorioDaw";
            String username = "u812167471_consultorioDaw";
            String password = "2024-Daw";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void ASNcerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static boolean ASNacceder(String user, String pass) throws Exception  {

        String SQL = "select usuario, contrasenya from personal "
                + "where usuario=? and contrasenya=?";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);

            ResultSet rs;
            
            pst.setString(1, user);
            pst.setString(2, ASNEncriptado.encriptar(pass));

            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public static String[] ASNrecuperaDatosUserLogado(String usuario) {
        try {
        String[] datos = new String[3];
        String consulta = "SELECT CONCAT(nombre, ' ', apellidos) AS nombre_completo, numero_colegiado, tipo FROM personal WHERE usuario=?";
        PreparedStatement pst = conn.prepareCall(consulta);
        pst.setString(1, usuario);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            datos[0] = rs.getString("nombre_completo");
            datos[1] = Integer.toString(rs.getInt("numero_colegiado"));
            datos[2] = rs.getString("tipo");
            return datos;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
    public static void recuperaCitasMedicas (DefaultTableModel modelo) {
        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consulta = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA"
                    + " from citas";

            ResultSet rs = conn.createStatement().executeQuery(consulta);

            while (rs.next()) {
                datos[0] = rs.getString("NOMBRE");
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);
                
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ASNcerrarConexion();
    }
    
    public static void recuperaCitasEnfermeria (DefaultTableModel modelo) {
        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consulta = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA"
                    + " from citasEnfermeria";

            ResultSet rs = conn.createStatement().executeQuery(consulta);

            while (rs.next()) {
                datos[0] = rs.getString("NOMBRE");
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);
                
            }  
        } 
        catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ASNcerrarConexion();
    }
    
    public static boolean registrarCitaMedica(ASNCita c) {
        return false;
    }
    
    public static boolean registrarCitaEnfermeria(ASNCita c) {
        return false;
    }
    
    public static String compruebaDni (String dni) {
        return null;
    }
    
    public static ASNPaciente recuperaDatosPaciente (String dni) {
        return null;
    }
    
    public static void cargaTablaConsultasMedicas (DefaultTableModel modelo, String dni) {
    }
    
    public static void cargaTablaConsultasEnfermeria (DefaultTableModel modelo, String dni) {
    }
    
    public static boolean registrarConsultaMedica (ASNConsulta c) {
        return false;
    }
    
    public static boolean registrarConsultaEnfermeria (ASNConsultaEnfermeria c) {
        return false;
    }
    
    public static void cagasComboCp (JComboBox combo) {
    }
    
    public static boolean registrarPaciente (ASNPaciente p) {
        return false;
    }
    
    public static void cargaTablaPacientes (DefaultTableModel modelo) {
    }
    
    public static boolean compruebaUser (String user) {
        return false;
    }
    
    public static boolean compruebaNumeroColegiado (long numero) {
        return false;
    }
    
    public static boolean registrarPersonal (ASNPersonal p) {
        return false;
    }
    
    
    
    
    
    
    
    
    
    

    public static boolean registrarProfesor(ASNCita p) {
        try {
            String SQL = "insert into asnistor_Profesores (asnistor_nombre, asnistor_apellidos, asnistor_ciudad, asnistor_ciclo) "
                    + "VALUES(?,?,?)";

            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setString(1, p.getDniPaciente());
            pst.setString(2, p.getNombre());
            pst.setDate(3, (Date) p.getDia());

            pst.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
