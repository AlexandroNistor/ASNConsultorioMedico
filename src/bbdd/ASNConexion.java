/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import modelo.ASNCita;
import modelo.ASNConsulta;
import modelo.ASNConsultaEnfermeria;
import modelo.ASNPaciente;
import modelo.ASNPersonal;
import utilidades.ASNEncriptado;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


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

        String SQL = "SELECT usuario, contrasenya FROM personal "
                + "WHERE usuario=? AND contrasenya=?";

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
            datos[0] = rs.getString("NOMBRE_COMPLETO");
            datos[1] = Integer.toString(rs.getInt("NUMERO_COLEGIADO"));
            datos[2] = rs.getString("TIPO");
            return datos;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
    public static void ASNrecuperaCitasMedicas (DefaultTableModel modelo) throws Exception {
        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consulta = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA "
                    + " from citas where dia = curdate()";

            ResultSet rs = conn.createStatement().executeQuery(consulta);

            while (rs.next()) {
                datos[0] = ASNEncriptado.desencriptar(rs.getString("NOMBRE"));
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
    
    public static void ASNrecuperaCitasEnfermeria (DefaultTableModel modelo) throws Exception {
        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consulta = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA"
                    + " from citasEnfermeria where dia = curdate()";

            ResultSet rs = conn.createStatement().executeQuery(consulta);

            while (rs.next()) {
                datos[0] = ASNEncriptado.desencriptar(rs.getString("NOMBRE"));
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
    
    public static boolean ASNregistrarCitaMedica(ASNCita c) throws Exception {
        try {
            String consulta = "INSERT INTO citas(dniPaciente, nombre , dia, hora) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consulta);

            pst.setString(1, ASNEncriptado.encriptar(c.getDniPaciente()));
            pst.setString(2, ASNEncriptado.encriptar(c.getNombre()));
            pst.setDate(3, new java.sql.Date(c.getDia().getTime()));
            pst.setDouble(4, c.getHora());

            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean ASNregistrarCitaEnfermeria(ASNCita c) throws Exception {
         try {
            String consulta = "INSERT INTO citasEnfermeria(dniPaciente, nombre , dia, hora) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consulta);

            pst.setString(1, ASNEncriptado.encriptar(c.getDniPaciente()));
            pst.setString(2, ASNEncriptado.encriptar(c.getNombre()));
            pst.setDate(3, new java.sql.Date(c.getDia().getTime()));
            pst.setDouble(4, c.getHora());

            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean ASNcompruebaDni (String dni) {
        String SSQL = "SELECT dni FROM paciente WHERE dni =?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(SSQL);

            pst.setString(1, dni);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    public static ASNPaciente ASNrecuperaDatosPaciente (String dni) {
        
        String consultaRecuperaTipo = "SELECT nombre, apellidos, telefono, email "
                + "from paciente where dni='" + dni + "'";

        ASNPaciente p = null;

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaRecuperaTipo);

            if (rs.next()) {
                p = new ASNPaciente(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("telefono"),
                        rs.getString("email")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public static void ASNcargaTablaConsultasMedicas (DefaultTableModel modelo, String dni) throws Exception {
        try {
            String SSQL = "SELECT fechaConsulta as FECHA, diagnostico as DIAGNOSTICO, tratamiento as TRATAMIENTO, observaciones as OBSERVACIONES FROM consultas WHERE dniPaciente = '" + dni + "'";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            while (rs.next()) {
                Object[] consultaDgonzalez = new Object[4];

                consultaDgonzalez[0] = rs.getString("FECHA");
                consultaDgonzalez[1] = rs.getString("DIAGNOSTICO");
                consultaDgonzalez[2] = rs.getString("TRATAMIENTO");
                consultaDgonzalez[3] = rs.getString("OBSERVACIONES");

                modelo.addRow(consultaDgonzalez);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
        
   
    public static void ASNcargaTablaConsultasEnfermeria (DefaultTableModel modelo, String dni) {
        try {
            String SSQL = "SELECT fechaConsulta as FECHA, tensionMax as MAXIMA, tensionMin as MINIMA, glucosa as GLUCOSA, peso as PESO  FROM enfermeria WHERE dniPaciente = '" + dni + "'";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            while (rs.next()) {
                Object[] consultaDgonzalez = new Object[5];

                consultaDgonzalez[0] = rs.getString("FECHA");
                consultaDgonzalez[1] = rs.getString("MAXIMA");
                consultaDgonzalez[2] = rs.getString("MINIMA");
                consultaDgonzalez[3] = rs.getString("GLUCOSA");
                consultaDgonzalez[4] = rs.getString("PESO");

                modelo.addRow(consultaDgonzalez);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean ASNregistrarConsultaMedica(ASNConsulta c) {
    try {
        String consulta = "INSERT INTO consultas(dniPaciente, fechaConsulta, diagnostico, tratamiento, observaciones, codigofacultativo) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = conn.prepareStatement(consulta);

        
        java.sql.Date sqlDate = java.sql.Date.valueOf(c.getFechaConsulta());
        
        
        pst.setString(1, c.getDniPaciente());
        pst.setDate(2, sqlDate);
        pst.setString(3, c.getDiagnostico());
        pst.setString(4, c.getTratamiento());
        pst.setString(5, c.getObservaciones());
        pst.setInt(6, c.getCodigoFacultativo());

        pst.execute();
        return true; // Asumiendo que quieres retornar true si todo salió bien

    } catch (SQLException ex) {
        Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    
    public static boolean ASNregistrarConsultaEnfermeria (ASNConsultaEnfermeria c) {
        try {
            String consulta = "INSERT INTO enfermeria(dniPaciente, fechaConsulta , tensionMax, tensionMin, glucosa, peso, codigoFacultativo) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consulta);

            pst.setString(1, c.getDniPaciente());
            pst.setDate(2, new java.sql.Date(c.getFechaConsulta().getTime()));
            pst.setDouble(3, c.getMaxima());
            pst.setDouble(4, c.getMinima());
            pst.setInt(5, c.getGlucosa());
            pst.setDouble(6, c.getPeso());
            pst.setInt(7, c.getCodigoFacultativo());

            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void ASNcagasComboCp (JComboBox combo) {
    }
    
    public static boolean ASNregistrarPaciente (ASNPaciente p) {
        return false;
    }
    
    public static void ASNcargaTablaPacientes (DefaultTableModel modelo) {
    }
    
    public static boolean ASNcompruebaUser (String user) {
        return false;
    }
    
    public static boolean ASNcompruebaNumeroColegiado (long numero) {
        return false;
    }
    
    public static boolean ASNregistrarPersonal (ASNPersonal p) {
        return false;
    }}

