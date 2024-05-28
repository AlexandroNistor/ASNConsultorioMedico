/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author sebas
 */
public class ASNCita {

    public ASNCita(String dniPaciente, String nombre, Date dia, double hora) {
        this.dniPaciente = dniPaciente;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }
    
    String dniPaciente;
    String nombre;
    Date dia;
    double hora;

    @Override
    public String toString() {
        return "<h2>-----------Datos de la cita---------------</h2>" 
                +"\n\nDni: <b>" + this.dniPaciente + "</b>"
                + "\nNombre: <b>" + this.nombre + "</b>" 
                + "\nDia: <b>" + this.dia + "</b>"
                + "\nHora: <b>" + this.hora + "0" + "</b>"
                + "<h2>--------------------------------------------------------</h2>"
                + "<br/><br/><img src= http://reynaldomd.com/firmacorreo/firmacorreo.png>"
                + "<br/><br/>Has recibido este email porque has solicitado una cita en el centro médico. \nPor favor, no responda a este correo electronico: ha sido generado automáticamente.";
    }
    
    
    
}

