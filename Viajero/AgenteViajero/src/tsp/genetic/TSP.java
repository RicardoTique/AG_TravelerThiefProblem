/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.genetic;

import unalcol.optimization.OptimizationFunction;

/**
 *
 * @author Ricardo Tique
 */
public class TSP extends OptimizationFunction<TSP_Individual>{
    private double [][] coordinates;
   
    public TSP(double[][] coordinates){
        this.coordinates = coordinates;
    }
    @Override
    public Double compute(TSP_Individual genome) {
        int [] aux = new int[ genome.size() + 1 ];
        
        aux[aux.length - 1] = genome.get(0);
        
        for (int i = 0; i < genome.size(); i++) 
        {
            aux[i] = genome.get(i); //TODO review this
        }
        
        double response = 0;
     
        for (int i = 0; i < aux.length-1; i++) 
        {

            double x = coordinates[ aux[i+1]-1 ][0] - coordinates[ aux[i] -1][0];
            double y = coordinates[ aux[i+1] -1][1] - coordinates[ aux[i] -1][1];
            response += Math.sqrt( (x * x) + (y * y) );
        }        
        return response;
    }
    
}
