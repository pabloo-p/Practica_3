/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import javax.swing.table.AbstractTableModel;

public class ModeloMatriz extends AbstractTableModel {

    private Object[][] matriz = new Object[0][0];
    
     public void actualizarDesdeEnteros(int[][] datos) {
        matriz = new Object[datos.length][datos[0].length];

        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                matriz[i][j] = (datos[i][j] == -1) ? "*" : datos[i][j];
            }
        }

        fireTableStructureChanged(); // ✔ Notifica a la vista para redibujar
    }

    public void setMatriz(Object[][] nuevaMatriz) {
        this.matriz = nuevaMatriz;
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return matriz.length; 
    }

    @Override
    public int getColumnCount() {
        return matriz.length > 0 ? matriz[0].length : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matriz[rowIndex][columnIndex];
    }
}
