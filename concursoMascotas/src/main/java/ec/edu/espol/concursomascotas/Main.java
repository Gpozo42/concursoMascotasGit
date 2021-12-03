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
import ec.edu.espol.model.Mascota;
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
        ArrayList<MiembroJurado> miembrosJurado = MiembroJurado.readFile("miembroJurados.txt");
        ArrayList<Inscripcion> inscripciones = Inscripcion.readFile("inscripciones.txt");
        ArrayList<Criterio> criterios = Criterio.readFile("criterios.txt"); //Criterio no se encuentra inicilizada aún 
        ArrayList<Mascota> mascotas = Mascota.readFile("mascotas.txt"); //Mascotas sin inicializar
        ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt"); //Concursos sin inicializar

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

        // Acciones del menú
        switch (opcion) { //Recibimos un int de la validacion anterior, un switch case es rapido y efectivo
            case 1: //Dueño
                break;
            case 2: //Mascota
                break;
            case 3: //Concurso
                Scanner sc3=new Scanner(System.in);
                sc3.useDelimiter("\n");
                (Concurso.nextConcurso(sc3)).saveFile("concursos.txt");
                break;
            case 4: //Premio
                Scanner sc4=new Scanner(System.in);
                sc4.useDelimiter("\n");
                Premio.nextPremio(sc4);
                break;
            case 5: //Criterio
                Scanner sc5=new Scanner(System.in);
                sc5.useDelimiter("\n");
                Criterio.nextCriterio(sc5);
                break;
            case 6: //Incripción
                int contador = 0;
                int numMascotas = 0;
                double pagoPorInscripcion = 0;
                ArrayList<Mascota> mascotasEnInscripcion = new ArrayList<>();
                System.out.println("Cuántas mascotas desea inscribir?");
                numMascotas = sc.nextInt();

                while (contador < numMascotas) {
                    String nombreMascota;
                    String nombreConcurso;

                    System.out.println("Ingrese el nombre de su mascota: ");
                    nombreMascota = sc.nextLine();
                    System.out.println("Ingrese el nombre del concurso a inscribirse: ");
                    nombreConcurso = sc.nextLine();

                    if (validacionDatosInscripcion(nombreMascota, nombreConcurso, mascotas, concursos)) {
                        for (Concurso c : concursos) {
                            if (Objects.equals(nombreConcurso, c.getNombre())) {
                                pagoPorInscripcion += c.getCosto();
                            }
                        }
                        for (Mascota m : mascotas) {
                            if (Objects.equals(nombreMascota, m.getNombre())) {
                                mascotasEnInscripcion.add(m);
                            }
                        }
                    }
                    contador++;
                }

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
                } while (validacionDatosEvaluacion(emailJurado, idInscripcion, criterioEvaluar, notaEvaluacion, miembrosJurado, inscripciones, criterios));

                recepcionDatos(emailJurado, idInscripcion, criterioEvaluar, notaEvaluacion);
                break;
        }

    }

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

    //En corrección
    public static Evaluacion recepcionDatos(String emailJurado, int idInscripcion, int criterioEvaluar, double notaEvaluacion) {
        //
        // Diseñada para el case "Evaluacion"
        //
        int id = 0;
        MiembroJurado miembroJuradoFiltrado = null;
        Inscripcion inscripcionFiltrada = null;
        Criterio criterioFiltrado = null;

        for (MiembroJurado mj : miembrosJurado) {
            if (Objects.equals(emailJurado, mj.getEmail())) {
                miembroJuradoFiltrado = mj;
            }
        }
        for (Inscripcion i : inscripciones) {
            if (idInscripcion == i.getId()) {
                inscripcionFiltrada = i;
            }
        }
        for (Criterio c : criterios) {
            if (criterioEvaluar == c.getId()) {
                criterioFiltrado = c;
            }
        }

        return new Evaluacion(id, idInscripcion, inscripcionFiltrada, miembroJuradoFiltrado.getId(), miembroJuradoFiltrado, notaEvaluacion, criterioEvaluar, criterioFiltrado);

    }
}
