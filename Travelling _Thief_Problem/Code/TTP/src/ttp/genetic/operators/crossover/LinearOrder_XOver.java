/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic.operators.crossover;

import ttp.genetic.TTP_Individual;
import ttp.utils.Utils;
import unalcol.search.variation.Variation_2_2;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class LinearOrder_XOver extends MicroService<TTP_Individual> implements Variation_2_2<TTP_Individual> {

    @Override
    public TTP_Individual[] apply(TTP_Individual child1, TTP_Individual child2) {
        try {
            TTP_Individual[] offspring = new TTP_Individual[2];
            int[] cutPoint = Utils.generateTwoRandomNumbers(child1.size());
            int subSize = (cutPoint[1] - cutPoint[0]) + 1;
            offspring[0] = new TTP_Individual(child1);
            offspring[1] = new TTP_Individual(child2);

            this.construct(child2, offspring[0], subSize, cutPoint[0], cutPoint[1]);
            this.construct(child1, offspring[1], subSize, cutPoint[0], cutPoint[1]);
            
            return offspring;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[XOver]" + e.getMessage());
        }
        return null;
    }

    private void construct(TTP_Individual child, TTP_Individual offspring, int subSize, int cutPoint_a, int cutPoint_b) {
        int[] subArray = new int[subSize];
        // Genera el primer hijo.
        for (int j = 0; j < subSize; j++) {
            subArray[j] = child.getCity(j + cutPoint_a);
        }
        offspring.removeCity(subArray);
        offspring.slideLeft(cutPoint_a);
        offspring.slideRight(cutPoint_b);
        offspring.insertSubGenome(cutPoint_a, cutPoint_b, subArray);// 
    }
}
