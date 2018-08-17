/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic;

import java.util.Random;
import ttp.utils.Order_Methods;
import unalcol.search.space.Space;

/**
 *
 * @author Ricardo Tique
 */
public class TTP_Space implements Space<TTP_Individual> {

    protected int numberCitys;
    protected int numberItems;
    protected int[][] avaibleProdCity;
    protected double[] productsWeight;
    protected double knapsackCapacity;

    public TTP_Space(int numberCitys, int numberProducts, double knapsackCapacity,
            int[][] avaibleProdCity, double[] productsWeight) {
        this.numberCitys = numberCitys;
        this.numberItems = numberProducts;
        this.knapsackCapacity = knapsackCapacity;
        this.avaibleProdCity = avaibleProdCity;
        this.productsWeight = productsWeight;
    }

    @Override
    public boolean feasible(TTP_Individual genome) {
        return productAvailabity(genome) && productWeight(genome);
    }

    @Override
    public double feasibility(TTP_Individual x) {
        return 0;
    }

    @Override
    public TTP_Individual repair(TTP_Individual genome) {
//        System.out.println("repair");
//        for (int i = 0; i < genome.size(); i++) {
//            System.out.print(genome.getCity(i) + " ");
//        }
//        System.out.println("");
//        for (int i = 0; i < genome.size(); i++) {
//            System.out.print(genome.getProduct(i) + " ");
//        }
//        System.out.println("");
        if (!productAvailabity(genome)) {            
            this.repairAvailability(genome);
        }
        if (!productWeight(genome)) {
            this.repairWeight(genome);
        }
//        for (int i = 0; i < genome.size(); i++) {
//            System.out.print(genome.getProduct(i) + " ");
//        }
//        System.out.println("");
        return genome;
    }

    @Override
    public TTP_Individual pick() {
        return new TTP_Individual(numberCitys, numberItems, avaibleProdCity);
    }

    private boolean productWeight(TTP_Individual genome) {
        double weight = 0;
        for (int i = 0; i < genome.size(); i++) {
            if (genome.getProduct(i) != 0) {
                weight += productsWeight[genome.getProduct(i) - 1];
            }
        }
        return weight <= knapsackCapacity;
    }

    private boolean productAvailabity(TTP_Individual genome) {
        for (int i = 0; i < genome.size(); i++) {
            if (genome.getProduct(i) != 0) {
                if (avaibleProdCity[genome.getProduct(i) - 1][genome.getCity(i) - 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void repairAvailability(TTP_Individual genome) {
        for (int i = 0; i < genome.size(); i++) {
            if (genome.getProduct(i) != 0) {
                genome.setProduct(i, av_product(genome.getCity(i), genome.getProduct(i)));
            }
        }
    }

    private int av_product(int city, int item) {

        int aux = 0;
        for (int i = 0; i < numberItems; i++) {
            if (this.avaibleProdCity[i][city - 1] != 0) {
                aux += 1;
            }
        }
        if (aux != 0) {
            Random rd = new Random();
            aux = item;
            do {
                aux = rd.nextInt(numberItems) + 1;
            } while (this.avaibleProdCity[aux - 1][city - 1] == 0);
            return aux;
        } else {
            return 0;
        }

    }

    private void repairWeight(TTP_Individual genome) {
        double[][] weight_aux = new double[genome.size()][2];

        do {
            for (int i = 0; i < genome.size(); i++) {
                weight_aux[i][0] = i;
                weight_aux[i][1] = (genome.getProduct(i) == 0) ? 0 : this.productsWeight[genome.getProduct(i) - 1];
            }
            Order_Methods.sort(weight_aux, 0, genome.size() - 1);
            genome.setProduct((int) weight_aux[0][0], minor_product(genome.getCity((int) weight_aux[0][0]), weight_aux[0][1]));
        } while (!this.productWeight(genome));
    }

    private int minor_product(int city, double prod_weight) {
        int aux = 0;
        for (int i = 0; i < numberItems; i++) {
            if (this.avaibleProdCity[i][city - 1] != 0) {
                aux += 1;
            }
        }
        if (aux > 1) {
            for (int i = 0; i < numberItems; i++) {
                if (this.avaibleProdCity[i][city - 1] != 0 && productsWeight[i] < prod_weight) {
                    return i + 1;
                }
            }
        }
        return 0;

    }
}
