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
public class Scramble_Mutation extends MicroService<TTP_Individual> implements Variation_1_1<TTP_Individual> {

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
            for (int i = a; i < b + 1; i++) {
                int index = (int) (Math.random() * (b - a + 1) + a);
                int city = genome.getCity(i);
                int prod = genome.getProduct(i);
                genome.setCity(i, genome.getCity(index));
                genome.setProduct(i, genome.getProduct(index));
                genome.setCity(index, city);
                genome.setProduct(index, prod);

            }
            System.out.println("Sc");
            return genome;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Mutation]" + e.getMessage());
        }
        return null;
    }

}
