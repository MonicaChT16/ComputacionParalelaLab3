/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computacionparalelolab3;

import java.util.Arrays;
import java.util.Scanner;

public class ComputacionParaleloLab3 {

    public static void main(String[] args) {
        
        Scanner leer = new Scanner(System.in);
        
        System.out.println("Ingrese la cantidad de elementos del arreglo:");
        int n = leer.nextInt();
        int[] data = new int[n];

        // Llenamos el arreglo con números aleatorios
        for (int i = 0; i < n; i++) {
            data[i] = (int)(Math.random()*100); 
        }
        

        // Ordenamiento secuencial
        System.out.println("Antes de ordenar (secuencial): " + Arrays.toString(data));
        int[] sequentialResult = data.clone();  
        Arrays.sort(sequentialResult);  
        System.out.println("Después de ordenar (secuencial): " + Arrays.toString(sequentialResult));

        // Ordenamiento paralelo utilizando ForkJoinPool
        System.out.println("\nOrdenamiento paralelo:");
        int[] parallelResult = data.clone();  // Copiar el array original
        OrdenamientoParalelo OrdenamientoParalelo = new OrdenamientoParalelo();
        OrdenamientoParalelo.sortArray(parallelResult);
        System.out.println("Después de ordenar (paralelo): " + Arrays.toString(parallelResult));
    }
}
