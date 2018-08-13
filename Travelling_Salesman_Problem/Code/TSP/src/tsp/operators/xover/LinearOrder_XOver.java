/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.operators.xover;

import tsp.genetic.TSP_Individual;
import tsp.utils.Utils;
import unalcol.search.variation.Variation_2_2;

/**
 *
 * @author Ricardo Tique
 */
public class LinearOrder_XOver implements Variation_2_2<TSP_Individual> {

    @Override
    public TSP_Individual[] apply(TSP_Individual child1, TSP_Individual child2) {
        try {
            TSP_Individual[] offspring = new TSP_Individual[2];
            int[] cutPoint = Utils.generateTwoRandomNumbers(child1.size());
            int subSize = (cutPoint[1] - cutPoint[0]) + 1;
            offspring[0] = new TSP_Individual(child1);
            offspring[1] = new TSP_Individual(child2);
            int[] subArray = new int[subSize];            
            // Genera el primer hijo.
            for (int j = 0; j < subSize; j++) {
                subArray[j] = child2.get(j + cutPoint[0]);
            }
            offspring[0].removeNumbers(subArray);
            offspring[0].slideLeft(cutPoint[0]);
            offspring[0].slideRight(cutPoint[1]);
            offspring[0].insertSubArray(cutPoint[0], cutPoint[1], subArray);//            
            //Genera el segundo hijo.
            for (int j = 0; j < subSize; j++) {
                subArray[j] = child1.get(j + cutPoint[0]);
            }
            offspring[1].removeNumbers(subArray);
            offspring[1].slideLeft(cutPoint[0]);
            offspring[1].slideRight(cutPoint[1]);
            offspring[1].insertSubArray(cutPoint[0], cutPoint[1], subArray);
            return offspring;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[XOver]" + e.getMessage());
        }
        return null;
    }

}
