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
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author gerar
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private double nota;
    private int idCriterio;
    private Criterio criterio;

    public Evaluacion(int id, int idInscripcion, Inscripcion inscripcion, int idMiembroJurado, MiembroJurado miembroJurado, double nota, int idCriterio, Criterio criterio) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.inscripcion = inscripcion;
        this.idMiembroJurado = idMiembroJurado;
        this.miembroJurado = miembroJurado;
        this.nota = nota;
        this.idCriterio = idCriterio;
        this.criterio = criterio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idIncripcion) {
        this.idInscripcion = idIncripcion;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getIdMiembroJurado() {
        return idMiembroJurado;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return miembroJurado;
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        this.miembroJurado = miembroJurado;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
    
    public static Evaluacion nextEvaluacion(Scanner sc) {
        String emailJurado;
        int idInscripcion;
        int criterioEvaluar;
        double notaEvaluacion;
        MiembroJurado mjFiltrado = null;
        Inscripcion inscripcionFiltrada = null;
        Criterio criterioFiltrado = null;
        
        ArrayList<Inscripcion> inscripciones = Inscripcion.readFile("inscripciones.txt");
        ArrayList<MiembroJurado> miembroJurados = MiembroJurado.readFile("miembroJurados.txt");
        ArrayList<Criterio> criterios = Criterio.readFile("criterios.txt");

                
        System.out.println("Ingrese su mail de jurado");
        emailJurado = sc.next();
        System.out.println("Ingrese el id de Inscripcion");
        idInscripcion = sc.nextInt();
        System.out.println("Ingrese el criterio a evaluar");
        criterioEvaluar = sc.nextInt();
        System.out.println("Ingrese la nota de evaluacion");
        notaEvaluacion = sc.nextDouble();
        
        for (MiembroJurado mj : miembroJurados) if (Objects.equals(emailJurado, mj.getEmail())) mjFiltrado = mj;
        for (Inscripcion i : inscripciones) if (idInscripcion == i.getId()) inscripcionFiltrada = i;
        for (Criterio c : criterios) if(criterioEvaluar == c.getId()) criterioFiltrado = c;
        
        return new Evaluacion(Util.nextID("evaluaciones.txt"), idInscripcion, inscripcionFiltrada, mjFiltrado.getId(), mjFiltrado, notaEvaluacion, criterioFiltrado.getId(), criterioFiltrado);
        
    }
    
    public static boolean validacionDatosEvaluacion(String emailJurado, int idInscripcion, int criterioEvaluar, double notaEvaluacion, ArrayList<MiembroJurado> miembrosJurado, ArrayList<Inscripcion> inscripciones, ArrayList<Criterio> criterios) {
        boolean jurado = false;
        boolean inscripcion = false;
        boolean criterio = false;
        boolean evaluacion = false;

        for (MiembroJurado mj : miembrosJurado) {
            if (Objects.equals(emailJurado, mj.getEmail())) {
                jurado = true;
            }
        }
        for (Inscripcion i : inscripciones) {
            if (idInscripcion == i.getId()) {
                inscripcion = true;
            }
        }
        for (Criterio c : criterios) {
            if (criterioEvaluar == c.getId()) {
                criterio = true;
            }
        }
        if (notaEvaluacion >= 0) {
            evaluacion = true;
        }
        return jurado && inscripcion && criterio && evaluacion;
    }
    
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(this.toString());
            // Solo se guardan los Ids, los objetos son totalmente innecesarios de guardar
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<Evaluacion> readFile(String nomFile) {
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id|idInscripcion|idMiembroJurado|nota|idCriterio
                String[] datos = linea.split("\\|"); //Eliminamos | y hacemos el split
                evaluaciones.add( new Evaluacion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), null, Integer.parseInt(datos[2]), null, Double.parseDouble(datos[3]), Integer.parseInt(datos[4]), null) );
                /*
                Los argumentos con null, serán reemplazados por los objetos dependiendo de su id. Esto se elabora en el Main con los setters
                */
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return evaluaciones;
    }

    @Override
    public String toString() {
        return this.id + "\\|" + this.idInscripcion + "\\|" + this.idMiembroJurado + "\\|" + this.nota + "\\|" + this.idCriterio;
    }
}
