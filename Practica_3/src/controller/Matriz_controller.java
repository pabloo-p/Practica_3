package controller;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class Matriz_controller {

    private int[][] datos;
    private int filas;
    private int columnas;

    public Matriz_controller(int filas, int columnas) {
        if (filas <= 0 || columnas <= 0) {
        }
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new int[filas][columnas];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int[][] getDatos() {
        return datos;
    }

    public void llenarMatrizAleatorio() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = (int) (Math.random() * 100) + 1; // Números entre 1 y 100
            }
        }
    }

    public boolean eliminarNumero(int numeroAEliminar, int valorReemplazo) {
        boolean encontrado = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (datos[i][j] == numeroAEliminar) {
                    datos[i][j] = valorReemplazo;
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    public boolean eliminarPrimos(int valorReemplazo) {
        boolean encontradoPrimo = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (esPrimo(datos[i][j])) {
                    datos[i][j] = valorReemplazo;
                    encontradoPrimo = true;
                }
            }
        }
        return encontradoPrimo;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero <= 3) {
            return true;
        }
        if (numero % 2 == 0 || numero % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= numero; i = i + 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) {
                return false;
            }
        }
        return true;

    }
}
