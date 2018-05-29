/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic;

import java.util.Random;
import unalcol.search.space.Space;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class TTP_Space extends MicroService<TTP_Individual> implements Space<TTP_Individual> {

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
    public boolean feasible(TTP_Individual x) {
        return productAvailabity(x) && productWeight(x);
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
//        if (!productAvailabity(genome)) {
//            this.repairAvailability(genome);            
//        }
//        if (!productWeight(genome)) {
//            System.out.println("p");
//            this.repairAvailability(genome);
//        }
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

    private boolean productWeight(TTP_Individual x) {
        double weight = 0;
        for (int i = 0; i < x.size(); i++) {
            if (x.getProduct(i) != 0) {
                weight += productsWeight[x.getProduct(i) - 1];
            }
        }
        return weight <= knapsackCapacity;
    }

    private boolean productAvailabity(TTP_Individual x) {
        for (int i = 0; i < x.size(); i++) {
            if (x.getProduct(i) != 0) {
                if (avaibleProdCity[x.getProduct(i) - 1][x.getCity(i) - 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void repairAvailability(TTP_Individual genome) {
        double [][] wegiht_aux = new double[genome.size()][2];
        
        for (int i = 0; i < genome.size(); i++) {
            wegiht_aux[i][0] = i;
            wegiht_aux[i][1] =(genome.getProduct(i)==0)?0:this.productsWeight[genome.getProduct(i)-1];
        }
    }

    private int av_product(int city, int item) {
        if (this.avaibleProdCity[item - 1][city - 1] == 0) {
            Random rd = new Random();
            int aux = 0;
            for (int i = 0; i < numberItems; i++) {
                if (this.avaibleProdCity[i][city - 1] != 0) {
                    aux += 1;
                }
            }
            if (aux != 0) {
                aux = item;
                do {
                    aux = rd.nextInt(numberItems) + 1;
                } while (this.avaibleProdCity[aux - 1][city - 1] == 0);
                return aux;
            } else {
                return 0;
            }
        }
        return item;
    }

    private void repairWight(TTP_Individual genome) {

    }
}
