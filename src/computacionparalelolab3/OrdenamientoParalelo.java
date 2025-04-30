/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package computacionparalelolab3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class OrdenamientoParalelo {

    public void sortArray(int[] arr) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelSortTask task = new ParallelSortTask(arr, 0, arr.length - 1);
        forkJoinPool.invoke(task);
    }

    // Clase que extiende RecursiveTask para el ordenamiento paralelo
    static class ParallelSortTask extends RecursiveTask<Void> {
        int[] arr;
        int left, right;

        public ParallelSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected Void compute() {
            if (right - left <= 1) {
                return null; // Caso base: si el rango es de 1 o 0 elementos, no se hace nada
            }

            int pivotIndex = partition(arr, left, right);  // Dividir el arreglo en dos
            ParallelSortTask leftTask = new ParallelSortTask(arr, left, pivotIndex);
            ParallelSortTask rightTask = new ParallelSortTask(arr, pivotIndex + 1, right);

            // Ejecutar las dos tareas de manera paralela
            leftTask.fork();
            rightTask.fork();

            // Esperar que ambas tareas terminen
            leftTask.join();
            rightTask.join();
            
            return null;
        }

        private int partition(int[] arr, int left, int right) {
            int pivot = arr[left];
            int i = left + 1;
            int j = right;

            while (true) {
                while (i <= right && arr[i] <= pivot) i++;
                while (j > left && arr[j] > pivot) j--;

                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            swap(arr, left, j);
            return j;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
