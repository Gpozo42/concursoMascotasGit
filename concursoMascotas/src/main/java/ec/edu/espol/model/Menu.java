/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author gerar
 */
public class Menu {
    private Menu(){}
    
    public static void menuInicial(int opcion, Scanner sc, ArrayList<MiembroJurado> miembrosJurado, ArrayList<Inscripcion> inscripciones, ArrayList<Criterio> criterios, ArrayList<Mascota> mascotas, ArrayList<Concurso> concursos) {
        
        // Acciones del menú
        switch (opcion) { //Recibimos un int de la validacion anterior, un switch case es rapido y efectivo
            case 1: //Dueño
                break;
            case 2: //Mascota
                break;
            case 3: //Concurso
                Scanner sc3=new Scanner(System.in);
                (Concurso.nextConcurso(sc3)).saveFile("concursos.txt");
                break;
            case 4: //Premio
                System.out.println("Ingrese la cantidad de premios para el concurso:");
                Scanner sc1 = new Scanner(System.in);
                int cantidad = sc1.nextInt();
                sc1.nextLine();
                int sumador = 0;
                String[] descripciones = new String[cantidad];
                int[] idPremio = new int[cantidad];
                while (sumador < cantidad) {
                    System.out.println("Ingrese la descripcion del premio " + (sumador + 1) + ":");
                    String descrip = sc1.nextLine();
                    descripciones[sumador] = descrip;
                    idPremio[sumador] = sumador + 1;
                }
                System.out.println("Ingrese el nombre del concurso: ");
                Scanner sc2 = new Scanner(System.in);
                String nombreConcurso = sc2.nextLine();
                for(int i=0;i<cantidad;i++){
                    Premio p=new Premio(idPremio[i], i+1,descripciones[i],Concurso.anexarIdPremio(nombreConcurso),Concurso.anexarNombrePremio(nombreConcurso));
                    p.saveFile("premios.txt");
                }
                break;

            case 5: //Criterio
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

                //recepcionDatos(emailJurado, idInscripcion, criterioEvaluar, notaEvaluacion);
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
    
    /*
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
    */
}
