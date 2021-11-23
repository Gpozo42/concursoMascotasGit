/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.concursomascotas;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.Criterio;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author gerar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        ArrayList<MiembroJurado> miembrosJurado = MiembroJurado.readFile("miembroJurados.txt");
        ArrayList<Inscripcion> inscripciones = Inscripcion.readFile("inscripciones.txt");
        ArrayList<Criterio> criterios = Criterio.readFile("criterios.txt"); //Criterio no se encuentra inicilizada aún 
        
        // Falta de corregir, hacer uso de isNumeric
        do {
            System.out.println("Opciones: ");
            System.out.println("1. Dueño\n2. Mascota\n3. Concurso\n4. Premio\n5. Criterio\n6. Incripción\n7. MiembroJurado\n8. Evaluación");
            opcion = sc.nextInt(); //Esto debe ser nextLine, puesto asi para seguir probando el código
            
            // if -> Debemos validar si es numerico, con una entrada String
            /*
            Ejemplo:
            nextLine -> Obtiene una "L", algo que no entra en un nextInt. El programa cae
            Con un if, validar si es dato enteramente numerico, caso contrario devolver un -1 que permita que el While siga funcionando
            */
            
        } while(opcion > 0 && opcion < 9);
        
        switch (opcion){ //Recibimos un int de la validacion anterior, un switch case es rapido y efectivo
            case 1: //Dueño
                break;
            case 2: //Mascota
                break;
            case 3: //Concurso
                break;
            case 4: //Premio
                break;
            case 5: //Criterio
                break;
            case 6: //Incripción
                break;
            case 7: //MiembroJurado
                MiembroJurado miembroJurado = MiembroJurado.nextMiembroJurado(sc);
                miembroJurado.saveFile("miembroJurados.txt");
                break;
            default: //Evaluacion
                String emailJurado;
                int idInscripcion;
                int criterioEvaluar;
                double notaEvaluacion;
                
                do {
                    System.out.println("Ingrese su mail de jurado");
                    emailJurado = sc.nextLine();
                    System.out.println("Ingrese el id de Inscripcion");
                    idInscripcion = sc.nextInt();
                    System.out.println("Ingrese el criterio a evaluar");
                    criterioEvaluar = sc.nextInt();
                    System.out.println("Ingrese la nota de evaluacion");
                    notaEvaluacion = sc.nextDouble();
                } while(validacionDatosEvaluacion(emailJurado, idInscripcion, criterioEvaluar, notaEvaluacion, miembrosJurado, inscripciones, criterios));
                
                recepcionDatos(emailJurado, idInscripcion, criterioEvaluar, notaEvaluacion);
                break;
        }
        
    }
    
    // Adicionales
    public static boolean validacionDatosEvaluacion(String emailJurado, int idInscripcion, int criterioEvaluar, double notaEvaluacion, ArrayList<MiembroJurado> miembrosJurado, ArrayList<Inscripcion> inscripciones, ArrayList<Criterio> criterios) {
        boolean jurado = false;
        boolean inscripcion = false;
        boolean criterio = false;
        boolean evaluacion = false;
        
        for (MiembroJurado mj : miembrosJurado) if ( Objects.equals(emailJurado, mj.getEmail()) ) jurado = true;
        for (Inscripcion i : inscripciones) if (idInscripcion == i.getId()) inscripcion = true;
        for (Criterio c : criterios) if(criterioEvaluar == c.getId()) criterio = true;
        if (notaEvaluacion >= 0) evaluacion = true;
        return jurado && inscripcion && criterio && evaluacion;
    }
    
    
    
    
    public static Evaluacion recepcionDatos(String emailJurado, int idInscripcion, int criterioEvaluar, double notaEvaluacion) {
        //
        // Diseñada para el case "Evaluacion"
        //
        int id = 0;
        MiembroJurado miembroJuradoFiltrado = null;
        Inscripcion inscripcionFiltrada = null;
        Criterio criterioFiltrado = null;
        
        for (MiembroJurado mj : miembrosJurado) if ( Objects.equals(emailJurado, mj.getEmail()) ) miembroJuradoFiltrado = mj;
        for (Inscripcion i : inscripciones) if (idInscripcion == i.getId()) inscripcionFiltrada = i;
        for (Criterio c : criterios) if(criterioEvaluar == c.getId()) criterioFiltrado = c;
        
        return new Evaluacion(id, idInscripcion, inscripcionFiltrada, miembroJuradoFiltrado.getId(), miembroJuradoFiltrado, notaEvaluacion, criterioEvaluar, criterioFiltrado);
        
    }
}
