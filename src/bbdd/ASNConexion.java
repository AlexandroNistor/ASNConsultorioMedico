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

    public static void ASNCerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean ASNacceder(String user, String pass) throws Exception {

        String consultaSQL = "SELECT usuario, contrasenya FROM personal "
                + "WHERE usuario=? AND contrasenya=?";

        try {
            PreparedStatement pst = conn.prepareStatement(consultaSQL);

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
            String consultaSQL = "SELECT CONCAT(nombre, ' ', apellidos) AS nombre_completo, numero_colegiado, tipo FROM personal "
                    + "WHERE usuario=?";
            PreparedStatement pst = conn.prepareCall(consultaSQL);
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

    public static void ASNrecuperaCitasMedicas(DefaultTableModel modelo) throws Exception {

        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consultaSQL = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA FROM citas "
                    + "WHERE dia = curdate()";

            ResultSet rs = conn.createStatement().executeQuery(consultaSQL);

            while (rs.next()) {
                datos[0] = ASNEncriptado.desencriptar(rs.getString("NOMBRE"));
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ASNCerrarConexion();
    }

    public static void ASNrecuperaCitasEnfermeria(DefaultTableModel modelo) throws Exception {

        try {
            ASNconectar();
            Object[] datos = new Object[3];

            String consultaSQL = "SELECT nombre as NOMBRE, dia as DIA, hora as HORA FROM citas "
                    + "WHERE dia = curdate()";

            ResultSet rs = conn.createStatement().executeQuery(consultaSQL);

            while (rs.next()) {
                datos[0] = ASNEncriptado.desencriptar(rs.getString("NOMBRE"));
                datos[1] = rs.getString("DIA");
                datos[2] = rs.getString("HORA");

                modelo.addRow(datos);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ASNCerrarConexion();
    }

    public static boolean ASNRegistrarCitaMedica(ASNCita c) throws Exception {

        try {
            String consultaSQL = "INSERT INTO citas(dniPaciente, nombre , dia, hora) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

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

    public static boolean ASNRegistrarCitaEnfermeria(ASNCita c) throws Exception {

        try {
            String consultaSQL = "INSERT INTO citasEnfermeria(dniPaciente, nombre , dia, hora) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

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

    public static boolean ASNcompruebaDni(String dni) {

        String consultaSQL = "SELECT dni FROM paciente "
                + "WHERE dni =?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(consultaSQL);

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

    public static ASNPaciente ASNrecuperaDatosPaciente(String dni) {

        String consultaSQL = "SELECT nombre, apellidos, telefono, email FROM paciente "
                + "WHERE dni='" + dni + "'";

        ASNPaciente p = null;

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

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

    public static void ASNcargaTablaConsultasMedicas(DefaultTableModel modelo, String dni) throws Exception {

        try {
            String consultaSQL = "SELECT fechaConsulta as FECHA, diagnostico as DIAGNOSTICO, tratamiento as TRATAMIENTO, observaciones as OBSERVACIONES FROM consultas "
                    + "WHERE dniPaciente = '" + dni + "'";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

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

    public static void ASNcargaTablaConsultasEnfermeria(DefaultTableModel modelo, String dni) {

        try {
            String consultaSQL = "SELECT fechaConsulta as FECHA, tensionMax as MAXIMA, tensionMin as MINIMA, glucosa as GLUCOSA, peso as PESO  FROM enfermeria "
                    + "WHERE dniPaciente = '" + dni + "'";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

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
            String consultaSQL = "INSERT INTO consultas(dniPaciente, diagnostico, tratamiento, observaciones) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

            

            pst.setString(1, c.getDniPaciente());
            pst.setString(2, c.getDiagnostico());
            pst.setString(3, c.getTratamiento());
            pst.setString(4, c.getObservaciones());

            pst.execute();
            return true; // Asumiendo que quieres retornar true si todo salió bien

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean ASNregistrarConsultaEnfermeria(ASNConsultaEnfermeria c) {

        try {
            String consultaSQL = "INSERT INTO enfermeria(dniPaciente, fechaConsulta , tensionMax, tensionMin, glucosa, peso, codigoFacultativo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

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

    public static void ASNcargasComboCp(JComboBox combo) {

        String consultaSQL = "SELECT codigopostal FROM codigospostales";

        try {
            Statement st = conn.createStatement();
            try (ResultSet rs = st.executeQuery(consultaSQL)) {
                while (rs.next()) {
                    combo.addItem(rs.getString("codigopostal"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean ASNregistrarPaciente(ASNPaciente p) throws Exception {

        try {
            String consultaSQL = "INSERT INTO paciente(dni, nombre , apellidos, fechaNacimiento, telefono, email, cp, sexo, tabaquismo, consumoAlcohol,antecedentesSalud, datosSaludGeneral, fechaRegistro) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

            pst.setString(1, ASNEncriptado.encriptar(p.getDni()));
            pst.setString(2, ASNEncriptado.encriptar(p.getNombre()));
            pst.setString(3, ASNEncriptado.encriptar(p.getApellidos()));
            pst.setDate(4, new java.sql.Date(p.getFechaNacimiento().getTime()));
            pst.setInt(5, p.getTelefono());
            pst.setString(6, p.getEmail());
            /* */
            pst.setInt(7, p.getCp());
            pst.setString(8, p.getSexo());
            pst.setString(9, p.getTabaquismo());
            pst.setString(10, p.getConsumoAlcohol());
            pst.setString(11, p.getAntecedentesSalud());
            pst.setString(12, p.getDatosSaludGeneral());
            pst.setDate(13, new java.sql.Date(p.getFechaRegistro().getTime()));

            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void ASNcargaTablaPacientes(DefaultTableModel modelo) throws Exception {

        try {
            String consultaSQL = "SELECT dni as DNI, nombre as NOMBRE, apellidos as APELLIDOS, telefono as TELEFONO, cp as CP  FROM paciente";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);

            while (rs.next()) {
                Object[] consulta = new Object[5];

                consulta[0] = ASNEncriptado.desencriptar(rs.getString("DNI"));
                consulta[1] = ASNEncriptado.desencriptar(rs.getString("NOMBRE"));
                consulta[2] = ASNEncriptado.desencriptar(rs.getString("APELLIDOS"));
                consulta[3] = rs.getString("TELEFONO");
                consulta[4] = rs.getString("CP");

                modelo.addRow(consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean ASNcompruebaUser(String user) {

        String consultaSQL = "SELECT usuario FROM personal "
                + "WHERE usuario =?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(consultaSQL);

            pst.setString(1, user);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean ASNcompruebaNumeroColegiado(long numero) {

        ASNconectar();
        String consultaSQL = "SELECT numero_colegiado FROM personal "
                + "WHERE numero_colegiado =?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(consultaSQL);

            pst.setLong(1, numero);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static boolean ASNregistrarPersonal(ASNPersonal p) throws Exception {

        try {
            String consultaSQL = "INSERT INTO personal() "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

            pst.setLong(1, p.getNumero_colegiado());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getApellidos());
            pst.setInt(4, p.getTelefono());
            pst.setString(5, p.getUsuario());
            pst.setString(6, ASNEncriptado.encriptar(p.getContrasenya()));
            pst.setString(7, p.getTipo());

            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean ASNactualizarPaciente(ASNPaciente p, String dni) throws Exception {

        try {
            String consultaSQL = "UPDATE paciente SET nombre = ?, apellidos = ?, telefono = ?, cp = ? "
                    + "WHERE dni = ?";

            PreparedStatement pst = conn.prepareStatement(consultaSQL);

            pst.setString(1, ASNEncriptado.encriptar(p.getNombre()));
            pst.setString(2, ASNEncriptado.encriptar(p.getApellidos()));
            pst.setInt(3, p.getTelefono());
            pst.setInt(4, p.getCp());
            pst.setString(5, ASNEncriptado.encriptar(dni));

            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ASNConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
