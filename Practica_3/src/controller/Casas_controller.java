package controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

 

public class Casas_controller {

    private String carpeta = "data";
   
    private String nombreArchivo = "Casas.dat";

    public void guardarEnArchivo(String texto, String nombreArchivo) throws IOException {
        FileWriter file = new FileWriter(carpeta + File.separatorChar + nombreArchivo, true);
        file.write(texto);
        file.close();
    }

    public boolean guardarCasa(float largo, float ancho, float altura, int nroPisos) {
        String datos = generarNumeracion() + "\t" + largo + "\t" + ancho + "\t" + altura + "\t" + nroPisos + "\n";
        try {
            guardarEnArchivo(datos, nombreArchivo);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String[][] listarCasas(String nombreArchivo) throws IOException {
        int fila = countRegister(nombreArchivo);
        String[][] casas = new String[fila][5];
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(carpeta + File.separatorChar + nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null && count < fila) {
                String[] partes = linea.split("\t");
                if (partes.length == 5) {
                    casas[count] = partes;
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer archivo:   " + e.getMessage());
        }
        return casas;
    }

    public String[][] listar() {
        try {
            return listarCasas(nombreArchivo);
        } catch (Exception e) {
            System.out.println("Error al listar casas:  " + e);
            return null;
        }
    }

    private int countRegister(String nombreArchivo) throws IOException {
        FileReader file = new FileReader(carpeta + File.separatorChar + nombreArchivo);
        BufferedReader br = new BufferedReader(file);
        int lines = (int) br.lines().count();
        file.close();
        br.close();
        return lines;
    }

    public int generarNumeracion() {
        String[][] listadoCasas = listar();
        if (listadoCasas != null) {
            return listadoCasas.length + 1;
        } else {
            return 1;
        }
    }

    public String verificarHomonimas(String[][] casas) {
        String resultado = "";
        boolean homonima = false;
        if (casas == null || casas.length == 0) {
            resultado = "No hay casas registradas o hubo un error al listarlas.";
            return resultado;
        }
        for (int i = 0; i < casas.length; i++) {
            for (int j = i + 1; j < casas.length; j++) {
                if (casas[i][1].equals(casas[j][1])//largo
                        && casas[i][2].equals(casas[j][2])//ancho
                        && casas[i][3].equals(casas[j][3])//altura
                        && casas[i][4].equals(casas[j][4])) {//pisos
                    resultado = resultado + "Casa " + casas[i][0] + " y Casa " + casas[j][0] + " son homónimas.\n";
                    homonima = true;
                }
            }
        }
        if (!homonima) {
            resultado = "No existen casas homónimas.";
        }
        return resultado;
    }
}
