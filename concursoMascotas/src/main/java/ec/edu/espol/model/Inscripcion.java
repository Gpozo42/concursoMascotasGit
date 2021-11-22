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
public class Inscripcion {
    private int id;
    private int idMascota;
    private Mascota mascota;
    private int idConcurso;
    private Concurso concurso;
    private double valor;
    private double descuento;
    private ArrayList<Evaluacion> evaluaciones;

    public Inscripcion(int id, int idMascota, Mascota mascota, int idConcurso, Concurso concurso, double valor, double descuento, ArrayList<Evaluacion> evaluaciones) {
        this.id = id;
        this.idMascota = idMascota;
        this.mascota = mascota;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
        this.valor = valor;
        this.descuento = descuento;
        this.evaluaciones = evaluaciones;
    }

    public int getId() {
        return id;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public double getValor() {
        return valor;
    }

    public double getDescuento() {
        return descuento;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(this.id + "," + this.idMascota + "," + this.mascota + "," + this.idConcurso + "," + this.concurso + "," + this.valor + "," + this.descuento + "," + this.evaluaciones);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<Inscripcion> readFile(String nomFile) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id,nombres,apellidos,telefono,email,perfil,evaluaciones
                String[] datos = linea.split(","); //Eliminamos el punto y hacemos el split
                inscripciones.add( new Inscripcion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), (Mascota) datos[2], Integer.parseInt(datos[3]), (Concurso) datos[4], Double.parseDouble(datos[5]), Double.parseDouble(datos[6]), new ArrayList<Evaluacion>()) ) ;
                /*
                Para el argumento "evaluaciones" que debe ser una lista de evaluaciones, realizar consulta
                Los argumentos para Mascota y Concurso deben ser transformados
                El archivo de documento debería entregar una dirección de memoria?
                */
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return inscripciones;
    }
}
