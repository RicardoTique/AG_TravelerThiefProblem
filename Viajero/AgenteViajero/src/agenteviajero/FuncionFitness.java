/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero;

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
        int [] aux = new int[genotipo.length+2];
        aux[0]=genotipo[genotipo.length-1];
        aux[aux.length-1]=genotipo[0];
        
        for (int i = 0; i < aux.length; i++) {
            
        }
        
        return 0;
    }
    
    
    
    
    
    
    
    
    
}
