/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gerar
 */
public class MiembroJurado {
    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String perfil;
    private ArrayList<Evaluacion> evaluaciones;

    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfil, ArrayList<Evaluacion> evaluaciones) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.perfil = perfil;
        this.evaluaciones = evaluaciones;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    // Comportamientos adicionales
    
    public static MiembroJurado nextMiembroJurado(Scanner sc) {
        
        return new MiembroJurado();
    }
    
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(this.id + "," + this.nombres + "," + this.apellidos + "," + this.telefono + "," + this.email + "," + this.perfil + this.evaluaciones);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<MiembroJurado> readFile(String nomFile) {
        ArrayList<MiembroJurado> miembrosJurado = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id,nombres,apellidos,telefono,email,perfil,evaluaciones
                String[] datos = linea.split(","); //Eliminamos el punto y hacemos el split
                miembrosJurado.add( new MiembroJurado(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], new ArrayList<Evaluacion>()) ) ;
                /*
                Para el argumento "evaluaciones" que debe ser una lista de evaluaciones, realizar consulta
                El archivo de documento debería entregar una dirección de memoria?
                */
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return miembrosJurado;
    }
    
    /*
    public static void saveFile(ArrayList<Vector2D> vectores, String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            for (Vector2D v : vectores) pw.println(v.x + "," +v.y + ".");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    */
}
