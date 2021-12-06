/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.concursomascotas;

import ec.edu.espol.model.Menu;
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
        sc.useDelimiter("\n");
        String opcion;

        do {
            System.out.println("Opciones: ");
            System.out.println("1. Dueño\n2. Mascota\n3. Concurso\n4. Premio\n5. Criterio\n6. Incripción\n7. MiembroJurado\n8. Evaluación\n");
            opcion = sc.next();
            
            if (opcion.matches("[0-9]+")) if (Integer.parseInt(opcion) > 0 && Integer.parseInt(opcion) < 9) Menu.menuInicial(Integer.parseInt(opcion));

        } while (!(opcion.toLowerCase().equals("salir")));
        
        sc. close();
        
    }    
}
