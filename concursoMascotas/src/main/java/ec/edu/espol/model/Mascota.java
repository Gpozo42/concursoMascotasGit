/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Gonzàlez
 */
public class Mascota 
{
    private int id;
    private String nombre;
    private String raza;
    private String tipo;
    private int idDueño;
    private Dueño dueño;   
    private LocalDate fechaNacimiento;
 
    

    public Mascota(int id, String nombre, String raza, String tipo, int idDueño, Dueño dueño, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.tipo = tipo;
        this.idDueño = idDueño;
        this.dueño = dueño;
        this.fechaNacimiento = fechaNacimiento;
 
        
    }
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", nombre=" + nombre + ", raza=" + raza + ", tipo=" + tipo + ", idDueño=" + idDueño + ", dueño=" + dueño + ", fechaNacimiento=" + fechaNacimiento +'}';
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(o.getClass() != this.getClass())
            return false;
        Persona other = (Persona)o;
        return Objects.equals(this.id, other.id);
    }
}
