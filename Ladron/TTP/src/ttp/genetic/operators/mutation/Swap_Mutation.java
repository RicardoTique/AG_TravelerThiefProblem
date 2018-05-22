/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic.operators.mutation;

import ttp.genetic.TTP_Individual;
import ttp.utils.Utils;
import unalcol.search.variation.ParameterizedObject;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class Swap_Mutation extends MicroService<TTP_Individual> implements Variation_1_1<TTP_Individual> {

    @Override
    public TTP_Individual apply(TTP_Individual gen) {

        try {
            TTP_Individual genome = new TTP_Individual(gen);
            int[] cutPoint = Utils.generateTwoRandomNumbers(gen.size());            
            //Intercambia la ciudad a y b
            int aux = genome.getCity(cutPoint[0]);
            genome.setCity(cutPoint[0], genome.getCity(cutPoint[1]));
            genome.setCity(cutPoint[1], aux);
            //Intercambia el producto a y b
            aux = genome.getProduct(cutPoint[0]);
            genome.setProduct(cutPoint[0], genome.getProduct(cutPoint[1]));
            genome.setProduct(cutPoint[1], aux);
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
