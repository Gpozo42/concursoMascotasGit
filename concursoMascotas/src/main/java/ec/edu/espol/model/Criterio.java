/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;

/**
 *
 * @author gerar
 */
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Criterio> evaluaciones;
    private int idConcurso;
    private Concurso concurso;

    public Criterio(int id, String descripcion, ArrayList<Criterio> evaluaciones, int idConcurso, Concurso concurso) {
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = evaluaciones;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Criterio> getEvaluaciones() {
        return evaluaciones;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }
    
    
}
