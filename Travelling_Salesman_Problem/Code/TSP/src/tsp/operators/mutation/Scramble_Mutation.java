/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.operators.mutation;

import tsp.genetic.TSP_Individual;
import tsp.utils.Utils;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class Scramble_Mutation extends MicroService<TSP_Individual> implements Variation_1_1<TSP_Individual> {

    public TSP_Individual apply(TSP_Individual gen) {
        try {
            
            TSP_Individual genome = new TSP_Individual(gen);
            int[] cutPoint = Utils.generateTwoRandomNumbers(gen.size());
            
            for (int i = cutPoint[0]; i < cutPoint[1] + 1; i++) {
                int index = (int) (Math.random() * (cutPoint[1] - cutPoint[0] + 1) + cutPoint[0]);
                int city = genome.get(i);                
                genome.set(i, genome.get(index));
                genome.set(index, city);
            }
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
