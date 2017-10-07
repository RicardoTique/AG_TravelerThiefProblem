/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero.genetico;

/**
 *
 * @author Ricardo Tique
 */
public class FuncionFitness {
    
    private int [][] coordenadas;

    public FuncionFitness(int[][] coordenadas) {        
        this.coordenadas = coordenadas;
    }
    public double calcularFitnes(int [] genotipo){
        int [] aux = new int[genotipo.length+1];        
        aux[aux.length-1]=genotipo[0];
        for (int i = 0; i < genotipo.length; i++) {
            aux[i+1] = genotipo[i];
        }
        double respuesta = 0;
        for (int i = 0; i < aux.length-1; i++) {
            double x = coordenadas[aux[i+1]][0]-coordenadas[aux[i]][0];
            double y = coordenadas[aux[i+1]][1]-coordenadas[aux[i]][1];
            respuesta+=Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
        return respuesta;
    }
    
    
    
    
    
    
    
    
    
}
