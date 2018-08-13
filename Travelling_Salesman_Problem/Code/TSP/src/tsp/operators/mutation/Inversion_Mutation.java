/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.operators.mutation;

import tsp.genetic.TSP_Individual;
import tsp.utils.Utils;
import unalcol.search.variation.Variation_1_1;

/**
 *
 * @author Ricardo Tique
 */
public class Inversion_Mutation implements Variation_1_1<TSP_Individual> {

    @Override
    public TSP_Individual apply(TSP_Individual gen) {
        try {

            TSP_Individual genome = new TSP_Individual(gen);
            int[] cutPoint = Utils.generateTwoRandomNumbers(gen.size());
            int k = cutPoint[1];

            for (int i = cutPoint[0]; i <= cutPoint[1]; i++) {
                genome.set(i, gen.get(k));                
                k--;
            }
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
