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
import java.util.Scanner;
/**
 *
 * @author isaac
 */
public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fecha;//fecha del concurso
    private LocalDate fechaIncripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private double costo;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterios;

    public Concurso(int id, String nombre, String tematica, double costo,LocalDate Fecha ,LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = this.fecha;
        this.fechaIncripcion=fechaInscripcion;
        this.fechaIncripcion = fechaIncripcion;
        this.tematica = tematica;
        this.costo = costo;
        this.inscripciones=new ArrayList<>();
        this.premios=new ArrayList<>();
        this.criterios=new ArrayList<>();
    }
    
    //getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDate getFechaIncripcion() {
        return fechaIncripcion;
    }
    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }
    public String getTematica() {
        return tematica;
    }
    public double getCosto() {
        return costo;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setFechaIncripcion(LocalDate fechaIncripcion) {
        this.fechaIncripcion = fechaIncripcion;
    }
    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        this.fechaCierreInscripcion = fechaCierreInscripcion;
    }
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }
    //FUNCIONES
    public String toString(){
        return this.id+"|"+this.nombre+"|"+this.tematica+"|"+this.costo+"|"+this.fecha+"|"+this.fechaIncripcion+"|"+this.fechaCierreInscripcion;
    }
    
    //funciones para llenar los atributos de lista
    public void ConcursoPremios(){
        premios=Premio.readFile("premios.txt");
    }
    public void ConcursoInscripciones(){
        inscripciones=Inscripcion.readFile("inscripciones.txt");
    }
    public void ConcursoCriterios(){
        criterios=Criterio.readFile("inscripciones.txt");
    }
    
    
    //ARCHIVOS ESCRITURA
    public void saveFile(String archivo){//esta en append 
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(archivo), true))){
            pw.println(this.id+"|"+this.nombre+"|"+this.tematica+"|"+this.costo+"|"+this.fecha+"|"+this.fechaIncripcion+"|"+this.fechaCierreInscripcion);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
    //ARCHIVOS LECTURA
    public static ArrayList<Concurso> readFile(String archivo){
        ArrayList<Concurso> listaConcurso=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(archivo))){
           while(sc.hasNextLine()){//mientras exista la sguiente linea
               String linea=sc.nextLine();
               String[] datos=linea.split("|");
               Concurso c=new Concurso(Integer.parseInt(datos[0]), datos[1], datos[2], Double.parseDouble(datos[3]),LocalDate.parse(datos[4]),LocalDate.parse(datos[5]),LocalDate.parse(datos[6]));//se crea un objeto concurso
               listaConcurso.add(c);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return listaConcurso;
    }
    
    public static Concurso anexarNombrePremio(String nombre){//verifica si el id de la clase concurso es igual a idCOcurso
        ArrayList<Concurso> lista=Concurso.readFile("concursos.txt");
        for (Concurso c: lista){
            if(c.nombre.equals(nombre)){
               return c; 
               }
            }
        
        return null;
    } 
    public static int anexarIdPremio(String nombre){
        Concurso k=Concurso.anexarNombrePremio(nombre);
        return k.id;
    }
    
}
