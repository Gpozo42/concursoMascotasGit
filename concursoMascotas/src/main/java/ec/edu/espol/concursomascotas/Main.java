/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.concursomascotas;
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
                break;
            default: //Evaliacion
                break;
        }
        
    }
    
}
