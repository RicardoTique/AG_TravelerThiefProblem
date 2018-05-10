/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic.operators.mutation;

import ttp.genetic.TTP_Individual;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class Inversion_Mutation extends MicroService<TTP_Individual> implements Variation_1_1<TTP_Individual> {

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
            if (a > b) {
                int aux = a;
                a = b;
                b = aux;
            }
            int k = b;
            for (int i = a; i <= b; i++) {
               genome.setCity(i, gen.getCity(k));
               genome.setProduct(i, gen.getProduct(k));
               k--;
            }
            System.out.println("I");
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
