/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic.operators.mutation;

import ttp.genetic.TTP_Individual;
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
            int size = gen.size();
            int a = (int) (Math.random() * (size));
            int b = a;
            
            do {
                b = (int) (Math.random() * (size));
            } while (a == b);

            //Intercambia la ciudad a y b
            int aux = genome.getCity(a);
            genome.setCity(a, genome.getCity(b));
            genome.setCity(b, aux);
            //Intercambia el producto a y b
            aux = genome.getProduct(a);
            genome.setProduct(a, genome.getProduct(b));
            genome.setProduct(b, aux);
            System.out.println("Sw");
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
