/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.concursomascotas;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.Menu;
import ec.edu.espol.model.Premio;
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

        // Falta de corregir, hacer uso de isNumeric
        //Validación de ingreso previo al menú
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
        } while (opcion > 0 && opcion < 9);
        
        Menu.menuInicial(opcion);
        
        sc. close();
        
    }    
    
    /*
    // Adicionales
    public static boolean validacionDatosInscripcion(String nombreMascota, String nombreConcurso, ArrayList<Mascota> mascotas, ArrayList<Concurso> concursos) {
        boolean nombreDeMascota = false;
        boolean nombreDeConcurso = false;
        for (Mascota m : mascotas) {
            if (Objects.equals(nombreMascota, m.getNombre())) {
                nombreDeMascota = true;
            }
        }
        for (Concurso c : concursos) {
            if (Objects.equals(nombreConcurso, c.getNombre())) {
                nombreDeConcurso = true;
            }
        }

        return nombreDeMascota && nombreDeConcurso;
    }
    */
}
