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

/**
 *
 * @author sebas
 */
public class ASNConexion {

    public static Connection conn;

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://145.14.151.1/u812167471_asnconsultorio";
            String username = "u812167471_reservasSebas";
            String password = "2024-reservasSebas";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean acceder(String user, String pass) {

        String SQL = "select usuario, contraseña from empleados "
                + "where usuario=? and contraseña=?";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);

            ResultSet rs;

            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public static String[] recuperaDatosUserLogado(String user) {
        return null;
    }
    
    public static void recuperaCitasMedicas (DefaultTableModel modelo) {
    }
    
    public static void recuperaCitasEnfermería (DefaultTableModel modelo) {
    }
    
    public static boolean registrarCitaMedica(ASNCita c) {
        return false;
    }
    
    public static boolean registrarCitaEnfermeria(ASNCita c) {
        return false;
    }
    
    public static boolean compruebaDni (String dni) {
        return false;
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
    
    
    public static void cargarListaTutores(JComboBox listaTutores) {
        String SQL = "select asnistor_apellidos || ',' || asnistor_nombre as tutor from asnistor_Profesores";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                listaTutores.addItem(rs.getString("tutor"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
