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
            int[] subArray = new int[subSize];            
            // Genera el primer hijo.
            for (int j = 0; j < subSize; j++) {
                subArray[j] = child2.getCity(j + cutPoint[0]);
            }
            offspring[0].removeNumbers(subArray);
            offspring[0].slideLeft(cutPoint[0]);
            offspring[0].slideRight(cutPoint[1]);
            offspring[0].insertSubGenome(cutPoint[0], cutPoint[1], subArray);//            
            //Genera el segundo hijo.
            for (int j = 0; j < subSize; j++) {
                subArray[j] = child1.getCity(j + cutPoint[0]);
            }
            offspring[1].removeNumbers(subArray);
            offspring[1].slideLeft(cutPoint[0]);
            offspring[1].slideRight(cutPoint[1]);
            offspring[1].insertSubGenome(cutPoint[0], cutPoint[1], subArray);
            return offspring;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[XOver]" + e.getMessage());
        }
        return null;
    }

}
