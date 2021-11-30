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
 * @author isaac
 */
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;

    public Criterio(int id, String descripcion, int idConcurso, Concurso concurso, ArrayList<Evaluacion> evaluaciones) {
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }
    //getters
    public int getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    public int getIdConcurso() {
        return idConcurso;
    }
    public Concurso getConcurso() {
        return concurso;
    }
    
    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }
    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    //funciones
    public StringBuilder recorrerEvaluaciones(){
        StringBuilder sb=new StringBuilder();
        for (Evaluacion e: this.evaluaciones){
            sb.append(e);
            sb.append(",");
             }
        sb.delete(sb.length()-1,sb.length()-1);
        return sb;
    }
    
    public void saveFile(String archivo){//esta en modo a(para añadir)
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(archivo), true))){
            pw.println(this.id+","+this.descripcion+","+","+this.idConcurso+this.concurso+","+this.recorrerEvaluaciones());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveFile(ArrayList<Criterio> criterios,String archivo){//modo w(sobreescribir)
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(archivo)))){
           for(Criterio c: criterios){
               pw.println(c.id+","+c.descripcion+","+c.idConcurso+","+c.concurso+","+c.recorrerEvaluaciones());
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //ARCHIVOS LECTURA
    public static ArrayList<Criterio> readFile(String archivo){
        ArrayList<Criterio> criterios=new ArrayList<>();//creo una lista de criterios
        try(Scanner sc=new Scanner(new File(archivo))){
           while(sc.hasNextLine()){//mientras exista la sguiente linea
               String linea=sc.nextLine();
               String[] datos=linea.split(",");
               Criterio cr=new Criterio(Integer.parseInt(datos[0]),datos[1], Integer.parseInt(datos[2]),);//se crea un objeto criterio
               criterios.add(cr);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return criterios;
    }
    
    
}
