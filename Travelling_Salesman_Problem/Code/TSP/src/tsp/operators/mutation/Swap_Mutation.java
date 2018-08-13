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
public class Swap_Mutation implements Variation_1_1<TSP_Individual> {

    @Override
    public TSP_Individual apply(TSP_Individual gen) {

        try {
            TSP_Individual genome = new TSP_Individual(gen);
            int[] cutPoint = Utils.generateTwoRandomNumbers(gen.size());
            //Intercambia la ciudad a y b
            int aux = genome.get(cutPoint[0]);
            genome.set(cutPoint[0], genome.get(cutPoint[1]));
            genome.set(cutPoint[1], aux);

            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
